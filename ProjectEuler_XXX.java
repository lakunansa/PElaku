/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_XXX {

	/**
	 * 
	 */
	public ProjectEuler_XXX() {
		// TODO Auto-generated constructor stub
	}
	
	public static int mod = 1000000007;
	public static int[] facs = null;
	
	//returns rank modulo 10^9+7
	public static long rank(ArrayList<Integer> al) {
		return 0;	
	}
	
	public static void init_facs(int n) {
		facs = new int[n+1]; facs[0]=1;
		for (int i=1; i<=n;i++) facs[i]=(int)(((long)facs[i-1] * (long)i )%((long)mod));
	}
	
	/**
	 * strategy: 
	 * (1)iterate over permutations (rank->rank+1)
	 * (2) find order o
	 * (3) multiply rank* (o-1)/o (mod)
	 * (4) finally multiply mit n! (mod)
	 * dauert zu lang
	 * 
	 * @param n
	 * @return
	 */
	public static long _9(int n) {
		long prod=1;
		for (long i=1;i<=n;i++) {
			prod*=i;
			prod%=mod;
		}
		return prod;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _9(10001) );
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
