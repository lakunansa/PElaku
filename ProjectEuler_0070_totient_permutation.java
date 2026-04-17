/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0070_totient_permutation {

	/**
	 * 
	 */
	public ProjectEuler_0070_totient_permutation() {
		// TODO Auto-generated constructor stub
	}

	public static long tm(int n) {
//		n=100;
		double tmmax = 10; int ntmmax=1; 
		for (int i=2;i<n;i++) {
//			System.out.print(" n " + i);
			int m = i;
			int result = m;
			if (m%2==0) {
				result -= result/2;
				while (m%2==0) m/=2;
			}
			for (int j=3; j*j<=m; j+=2)
				if (m%j==0) {
					result -= result/j;
					while (m%j==0) m/=j;

				}
			if (m>1) result -=result/m;
//			System.out.print(" phi " + result);
			if ((int)Math.floor(Math.log10(i)) == (int)Math.floor(Math.log10(result))) {
//				System.out.print(" equallength ");
				m=i;
				ArrayList<Integer> digsm = new ArrayList<Integer>();
				double novert = (double)m/(double)result;
//				System.out.print(" novert " + novert);
				while (m>0) {
					digsm.add(m%10); 
					m/=10;
				}
				while (result>0) {
					int mod=result%10;
					if (digsm.remove((Integer)mod)) {
						result/=10;
					} else {
						novert = 10;
						result=0;
//						System.out.println(" noperm ");
					}
				}
				if (novert < tmmax) {
					tmmax = novert;
					ntmmax = i;
//					System.out.println(" new max ");
				}
			} 
//				else System.out.println(" noequallength ");
		}
		return ntmmax;
	}

	public static long tp(int n) {
		return tm(n);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( tp(10000000) );
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
