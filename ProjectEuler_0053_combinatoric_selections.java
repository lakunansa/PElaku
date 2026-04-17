/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0053_combinatoric_selections {

	/**
	 * 
	 */
	public ProjectEuler_0053_combinatoric_selections() {
		// TODO Auto-generated constructor stub
	}


	public static long cs(int n) {
		long sum = 0;
		sum += -6 + 81*41; //pyramide mit 0 (20) als spitze bis 100 (80) 
		long ind = (10*1144066)/14; //von 23-10 auf 23-9, <mio //1144066 -> 817190
		long j=9;
		for (long i=24;i<=100;i++) { 
			ind = (ind*i)/(i-j);	//von 23-9 auf 24-9, <mio //817190 -> 1307504
			System.out.println(ind +" "+ j+" "+i);
			if (ind >= 1000000) {
				sum+=2*(100-i+1);
				ind = (j*ind)/(i-j+1); //von 24-9 auf 24-8 // 1307504 -> 735471
				j--;
				System.err.println(ind +" "+ j+" "+i);
			}
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of binomcoeffs n<=100 that are >1 000 000


		long startTime = System.nanoTime();
		System.out.println( cs(10001) );
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
