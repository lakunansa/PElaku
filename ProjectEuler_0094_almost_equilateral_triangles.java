/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0094_almost_equilateral_triangles {

	/**
	 * 
	 */
	public ProjectEuler_0094_almost_equilateral_triangles() {
		// TODO Auto-generated constructor stub
	}


	public static long solveCorrect() {
	    long limit = 1000000000L;
	    long sum = 0;
	    
	    // Startwerte für die Pell-Gleichung x^2 - 3y^2 = 1
	    long x = 2; 
	    long y = 1;
	    
	    while (true) {
	        // Nächste Lösung der Pell-Gleichung generieren
	        long nextX = 2 * x + 3 * y;
	        long nextY = x + 2 * y;
	        x = nextX;
	        y = nextY;
	        
	        // Fall 1: Basis b = a + 1
	        // Formel: a = (2x + 1) / 3
	        if ((2 * x + 1) % 3 == 0) {
	            long a = (2 * x + 1) / 3;
	            long perimeter = 3 * a + 1;
	            if (perimeter > limit) break; // Da x wächst, können wir hier abbrechen
	            if (a > 1) sum += perimeter;
	        }
	        
	        // Fall 2: Basis b = a - 1
	        // Formel: a = (2x - 1) / 3
	        if ((2 * x - 1) % 3 == 0) {
	            long a = (2 * x - 1) / 3;
	            long perimeter = 3 * a - 1;
	            if (perimeter <= limit) {
	                if (a > 1) sum += perimeter;
	            }
	        }
	        
	        // Abbruchbedingung basierend auf dem kleinstmöglichen nächsten Umfang
	        if (3 * ((2 * x - 1) / 3) - 1 > limit) break;
	    }
	    return sum;
	}


	
	public static long _aet(int n) {
		long sum=0;
		for (long i=3;3*i+1<=n;i+=2) {
			long c1 = (i+1)/2;
			long c2sq = ((i*i)-(c1*c1));
			long c2 = (long)(Math.sqrt(c2sq));
			if (c2*c2==c2sq )sum+=3*i+1;
		}
		for (long i=3;3*i-1<=n;i+=2) {
			long k1 = (i-1)/2;
			long k2sq = ((i*i)-(k1*k1));
			long k2 = (long)(Math.sqrt(k2sq));
			if (k2*k2==k2sq )sum+=3*i-1;
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _aet(1_000_000_000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		startTime = System.nanoTime();
		System.out.println( solveCorrect());
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);



	}

}
