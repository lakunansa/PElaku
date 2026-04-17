/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0010_sum_of_primes {

	/**
	 * 
	 */
	public ProjectEuler_0010_sum_of_primes() {
		// TODO Auto-generated constructor stub
	}


	
	public static long sum_sieve(int n) {
		long sum = 2;

		int sieve_bound = ( n -1 )/2;
		boolean[] indexed_sieve = new boolean[sieve_bound + 1];
		int sqrtbound = ( ( (int) Math.sqrt(n) )-1 )/2;
		for (int i=1;i<=sqrtbound;i++)
			if(!indexed_sieve[i])
				for (int j=2*i*(i+1);j<=sieve_bound && j>=0;j+=2*i+1)
					indexed_sieve[j]=true;
		for (int i=1;i<=sieve_bound;i++)
			if(!indexed_sieve[i])sum+=2*i+1;
		
		
		
		
//		boolean[] sieve = new boolean[n+1];
//
//		
//		int sqn = (int) Math.sqrt(n);
//		for (int i=4;i<=n;i+=2) sieve[i]=true;
//
//		for (int i=3;i<=sqn;i+=2) 
//			if (!sieve[i])
//				for (int j=i*i;j<=n;j+=2*i) sieve[j]=true;
//		for (int i=2;i<=n;i++)
//			if (!sieve[i]) sum+=i;
		

//		for (int i=2;i<n+1;i++)
//			if (!sieve[i]) {
//				sum +=i;
//				for (int j=2*i;j<n+1;j+=i)
//					if (!sieve[j])sieve[j]=true;
//			}
		
		return sum;
		
		
		
	}
	
	
	//faster
	public static long sum_s(int n) {
//		n=100;
		long count=2; //we know that 2 is prime
		long candidate=3;
		do {
//			System.out.println("c:" +candidate);
			if (ProjectEuler_0007_10001st_prime.isPrime(candidate)) {
				count+=candidate;
//				System.out.println(candidate + " is prime,count "+count);
			}
			candidate+=2;
		} while (candidate < n);
		return count;
	}
	
	
	//too slow
	public static long summation(int n) {
		long sum = 2, curr = 3; 
		int iter=1;
		
		while (curr<n) {
			sum += curr;
			iter++;
			curr = ProjectEuler_0007_10001st_prime.prime(iter);
			if (iter % 1000 ==0) System.out.println(curr);
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of primes below n


		long startTime = System.nanoTime();
		System.out.println( sum_sieve(2000000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

//		boolean[] a= new boolean[2]; System.out.println(a[0]);
		
//		startTime = System.nanoTime();
//		System.out.println( prime_s(10001) );
//		endTime = System.nanoTime();
//		duration = (endTime - startTime);
//		System.out.println(duration/1000000);


	}

}
