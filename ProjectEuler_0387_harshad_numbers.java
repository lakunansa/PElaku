/**
 * 
 */

import java.math.BigInteger;

/**
 * 
 */
public class ProjectEuler_0387_harshad_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0387_harshad_numbers() {
		// TODO Auto-generated constructor stub
	}

	
	public class MillerRabin {

	    /**
	     * Prüft deterministisch, ob n eine Primzahl ist.
	     * Gültig für n < 3,825,123,056,546,413,051 (deckt 10^14 ab).
	     */
	    public static boolean isPrime(long n) {
	        if (n < 2) return false;
	        if (n == 2 || n == 3) return true;
	        if (n % 2 == 0 || n % 3 == 0) return false;

	        // n - 1 als 2^s * d schreiben
	        long d = n - 1;
	        int s = 0;
	        while (d % 2 == 0) {
	            d /= 2;
	            s++;
	        }

	        // Deterministische Basen für den Bereich bis 10^14
	        // Die ersten 7-9 Primzahlen reichen für den long-Bereich aus
	        long[] bases = {2, 3, 5, 7, 11, 13, 17, 19, 23};

	        for (long a : bases) {
	            if (n <= a) break;
	            if (!millerRabinTest(n, a, d, s)) return false;
	        }
	        return true;
	    }

	    private static boolean millerRabinTest(long n, long a, long d, int s) {
	        long x = powerMod(a, d, n);
	        if (x == 1 || x == n - 1) return true;

	        for (int r = 1; r < s; r++) {
	            x = multiplyMod(x, x, n);
	            if (x == n - 1) return true;
	        }
	        return false;
	    }

	    // (base^exp) % mod unter Vermeidung von Überläufen
	    private static long powerMod(long base, long exp, long mod) {
	        long res = 1;
	        base %= mod;
	        while (exp > 0) {
	            if (exp % 2 == 1) res = multiplyMod(res, base, mod);
	            base = multiplyMod(base, base, mod);
	            exp /= 2;
	        }
	        return res;
	    }

	    // Sicherere Multiplikation (a * b) % mod für große long-Werte
	    private static long multiplyMod(long a, long b, long m) {
	        return BigInteger.valueOf(a)
	                         .multiply(BigInteger.valueOf(b))
	                         .remainder(BigInteger.valueOf(m))
	                         .longValue();
	    }
	}
	
	
	
	public static long shn(long N, int d, long max) {
		long sum=0;
		if (N>max) return 0;
		if (N%d != 0) return 0;
		if (MillerRabin.isPrime(N/d)) {
			for (int i=7;i!=5;i=(i+2)%10) {
				long t = 10*N+i;
				if (MillerRabin.isPrime(t) && t<= max) sum+= t;
			}
		}
		for (int i=0;i<=9;i++) {
			sum+=shn(10*N+i, d+i, max);
		}
		return sum;
	}
	
	/**
	 * strong right truncable HN: 
	 * 1.is Prime,
	 * 2. take first digit away: then (new number)/(new digit sum) = prime
	 * 3. take all digits away and is always divisible by sum of digits 
	 * 
	 * hence: 
	 * 1. start with 1..9, is Harshad 
	 * 2. add a number, s.t. is divisible by digit sum
	 * 3. if quotient is prime: add number to see whether is interesting number
	 * 
	 * @param n
	 * @return
	 */
	public static long _hn(long n) {
//		n=10000;
		long sum=0;
		for (int i=1;i<=9;i++) {
			sum+=shn(i,i, n);
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _hn(100_000_000_000_000L) );
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
