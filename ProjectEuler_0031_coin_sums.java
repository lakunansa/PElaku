/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0031_coin_sums {

	/**
	 * 
	 */
	public ProjectEuler_0031_coin_sums() {
		// TODO Auto-generated constructor stub
	}

//needto add 2pound coin case (+1)
	public static long cs(int n) {
		int 	count=0; 
		for (int p100=0;p100<=n;p100+=100) { 
			int sum100=p100;
			for (int p50=0;p50<=n-sum100;p50+=50) {
				int sum50 = sum100	+ p50;
				for (int p20=0;p20<=n-p50;p20+=20) {
					int sum20=  sum50 + p20;
					for (int p10=0;p10<=n-sum20;p10+=10) {
						int sum10=sum20+p10;
						for (int p5=0;p5<=n-sum10;p5+=5) {
							int sum5=sum10+p5;
							for (int p2=0;p2<=n-sum5;p2+=2) {
								System.out.println(count+" "+p100+" "+p50+" "+p20+" "+p10+" "+p5+" "+p2+" "+(200-sum5-p2));
								count++;
							}
						}
						//													System.out.println();
					}
				}
			}						
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes How many different ways can £2 be made using any number of coins


		long startTime = System.nanoTime();
		System.out.println( cs(200) );
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
