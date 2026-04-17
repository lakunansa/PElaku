/**
 * 
 */
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0065_convergents_of_e {

	/**
	 * 
	 */
	public ProjectEuler_0065_convergents_of_e() {
		// TODO Auto-generated constructor stub
	}


	public static long coe(int n) {
		long count = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		for (int i = 1; 3*i<=101; i++) {
			list.add(1);
			list.add(2*i);
			list.add(1);
		}
		
		long a=1, b=0;
		BigInteger ba=BigInteger.ONE, bb=BigInteger.ZERO;
		while (!list.isEmpty()) {
			BigInteger last = BigInteger.valueOf(list.removeLast()), tmp = ba, ggt;
			
			ba=last.multiply(ba).add(bb);
			bb=tmp;
			ggt = ba.gcd(bb);
			ba=ba.divide(ggt);
			bb=bb.divide(ggt);
			System.out.println(ba + " " + bb);
		}
		while (!ba.equals(BigInteger.ZERO)) {
			count += ba.mod(BigInteger.TEN).longValue();
			ba = ba.divide(BigInteger.TEN);
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
		System.out.println( coe(10001) );
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
