/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0014_long_collatz {

	/**
	 * 
	 */
	public ProjectEuler_0014_long_collatz() {
		// TODO Auto-generated constructor stub
	}

	public static long loncoll(int n) {
		long l=999999;
		int count, maxcount=0;
		boolean[] b = new boolean[1000000];
		for (int i=999999;i>4;i-=2) {
			if (b[i])continue;
			long s=i;
			count = 0;
			while(s!=1) {
				if(s%2==1) s=3*s+1;else s/=2;
				count++;
				if (s<999999)b[(int)s]=true;
			}
			if (count>maxcount) {
				maxcount=count;
				l=i;
			}
		}
		return l;
	}
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes starting number below 1000000 with the longest chain


		long startTime = System.nanoTime();
		System.out.println( loncoll(100) );
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
