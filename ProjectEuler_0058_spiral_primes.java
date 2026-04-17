/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0058_spiral_primes {

	/**
	 * 
	 */
	public ProjectEuler_0058_spiral_primes() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isPrime(long n) {
		if (n==1) return false;
		else if (n<4) return true; //2 and 3 are prime
		else if (n % 2 == 0) return false;
		else if (n<9) return true; //we have already excluded 4,6 and 8.
		else if (n % 3==0) return false;
		else {
			int r=(int)Math.sqrt(n ), f=5;
			while (f<=r) if (n % f==0) return false;
			else if (n % (f+2)==0) return false;
			else f+=6;
			return true;
		}
	}

	public static long sp(int n) {
		long count = 0;
		int sh = 0, shq=1;
		do {
			sh++; shq+=8*sh;
			for (int i=2; i<8; i+=2) {
				if (isPrime(shq - i*sh)) count++;
			}
		}while (n*count > 4*sh );
		return 2*sh+1;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the point where the diagonals in the spiral have less than 10%primes
		//
		//		5	4	3
		//		6	1	2
		//		7	8	9	10


		long startTime = System.nanoTime();
		System.out.println( sp(10) );
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
