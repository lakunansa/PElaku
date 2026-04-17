/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0056_powerful_digit_sums {

	/**
	 * 
	 */
	public ProjectEuler_0056_powerful_digit_sums() {
		// TODO Auto-generated constructor stub
	}

	public static long ds(long n) {
		long ds=0;
		while (n>0) {
			ds+=n%10;
			n/=10;
		}
		return ds;
	}


	public static long pds(int n) {
		long compare = 0;
		for (int i=2; i<n; i++) {
			ArrayList<Byte> nu = new ArrayList<Byte> (0);
			int tmp = i, ds=0;
			while (tmp>0) {
				nu.add((byte)(tmp%10));
				ds+=tmp%10;
				tmp/=10;
			}
			if (ds > compare) compare = ds;
//			System.out.println(i + " " + ds);
			for (int j=2; j<n; j++) {
				ds=0; int carry=0;
				for (int k=0;k<nu.size();k++) {
					carry += nu.get(k)*i;
					nu.set(k, (byte) (carry%10));
					ds += carry%10;
					carry/=10;
				}
				while (carry > 0) {
					nu.add((byte) (carry%10));
					ds+=carry%10;
					carry/=10;
				}
				if (ds > compare) compare = ds;
			}
		}
		return compare;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the highest sum of digits in a^b,  where a,b<100


		long startTime = System.nanoTime();
		System.out.println( pds(100) );
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
