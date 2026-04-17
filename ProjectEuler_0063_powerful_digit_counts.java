/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0063_powerful_digit_counts {

	/**
	 * 
	 */
	public ProjectEuler_0063_powerful_digit_counts() {
		// TODO Auto-generated constructor stub
	}


	public static long pdc(int n) {
		int count = 0;

//nicht ermittelt, weil ab t=19 eine 64bitZahl überläuft. Und 9^19, 9^20, 9^21 sind noch valide...
		for (long t=1, lotens =1, hitens = 10; t<19; t++, lotens*=10, hitens*=10) {
			//stellen ermitteln
			long lower = 1, lp=1, upper;
			for(long u=t; u>0;u--) {
				lp*=lower;
			}
			while (lp < lotens) {
				lower++;
				lp=1;
				for(long u=t; u>0;u--) {
					lp*=lower;
				}
			}
			System.out.println(t + " " + lower);
			upper=lower;
			while (lp < hitens) {
				count++;
				upper++;
				lp=1;
				for(long u=t; u>0;u--) {
					lp*=upper;
				}
			}
			System.out.println(t + " " + upper + " " + (upper-lower));
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
		System.out.println( pdc(10001) );
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
