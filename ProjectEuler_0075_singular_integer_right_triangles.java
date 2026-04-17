/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0075_singular_integer_right_triangles {

	/**
	 * 
	 */
	public ProjectEuler_0075_singular_integer_right_triangles() {
		// TODO Auto-generated constructor stub
	}


	public static long sirt(int n) {
//		n=120;
		boolean[] once = new boolean[n+1];
		boolean[] twice = new boolean[n+1];
		int sqN = (int)Math.floor(Math.sqrt((double)n/2));
		for1: for (int i=1;i<=sqN;i++) {
			for2: for (int j=1;j<=n;j++) 
				if ( (i>j) && (i+j)%2==1 && ProjectEuler_Library.gcd(i,j)==1)
					for (int k=1;;k++) {
						int sum=2*i*(i+j)*k;
						if (k==1 && sum>n) continue for1;
						if (sum>n) continue for2;
//						System.out.println(i + " " + j + " " + k + " " + sum);
						if (!twice[sum])
							if (!once[sum])
								once[sum]=true;
							else {
								once[sum]=false;
								twice[sum]=true;
							}
					}
				}
		int count=0;
		for (boolean a:once) if(a) count++;
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( sirt(1500000) );
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
