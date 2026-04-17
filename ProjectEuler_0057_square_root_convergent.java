/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0057_square_root_convergent {

	/**
	 * 
	 */
	public ProjectEuler_0057_square_root_convergent() {
		// TODO Auto-generated constructor stub
	}


	public static long sqc(int n) {
		int count = 0;
		//prelims
		ArrayList<Byte> a = new ArrayList<Byte>(0);
		a.add((byte)0);
		ArrayList<Byte> b = new ArrayList<Byte>(0);
		b.add((byte)1);
		//create numbers
		for (int i=1; i<=n; i++) {
			//new b = 2b+a
			byte carry=0;
			for (int j=0;j<b.size();j++) {
				carry += (byte)(2*b.get(j) + a.get(j)); 
				b.set(j,(byte)(carry%10));
				carry/=10;
			}
			while (carry>0) {
				b.add((byte)(carry%10));
				a.add((byte)0);
				carry/=10;
			}
			//new a = ( newb-a )/2
			for (int j=0;j<b.size();j++) {
				carry += (byte)( b.get(j)-a.get(j) );
				if (carry>=0) {
					a.set(j, (byte) (carry%10));
					carry/=10;
				}
				else {
					a.set(j, (byte) (carry%10+10));
					carry=(byte) (carry/10-1);
				}
			}
			for (int j=0;j<b.size();j++) {
				if (a.get(j)%2==1)
					a.set(j-1, (byte) (a.get(j-1)+5) );
					a.set(j, (byte) (a.get(j)/2));
			}
			for (int j=0;j<b.size();j++) {
				carry += (byte)(b.get(j) + a.get(j)); 
				carry/=10;
			}
//			System.out.println("start " + i);
//			for (int j=0;j<b.size();j++) {
//				System.out.print(a.get(j));
//			}System.out.println();
//			for (int j=0;j<b.size();j++) {
//				System.out.print(b.get(j));
//			}System.out.println(" carry " + carry);
			if (carry >0) count++;
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of times, where the first 1000 partial continued fractions of sq2 
		//have more digits in the enumerator than in the denominator 

		long startTime = System.nanoTime();
		System.out.println( sqc(1000) );
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
