/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0048_self_powers {

	/**
	 * 
	 */
	public ProjectEuler_0048_self_powers() {
		// TODO Auto-generated constructor stub
	}


	public static long sp(int n) {
		long mod = 10000000000L;
		long sum = 10405071317L;
		for (int i=11; i<=n;i++) {
			long sum2 = 1;
			for (int j=1; j<=i;j++) {
				sum2*=i;
				sum2%=mod;
			}
			sum+=sum2;
			sum%=mod;
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the ten last digits of series i^i from 11 to n


		long startTime = System.nanoTime();
		System.out.println( sp(1000) );
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
