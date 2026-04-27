import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class ProjectEuler_0918_resursive_sequence_summation {

	/**
	 * 
	 */
	public ProjectEuler_0918_resursive_sequence_summation() {
		// TODO Auto-generated constructor stub
	}

	
    private static Map<Long, Long> cache = new HashMap<>();

    public static long a(long n) {
        if (n == 1) return 1;
        if (cache.containsKey(n)) return cache.get(n);

        long res;
        if (n % 2 == 0) {
            res = 2 * a(n / 2);
        } else {
            long k = n / 2;
            res = a(k) - 3 * a(k + 1);
        }
        
        cache.put(n, res);
        return res;
    }

    public static long _rss_ki(long n) {
        // Die Formel für S(2n) ist 4*a(1) - a(n), 
        // da S(2n) = 3*a(1) + a(1) - a(n) = 4 - a(n)
        return 4 * a(1) - a(n / 2);
    }

	
	
	public static long recursive(long n) {
//		if (n==0) return 0;
		if (n==1) return 1;
		else if (n%2==0) return 2* recursive(n/2);
		else return recursive(n/2) - 3*recursive(n/2 +1) ;
	}

	public static long _rss(long n) {
		if (n%2==0) return 4*recursive(1) - recursive(n/2);
		else return 4*recursive(1) - 3* recursive(n/2 + 1);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _rss_ki((long)1E12) );
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
