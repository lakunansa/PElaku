/**
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * 
 */
public class ProjectEuler_0073_counting_fractions_in_range {

	/**
	 * 
	 */
	public ProjectEuler_0073_counting_fractions_in_range() {
		// TODO Auto-generated constructor stub
	}

	public static long cfir_sublinear(int n) {

		int N=n, K=(int)Math.floor(Math.sqrt(((double)N)/2d)), M=N/(2*K+1);
		int[] rsmall = new int[M+1], rlarge = new int[K];
		long count=0;
		for (int i=5;i<=M;i++) R(i, rsmall, rlarge, M, N);
		for (int i=K-1;i>=0;i--) R(N/(2*i+1), rsmall, rlarge, M, N);
		count=rlarge[0];
		return count;
	}


	public static long cfir_inclusionExclusion(int n, int index, ArrayList<Integer> l) {
		int q=n/6, r=n%6,p;
		long count=q*(3*q-2+r)+((r==5)?1:0);
		while (index<l.size() && 5*(p=l.get(index))<=n) {
			int newN= n/p;
			count-=cfir_inclusionExclusion(newN,index+1,l);
			index++;
		}
		return count;
	}

	public static long cfir_faster(int n) {
		long count =0;
		int top = 0;
		int[] stack = new int[n/2+1];
		int left = 3;
		int right = 2;
		while (true) {
			int med = left+right;
			if (med>n)
				if (top >0) {
					left=right;
					top--;
					right = stack[top];
				}
				else break;
			else {
				count++;
				stack[top]=right;
				top++;
				right=med;
			}
		}
		return count;
	}

	public static long cfir_SternBrocotTree(int n, int leftN, int leftD, int rightN, int rightD){
		//		n=10;
		int medN=leftN+rightN;
		int medD=leftD+rightD;
		if (medD>n)
			return 0;
		else {
			return  1 + cfir_SternBrocotTree(n, leftN, leftD, medN, medD)+cfir_SternBrocotTree(n, medN, medD, rightN, rightD);
		}
	}

	public static long cfir_farey(int n) {
		//		n=10;
		int limit = n;
		long a=1, b=3, c0=1, d0=2, k=(limit-d0)/b, c=c0+k*a, d=d0+k*b, count=0;
		while (! (c == 1 && d == 2)) {
			count++;
			k=(limit+b)/d;
			long e=k*c-a, f=k*d-b;
			a=c;
			b=d;
			c=e;
			d=f;
		}
		return count;
	}

	public static long cfir_straightforward(int n) {
		//		n=10;
		int limit = n;
		long count =0;
		for (n = 5; n<=limit;n++) 
			for (int k = n/3 +1; k<=(n-1)/2;k++) 
				if (gcd(n,k)==1) count++;
		return count;
	}

	public static long cfir(int n) {
		//		n=8;
		int count=0;
		int mine=1, maxe=1;
		for (int cd=4; cd<n;cd++) {
			//			System.out.print(" cd " + cd + " mine " + mine + " maxe " + maxe);
			if (2*maxe<cd) {
				maxe++;
				//				System.out.print(" augmenting maxe: " + maxe);
			} 
			for (int ce=mine; ce<=maxe; ce++) {
				//				System.out.print(" ce " + ce);
				if (ProjectEuler_Library.gcd(ce, cd)>1) {
					//					System.out.print(" (ce,cd) != 1 " + ce + " " + cd);
					continue;
				}
				if (3*ce<cd) {
					mine++;
					//					System.out.print(" augmenting mine: " + mine);
					continue;
				} 
				if (2*ce>cd) {
					//					System.out.print(" ce too high: " + ce);
					continue;
				} 
				//				System.out.print(" adding " + ce + " " + cd);
				count++;
			}
			//			System.out.println();
		}
		return count;
	}


	public static long gcd(int a, int b) {
		int r=a%b;
		while (!(r==0)) {
			a=b;
			b=r;
			r=a%b;
		}
		return b;
	}

	public static ArrayList<Integer> primesUpToSqrtN(int n){
		//		n=100;
		int lim = (int)Math.floor(Math.sqrt(n));
		boolean[] mark = new boolean[lim+1];
		ArrayList<Integer> primes = new ArrayList<Integer>();
		int i;
		primes.add(i=2);
		for (int j = i * i; j <= lim; j += i) mark[j] = true;
		for (i++; i<=lim; i+=2) {
			if(!mark[i]) {
				primes.add(i);
				for (int j = i * i; j <= lim; j += i) mark[j] = true;
			}
		}
		//		for (Integer j:primes) System.out.println(j);
		return primes;
	}

	public static long F(int n) {
		int q=n/6, r=n%6;
		return q*(3*q-2+r)+((r==5)?1:0); 
	}
	public static void R(int n, int[] rsmall, int[] rlarge, int M, int N) {
		int swtch = (int)Math.floor(Math.sqrt(((double)n)/2d));
		int count = (int)F(n);
		count -= F(n/2);
		int m=5;
		int k=(n-5)/10;
		while (k>=swtch) {
			int nextk= (n/(m+1)-1)/2;
			count -= (k-nextk)*rsmall[m];
			k=nextk;
			m++;
		}
		while (k>0) {
			m=n/(2*k+1);
			if (m<=M) 
				count -= rsmall[m];
			else 
				count -= rlarge[(N/m - 1)/2];
			k--;
		}
		if (n<=M) rsmall[n]=count;
		else rlarge[(N/n - 1)/2] = count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime
		int n=1200;
		{
			long startTime = System.nanoTime();
			System.out.println( cfir_sublinear(n) );
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration/1000000);
		}
		{
			long startTime = System.nanoTime();
			ArrayList<Integer> l=primesUpToSqrtN(n*n);
			System.out.println( cfir_inclusionExclusion(n,0,l) );
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration/1000000);

		}

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/1000000);


	}

}
