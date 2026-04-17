/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0020_factorial_digit_sum {

	/**
	 * 
	 */
	public ProjectEuler_0020_factorial_digit_sum() {
		// TODO Auto-generated constructor stub
	}

	//factorial digit sum

	public static long fds(int n) {
		int[]digs = new int[n*(int)Math.log10(n)];
		digs[0]=1;
		for (int i=2;i<=n;i++) {
			for (int j=0; j<digs.length;j++) {
				digs[j]*=i;
			}
			for (int j=0; j<digs.length-1;j++) {
				if (digs[j]>9) {
					digs[j+1] += (digs[j]/10);
					digs[j]%=10;
				}
			}
		}
		int sum=0;
		for (int i:digs) sum+=i;
		return sum;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of the digits in the number 100!


		long startTime = System.nanoTime();
		System.out.println( fds(100) );
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
