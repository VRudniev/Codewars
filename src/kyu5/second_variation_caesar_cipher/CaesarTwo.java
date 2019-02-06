/**
 * Description:
 * In this country soldiers are poor but they need a certain level of secrecy for their communications so, though they do not know Caesar cypher, they reinvent it in the following way.
 *
 * They use ASCII, without really knowing it, but code only letters a-z and A-Z. Other caracters are kept such as.
 *
 * They change the "rotate" each new message. This "rotate" is a prefix for their message once the message is coded. The prefix is built of 2 letters, the second one being shifted from the first one by the "rotate", the first one is the first letter, after being downcased, of the uncoded message.
 *
 * For example if the "rotate" is 2, if the first letter of the uncoded message is 'J' the prefix should be 'jl'.
 *
 * To lessen risk they cut the coded message and the prefix in five pieces since they have only five runners and each runner has only one piece.
 *
 * If possible the message will be evenly split between the five runners; if not possible, parts 1, 2, 3, 4 will be longer and part 5 shorter. The fifth part can have length equal to the other ones or shorter. If there are many options of how to split, choose the option where the fifth part has the longest length, provided that the previous conditions are fulfilled. If the last part is the empty string don't put this empty string in the resulting array.
 *
 * For example, if the coded message has a length of 17 the five parts will have lengths of 4, 4, 4, 4, 1. The parts 1, 2, 3, 4 are evenly split and the last part of length 1 is shorter. If the length is 16 the parts will be of lengths 4, 4, 4, 4, 0. Parts 1, 2, 3, 4 are evenly split and the fifth runner will stay at home since his part is the empty string and is not kept.
 *
 * Could you ease them in programming their coding?
 *
 * Example with shift = 1 :
 *
 * message : "I should have known that you would have a perfect answer for me!!!"
 *
 * code : => ["ijJ tipvme ibw", "f lopxo uibu z", "pv xpvme ibwf ", "b qfsgfdu botx", "fs gps nf!!!"]
 *
 * By the way, maybe could you give them a hand to decode?
 */
package kyu5.second_variation_caesar_cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaesarTwo
{

	private static List<Character> alphabet = IntStream.rangeClosed('a', 'z')
			.mapToObj(c -> (char) c).collect(Collectors.toList());

	public static void main(String[] args) {
		System.out.println(encodeStr("I should have known that you would have a perfect answer for me!!!", 1));
		System.out.println(decode(encodeStr("I should have known that you would have a perfect answer for me!!!", 1)));

		System.out.println(encodeStr("O CAPTAIN! my Captain! our fearful trip is done;", 1));
		System.out.println(decode(encodeStr("O CAPTAIN! my Captain! our fearful trip is done;", 1)));
	}

	public static List<String> encodeStr(String s, int shift) {
		List<String> result = new ArrayList<>();
		int inputMessageLength = s.length() + 2;
		int messageSize = (inputMessageLength % 5 == 0) ? inputMessageLength / 5 : inputMessageLength / 5 + 1;
		StringBuilder sb = new StringBuilder();
		char firstChar = Character.toLowerCase(s.charAt(0));
		sb.append(firstChar);
		sb.append(getEncodedChar(firstChar, shift));
		for (char c : s.toCharArray()) {
			sb.append(getEncodedChar(c, shift));
			if (sb.length() >= messageSize) {
				result.add(sb.toString().substring(0, messageSize));
				sb.delete(0, messageSize);
			}
		}
		if (result.size() < 5 && sb.length() != 0)
			result.add(sb.toString());
		return result;
	}

	public static String decode(List<String> s) {
		StringBuilder sb = new StringBuilder();
		String codedMessage = s.stream().collect(Collectors.joining());
		int shift = alphabet.indexOf(codedMessage.charAt(1)) - alphabet.indexOf(codedMessage.charAt(0));
		codedMessage = codedMessage.substring(2);
		int size = codedMessage.length();
		for (char c : codedMessage.toCharArray()) {
			boolean isUpper = Character.isUpperCase(c);
			int indexOf = alphabet.indexOf(Character.toLowerCase(c));
			if (indexOf >= 0) {
				c = alphabet.get(((indexOf - shift) + (alphabet.size() * size)) % alphabet.size());
				if (isUpper)
					c = Character.toUpperCase(c);
			}
			sb.append(c);
		}
		return sb.toString();
	}

	private static char getEncodedChar(char c, int shift) {
		boolean isUpper = Character.isUpperCase(c);
		int indexOf = alphabet.indexOf(Character.toLowerCase(c));
		if (indexOf >= 0) {
			c = alphabet.get((indexOf + shift) % (alphabet.size()));
			if (isUpper)
				c = Character.toUpperCase(c);
		}
		return c;
	}
}
