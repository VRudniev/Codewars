package kyu5.first_variation_caesar_cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaesarCipher {

	private static List<Character> alphabet = IntStream.rangeClosed('a', 'z')
			.mapToObj(c -> (char) c).collect(Collectors.toList());

	public static void main(String[] args) {
		for (String s : movingShift("I should have known that you would have a perfect answer for me!!!",1))
		{
			System.out.println(s);
		}
	}

	public static List<String> movingShift(String s, int shift) {
		List<String> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (String word : s.split(" "))
		{
			char chars[] = word.toCharArray();
			for (int i = 0; i < word.length(); i++)
			{
				boolean isUpper = Character.isUpperCase(chars[i]);
				if(isUpper)
					chars[i] = Character.toLowerCase(chars[i]);
				if(!alphabet.contains(chars[i]))
					sb.append(chars[i]);
				else
				{
					char c = alphabet.get((alphabet.indexOf(chars[i]) + shift) % (alphabet.size()));
					if(isUpper)
						c = Character.toUpperCase(c);
					sb.append(c);
					shift++;
				}
			}
			sb.append(" ");
			shift++;
		}
		String codedString = sb.toString();
		int messageSize = (sb.length() % 5 == 0) ? sb.length() / 5 : sb.length() / 5 + 1;
		for (int i = 0; i < 4; i++)
		{
			result.add(codedString.substring(0, messageSize));
			codedString = codedString.substring(messageSize , codedString.length());
		}
		result.add(codedString);
		return result;
	}

	public static String demovingShift(List<String> s, int shift) {
		return null;
	}
}
