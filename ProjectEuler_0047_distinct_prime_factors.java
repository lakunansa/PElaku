/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0047_distinct_prime_factors {

	/**
	 * 
	 */
	public ProjectEuler_0047_distinct_prime_factors() {
		// TODO Auto-generated constructor stub
	}

	public static long dpf(int n) {
		int[] numbers = new int[n+1];
		for (int i=1; i<=n;i++) numbers[i]=i;
		int[] indexed_sieve = new int[n+1];
		int sqrtbound = ( ( (int) Math.sqrt(n) ) );
		int count=0;
		for (int i=2;i<=n;i++) {
			if (indexed_sieve[i]!=3) count = 0;
			else count++;
//			System.out.println(i + " " + indexed_sieve[i] + " "+ numbers[i] + " " + count);
			if (count==4) return i;
			if(indexed_sieve[i]==0 && numbers[i]>1)
				for (int j=2*i;j<=n;j+=i) {
					do {
						numbers[j]/=i;
					}while (numbers[j]%i==0);
					if (numbers[j]>1) indexed_sieve[j]++;
				}
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes smallest quadruple of consec naturals that have 4 distinct prime facs each


		long startTime = System.nanoTime();
		System.out.println( dpf(134050) ); //134046
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
