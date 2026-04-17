/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0052_permuted_multiples {

	/**
	 * 
	 */
	public ProjectEuler_0052_permuted_multiples() {
		// TODO Auto-generated constructor stub
	}

	public static long[] tens = {
			1,
			10,
			100,
			1000,
			10000,
			100000,
			1000000,
			10000000,
			100000000,
			1000000000,
			10000000000L
	};


	public static long pm(int n) {
		for (long i=100;i<Integer.MAX_VALUE;i++) {
			long[] ind = new long[7];
			boolean dead=false;
			for (int j=1;j<7;j++) {
				long tmp = j*i;
				while (tmp>0) {
					ind[j] += tens[(int)(tmp%10)];
					tmp/=10;
				}
//				System.out.println(ind[j]);
				if (j>1 && ind[j]!=ind[j-1]) {
					dead=true;
					j=7;
				}
			} 
			if (dead) continue;
			else return i;
		}
		return -1;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the smallest number n, such that 1n, 2n, 3n,...,6n contain the same digits


		long startTime = System.nanoTime();
		System.out.println( pm(10001) );
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
