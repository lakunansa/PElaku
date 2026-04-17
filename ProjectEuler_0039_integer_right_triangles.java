/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0039_integer_right_triangles {

	/**
	 * 
	 */
	public ProjectEuler_0039_integer_right_triangles() {
		// TODO Auto-generated constructor stub
	}

	public static boolean div(int m, int n){
		int target = (int)Math.max( (int)Math.sqrt(n), (int)Math.sqrt(m) );
		if (m<=0 || n<=0) return false;
		if (n==1 || m==1) return false;
		if (m%2==0 && n%2==0) return true;
		for (int i=3;i<=target;i+=2)
			if (m%i==0 && n%i==0) return true;
		return false;
	}



	public static long irt(int p) {
		int [] numbers = new int[1001];
		int a=0,b=0;
		for (int m=2;m<23;m++)
			for (int n=1;n<m;n++) {
				if ( !div(m,n) && (m+n)%2==1 ) {
					int tmp=2*(m)*(m+n);
					for (int k=1;(tmp*k)<1001;k++) {
						System.out.println(m+" "+n+" "+k+" "+tmp+" "+b);
						numbers[k*tmp]++;
						if (numbers[k*tmp]>b) {
							b=numbers[k*tmp];
							System.err.println(m+" "+n+" "+k+" "+tmp+" "+b);
						}
					}
					
				}
			}
		return b;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes n such that the number of integer right triangles with perimeter n is maximal
		//n <=1000


		long startTime = System.nanoTime();
		System.out.println( irt(1000) );
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
