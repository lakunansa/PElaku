/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0012_highly_divisible_triangle_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0012_highly_divisible_triangle_numbers() {
		// TODO Auto-generated constructor stub
	}


	public static long divisors(int n) {
		int step = 2;
		long tn = 1;
		int count=1, divisors=0;
		//		bedingung festhalten
		while(count<n) {
			count=1;
			tn += step++;
//			System.out.println("hier "+tn+" "+ count+ " " +divisors);
			long copy=tn;
			long target = (long)Math.sqrt(copy);
			//teiler von tn itererieren
			for (int i=2;i<=copy;i++) {
//				System.out.println(i+" dort "+copy+" "+ count+ " " +divisors);
				
				//				teiler rausdividieren
				if (copy%i == 0) {
					while (copy%i == 0) {
						copy /= i;
						divisors++;
//						System.out.println(i+" dort3 "+copy+" "+ count+ " " +divisors);
					};
					count*=(divisors+1);
//					target = (long)Math.sqrt(copy);
//					System.out.println(i+" dort4 "+copy+" "+ count+ " " +divisors);
					
				}
				divisors=0; 
			}
		}
		return tn;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the triangel number with more than 500divisors

		long startTime = System.nanoTime();
		System.out.println( divisors(500) );
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
