/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0041_pandigital_prime {

	/**
	 * 
	 */
	public ProjectEuler_0041_pandigital_prime() {
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

	
	public static long decrement (long tmp) {
		//doesnt check whether sigma is finisheshed
		long tmpLast = 10;
		long nextLast = tmp%10;
		long tens = 1;
		long reverseTail = 0; 
		while (nextLast < tmpLast) {
			reverseTail = 10*reverseTail + nextLast;
			tens*=10;
			tmpLast = nextLast;
			tmp/=10;
			nextLast = tmp%10;
		}
		if (tens==10) return ( (tmp/10)*10+tmpLast)*10+nextLast;
		tmpLast = nextLast;
		long tmpTens = tens;
		long lastDigitsOfreverseTail = 0;
		tens = 1;
		while (tens < tmpTens && reverseTail%10 < tmpLast) {
			nextLast = reverseTail%10;
			reverseTail /=10;
			lastDigitsOfreverseTail = tens*nextLast+lastDigitsOfreverseTail;
			tens *=10;
		}
		if (tens == 1) return reverseTail/10;
		return ((tmp/10)*10 + nextLast)*tmpTens+reverseTail*tens +tmpLast*(tens/10) 
				+ lastDigitsOfreverseTail%(tens/10);
	}


	public static long pp(long n) {
		while (!isPrime(n)) {
			System.out.println(n);
			n=decrement(n);
		}
		return n;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the he largest n-digit pandigital prime that exists


		long startTime = System.nanoTime();
		System.out.println( pp(7654321L) );
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
