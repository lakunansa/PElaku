import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class ProjectEuler_0755_not_zeckendorf {

	/**
	 * 
	 */
	public ProjectEuler_0755_not_zeckendorf() {
		// TODO Auto-generated constructor stub
	}

	
	


	    private static Map<Long, Long> memoS = new HashMap<>();

	    	static {
	    		memoS.put(0L, 1L);
	    		memoS.put(1L, 2L);
	    		memoS.put(2L, 3L);
	    	}
	    
	    public static long sumS(long n) {//n nnormal
	        if (n < 0) return 0;
	        if (memoS.containsKey(n)) return memoS.get(n);

	        long bzn = toZeckendorf_ki(n); //bzn Zeckendorf

	        // Neue Abbruchbedingung: 
	        // n+1 oder n+2 ist eine Fibonacci-Zahl
	        if (isFibPlus(n)) {
		        memoS.put(n, bzn+1);
	            return bzn + 1;
	        }

	        boolean hasF1orF2 = (bzn & 0b11) != 0;

	        long nPrime;
	        long nDoublePrime;

	        if (hasF1orF2) {
	            nPrime = fromZeckendorfBzn(bzn >> 1); //normal
	            nDoublePrime = fromZeckendorfBzn(bzn >> 2);//normal
	        } else {
	            nPrime = fromZeckendorfBzn(toZeckendorf_ki(n - 1) >> 1);//normal
	            nDoublePrime = fromZeckendorfBzn(bzn >> 2);//normal
	        }

	        long result = sumS(nPrime) + 2 * sumS(nDoublePrime - 1) + rec_nz_ki(bzn >> 2,-1);

	        memoS.put(n, result);
	        return result;
	    }

	    /**
	     * Prüft, ob n+1 oder n+2 eine Fibonacci-Zahl ist.
	     * In der Zeckendorf-Darstellung (BZN) sind das Zahlen wie 
	     * 10101... (n+2 ist Fib) oder 101010 (n+1 ist Fib).
	     */
	    private static boolean isFibPlus(long n) {
	        // Alternativ: Check ob (n+1) oder (n+2) in einem Set von Fib-Zahlen ist
	        // Oder über das Bitmuster: Keine zwei Nullen hintereinander (außer am Ende)
//	        long bzn = toZeckendorf_ki(n);
	        // Ein n, das "fast" eine Fib-Zahl ist, hat ein sehr regelmäßiges Bitmuster.
	        // Für die Implementierung hier ist ein einfacher Check gegen bekannte Fibs am schnellsten:
	        return isFibonacci(n + 1) || isFibonacci(n + 2);
	    }

	    private static boolean isFibonacci(long x) {
	        // Ein schneller Test für Fibonacci-Zahlen: 
	        // 5*x^2 + 4 oder 5*x^2 - 4 ist ein perfektes Quadrat
	        return isPerfectSquare(5 * x * x + 4) || isPerfectSquare(5 * x * x - 4);
	    }

	    private static boolean isPerfectSquare(long n) {
	        if (n < 0) return false;
	        long s = (long) Math.sqrt(n);
	        return s * s == n;
	    }
	    
	    
	    // Hilfsmethode zur Konvertierung BZN -> N
	    private static long fromZeckendorfBzn(long bzn) {
	        if (bzn <= 0) return 0;
	        long value = 0;
	        long fMinus1 = 1; // F1
	        long fCurrent = 1; // F2
	        
	        long tempBzn = bzn;
	        while (tempBzn > 0) {
	            if ((tempBzn & 1) == 1) {
	                value += fCurrent;
	            }
	            long nextF = fCurrent + fMinus1;
	            fMinus1 = fCurrent;
	            fCurrent = nextF;
	            tempBzn >>= 1;
	        }
	        return value;
	    }

	    // Deine existierenden Methoden
	    // long fromZeckendorfBzn(long bzn) { ... }
	    // long toZeckendorf(long n) { ... }
	    // long fNausBZn(long bzn) { ... }

	
	
	
	
	
	
	private static final long[] FIBO = new long[91];


	/**
	 * sum <T> from 0 to n = sum <T> from 0 to n' + 2*sum <T> from 0 to n'' + <n''+1> 
	 * 
	 * 1. wenn n Fibo -1 oder Fibo -2 , dann result= Bit(n)
	 * 
	 * 2. given n: ermitteln von n' und n''
	 * 
	 * 
	 * 
	 */


	static {
		FIBO[0] = 1;
		FIBO[1] = 2;
		for (int i = 2; i < FIBO.length; i++) {
			FIBO[i] = FIBO[i - 1] + FIBO[i - 2];
		}
	}

	public static long toZeckendorf_ki(long n) {
		if (n <= 0) return 0;

		long zeckBitRep = 0;

		// Wir suchen den Startpunkt: Die größte Fibonacci-Zahl <= n
		// Da die Liste sortiert ist, könnte man Binary Search nutzen,
		// aber ein einfacher Rückwärts-Scan ist bei 90 Elementen extrem schnell.
		for (int i = FIBO.length - 1; i >= 0; i--) {
			if (n >= FIBO[i]) {
				n -= FIBO[i];
				// Bit-Position: i (da Bit 0 für FIBO[0]=1 stehen soll)
				zeckBitRep |= (1L << i);

				// Da keine benachbarten Fibonaccis erlaubt sind, 
				// können wir das nächste i sicher überspringen.
				i--; 

				if (n == 0) break;
			}
		}
		return zeckBitRep;
	}


	public static long rec_nz_ki(long K, int last) {
		long sum = 0;
		if (last == -1 && K%2==0) last++;
		int i = last + 1;


		// Solange noch gesetzte Bits oberhalb von i existieren
		while ((K >> i) != 0) {
			if (((K >> i) & 1L) == 1L) {
				if (i - last > 1) {
					sum += ((i - last) / 2) * rec_nz_ki(K, i);
				}

				// Überspringe den Block aus aufeinanderfolgenden 1-Bits
				while (((K >> i) & 1L) == 1L) {
					i++;
				}
				last = i;
			}
			i++;
		}
		return sum + 1;
	}


	public static long rec_nz(boolean[] bools, int last) {//last ist "leer"
		long sum=0;
		int i=last+1;
		for (; i<bools.length;i++) 
			if (bools[i]) {
				if (i-last >1 ) {
					sum+=((i-last)/2)*rec_nz(bools, i);
				}
				while(i<bools.length && bools[i]) i++;
				last=i;
				i++;
			} 
		return sum+1;
	}

	public static long _nz(int n) {
		//		boolean[] number = new boolean[8]; number[7] =true;
		//		System.out.println(toZeckendorf_ki(16));
//		long sum=0;
//		for (int i=0; i<= 100;i++) {
//			sum+= rec_nz_ki(toZeckendorf_ki(i), -1);
//			System.out.println( "i: " + i + " Z: " + toZeckendorf_ki(i) + " i': " + fromZeckendorfBzn(toZeckendorf_ki(i)) +  " numNZ: " + rec_nz_ki(toZeckendorf_ki(i), -1) + " sum: "+ (sum-1) + " diff: (sum-1) - Z " + (sum-1-toZeckendorf_ki(i)));
//		}
		for (int i=0;i<=10000;i++) System.out.println("i: " + i + " Z: " + toZeckendorf_ki(i) + " sum: " + sumS(i));
		return sumS((long)1E13);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _nz(10001) );
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
