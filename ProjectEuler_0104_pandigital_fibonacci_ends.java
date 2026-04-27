/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0104_pandigital_fibonacci_ends {

	/**
	 * 
	 */
	public ProjectEuler_0104_pandigital_fibonacci_ends() {
		// TODO Auto-generated constructor stub
	}


	public static long _pdf(int n) {
		double F1t=1d, F2t=1d, F1h=1, F2h=1;
		double tmpt, tmph;
		long count=2;
		boolean isHeadAndTail=false;
		while1: while (!isHeadAndTail) {
			count++;
			boolean[] tail = new boolean[10];
			boolean[] head = new boolean[10];
			tmpt=(F1t+F2t)%1_000_000_000d; F2t=F1t; F1t=tmpt; 
			tmph=(F1h+F2h); 
			if (tmph>=1_000_000_000) {
				tmph/=10; F1h/=10;
			}
			F2h=F1h; F1h=tmph;
			int tmpti = (int)tmpt;
			int tmphi = (int)Math.floor(tmph);
			for (int i=0;i<9;i++) {

				int dt=tmpti%10;
				if (!tail[dt] && dt>0) tail[dt]=true;
				else continue while1;
				tmpti/=10;
				
				int dh=tmphi%10;
				if (!head[dh] && dh>0) head[dh]=true;
				else continue while1;
				tmphi/=10;
			}
			isHeadAndTail=true;
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _pdf(10001) );
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
