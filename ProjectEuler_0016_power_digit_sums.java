/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0016_power_digit_sums {

	/**
	 * 
	 */
	public ProjectEuler_0016_power_digit_sums() {
		// TODO Auto-generated constructor stub
	}


	public static long ds(long n) {
		//		System.out.println("we go n "+ n);
		long ds=0;
		while (n>0) {
			ds+=n%10;
			n/=10;
			//			System.out.println(""+ n+ " "+ ds);
		}
		//		System.out.println("ds" +ds);
		return ds;
	}

	public static long pow(long n, int m) {
		while (m-->1)n*=n;
		long i= (long) Math.sqrt(m);
		return n;
	}



	public static long ds_2pow(int n) {
		int boud= 1+ (int) (Math.log10(2)*n)  ; 
		byte[] digits = new byte[boud];

		int last_digit=0;
		digits[0]=1;
		for(int i=0;i<n;i++) {
			byte carry=0, carriold=0;
			for (int j = 0; j < digits.length ; j++) {
				if( digits[j]>4 ) {
//					if (j==last_digit)
//						last_digit++;
					carry=1;
				}else carry=0;
				digits[j]=(byte)((2*digits[j])%10);
				if (carriold!=0)digits[j]++;
				carriold=carry;
				System.out.println(digits[j] +" "+j+ " ");
				if (j>=last_digit) {
					last_digit++;
				}
			}
		}
		int sum=0;
		for(int i=0;i<digits.length;i++) {
			sum+=digits[i];
		}	

		return sum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of the digits of the number 2^1000



		long startTime = System.nanoTime();
		System.out.println(ds_2pow(1000));
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
