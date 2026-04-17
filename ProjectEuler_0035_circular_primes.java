/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0035_circular_primes {

	/**
	 * 
	 */
	public ProjectEuler_0035_circular_primes() {
		// TODO Auto-generated constructor stub
	}

	public static int rotate(int n, int digits) {//digits in form of power of 10
		return n/digits + n%digits*10;
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
	
	public static long cp(int n) {
		int digits=1, ones=1, count=0;
		for (int i=1;i<n;digits*=10, ones=digits+1, i++);
		for (int i=ones;i<digits*10;i++) {
			int tmp = i;
			if (!isPrime(tmp))continue;
			else {
				tmp=rotate(tmp,digits);
				while (isPrime(tmp) && tmp!=i)
					tmp=rotate(tmp,digits);
				if (tmp==i) {
					System.out.println(i);
					count++;
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
		//computes How many circular primes are there below one million


		long startTime = System.nanoTime();
		System.out.println( cp(6) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

//		startTime = System.nanoTime();
//		System.out.println( prime_s(10001) );
//		endTime = System.nanoTime();
//		duration = (endTime - startTime);
//		System.out.println(duration/1000000);
// 13 below 100
// after that: 12 below 1000 
// after that: 8 below 10000 
// after that: 10 below 100000 
// after that: 12 below 1000000 
	}
}
