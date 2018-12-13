package kyu5.first_variation_caesar_cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaesarCipher {

	private static List<Character> alphabet = IntStream.rangeClosed('a', 'z')
			.mapToObj(c -> (char) c).collect(Collectors.toList());

	public static void main(String[] args) {
		System.out.println(movingShift("I should have known that you would have a perfect answer for me!!!", 1));
		System.out.println(demovingShift(movingShift("I should have known that you would have a perfect answer for me!!!", 1), 1));
	}

	public static List<String> movingShift(String s, int shift) {
		List<String> result = new ArrayList<>();
		int messageSize = (s.length() % 5 == 0) ? s.length() / 5 : s.length() / 5 + 1;
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			boolean isUpper = Character.isUpperCase(c);
			int indexOf = alphabet.indexOf(Character.toLowerCase(c));
			if (indexOf >= 0) {
				c = alphabet.get((indexOf + shift) % (alphabet.size()));
				if (isUpper)
					c = Character.toUpperCase(c);
			}
			sb.append(c);
			shift++;
			if (sb.length() >= messageSize) {
				result.add(sb.toString().substring(0, messageSize));
				sb.delete(0, messageSize);
			}
		}
		if (result.size() < 5)
			result.add(sb.toString());
		return result;
	}

	public static String demovingShift(List<String> s, int shift) {
		StringBuilder sb = new StringBuilder();
		String codedMessage = s.stream().collect(Collectors.joining());
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
			shift++;
		}
		return sb.toString();
	}
}
