/**
 * 
 */

/**
 * lexicographic permutations
 * what is the  millions lexicographic permutation of <10>?
 * 
 */
public class ProjectEuler_0024_lexicographic_permutation {

	/**
	 * 
	 */
	public ProjectEuler_0024_lexicographic_permutation() {
		// TODO Auto-generated constructor stub
	}

	public static long fac(int n) {
		if (n<=0)return 1;
		long res=1;
		while (n>1) {
			res*=n--;
		}
		return res;
	}

	public static long findNumber(long s, long test){
		if (s==0) {
			return test;
		}
		long count, countmax=0, test2;
		do{
			test2 = test+countmax;
			count=0;
			long tmp = s;
			while (tmp>0) {
				long digit=tmp%10-1;
				tmp/=10;
				if (digit <= test2) {
					count++;
				}
			}
			if (count > countmax) {
				countmax=count;
			}
			else {
				break;
			}
		}while (true);
	return test2;
}
	
	

public static long lpm(int n, int m) {
	//n is the nth permutation
	n-=1;
	long s=0, div, mod=n;
	if (m==10) {
		//finde heraus, was die erste zahl ist
		//alle zahlen in der Teillösung mod9!, 
		//die kleiner gleich dieser Zahl sind: um 1 verringern...
	}
	for (int i=0;i<m;i++) {
		long pdiv = fac(m-i-1);
		div = mod/pdiv;
		mod = mod%pdiv;
		s*=10;
		s+=findNumber(s/10, div)+1;
		
		
	}
	//gibt die permutation +1 aus
	return s;
}




/**
 * @param args
 */
public static void main(String[] args) {
	// TODO Auto-generated method stub
	//computes  the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9


	long startTime = System.nanoTime();
//	for (int i=1; i<=fac(9);i++)
	System.out.println( lpm(1000000,10) );
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
