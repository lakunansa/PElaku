/**
 * 
 */

import java.util.HashSet;

/**
 * 
 */
public class ProjectEuler_0346_strong_repunits {

	/**
	 * 
	 */
	public ProjectEuler_0346_strong_repunits() {
		// TODO Auto-generated constructor stub
	}

	
	public static void check() {
//		long j = 782842733569L;
//		long i = 9216;
//		for (long k=2;k<i;k++){
//			if ((j-1)%k==0) {
//				System.out.println(k + " divides " + j + "-1");
//				long m = j*(k-1)+1;
//				long o = k;
//				while (o<m && o>0) {
//					o*=k;
//				}
//				if (m==o) {
//					j=j*i+1;
//					System.out.println("here " + k);				}
//			}
//		}
	}

	public static long _sr(long n) {
//		n=1000;
//		int mod=1_000_000;
//		boolean[][] have = new boolean [mod][mod];
		int lim = (int)Math.sqrt(n);
		long sum=0;
		HashSet<Long> hs = new HashSet<Long>(1_000_000);
		for1: for (long i=2; i<=lim;i++) {
			long j=i*i+i+1;
//			System.out.print(j + " ");
			while1: while(j<n && j>0) {
				if (!hs.add(j)) {
					System.out.println(j + " " + i);
				}
				j=j*i+1;
//				System.out.print(j + " ");
			};
//			System.out.println(i);
		}
		for (Long l:hs) sum+=l;
		
		return sum+1;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		check();
		System.out.println( _sr(1_000_000_000_000L) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

//		startTime = System.nanoTime();
//		System.out.println( prime_s(10001) );
//		endTime = System.nanoTime();
//		duration = (endTime - startTime);
//		System.out.println(duration/100

	}

}
