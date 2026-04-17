/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0006_sum_square_difference {

	/**
	 * 
	 */
	public ProjectEuler_0006_sum_square_difference() {
		// TODO Auto-generated constructor stub
	}

	public static long sum_square_difference(int n) {

		//n n (n+1) (n+1) /2 /2
		// n(n+1)(2n+1) /6
//		1	6
//		5	30
//		14	84
		return (n*n*(n+1)*(n+1))/4 - (n*(n+1)*(2*n+1))/6;
	}





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//the difference between the sum of the squares of the first one hundred 
		//natural numbers and the square of the sum


		long startTime = System.nanoTime();
		System.out.println( sum_square_difference(100) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);

//		startTime = System.nanoTime();
//		System.out.println( getMultiplog(20) );
//		endTime = System.nanoTime();
//		duration = (endTime - startTime);
//		System.out.println(duration);


	}

}
