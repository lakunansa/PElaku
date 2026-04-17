/**
 * 
 */

import java.util.Arrays;

/**
 * 
 */
public class ProjectEuler_0095_amicable_chains {

	/**
	 * 
	 */
	public ProjectEuler_0095_amicable_chains() {
		// TODO Auto-generated constructor stub
	}

	public static boolean[] listPrimality(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Negative array size");
		boolean[] result = new boolean[n + 1];
		if (n >= 2)
			result[2] = true;
		for (int i = 3; i <= n; i += 2)
			result[i] = true;
		// Sieve of Eratosthenes
		for (int i = 3, end = (int)Math.sqrt(n); i <= end; i += 2) {
			if (result[i]) {
				// Note: i * i does not overflow
				for (int j = i * i, inc = i * 2, bound = Integer.MAX_VALUE - inc; j <= n; j += inc) {
					result[j] = false;
					if (j > bound)
						break;
				}
			}
		}
		return result;
	}


	public static long _acfast(int n) {
//		n=1000;
		int[] next = new int[n+1];
		Arrays.fill(next,1);
		next[0]=0; next[1]=0;
		boolean[] isPrime = listPrimality(n);
		for (int i=2; i<n; i++) { //build next
			if (!isPrime[i]) {
				next[i]-=i;
//				System.out.println(i + " " + next[i]);
				continue;
			}
			for (int pow = i; 1<=pow && pow<= n; pow*=i) {
//				System.out.println(i+" "+pow);
				for (int j=1; j*pow<= n;  j+=( (j+1)%i== 0? 2:1 ) ){
//					System.out.println(i+" "+pow + " " + j + " " + (int)(((long)pow*i-1)/(i-1)));
					next[j*pow]*= (int)(((long)pow*i-1)/(i-1));
				}
				
			}
			next[i]-=i;
//			System.out.println(i + " " + next[i]);
		}
		int smallest=n, longest=0; 
		
		for (int i=2; i<n; i++) { //cycle
			int l=next[i];
			int cycle = 1;
			while (l>i && l<n && cycle <n) {
				l=next[l];
				cycle++;
			}
			if (l==i && longest < cycle) {
				longest = cycle;
				smallest=l;
			}
		}
		System.out.println("sammlest " + "cycl " + longest);
		return smallest;
	}

	public static long _ac(int n) {
		int longest=0;//longest chain
		int smallest=-1;//smallest member of longest chain
		boolean[] isCheck = new boolean[n+1];
		isCheck[0]=true; isCheck[1]=true;
		boolean[] isHit = new boolean[n+1];
		isHit[0]=true;
		boolean[] canSkip = new boolean[n+1];
		canSkip[0]=true; canSkip[1]=true;
		int[] next = new int[n+1];
		next[0]=0; next[1]=0;
		boolean[] isPrime = listPrimality(n);
		for1: for (int i=1;i<=n;i++) {
			//			System.out.println("i=" + i);
			if (  isCheck[i] ) continue for1; //if already checked go on
			if ( isPrime[i] ) { //if is Prime go on
				canSkip[i] = true;
				isCheck[i] = true;
				next[i] = 1;
				continue;
			}

			int j=i;
			int cycle=0;
			while (j<=n && !isCheck[j]) { //goes through sequence, ends if j is already check or exceeds n
				cycle++;
				isCheck[j] = true;
				int k=j;
				int count = 0; //first check mults of 2
				//				System.out.println("div by two " + count);
				while (j%2==0) {
					count++;
					j/=2;
				}
				long sumdiv = (long)(Math.pow(2, count+1)-1);
				int cur=3;
				while (j>1) {
					//					System.out.println("div by cur " + cur + " "+ count);
					while ( !( isPrime[cur] && j%cur==0 ) ) cur+=2;
					count = 0;
					while (j%cur==0) {
						count++;
						j/=cur;
					}
					sumdiv *= (long)(Math.pow(cur, count+1)-1)/(cur-1);
				}
				j=(int)sumdiv-k;
				next[k]=j;
				if (j<=n) {
					isHit[j] = true;
					if (isPrime[j] ) {
						canSkip[i] = true;
						canSkip[k] = true;
						canSkip[j] = true;
						isCheck[j] = true;
						next[j]=1;
					} else if (j<i) {
						canSkip[i] = true;
						canSkip[k] = true;
						canSkip[j] = true;
					} else if (j==i && cycle>longest) {
						longest = cycle;
						smallest = i;
					}
				} else {
					canSkip[i] = true;
					canSkip[k] = true;
				}
			}
		}
		for (int i=2;i<n;i++) {
			if(canSkip[i])continue;
			int l=next[i], cycle=1; 
			while (l<n && l>i && cycle < n) {
				l=next[l];
				cycle++;
			}
			if (l==i && cycle > longest) {
				longest = cycle;
				smallest = i;
			}
		}
		return smallest;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _acfast(1_000_000) );
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
