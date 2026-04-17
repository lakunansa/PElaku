/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0002_even_fibos_below {

	/**
	 * 
	 */
	public ProjectEuler_0002_even_fibos_below() {
		// TODO Auto-generated constructor stub
	}

	public static int sum_even_fibo_ultra_fast(int limit) {//slower than "fast"
	    int a = 2, b = 8;
	    int sum = a;
	    
	    while (b < limit) {
	        sum += b;
	        int next = 4 * b + a;
	        a = b;
	        b = next;
	    }
	    return sum;
	}
	
	public static int sum_even_fibo_fast(int l) {
		int one=0, two=0, thr=2, tmp;
		while ( ( tmp=4*thr - 3*two - one ) < l) {
			one=two;
			two = thr;
			thr= tmp+thr;
		}
		return thr;
	}
	
	public static int sum_even_fibo(int l){
		int sum =  2, u = 2, v = 0, temp = 0;
		while ( ( temp = 4*u+v ) < l) {
			v=u; 
			u=temp;
			sum += temp;
		}; 
		return sum;
}
	
	
	public static int new_even_fibo(int m, int n){
				return ( 4 * m + n) ;
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//finds sum of even Fibo numbers below 4000000

		
		long startTime = System.nanoTime();
		System.out.println( sum_even_fibo_ultra_fast(4000000));
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
		startTime = System.nanoTime();
		System.out.println( sum_even_fibo_fast(4000000));
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration);
		
	}

}
