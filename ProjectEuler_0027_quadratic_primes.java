/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0027_quadratic_primes {

	/**
	 * 
	 */
	public ProjectEuler_0027_quadratic_primes() {
		// TODO Auto-generated constructor stub
	}

	//finds prime sieve below o, first index is to be ignored, 2i+1 is prime
	public static boolean[] ps (int o) {
		int sieve_bound = ( o -1 )/2; // last index of sieve
		boolean[] indexed_sieve = new boolean[sieve_bound + 1];
		int sqrtbound = ( ( (int) Math.sqrt(o) )-1 )/2;
		for (int i=1;i<=sqrtbound;i++) 
			if(!indexed_sieve[i]) //2i+1 is prime
				for (int j=2*i*(i+1);j<=sieve_bound && j>=0;j+=2*i+1)
					indexed_sieve[j]=true;
//		for (int i=1;i<=sieve_bound;i++) {
//			if(!indexed_sieve[i])System.out.println(2*i+1 + " "+i);
//		}
		return indexed_sieve;
	}
	
	
	//finds product of a,b where n^2 + an + b prduces max number of primes
	public static long qp(int n) {
		if (n % 2 == 0) n-=1;
		boolean[] ps = ps(2*n*n+n);
		int max = 0;
		for (int i=n/2;i>0;i--) {
			if (!ps[i]) {
				int ireal = 2*i+1;
				for (int j=n;j>-n;j-=2) {
					int c;
					for (c=1;c<ireal;c++) {
						int result = c*(c+j)+ireal;
						if (result <= 0) break;
						if ( ps[(result-1)/2] ) break;
					}
					if (c>max+1) {
						max = c-1;
						System.err.println(ireal+" "+j+" "+max);
					}
				}
			}
		}
		System.out.println(max);
		//case no zeroes (a is too small)
		//case zeroes negative a is big, but positive
		//case zeroes are positive: a is big, negative, and there exists a boundary in sqroot(b)
		
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the product of the coefficients a,b
		 //for the quadratic expression that produces the maximum number of primes for consecutive values of 
		 //starting with n=0


		long startTime = System.nanoTime();
		System.out.println( qp(1000) );
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
