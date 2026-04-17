/**
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */
public class ProjectEuler_0757_stealthy_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0757_stealthy_numbers() {
		// TODO Auto-generated constructor stub
	}


	public static long _sn(long n) {
//				n=(long)(1E6);
		long count=0;
		long LIMIT = (long)Math.sqrt(n/2);
		ArrayList<Long> sn = new ArrayList<Long>();
		forI:for (long i=1;i<=LIMIT; i++) {
			forJ:for (long j=1;j<=i;j++) {
				long num=i*(i+1)*j*(j+1);
				if ( num>n ) {
					if (i%(1E5) ==0 ) System.out.println(i);   
					continue forI;
				}
				sn.add(num);
			}
		}
		System.err.println(sn.size());
		long last=0;
		Collections.sort(sn);
		count=1;
		for (int k=1;k<sn.size();k++) {
			if (!sn.get(k) .equals( sn.get(k-1) )) {
				count++;
			}
			
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _sn((long)(1E14)) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/1000000


	}

}
