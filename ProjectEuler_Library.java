import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 
 */
public class ProjectEuler_Library {
	/**
	 * contains useful methods and constants
	 */
	public ProjectEuler_Library() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param n
	 * @return generates all primes up to sqrtR via Eratosthenes
	 */
	public static ListIterator<Integer> primesUpToSqrtN(int n){
//		n=100;
		int lim = (int)Math.floor(Math.sqrt(n));
		boolean[] mark = new boolean[lim+1];
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int i;
		primes.add(i=2);
        for (int j = i * i; j <= lim; j += i) mark[j] = true;
		for (i++; i<=lim; i+=2) {
			if(!mark[i]) {
				primes.add(i);
		        for (int j = i * i; j <= lim; j += i) mark[j] = true;
			}
		}
//		for (Integer j:primes) System.out.println(j);
		return primes.listIterator(0);
	}
	
	public static long eulerPhi(int n) {
		long phi = n;
		int i=1;
		Iterator<Integer> it = ProjectEuler_Library.primesUpToSqrtN(n);
		long p = it.next();
		while (it.hasNext() && p*p <= n) {
			if (n%p==0) {
				phi-=phi/p;
				while (n%p==0) n/=p;
			}
			p=it.next();
		}
		if (n>1) phi-=phi/n;
		return phi;
	}
	
	public static long fractionCount(int n) {
		long fractionCount=0;
		for (int i=2;i<=n;i++) fractionCount += eulerPhi(i);
		System.out.println(fractionCount);
		return fractionCount;
	}
	
/**
 * returns primality of given long n
 * @param n
 * @return
 */
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
	
	/**
	 * returns the next coefficients for contfrac sequence for a number z;
	 * @param a
	 * has 5 entries:
	 * first: a0 = z, a1=floor(sqrootz)
	 * foled by: mi, di, ai 
	 * 
	 * 
	 * s.t. ai = (a1 + mi) div di // ai being the entrances of the fraction
	 * 
	 * earlier
	 * 				bi=(n-ai*ai)/bi;
	 * 				ai = a0-(a0+ai)%bi;
	 * 	 such that bi/(z-ai) = di' + (z-ai')/bi's
	 *  should account for rational b if happens
	 * 
	 * @return
	 * new [] 
	 *
	 */
	// möglicherweise muss man rationales b annehmen, und die methode umschreiben...
	public static long[] contFrac (long[] a) {
		long tmpm = a[3]*a[4]-a[2]; //=new mi
		long tmpd = (a[0]-tmpm*tmpm)/a[3]; //=new di
		
		return new long[] {a[0], a[1], tmpm, tmpd, (a[1]+tmpm)/tmpd};
	}
	
	
    public static long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = multiplyMod(res, base, mod);
            base = multiplyMod(base, base, mod);
            exp /= 2;
        }
        return res;
    }
    public static long multiplyMod(long a, long b, long m) {
        return Math.multiplyExact(a, b) % m; // Nutzt Java 8+, wirft Fehler bei Overflow
        // Für extrem große p nahe Long.MAX_VALUE müsste man BigInteger nutzen.
    }
    
    /**
     * computes the greatest common divisor of two númbers
     * @param m
     * @param n
     * @return int
     */
    public static long gcd(long m, long n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fractionCount(8);

	}

}
