/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0045_triangular_pentagonal_hexagonal {

	/**
	 * 
	 */
	public ProjectEuler_0045_triangular_pentagonal_hexagonal() {
		// TODO Auto-generated constructor stub
	}


	public static long tph(int n) {
		
		/**
		 * m = 31977 and n = 27693
		 */
		
		return 27693*(27693*2-1);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the next triangle number that is also pentagonal and hexagonal (n>285 for Tn).


		long startTime = System.nanoTime();
		System.out.println( tph(10001) );
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
