/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0005_smallest_multiple {

	/**
	 * 
	 */
	public ProjectEuler_0005_smallest_multiple() {
		// TODO Auto-generated constructor stub
	}

	
	public static long smallestMultiple_KI(int n) {//faster f10, even faster than log
	    long result = 1;
	    for (int i = 1; i <= n; i++) {
	        result = lcm(result, i);
	    }
	    return result;
	}

	private static long gcd(long a, long b) {
	    while (b > 0) {
	        a %= b;
	        // Swap a and b
	        long tmp = a; a = b; b = tmp;
	    }
	    return a;
	}

	private static long lcm(long a, long b) {
	    if (a == 0 || b == 0) return 0;
	    // Zuerst teilen, um Überlauf zu vermeiden: (a / ggT) * b
	    return (a / gcd(a, b)) * b;
	}
	
	
	public static long getMultiple() {

		return 2520*11*13*17*19*2;
	}


	public static long getMultiple(int n) {
		long p = 1, tmp=1;
		while (p<=(n/2)) p*=2;
		for (int i=3; i<=n; i+=2) {
			if(p%i != 0) {
				tmp = 1;
				while (tmp <= (n/i)) tmp*=i;
				p*=tmp;
			}
		}
		return p;
	}


	public static long getMultiplog(int n) {//hier gibt es logischen fehler, wenn i ungerade und zusgesetzt noch nicht gesiebt wurde
		if (n<2) return 1;
		long p = (long) Math.pow(2,Math.floor(Math.log(n)/Math.log(2)));
		for (int i=3; i<=n; i+=2) 
			if(p%i != 0) p *= (long) Math.pow(i,Math.floor(Math.log(n)/Math.log(i)));
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//finds smallest number divisible by first n numbers
		//log mehtod is faster

		{
		long startTime = System.nanoTime();
		System.out.println( getMultiple(20) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);

		startTime = System.nanoTime();
		System.out.println( getMultiplog(20) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration);
		}
		{
		long startTime = System.nanoTime();
		System.out.println( smallestMultiple_KI(20) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
		}

	}

}
