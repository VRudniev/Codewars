package kyu5.prime_decomposition;

/*
 * My solution for Primes in numbers kata:
 * https://www.codewars.com/kata/54d512e62a5e54c96200019e
 */

public class PrimeDecomp {
	private static String result;
	private static int counter = 0;


	public static void main(String[] args) {
		System.out.println(factors(7919));
	}

	public static String factors(int n) {
		result = "";
		if (n % 2 == 0) {
			while (n % 2 == 0) {
				n /= 2;
				counter++;
			}
			append(2, counter);
		}
		for (int i = 3; i <= Math.sqrt(n); i+= 2) {
			if (n % i == 0) {
				while (n % i == 0) {
					n /= i;
					counter++;
				}
				append(i, counter);
			}
		}
		if (n > 2)
			append(n, 1);
		return result;
	}

	private static void append(int number, int count) {
		if(count == 1)
			result += "(" + number + ")";
		else
			result += "(" + number + "**" + count + ")";
		counter = 0;
	}
}
