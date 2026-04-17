/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0009ki_special_pytha_triplet {

	/**
	 * 
	 */
	public ProjectEuler_0009ki_special_pytha_triplet() {
		// TODO Auto-generated constructor stub
	}


	public static long pytha(int n) {
		for (int c=997; c>/*334*/ 334 ; c--) 
			for (int b=(1000-c)/2+1;b<(1000-c)&&b<c;b++) {
				System.out.println(""+(1000-c-b) + " "+ b + " " + c);
				if ( (1000-c-b)*(1000-c-b) + b*b == c*c )
					return b*c*(1000-c-b);
			}
		return 0;
	}


	public static long gcd(int a, int b) { 
//		if(b==0)System.out.println(a+ " "+b);

		return b==0 ? a : gcd(b, a%b); 
		}


	public static long pytha_exam(int n) {
		int n2=n/2;
		int mmax = (int)( Math.ceil(Math.sqrt(n2)) -1 );
		System.out.println("n "+n +" n2 "+n2 + " mmax "+mmax);
		for(int m=2; m<= mmax;m++) {
			if (n2 % m == 0){
				System.out.println("for m "+m +" m<=mmax und m|n2 ");
				int nm = n2 / m;
				while (nm %2 ==0) nm/=2;
				int k;
				if (m % 2 == 1) k = m+2; else k = m+1;
				System.out.println("nm "+ nm +" k " + k);
				while ((k < 2*m) && k <= nm) {
					System.out.println("while k<2m AND k>=nm k:"+ k + " nm mod k: " + nm%k + " gcd (k,m)" + gcd(k,m) );

					if(nm%k==0 && gcd(k,m)==1) {
						int d = n2 / (k*m);
						int o = k-m;
						int a = d*(m*m-o*o);
						int b = 2*d*m*o;
						int c = d*(m*m+o*o);
						System.out.println(a+" "+b+" "+ c+" "+ d+" "+o);
						return a*b*c;				
					}
					k+=2;
				}
			}

		}
					return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the product abc of the Pythagorean triplet with a+b+c=1000

		long startTime, endTime, duration;

//		long startTime = System.nanoTime();
//		System.out.println( pytha(1000) );
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime);
//		System.out.println(duration/1000000);

				startTime = System.nanoTime();
				System.out.println( pytha_exam(1000) );
				endTime = System.nanoTime();
				duration = (endTime - startTime);
				System.out.println(duration/1000000);


	}

}
