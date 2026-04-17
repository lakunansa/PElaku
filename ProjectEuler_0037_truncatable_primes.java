/**
 * 
 */
import java.util.PriorityQueue;
/**
 * 
 */
public class ProjectEuler_0037_truncatable_primes {

	/**
	 * 
	 */
	public ProjectEuler_0037_truncatable_primes() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isPrime(long n) {
		if (n<=1) return false;
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


	public static long tp(int n) {
		long sum=0;
		int[] primes={2,3,5,7};
		int[] attach= {1,3,7,9};
		PriorityQueue<Integer> pq= new PriorityQueue<Integer>();
		int[] size= {1,
					 10,
					 100,
					 1000,
					 10000,
					 100000,
					 1000000,
					 10000000,
					 100000000,
					 1000000000,0};
		for (int i = 0; i < 4; i++) {
			pq.add(primes[i]);
		}
		while (!pq.isEmpty()) {
			int ptmp=pq.poll();
//			System.out.println(ptmp);
			for (int i = 0; i < 4; i++) {
				int tmp=10*ptmp+attach[i];
				if (isPrime(tmp)) pq.add(tmp);
			}
			int tmps = (int)Math.floor(Math.log10(ptmp));
//			System.out.println(tmps);
			int tmpp=ptmp;
			while(isPrime(ptmp)) {
				ptmp%=size[tmps];
				tmps--;
//				System.out.println(ptmp);
			}
			if (ptmp==0) {
				System.out.println(tmpp);
				sum+=tmpp;
			}
		}
		return sum-17;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes sum of primes that are both left and right trancable and >10
		// there are exactly 11 such primes


		long startTime = System.nanoTime();
		System.out.println( tp(10001) );
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
