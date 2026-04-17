/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0023_non_abundant_sums {

	/**
	 * 
	 * 28132
	 * sum of all numbers that cannot be written as two abundant numbers
	 * 
	 */
	public ProjectEuler_0023_non_abundant_sums() {
		// TODO Auto-generated constructor stub
	}

	public static int sopd (int m) {
		int s=1, p=2,tmp=m;
		while (p*p<=m && m>1) {
			if (m%p==0) {
				int j=p*p;
				m/=p;
				while(m%p==0) {
					j*=p;
					m/=p;
				}
				s*=(j-1);
				s/=(p-1);
			}
			if (p==2) p=3; else p+=2;
		}
		if(m>1) s*=(m+1);
		return s-tmp;
	}



	public static long nas() {
		int s=0;
		for (int i=1;i<=29000;i+=1) {
			boolean can = false;
			for (int j = 12; (j<=i/2) && !can; j++) {
				if ( (j < sopd(j)) && (i-j < sopd(i-j) ) ) {
//					System.out.println(i+" "+j);
//					if (j>40) System.err.println(j);
					can=true;
				}
			}
			if (!can) {
				s+=i;
				System.out.println(i + " "+ s);
			}
		}
		return s;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all the positive integers which cannot be written as the sum of two abundant numbers


		long startTime = System.nanoTime();
		System.out.println( nas() );
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
