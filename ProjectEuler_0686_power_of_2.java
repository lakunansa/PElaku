/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0686_power_of_2 {

	/**
	 * 
	 */
	public ProjectEuler_0686_power_of_2() {
		// TODO Auto-generated constructor stub
	}


	public static long _po2(int n) {
		double lower = Math.log10(1.23), upper = Math.log10(1.24);
		double base = Math.log10(2);
//		System.out.println(base);
		
		int count=0;
		long pow=1;
		double add = base;
		while (count!=678910) {
			add+=base;
			pow++;
			if(pow%10000==0)System.out.println(pow + " " + count);
			if (add>1) add-=1;
			if (lower<=add && add<upper) count++;
		}
		
		
		return pow;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _po2(10001) );
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
