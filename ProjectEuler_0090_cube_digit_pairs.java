/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0090_cube_digit_pairs {


	

	/**
	 * 
	 */
	public ProjectEuler_0090_cube_digit_pairs() {
		// TODO Auto-generated constructor stub
	}

	public static long cds(int n) {
		int count = 0;
		for(int i=63;i<1024;i++) {
			for(int j=63;j<i;j++) {
				int d1=i, d2=j;
				int Nd1=0, Nd2=0;
				boolean[]Dd1=new boolean[10];
				boolean[]Dd2=new boolean[10];
				int digits=0;
				while(d1>0) {
					if (d1%2==1) {
						Nd1++;
						Dd1[digits] = true;
					}
					d1/=2;
					digits++;
				}
				digits=0;
				while(d2>0) {
					if (d2%2==1) {
						Nd2++;
						Dd2[digits] = true;
					}
					d2/=2;
					digits++;
				}
				if (Nd1==6 && Nd2==6) {
					if( 
							( Dd1[0]&&Dd2[1] || Dd1[1]&&Dd2[0] )
						&&  ( Dd1[0]&&Dd2[4] || Dd1[4]&&Dd2[0] )
						&&  ( Dd1[0]&&Dd2[6] || Dd1[6]&&Dd2[0] || Dd1[0]&&Dd2[9] || Dd1[9]&&Dd2[0] )
						&&  ( Dd1[1]&&Dd2[6] || Dd1[6]&&Dd2[1] || Dd1[1]&&Dd2[9] || Dd1[9]&&Dd2[1] )
						&&  ( Dd1[2]&&Dd2[5] || Dd1[5]&&Dd2[2] )
						&&  ( Dd1[3]&&Dd2[6] || Dd1[6]&&Dd2[3] || Dd1[3]&&Dd2[9] || Dd1[9]&&Dd2[3] )
						&&  ( Dd1[4]&&Dd2[6] || Dd1[6]&&Dd2[4] || Dd1[4]&&Dd2[9] || Dd1[9]&&Dd2[4] )
						&&  ( Dd1[1]&&Dd2[8] || Dd1[8]&&Dd2[1] )
					) {
						count+=1;
					}
				}
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
		System.out.println( cds(10001) );
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
