/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0206_concealed_square {

	/**
	 * 
	 */
	public ProjectEuler_0206_concealed_square() {
		// TODO Auto-generated constructor stub
	}


	public static long _cs(int n) {
		//1_2_3_4_5_6_7_8_9_0
		
		// 0=0:   
		// 1=0:          
		// 2=9:   x1^2 (3/7) 
		// 3=_:        + (6x2/14x2+4)
		// 4=8:   x2^2 + 2 x1x3
		// 5=_:        + 2 x1x4 + 2 x2x3
		// 6=7:   x3^2 + 2 x1x5 + 2 x2x4
		// 7=_:        + 2 x1x6 + 2 x2x5 + 2 x3x4
		// 8=6:   x4^2 + 2 x1x7 + 2 x2x6 + 2 x3x5
		// 9=_:        + 2 x1x8 + 2 x2x7 + 2 x3x6 + 2 x4x5
		//10=5:   x5^2 + 2 x1x9 + 2 x2x8 + 2 x3x7 + 2 x4x6
		//11=_:          2 x2x9 + 2 x3x8 + 2 x4x7 + 2 x5x6
		//12=4:   x6^2 + 2 x3x9 + 2 x4x8 + 2 x5x7
		//13=_:          2 x4x9 + 2 x5x8 + 2 x6x7
		//14=3:   x7^2 + 2 x5x9 + 2 x6x8
		//15=_:          2 x6x9 + 2 x7x8
		//16=2:   x8^2 + 2 x7x9
		//17=_:          2 x8x9
		//18=1:   x9^2 (3/4)
		long i;
		
		for (i=100_000_003L;i<150_000_000;i=(i%10==3?i+4:i+6) ) {
			long sqi = i*i;
			int d=9;
			while (sqi%10 == d) {
				sqi/=100;
				d--;
			}
			if (d>-1) continue;
			else break;
		}
		System.out.println(i*i);
		return i*10;
	}

//1122334455667788990
//19293742546274889	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _cs(10001) );
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
