/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0038_pandigital_multiples {

	/**
	 * 
	 */
	public ProjectEuler_0038_pandigital_multiples() {
		// TODO Auto-generated constructor stub
	}


	public static long pm(int n) {
		long res=0;
		int[] size= {1,
				 	 10,
				 	 100,
				 	 1000,
				 	 10000,
				 	 100000,0};
		int digits=0;
		for (int i=1;i<9828;i++) {
			long sum=0;
			digits=0;
			for (int j=1;digits<9;j++) {
				int ij = i*j;
				int siz=(int)Math.floor(Math.log10(ij))+1;
				digits+=siz;
				sum=sum*size[siz]+ij;
//				System.out.println(sum+" "+digits + " "+ size[siz]);
			}
			if (digits==9);// System.out.println(sum + " "+ digits);
			else continue;
			long tmp = sum;
			boolean[] check = new boolean[10];
			while (!check[(int)(tmp%10)] && tmp%10>0) {
				check[(int)tmp%10]=true;
//				System.out.print(tmp+" "+check[mod]+" "+mod +" ");
				tmp/=10;
			}
			if (tmp==0) {
				res=sum;
				System.err.println(sum +" "+i+" "+tmp);
			}
		}
		return res;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the largest pandigital multiple, i.e. the largest concatenated multiples
		// of an integer with 1,...,n with n>1, that are pandigital 1..9


		long startTime = System.nanoTime();
		System.out.println( pm(10001) );
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
