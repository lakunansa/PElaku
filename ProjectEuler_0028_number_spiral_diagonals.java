/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0028_number_spiral_diagonals {

	/**
	 * 
	 */
	public ProjectEuler_0028_number_spiral_diagonals() {
		// TODO Auto-generated constructor stub
	}


	public static long nsd(int n) {
		if (n%2==0)n--;
//		System.out.println(n);
		long sum = 1;
		for (int i=1;i<n;i+=2) {
			sum+=4*i*i + 10*i+10;
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of the numbers on the diagonals in a n by n spiral formed in the same way


		long startTime = System.nanoTime();
		System.out.println( nsd(1001) );
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
