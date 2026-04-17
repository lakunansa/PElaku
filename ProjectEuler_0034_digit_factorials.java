/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0034_digit_factorials {

	/**
	 * 
	 */
	public ProjectEuler_0034_digit_factorials() {
		// TODO Auto-generated constructor stub
	}

	public static long fac(int n) {
		long p=1;
//		if (n<=1) return 1;
		while (n>1) p*=n--;
		return p;
	}
	
	public static long df(int n) {
		//find maximal length
		int[] facs = {1,1,2,6,24,120,720,5040,40320,362880};
		int i,j;
		for (i=1, j=10; i*facs[9] >=j; i++, j*=10 ) {
//7 stellen
		}
		long sum=0;
		for (int k=3;k<2540161 ;k++) {
			long sumk=0;
			int tmp=k;
			while(tmp>0) {
				sumk+=facs[tmp%10];
				tmp/=10;
			}
			if (k==sumk) {
				System.out.println(k);
				sum+=sumk;
			}
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all numbers which are equal to the sum of the factorial of their digits.


		long startTime = System.nanoTime();
		System.out.println( df(0) );
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
