/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0015_lattice_path {

	/**
	 * 
	 */
	public ProjectEuler_0015_lattice_path() {
		// TODO Auto-generated constructor stub
	}


	public static long lp(int n1, int n2) {
//rekursiv, mit faktoren 
		long sum=1;
		n1+=n2;
		for (int i=1;i<=n2;i++) {
			sum*=n1--;
			sum/=i;
		}
		return sum;
//		if ((n1 <= 0) || (n2 <= 0)) return 1;
//		return lp(n1-1,n2)+lp(n1,n1);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of paths on a grid (binomial coeff)


		long startTime = System.nanoTime();
		System.out.println( lp(20,20) );
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
