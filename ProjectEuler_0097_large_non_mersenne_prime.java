/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0097_large_non_mersenne_prime {

	/**
	 * 
	 */
	public ProjectEuler_0097_large_non_mersenne_prime() {
		// TODO Auto-generated constructor stub
	}


	public static long _9(int n) {
		long c = 1024L;
		for (int i=11;i<=17957; i++) {//uses that the cycle of the first  m digits of powers of 2  
			c<<=1;// has length 5^(m-1)*4, used with an offset of 10 
			c%=10_000_000_000L;
		}
		c*=28433;
		c%=10_000_000_000L;
		c+=1;
		return c;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _9(10001) );
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
