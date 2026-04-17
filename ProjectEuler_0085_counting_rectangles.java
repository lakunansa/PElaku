/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0085_counting_rectangles {

	/**
	 * 
	 */
	public ProjectEuler_0085_counting_rectangles() {
		// TODO Auto-generated constructor stub
	}


	public static long cs(int n) {
		int[] tn = new int[2001];
		for (int i=1;i<tn.length;i++) tn[i]=tn[i-1]+i;
//		System.out.println(tn[2000]);
		
		long min = Long.MAX_VALUE, minA = -1;
		
		for (int i=1;i<2001;i++)
			for (int j=i;j<2001;j++) {
				long d = Math.abs((long)tn[i]*(long)tn[j] - 2_000_000L);
				if (d<min) {
					min=d;
					minA = (i)*(j);
				}
			}
		System.out.println(min);		
		
		return minA;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( cs(2_000_000) );
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
