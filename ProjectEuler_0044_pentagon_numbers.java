/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0044_pentagon_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0044_pentagon_numbers() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isP(long n) {
		long o = (long) Math.sqrt(24*n+1);
		if (o*o==(24*n+1) && o%6==5) return true;
		return false;
	}

	public static long pn(int n) {
		int m = 1;
		int border = 5000; border *=border;
		long[] ps = new long[border];
		long ps_=1, iter=4;
		for (int i=1; i < border; i++, iter+=3) {
			ps[i]=ps_;
			ps_+=iter;
		}
		while (m<10000) {
			long um = (ps[m]-1)/3;
//			System.out.println((1+(int)(Math.sqrt(72*m+25))) /6);
			for (int k= (1+(int)(Math.sqrt(72*m+25))) /6; k<um; k++ ) {
//				System.out.println(m+" "+pm +" "+ k);
				if (isP(ps[m]+ps[k]) && isP(ps[m]+2*ps[k])) {
					System.out.println(m+" "+ " "+ k);
					return ps[m];
				}
			}
			m++;
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes  the pair of pentagonal numbers, for which their sum and difference are pentagonal and minimised;

		long startTime = System.nanoTime();
		System.out.println( pn(10001) );
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
