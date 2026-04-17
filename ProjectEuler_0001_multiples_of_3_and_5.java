/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0001_multiples_of_3_and_5 {

	/**
	 * 
	 */
	public ProjectEuler_0001_multiples_of_3_and_5() {
		// TODO Auto-generated constructor stub
	}

	
	public static long solveProblem1(int n) {//better factor 10
	    n--; // "Below n" -> wir betrachten den Bereich bis n-1
	    return sumMultiples(n, 3) + sumMultiples(n, 5) - sumMultiples(n, 15);
	}

	private static long sumMultiples(int limit, int factor) {
	    long numTerms = limit / factor;
	    // Gauß: factor * (1 + 2 + ... + numTerms)
	    return factor * (numTerms * (numTerms + 1)) / 2;
	}
	

	public static int fivthresect(int n){

		int fiv = n / 5;
		int thre = n / 3;
		int sect = n / 15;

		return ( 5 * fiv * (fiv+1) + 3 * thre * (thre+1) - 15 * sect * (sect+1) ) / 2;

		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//finds sum of all multiples of three and five below 1000
		{
		long startTime = System.nanoTime();
		System.out.println( fivthresect(1000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
		}
		{
		long startTime = System.nanoTime();
		System.out.println( solveProblem1(1000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);
		}
	}

}
