/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0025_1000digit_fibo {

	/**
	 * f1=1
	 * f2=1
	 * 
	 * 
	 * 
	 */
	public ProjectEuler_0025_1000digit_fibo() {
		// TODO Auto-generated constructor stub
	}


	public static long dfn(int n) {
		/**
		 * d = (sqroot(5)+1)/2
		 */
		double phi = (Math.sqrt(5)+1d)/2d;
		double logphi = Math.log10(phi);
		double logsq5= Math.log10(Math.sqrt(5));
//		System.out.println(Math.ceil((n-1+logsq5)/logphi));
		return (long)(Math.ceil((n-1+logsq5)/logphi));
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the index of the first term in the Fibonacci sequence to contain 1000digits


		long startTime = System.nanoTime();
		System.out.println( dfn(1000) );
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
