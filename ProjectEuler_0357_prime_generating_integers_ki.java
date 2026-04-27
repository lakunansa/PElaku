import java.util.Arrays;

/**
 * 
 */
public class ProjectEuler_0357_prime_generating_integers_ki {

	/**
	 * 
	 */
	public ProjectEuler_0357_prime_generating_integers_ki() {
		// TODO Auto-generated constructor stub
	}

	
	
    public static long solve_ki(int n) {
        // 1. Primzahlsieb bis n + 1 (da d + k/d maximal k + 1 ist)
        // Nutze BitSet oder boolean[] - für 10^8 braucht boolean[] ca. 100MB
        boolean[] isPrime = new boolean[n + 2];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= n + 1; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n + 1; i += p)
                    isPrime[i] = false;
            }
        }

        long totalSum = 0; // long verwenden, um Überlauf zu vermeiden!

        for (int k = 1; k <= n; k++) {
            if (isValid(k, isPrime)) {
                totalSum += k;
            }
        }
        return totalSum;
    }

    private static boolean isValid(int k, boolean[] isPrime) {
        // Iteriere über alle Teiler d bis sqrt(k)
        for (int d = 1; d * d <= k; d++) {
            if (k % d == 0) {
                int pairSum = d + (k / d);
                // Da d + k/d bei k <= 10^8 maximal 10^8 + 1 ist,
                // prüfen wir direkt im Sieb.
                if (!isPrime[pairSum]) {
                    return false;
                }
            }
        }
        return true;
    }

	
	
	
	
	public static boolean[] sieve(int n) {
	    boolean[] isPrime = new boolean[n + 1];
	    // Initialisiere alle als prim (außer 0 und 1)
	    for (int i = 2; i <= n; i++) isPrime[i] = true;

	    for (int p = 2; p * p <= n; p++) {
	        // Wenn p nicht markiert ist, ist es eine Primzahl
	        if (isPrime[p]) {
	            // Markiere alle Vielfachen von p ab p*p
	            for (int i = p * p; i <= n; i += p) {
	                isPrime[i] = false;
	            }
	        }
	    }
	    return isPrime;
	}

	
	public static void sievePrimesAndSquareFree(int n, boolean[] isPrime, boolean[] isSquareFree) {
	    
	    // Initialisierung
	    for (int i = 2; i <= n; i++) {
	        isPrime[i] = true;
	        isSquareFree[i] = true;
	    }
	    isSquareFree[1] = true; // 1 ist quadratfrei

	    for (int p = 2; p <= n; p++) {
	        if (isPrime[p]) {
	            long pSquare = (long) p * p;
	            
	            // 1. Markiere Vielfache von p als "nicht prim"
	            for (int i = 2 * p; i <= n; i += p) {
	                isPrime[i] = false;
	            }
	            
	            // 2. Markiere Vielfache von p^2 als "nicht quadratfrei"
	            if (pSquare <= n) {
	                for (int i = (int) pSquare; i <= n; i += (int) pSquare) {
	                    isSquareFree[i] = false;
	                }
	            }
	        }
	    }
	    
	}
	
	
	public static long _pgi_ki(int n) {
        // 1. Sieb vorbereiten: Primes & Square-free
        // Da j + N/j immer <= N + 1 ist und N < n, reicht ein Sieb bis n.
        boolean[] isPrime = new boolean[n + 1];
        boolean[] isSquareFree = new boolean[n + 1];
        sievePrimesAndSquareFree(n, isPrime, isSquareFree);

        long sum = 0;

        // 2. Hauptschleife: i ist Primzahl und i = 4k + 3
        for (int i = 3; i <= n; i += 4) {
            if (!isPrime[i]) continue;

            int N = i - 1;
            if (!isSquareFree[N]) continue;

            // Da N = i-1 und i >= 3, ist N immer gerade.
            // Erster Check für j=2 (der einzige gerade Teiler, den wir separat prüfen)
            if (!isPrime[2 + N / 2]) continue;

            boolean valid = true;
            // Wir iterieren nur über ungerade Teiler j von N
            // Da N quadratfrei ist, sind alle ungeraden Teiler Produkte verschiedener Primzahlen
            double limit = Math.sqrt(N);
            for (int j = 3; j <= limit; j += 2) {
                if (N % j == 0) {
                    // Prüfe Teilerpaar für j (ungerade)
                    if (!isPrime[j + N / j]) {
                        valid = false;
                        break;
                    }
                    
                    // Prüfe Teilerpaar für 2j (gerade)
                    // Da N quadratfrei und gerade, ist N/2 ungerade.
                    // Damit ist j2 = 2j ein Teiler und N/j2 = (N/2)/j ebenfalls eine Ganzzahl.
                    int j2 = 2 * j;
                    if (!isPrime[j2 + N / j2]) {
                        valid = false;
                        break;
                    }
                }
            }

            if (valid) {
                sum += i;
                // System.out.println("Gefunden: " + i);
            }
        }
        return sum;
    }
	

	public static long _pgi(int n) {
	    boolean[] isPrime = new boolean[n + 1];
	    boolean[] isSquareFree = new boolean[n + 1];
	    sievePrimesAndSquareFree(n, isPrime, isSquareFree);
	    long sum=0;
	    fori: for (int i=3; i<=n; i+=4) {
			if (isPrime[i] && isSquareFree[i-1]) {
				int N=i-1;
//				System.out.println("candidate "+N);
//				System.out.println("divisor "+ 2 + " " +(2+N/2) + " "+isPrime[2+N/2]);
//				if (!isPrime[2+N/2]) {
////		    		System.out.println("no\n");
//					continue fori;
//				}
			    for (int j=1; j*j<N; j+=1) {
//			    	int j2 = 2*j;
			    	if (N%j==0) {
//				    	System.out.println("divisor "+ j + " "+ (j+(N/j)) + " " + isPrime[j+N/j]  + " " + (j2+(N/j2)) + " " + isPrime[j2+N/j2]) ;
				    	if (!(isPrime[j+N/j])) {
//				    		System.out.println("no\n");
				    		continue fori;
				    	}
			    	}
			    }
//				System.out.println("yes\n");
				sum+=(long)i;
			}
		}
		
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of integers up to n (incluisve) s.t. every divisor + its complement isPrime


		long startTime = System.nanoTime();
		System.out.println( _pgi_ki( (int)1E8 + 1 ) );
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
