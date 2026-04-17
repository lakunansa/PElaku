/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0091_right_triangles_with_integer_coordinates {

	/**
	 * 
	 */
	public ProjectEuler_0091_right_triangles_with_integer_coordinates() {
		// TODO Auto-generated constructor stub
	}

	
	public static long _rtwic(int n) {
//		n=2;
		int count=0;
		//case 1-3: right angle is in origin or points on the axeis
		count += n*n;
		//case 4-5: hypothenouse is on the axis
//		count += 2*(n/2);
		//case 6: right angle is upper angle
		
		
		
		for (int x1=0;x1<=n;x1++)
			for (int y1=n;y1>=0;y1--)
				for (int x2=x1;x2<=n;x2++)
					for (int y2=y1;y2>=0;y2--) {
						if (!( x2==x1 && y2==y1 ) && !(x1==0 && y1==0) && !(x2==0 && y2==0) ) {
							if((x2-x1)*x1+(y2-y1)*y1==0) {
//								System.out.println("here");
								count++;
							}
							if((x2-x1)*x2+(y2-y1)*y2==0) {
//								System.out.println("there");
								count++;
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
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _rtwic(50) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/

	}

}
