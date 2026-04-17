/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
/**
 * 
 */
public class ProjectEuler_0071_ordered_fractions {

	/**
	 * 
	 */
	public ProjectEuler_0071_ordered_fractions() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param n
	 * @param a
	 * @param b
	 * @return the best numerator of the fraction left of a/b with denom less than n;
	 * may not be in reduced form
	 */
	public static long of_best(int n, long a, long b) {
//		n=10;
		long
		bestNum = 0,
		bestDenom = 1,
		currDenom = n,
		minDenom = 1,
		currNum, delta=0;
//		System.out.println("bnum " + bestNum + " bdenom " + bestDenom + " cdenom " + currDenom + " mdenom " + minDenom);
		while(currDenom>=minDenom) {
//			System.out.println("while currDenom >= minDenom " + -(currDenom - minDenom));
			currNum=(a*currDenom-1)/b;
//			System.out.println("currnum new " + currNum);
			if(bestNum*currDenom<currNum*bestDenom) {
//				System.out.println("if bestNum*currDenom<currNum*bestDenom " + -(bestNum*currDenom-currNum*bestDenom));
				bestNum=currNum;
				bestDenom=currDenom;
				delta=a*currDenom-b*currNum;
				minDenom=currDenom/delta +1;
//				System.out.println("bnum new " + bestNum + " bdenom new " + bestDenom + " delta " + delta + " mdenom new " + minDenom);
			}
			currDenom--;
		}
//		System.out.println(bestNum + " "+ bestDenom + " delta " + delta);
		return bestNum;
	}


	public static long of(int n) {
		//		n=20;
		int le=0, ld=1, ce=0;
		for (int cd=1; cd<n;cd++) {
			System.out.print(" ce " + ce + " cd " + cd);
			if (ce*ld<=le*cd) {
				ce++;
				System.out.print(" enum too small for left ce: " + ce);
			} 
			if (7*ce<3*cd && ProjectEuler_Library.gcd(ce, cd)==1) {
				le=ce;
				ld=cd;
				//				ce++;
				System.out.print(" left changed: le " + le + " ld " + ld + " ce " + ce);
			} 
			System.out.println();
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( of_best(1000001,3,7) );
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

