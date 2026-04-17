/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0112_bouncy_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0112_bouncy_numbers() {
		// TODO Auto-generated constructor stub
	}


	public static long _bn(int n) {
		int cid=100,cb=1;
		int c=102;
		while (99*cid!=cb) {
			System.out.print(c + " ");
			int s = c;
			int d = s%10, nextd=d;
			while (s>0 && d==(nextd=s%10)) {
				s/=10;
			}
			System.out.print(" s " + s + " d " +d + " nextd "+ nextd);
			if (d==nextd) {
				cid++;
				System.out.println(" same digit number " );
			}
			else if (d > nextd) {
				System.out.print(" increasing ? " );
				while (s>0 && d>=nextd) {
					d=nextd; nextd=s%10;
					s/=10;
				}
				if (d < nextd) {
					System.out.println(" no, bouncy " );
					cb++;
				}
				else {
					System.out.println(" yes, increasing " );
					cid++;
				}
			} else if (d < nextd) {
				System.out.print(" decreasing ? " );
				while (s>0 && d<=nextd) {
					d=nextd; nextd=s%10;
					s/=10;
				}
				if (d > nextd) {
					System.out.println(" no, bouncy " );
					cb++;
				}
				else {
					System.out.println(" yes, decreasing " );
					cid++;
				}
			} else System.out.println("Problem " + c + " " + d + " " + nextd);
			c++;
		}
		return c;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _bn(10001) );
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
