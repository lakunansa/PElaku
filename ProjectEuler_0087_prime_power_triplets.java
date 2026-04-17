/**
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;


/**
 * 
 */
public class ProjectEuler_0087_prime_power_triplets {

	/**
	 * 
	 */
	public ProjectEuler_0087_prime_power_triplets() {
		// TODO Auto-generated constructor stub
	}


	public static long ppt(int n) {

//		n=50;
		int count=0;
		int[][]sieve = sieve(n);
		boolean[] beenused = new boolean[n];
		for (int i: sieve[0]) {
			int i4 = i*i*i*i;
			if (i4>=n) break;
			for (int j: sieve[0]) {
				int j3 = j*j*j;
				if ( i4+j3 > n) break;
				for (int k: sieve[0]) {
					int sum = i4 + j3 + k*k;
					if ( sum >= n) break;
					if(!beenused[sum]) {
						count++;
						beenused[sum]=true;
					}
				}
			}
		}
		return count;
	}

	public static long ppt_KI(int n) {//faster x10
		int limit = n;
		int maxPrime = (int) Math.sqrt(limit);
		List<Integer> primes = getPrimes(maxPrime);

		// BitSet markiert alle Zahlen, die als Summe darstellbar sind
		BitSet foundNumbers = new BitSet(limit);

		for (int p4 : primes) {
			long pow4 = (long) Math.pow(p4, 4);
			if (pow4 >= limit) break;

			for (int p3 : primes) {
				long pow3 = (long) Math.pow(p3, 3);
				if (pow4 + pow3 >= limit) break;

				for (int p2 : primes) {
					long sum = pow4 + pow3 + (long) p2 * p2;
					if (sum >= limit) break;

					foundNumbers.set((int) sum);
				}
			}
		}

		System.out.println("Result: " + foundNumbers.cardinality());
		return maxPrime;
	}

	// Einfaches Sieb für Primzahlen bis max
	private static List<Integer> getPrimes(int max) {
		boolean[] isPrime = new boolean[max + 1];
		Arrays.fill(isPrime, true);
		List<Integer> primes = new ArrayList<>();
		for (int p = 2; p <= max; p++) {
			if (isPrime[p]) {
				primes.add(p);
				for (int i = p * p; i <= max; i += p) isPrime[i] = false;
			}
		}
		return primes;
	}


	public static int[][] sieve(int n) {
		int limit = n;


		boolean[] isPrime = new boolean[limit + 1];
		// Initialisiere alle Zahlen als prim (außer 0 und 1)
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		// Das eigentliche Sieb
		for (int p = 2; p * p <= limit; p++) {
			if (isPrime[p]) {
				// Markiere alle Vielfachen von p als nicht-prim
				// Wir starten bei p*p, da kleinere Vielfachen bereits markiert wurden
				for (int i = p * p; i <= limit; i += p) {
					isPrime[i] = false;
				}
			}
		}

		int count = 0;
		for (int i = 2; i <= limit; i++) {
			if (isPrime[i]) count++;
		}
		int[][] primesInds = new int[2][];
		primesInds[0] = new int[count+1];
		primesInds[1] = new int[limit+1];
		int cur=0;
		for (int i = 2; i <= limit; i++) {
			if (isPrime[i]) {
				primesInds[0][cur]=i;
				primesInds[1][i]=cur;
				cur++;
			}
		}
		return primesInds;
	}






	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
				System.out.println( ppt_KI(50_000_000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		startTime = System.nanoTime();
		System.out.println( ppt(50_000_000) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);


	}
}


