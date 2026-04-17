/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0040_champernownes_constant {

	/**
	 * 
	 */
	public ProjectEuler_0040_champernownes_constant() {
		// TODO Auto-generated constructor stub
	}


	public static int delta10(int n) {
		//computes the number of digits between period and
		//the first contribution of a n-digit number
		int sum=0;
		for (int i=1,tens=1;i<n;i++, tens*=10) sum+=i*tens;
		return 9*sum;
	}

	public static int delta10simpler(int n) {
		int tens=1;
		for (int i=1;i<n;i++, tens*=10);
		return (1+tens*(9*n-10))/9;
	}

	
	public static int digit(int n) {
		//computes the number of digits
		int digits=1;
		while (delta10simpler(digits+1)<n) {
			digits++;
		}
		return digits;
	}
	
	public static int num(int n) {
		//computes the number of digits
		int[] tens = {1,10,100,1000,10000,100000,1000000,10000000};
		int corr= digit(n);
		int rest=n-1-delta10simpler(corr);
		int num=rest/corr+tens[corr-1];
		int mod = corr-1-rest%corr;
		System.out.print(" n: "+n+" corr: "+corr+" rest: "+ rest+" num: "+num+" mod:  "+mod+" ");
		while (mod-->0) {
			num/=10;
		}
		return num%10;
	}
	

	
	
	
	public static long cc(int n) {
		long[] start = new long[7];
		for (int i=1;i<7;i++) {
			start[i]=delta10simpler(i);
//			System.out.println(start[i]);
		}
		long prod=1;
		for (int i=1;i<10000000; i*=10) {
//			sum+=num(i);
			System.out.println(" "+(prod*=num(i)));
		}
		return prod;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the product of certains digits of champernownes constant


		long startTime = System.nanoTime();
		System.out.println( cc(10001) );
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
