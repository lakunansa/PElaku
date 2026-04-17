/**
 * 
 */

import java.util.Arrays;

/**
 * 
 */
public class ProjectEuler_0007_10001st_prime {

	/**
	 * 
	 */
	public ProjectEuler_0007_10001st_prime() {
		// TODO Auto-generated constructor stub
	}

	
	public static int nthPrime_KI(int n) {//fastest so far eratosthenes ohne mod
	    int limit = 150000; // Großzügige Schätzung für die 10.001-te Primzahl
	    boolean[] isPrime = new boolean[limit];//schneller, wenn 2 UND 3 geklammert werden (6k+-1)
	    Arrays.fill(isPrime, true);
	    isPrime[0] = isPrime[1] = false;

	    int count = 0;
	    for (int p = 2; p < limit; p++) {
	        if (isPrime[p]) {
	            count++;
	            if (count == n) return p; // Ziel erreicht!
	            
	            // Streiche alle Vielfachen von p
	            if ((long)p * p < limit) {
	                for (int i = p * p; i < limit; i += p)
	                    isPrime[i] = false;
	            }
	        }
	    }
	    return -1; // Falls Limit zu klein gewählt wurde
	}


	public static long prime(int n) {
		int[] primes = new int[n];
		primes[0]=2; primes[1]=3;
		int last = 2;
		for (int i=5; last < n; i+=2) {
			boolean prime=true;
			int sqi=(int) Math.sqrt(i);
			for (int j=0; j<last;j++) 
				if ( i%primes[j]==0 ) {
					prime=false;
					break;
				}else if(primes[j]>sqi){
					break;
				}
			if (prime) {
				primes[last] = i;
				last++;
			}
		}
		return primes[n-1];
	}

//analogue without space usage
	public static boolean isPrime(long n) {
		if (n==1) return false;
		else if (n<4) return true; //2 and 3 are prime
		else if (n % 2 == 0) return false;
		else if (n<9) return true; //we have already excluded 4,6 and 8.
		else if (n % 3==0) return false;
		else {
			int r=(int)Math.sqrt(n ), f=5;
			while (f<=r) if (n % f==0) return false;
			else if (n % (f+2)==0) return false;
			else f+=6;
			return true;
		}
	}

	public static long prime_s(int n) {

		int count=1; //we know that 2 is prime
		long candidate=1;
		do {
			candidate+=2;

			if (isPrime(candidate)) count++;
		} while (count < n);
		return candidate;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( prime(10001) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		startTime = System.nanoTime();
		System.out.println( prime_s(10001) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);

		startTime = System.nanoTime();
		System.out.println( nthPrime_KI(10001) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);

		
	}

}
