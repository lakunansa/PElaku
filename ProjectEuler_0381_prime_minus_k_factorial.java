/**
 * 
 */

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * 
 */
public class ProjectEuler_0381_prime_minus_k_factorial {

	/**
	 * 
	 */
	public ProjectEuler_0381_prime_minus_k_factorial() {
		// TODO Auto-generated constructor stub
	}
	
	// Returns x^-1 mod m, where the result is in the range [0, m).
	// Note that (x * x^-1) mod m = (x^-1 * x) mod m = 1.
	public static int reciprocalMod(int x, int m) {
		if (!(0 <= x && x < m))
			throw new IllegalArgumentException();
		
		// Based on a simplification of the extended Euclidean algorithm
		int y = x;
		x = m;
		int a = 0;
		int b = 1;
		while (y != 0) {
			int z = x % y;
			int c = a - x / y * b;
			x = y;
			y = z;
			a = b;
			b = c;
		}
		if (x == 1)
			return a >= 0 ? a : a + m;
		else
			throw new IllegalArgumentException("Reciprocal does not exist");
	}
	
	public static String run() {
		boolean[] isPrime = listPrimality(100_000_000);
		long sum = 0;
		for (int i = 5; i < isPrime.length; i++) {
			if (isPrime[i])
				sum += s(i);
		}
		return Long.toString(sum);
	}
	
	public static boolean[] listPrimality(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Negative array size");
		boolean[] result = new boolean[n + 1];
		if (n >= 2)
			result[2] = true;
		for (int i = 3; i <= n; i += 2)
			result[i] = true;
		// Sieve of Eratosthenes
		for (int i = 3, end = (int)Math.sqrt(n); i <= end; i += 2) {
			if (result[i]) {
				// Note: i * i does not overflow
				for (int j = i * i, inc = i * 2, bound = Integer.MAX_VALUE - inc; j <= n; j += inc) {
					result[j] = false;
					if (j > bound)
						break;
				}
			}
		}
		return result;
	}
	
	
	/* 
	 * Note about the mathematical simplification:
	 * (p-5)! + (p-4)! + (p-3)! + (p-2)! + (p-1)!
	 * = (p-5)! * (1 + (p-4) + (p-4)(p-3) + (p-4)(p-3)(p-2) + (p-4)(p-3)(p-2)(p-1))
	 * = (p-5)! * (1 + (-4) + (-4)(-3) + (-4)(-3)(-2) + (-4)(-3)(-2)(-1))
	 * = (p-5)! * (1 + -4 + 12 + -24 + 24)
	 * = (p-5)! * 9
	 * = (p-1)! / ((p-1)(p-2)(p-3)(p-4)) * 9
	 * = (p-1)! / ((-1)(-2)(-3)(-4)) * 9
	 * = (p-1)! / 24 * 9
	 * = (p-1)! * (3 * 3) / (3 * 8)
	 * = (p-1)! * 3 / 8
	 * = -1 * 3 / 8  (by Wilson's theorem)
	 * = -3/8 mod p.
	 * Every part of the equation is modulo a prime p > 4.
	 */
	private static int s(int p) {
		return (int)((long)(p - 3) * reciprocalMod(8 % p, p) % p);
	}
	
	
	
	






	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
//		System.out.println( _pmkf(10001) );
		System.out.println(run());
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

//		startTime = System.nanoTime();
//		System.out.println( prime_s(10001) );
//		endTime = System.nanoTime();
//		duration = (endTime - startTime);
//		System.out.println(duration/1000000);


	}

}
