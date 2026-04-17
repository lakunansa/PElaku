/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0076_counting_summations {

	/**
	 * 
	 */
	public ProjectEuler_0076_counting_summations() {
		// TODO Auto-generated constructor stub
	}


	public static long cs(int n) {
//		n=5;
		long[][] evaluated = new long[n+1][n+1];
		evaluated[1][1]=1;
		for (int sum=0;sum<=n;sum++)
			for (int max=0;max<=n; max++) {
				if (max>sum) evaluated[sum][max] = evaluated[sum][max-1];
				else if (sum==0 || max==1) evaluated[sum][max]=1;
				else if (max==0) continue;
				else evaluated[sum][max]=evaluated[sum][max-1]+evaluated[sum-max][max];
			}
//		for (int i=0; i<=n;i++) {
//			for (int j=0;j<=n;j++)System.out.print( evaluated[i][j] + " ");
//			System.out.println();
//		}
		return evaluated[n][n];
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( cs(100) );
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
