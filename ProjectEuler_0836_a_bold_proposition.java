/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0836_a_bold_proposition {

	/**
	 * 
	 */
	public ProjectEuler_0836_a_bold_proposition() {
		// TODO Auto-generated constructor stub
	}


	public static String _9(int n) {
		return "aprilfoolsjoke";
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
