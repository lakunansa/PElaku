/**
 * 
 */
import java.util.LinkedList;

/**
 * 
 */
public class ProjectEuler_0051_prime_digit_replacement {

	/**
	 * 
	 */
	public ProjectEuler_0051_prime_digit_replacement() {
		// TODO Auto-generated constructor stub
	}

	public static boolean[] sum_sieve(int n) {
		int sieve_bound = ( n -1 )/2;
		boolean[] indexed_sieve = new boolean[sieve_bound + 1];
		int sqrtbound = ( ( (int) Math.sqrt(n) )-1 )/2;
		for (int i=1;i<=sqrtbound;i++)
			if(!indexed_sieve[i])
				for (int j=2*i*(i+1);j<=sieve_bound && j>=0;j+=2*i+1)
					indexed_sieve[j]=true;
//		for (int i=500000;i<=sieve_bound;i++)
//			if(!indexed_sieve[i])System.out.println(2*i+1);;
		return indexed_sieve;
	}

	public static int[] tens = {
			1,
			10,
			100,
			1000,
			10000,
			100000,
			1000000,
			10000000,
			100000000
	};
	
	public static long pdr(int n) {
		boolean[] nums = sum_sieve(n);
		for (int i=500; i<nums.length;i++) {
			if (!nums[i]) {
				int j=2*i+1, tmp=j;
				LinkedList<Byte>[] mults = new LinkedList[10];
				byte dig;
				for (dig=0;dig<10;dig++) mults[dig]=new LinkedList<Byte>();
				byte count = 0;
				while (tmp>0) {
					dig = (byte)(tmp%10);
					mults[dig].add(count);
					if (mults[dig].size()==3) {
						tmp=0;
						System.out.println("here "+j);
					}
					tmp/=10;
					count++;
				}
				int tmp2, count2=0;
				for (tmp=0,count=0, tmp2=dig;mults[dig].size()==3 && tmp<10;tmp++ ) {
//					System.out.print(j);
					for (byte a:mults[dig]) {
						j+=(tmp-tmp2)*tens[a];
					}
					tmp2=tmp;
//					System.out.println(" "+j);
					if (j>0 &&!nums[(j-1)/2] && ( (int)Math.log10(2*i+1)==(int)Math.log10(j) ) ) {
						count++;
						count2=count2*10+tmp;
						System.out.println(j + " " +nums[(j-1)/2]);
//						nums[(j-1)/2]=true;
					}
					if (count > 7) {
						System.out.println("there " + count2);
						return j;
					}

				}
			}
		}
		return -1;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the smallest family of eight primes 
		//that share certain digits, while the others are some number


		long startTime = System.nanoTime();
		System.out.println( pdr(10000000) );
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
