/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0019_counting_sundays {

	/**
	 * 
	 */
	public ProjectEuler_0019_counting_sundays() {
		// TODO Auto-generated constructor stub
	}

	static byte day=1, month=1, leapyear=0, weekday=2;
	static int year=1901;


	public static void increment() {
//		System.out.println(day+ " "+month+ " "+year+ " "+weekday);
		switch (month) {
		case 12:
			if (day==31) {
				year++;
				month=1;
				day=1;
				if (year%4==0 && year!=100 ) leapyear=1;
				else leapyear=0;
			}else {
				day++;
			}
			break;
		case 1,3,5,7,8,10:
			if (day==31) {
				month++;
				day=1;
			}else {
				day++;
			}
		break;
		case 4,6,9,11:
			if (day==30) {
				day=1;
				month++;
			}else {
				day++;
			}
		break;
		case 2:
			if (day==28+leapyear) {
				System.out.println(day+ " "+month+ " "+year+ " "+weekday+leapyear);
				day=1;
				month++;

			}else {
				day++;
			}
			break;
		}
		weekday++;
		if (weekday==8)weekday=1;
	}
	public static long cs() {
		int sundaycount=0;

		do{
			if (weekday==7 && day==1) {
				sundaycount++;
//				System.out.println(sundaycount);
			}
			increment();
		}		while(day!=1 || month !=1 || year!=2001);
		return sundaycount;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes How many Sundays fell on the first of the month during the twentieth century


		long startTime = System.nanoTime();
		System.out.println( cs() );
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
