/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0145_reversible_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0145_reversible_numbers() {
		// TODO Auto-generated constructor stub
	}

	public static long reverse (long n) {
		//reverses number
		long reverse=0;
		while (n>0) {
			reverse= 10*reverse + n%10; 
			n /= 10;
		}
		return reverse;
	}

	/**
	 * can be computed for number of digits n
	 * n=2k: 20*30^k
	 * n=4k+1: keine lösung
	 * n=4k+3: 100*500^k
	 * @param n
	 * @return
	 */
	public static long _rn(int n) {
		long count=0;
		n++;
		for1:for (long i = 0; i < n; i++) {
			if (i%10==0)continue;
//			if(i%10_000_000 ==1)System.out.println(i);
			long j= i+reverse(i);
			while (j>0)
				if (j%2==1) {
					j/=10;
				}
				else continue for1;
			count++;
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
		System.out.println( _rn(1_000_000_000) );
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
