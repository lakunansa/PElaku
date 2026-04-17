/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0092_square_digit_chains {

	/**
	 * 
	 */
	public ProjectEuler_0092_square_digit_chains() {
		// TODO Auto-generated constructor stub
	}


	public static long _9(int n) {
//		n=10;
		int count = 0;
		for (int i=2;i<=n;i++) {
			int copyI = i;
			while (copyI != 1 && copyI != 89) {
				int sum=0;
				while (copyI != 0) {
					int d=copyI%10;
					sum+=(d*d);
					copyI/=10;
				}
//				System.out.println(i + " " + sum);
				copyI=sum;
			}
			if (copyI==89) {
//				System.out.println(i);
				count++;
			}
		}
//		System.out.println();
				
		
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _9(10_000_000) );
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
