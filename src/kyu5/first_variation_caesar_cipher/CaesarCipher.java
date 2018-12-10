package kyu5.first_variation_caesar_cipher;

import java.util.ArrayList;
import java.util.Arrays;
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
		int step = shift;
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
					char c = alphabet.get(getNewPositionInAlphabet(getPositionInAlphabet(chars[i]) + step));
					if(isUpper)
						c = Character.toUpperCase(c);
					sb.append(c);
					System.out.println("Position: " + getPositionInAlphabet(chars[i]));
					System.out.println("Shift " + step);
					step++;
				}
			}
			sb.append(" ");
			step++;
		}
		return Arrays.asList(sb.toString().split(" "));
	}

	private static int getPositionInAlphabet(char c) {
		return alphabet.indexOf(c);
	}

	private static int getNewPositionInAlphabet(int position)
	{
		return position % (alphabet.size()) ;
	}

	public static String demovingShift(List<String> s, int shift) {
		return null;
	}
}
