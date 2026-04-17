/**
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 */
public class ProjectEuler_0072_counting_fractions {

	/**
	 * 
	 */
	public ProjectEuler_0072_counting_fractions() {
		// TODO Auto-generated constructor stub
	}

	public static long cf_best(int n) {
//		n=10;
		int limit = n+1;
		int sieveLimit = ((int)Math.floor(Math.sqrt(limit)) -1 )/2;
		int maxIndex = (limit-1)/2;
		int[] cache = new int[maxIndex+1];
		for (int i=1; i<=sieveLimit;i++)
			if (cache[i]==0) {
				int p=2*i+1;
				for (int j=2*i*(i+1); j<maxIndex;j+=p)
					if (cache[j]==0) cache[j]=p;
			}
		int multiplier = 1;
		while (multiplier<limit)
			multiplier*=2;
		multiplier /=2;
		long fractionCount = multiplier-1;
		multiplier/=2;
		int stepindex = (limit/multiplier +1)/2;
		for (int i=1; i<maxIndex;i++) {
			if (i==stepindex) {
				multiplier/=2;
				stepindex = (limit/multiplier +1)/2;
			}
			if (cache[i]==0) {
				cache[i]=2*i;
				fractionCount+=multiplier*cache[i];
			} else {
				int p=cache[i];
				int cofactor = (2*i+1)/p, factor;
				if (cofactor%p == 0) 
					factor=p;
				else factor = p-1;
				cache[i] = factor*cache[cofactor/2];
				fractionCount+=multiplier*cache[i];
			}
		}
//		for (int i = 0; i < cache.length; i++) {
//			System.out.println(cache[i]);
//		}
		return fractionCount;
	}
	
	public static long cf_yet_faster(int n) {
		//		n=20;
		int limit = n+1;
		int sieveLimit = (int)Math.floor(Math.sqrt(limit));
		int[] spf = new int[limit];
		for (int i=2;i<limit;i++) 
			if (i%2==0) spf[i]=2;
			else spf[i]=i;
		for (int i=3;i<=sieveLimit;i+=2) 
			if (spf[i]==i) 
				for (int j=i*i;j<limit;j+=2*i)
					if (spf[j]==j)
						spf[j]=i;
		int[] phis = new int[limit];
		for (int i=2;i<limit;i++)
			if (spf[i]==i)
				phis[i]=i-1;
			else {
				int p=spf[i];
				int m= i/p;
				int factor;
				if (spf[m]==p) 
					factor=p;
				else
					factor=p-1;
				phis[i]=factor*phis[m];
				//				System.out.println(p + " " + m + " " + factor);

			}
		long fractionCount = 0;
		for (int i=2; i<phis.length;i++) 
			fractionCount+=phis[i];
		//		for (int i=2; i<phis.length;i++) System.out.println(i + " " + phis[i] + " " + spf[i]);
		return fractionCount;
	}


	public static long cf_faster(int n) {
		int limit = n;
		long[] phis = new long[limit+1];
		for (int i=0; i<phis.length;i++) phis[i]=i;
		for (int i=2; i<phis.length;i++) 
			if (phis[i]==i) 
				for (int j=i; j<phis.length;j+=i) 
					phis[j]-=phis[j]/i;
		long fractionCount = 0;
		for (int i=2; i<phis.length;i++) 
			fractionCount+=phis[i];
		return fractionCount;
	}


	public static long cf(int n) {
		//		n=9;
		long sum = 0;
		for (int i=2;i<n;i++) {
			//			System.out.print(" n " + i);
			int m = i;
			int result = m;
			if (m%2==0) {
				result -= result/2;
				while (m%2==0) m/=2;
			}
			for (int j=3; j*j<=m; j+=2)
				if (m%j==0) {
					result -= result/j;
					while (m%j==0) m/=j;

				}
			if (m>1) result -=result/m;
			sum+=result;
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
//		System.out.println( cf(1000001) );
//		System.out.println( cf_faster(1000000) );
		System.out.println( cf_yet_faster(1000000) );
		System.out.println( cf_best(1000000) );
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
