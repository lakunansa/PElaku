/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0069_totient_maximum {

	/**
	 * 
	 */
	public ProjectEuler_0069_totient_maximum() {
		// TODO Auto-generated constructor stub
	}

	public static long tm(int n) {
//		n=11;
		double tmmax = 1; int ntmmax=0; 
		for (int i=2;i<n;i++) {
			int m = i;
			double result = 1d;
			if (m%2==0) {
				result -= result/(2d);
				while (m%2==0) m/=2;
			}
			for (int j=3; j*j<=m; j+=2)
				if (m%j==0) {
					result -= result/(double)j;
					while (m%j==0) m/=j;

				}
			if (m>1) result -=result/(double)m;
			if (result < tmmax) {
				tmmax = result;
				ntmmax = i;
			}
		}
		return ntmmax;
	}

	public static int tmfast(int n) {
		int p=2;
		for (int i=3; i*p<n; i+=2) 
			if (ProjectEuler_Library.isPrime(i)) p*=i;
		return p;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( tmfast(1000001) );
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
