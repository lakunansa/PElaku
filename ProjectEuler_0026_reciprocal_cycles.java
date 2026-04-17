/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0026_reciprocal_cycles {

	/**
	 * 
	 */
	public ProjectEuler_0026_reciprocal_cycles() {
		// TODO Auto-generated constructor stub
	}

	//finds the number with longest period below 1000
	public static long ldp(int n) {
		for (int i=n-1;i>0;i-=1) {
			int count = 10%i;
			int j=1;
			for (;count !=1 && j<i;j++) {
				count*=10;
				count%=i;
			}
			if (j!=i-1) {
				if (count <= 1)
				System.out.println(i+" "+j+" "+count);
			} else {
				System.out.println(i+" "+j+" "+count+ " sumsum");
			}
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes d<n for which 1/d contains the longest recurring cycle in its decimal fraction part


		long startTime = System.nanoTime();
		System.out.println( ldp(1000) );
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
