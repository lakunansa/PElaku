/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0046_goldbachs_other_conjecture {

	/**
	 * 
	 */
	public ProjectEuler_0046_goldbachs_other_conjecture() {
		// TODO Auto-generated constructor stub
	}


	public static boolean[] sieve(int n) {
		int sieve_bound = ( n -1 )/2;
		boolean[] indexed_sieve = new boolean[sieve_bound + 1];
		int sqrtbound = ( ( (int) Math.sqrt(n) )-1 )/2;
		for (int i=1;i<=sqrtbound;i++)
			if(!indexed_sieve[i])
				for (int j=2*i*(i+1);j<=sieve_bound && j>=0;j+=2*i+1)
					indexed_sieve[j]=true;
//		for (int i=1;i<indexed_sieve.length;i++) System.out.println(i + " "+ (2*i+1) + " " + indexed_sieve[i]);

		return indexed_sieve;
	}

	public static boolean[] squares(int n) {
		boolean[] squares = new boolean[n+1];
		for (int i=1;i<=Math.sqrt(n);i++) squares[i*i] = true; 
//		for (int i=1;i<squares.length;i++) System.out.println(i + " " + squares[i]);
		return squares;
	}


	public static long goc(int n) {
		boolean[] primes = sieve(n); //odd number = index * 2 +1
		boolean[] squares = squares(n); //index as-is
		boolean check = false;
		for (int i=9; i<n; i+=2) {
			check = false;
			if (primes[(i-1)/2]) {
				for (int j=3;j<i;j+=2) {
					if (!primes[(j-1)/2]) {
						if (squares[(i-j)/2]) {
//							System.out.println(i + " "+ j);
							check=true;
							j=n;
						}else continue;
					}else continue;
				}
			}else continue;
			if(!check) return i;
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the smallest odd composite that is not sum of prime and 2*square


		long startTime = System.nanoTime();
		System.out.println( goc(10000) );
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
