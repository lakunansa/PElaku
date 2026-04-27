import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
/**
 * 
 */
public class ProjectEuler_0187_semiprimes_ki {

	/**
	 * 
	 */
	public ProjectEuler_0187_semiprimes_ki() {
		// TODO Auto-generated constructor stub
	}




	/**
	 * Erstellt das Primzahlensieb bis zum angegebenen Limit.
	 */
	public static BitSet computeSieve(int n) {
		BitSet sieve = new BitSet(n + 1);
		sieve.set(2, n + 1); // Alle Zahlen ab 2 initial als prim markieren

		for (int p = 2; p * p <= n; p++) {
			if (sieve.get(p)) {
				// Markiere alle Vielfachen von p als nicht prim
				for (int i = p * p; i <= n; i += p) {
					sieve.clear(i);
				}
			}
		}
		return sieve;
	}

	/**
	 * Zählt die markierten Primzahlen im BitSet.
	 */
	public static int countPrimes(BitSet sieve, int n) {
		return sieve.cardinality();
	}

	
	
	public static long countSemiprimes(long n) {
	    if (n < 4) return 0;

	    int sqrtN = (int) Math.sqrt(n);
	    // 1. Alle benötigten Primzahlen bis n/2 sieben
	    // (Für sehr große n wäre hier eine Pi(x) Funktion besser)
	    int limit = (int)(n / 2); 
	    BitSet isPrime = computeSieve(limit);
	    List<Integer> primes = new ArrayList<>();
	    int[] piTable = new int[limit + 1];

	    int countSoFar = 0;
	    for (int i = 2; i <= limit; i++) {
	        if (isPrime.get(i)) {
	            primes.add(i);
	            countSoFar++;
	        }
	        piTable[i] = countSoFar;
	    }

	    long totalSemiprimes = 0;

	    // 2. Über Primzahlen p_i iterieren
	    // Da p_i <= p_j sein muss, darf p_i maximal sqrt(n) sein
	    for (int i = 0; i < primes.size(); i++) {
	        int p_i = primes.get(i);
	        if (p_i > sqrtN) break;

	        // Finde Anzahl der p_j mit: p_i <= p_j <= n/p_i
	        int maxPj = (int) (n / p_i);
	        
	        // piTable[maxPj] gibt Anzahl Primzahlen bis n/p_i
	        // Wir ziehen (i + 1) ab, um nur p_j >= p_i zu zählen (keine Duplikate)
	        // Das + 1 kommt daher, dass i bei 0 startet (primes.get(0) = 2)
	        totalSemiprimes += (piTable[maxPj] - (i + 1) + 1);
	    }

	    return totalSemiprimes;
	}

	


	public static long _s(int n) {
//		BitSet sieve = computeSieve(n/2);
		return countSemiprimes(n);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _s((int)1E8) );
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
