/**
 * 
 */
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * 
 */
public class ProjectEuler_0077_prime_partitions {

	/**
	 * 
	 */
	public ProjectEuler_0077_prime_partitions() {
		// TODO Auto-generated constructor stub
	}


	public static long ps(int n) {
//		n=100;
		int[] primes;
		{
			ListIterator <Integer> lit= ProjectEuler_Library.primesUpToSqrtN(4*n*n);
			ArrayList<Integer> lis = new ArrayList<Integer>();
			int next;
			while( (next=lit.next()) < n) {
				lis.add(next);
			} lis.add(next);
			int numPrimes=lis.size();
			primes = new int[numPrimes];
			int count=0;
			for (int i:lis) {
				primes[count++]=i;
			}
		}
		long[][] eval = new long[n+1][primes.length];
		int num=0, maxp=primes.length-1;
		for1:for (int sum=2;sum<primes[maxp];sum++) {
			for2:for (int p=0;p<maxp; p++) {
				if (p==0) eval[sum][p]=(sum+1)%2;
				else if (sum==primes[p]) eval[sum][p] = 1 + eval[sum][p-1];
				else if ( primes[p] > sum ) eval[sum][p]=eval[sum][p-1];
				else eval[sum][p] = eval[sum-primes[p]][p] + eval[sum][p-1];
			}
//			System.out.println(eval[sum][maxp-1]);
			if (eval[sum][maxp-1]>n) {
				num=sum;
				break for1;
			}
		}
		for (int sum=2;sum<=num;sum++){
			System.out.print("sum "+sum+": ");
			for (int p=0;primes[p]<sum; p++)
				System.out.print( eval[sum][p] + " ");
			System.out.println();
		}
		return num;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( ps(5000) );
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
