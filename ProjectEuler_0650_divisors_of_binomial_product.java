/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.LongStream;

/**
 * 
 */
public class ProjectEuler_0650_divisors_of_binomial_product {

	/**
	 * 
	 */
	public ProjectEuler_0650_divisors_of_binomial_product() {
		// TODO Auto-generated constructor stub
	}

	
	public static long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;

        if (m == 1) return 0;

        while (a > 1) {
            if (m == 0) return -1; // Should not happen if m > 1
            
            // q is quotient
            long q = a / m;
            long t = m;

            // m is remainder now, process same as Euclid's algo
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // If gcd(a, m) is not 1, then inverse doesn't exist
        if (a != 1) return -1;

        // Make x positive
        if (x < 0) x += m0;

        return x;
    }

	
	
	// (base^exp) % mod unter Vermeidung von Überläufen
	private static long powerMod(long base, long exp, long mod) {
	    long res = 1;
	    base %= mod;
	    while (exp > 0) {
	        if (exp % 2 == 1) res = (res* base)% mod;
	        base = (base* base)% mod;
	        exp /= 2;
	    }
	    return res;
	}


	
	
    private static BitSet sieve(int n) {
        BitSet prime = new BitSet(n + 1);
        prime.set(2, n + 1);
        for (int p = 2; p * p <= n; p++) {
            if (prime.get(p)) {
                for (int i = p * p; i <= n; i += p)
                    prime.clear(i);
            }
        }
        return prime;
    }
	
	
	public static long _dobp(int n) {
		long mod = (long)1E9+7;
//		long mod = (long)1E18;
		int[][] nL = new int[n+1][];
		BitSet isPrime = sieve(n+1);
		//generates a list of numbers up to n, and their prime factors with exponents
		for (int i=0; i<n+1;i++) {
			System.out.println("here " + i);
			int[] dL = new int[n+1];
			nL[i]=dL;
			int tmp=i;
			forj:for (int j=0;j<=i ;j++) {
				int k=0;
				if (isPrime.get(j)) {
					while (tmp%j==0) {
						tmp/=j;
						k++;
					}
				}
				dL[j]=k;
				if (tmp==1) break forj;
			}
		}
		
		int[] primeFacsOfNFac = new int[n+1]; primeFacsOfNFac[2]=1;
		
		int[][] primefactorsOfBn = new int[n+1][n+1];
		primefactorsOfBn[2][2] = 1;
		for (int i=3; i<=n;i++) {
			System.out.println("there "+i);
			for (int j=2;j<i;j++) {
				if (primefactorsOfBn[i-1][j]>0) primefactorsOfBn[i][j]=primefactorsOfBn[i-1][j];
			}
			for (int j=2; j<=i;j++) {
				if (nL[i][j]>0)primefactorsOfBn[i][j] += nL[i][j]*(i-1);
			}
			for (int j=2; j<i;j++) {
					if (primeFacsOfNFac[j]>0) primefactorsOfBn[i][j] -= primeFacsOfNFac[j];
			}
			for (int j=2;j<=i;j++) {
				if (nL[i][j]>0) primeFacsOfNFac[j] += nL[i][j];
			}
		}
		
		
/**		
		int[][] exp = new int[n+1][n+1];
		for (int i=2; i<=n;i++) {
			for (int j=2;j<=i;j++) {
				for (int k=2;k<=j;k++) {
					if (nL[j][k] > 0) exp[i][k]+=nL[j][k]*(2*j-i-1);
				}
			}
		}
*/
		
		long[] sumDivBc = new long[n+1];
		Arrays.fill(sumDivBc, 1); sumDivBc[0]=0;
		for (int i=2; i<=n;i++) {
			System.out.println("last "+i);
			for (int j=2;j<=i;j++) {
//				System.out.print("i="+i+ " j="+j+" "+ primefactorsOfBn[i][j]+" ");
					if (primefactorsOfBn[i][j]!=0) {
						sumDivBc[i] = ((((powerMod(j, primefactorsOfBn[i][j]+1, mod)-1) * modInverse(j-1,mod))% mod)*sumDivBc[i])%mod;
					}
			}
//			System.out.println();
		}
		
		long sum = 0;
		for (long l:sumDivBc) sum = (sum+l)%mod;
		
		
//		for (int[] dL : nL) {
//			for (int i: dL) {
//				System.out.print(i+ " ");
//			}
//			System.out.println();
//		}
//		
		
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long startTime = System.nanoTime();
		System.out.println( _dobp(20000) );
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
