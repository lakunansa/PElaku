/**
 * 
 */
public class ProjectEuler_0120_square_remainders {

	/**
	 * 
	 */
	public ProjectEuler_0120_square_remainders() {
		// TODO Auto-generated constructor stub
	}


	   public static void mains(String[] args) {
	        int n = Integer.parseInt(args[0]); // Beispielwert
	        
	        long summeSchleife = berechneMitSchleife(n);
	        long summeFormel = berechneMitFormel(n);

	        System.out.println("Summe (Schleife) für N=" + n + ": " + summeSchleife);
	        System.out.println("Summe (Formel)   für N=" + n + ": " + summeFormel);
	    }

	    /**
	     * Berechnet die Summe iterativ von 3 bis N.
	     */
	    public static long berechneMitSchleife(int n) {
	        long summe = 0;
	        for (int i = 3; i <= n; i++) {
	            if (i % 2 != 0) {
	                summe += (long) i * i - i;      // i^2 - i für ungerade
	            } else {
	                summe += (long) i * i - 2 * i;  // i^2 - 2i für gerade
	            }
	        }
	        return summe;
	    }

	    /**
	     * Berechnet die Summe direkt über die explizite Formel.
	     * Nutzt long für präzise Ganzzahl-Arithmetik bei großen N.
	     */
	    public static long berechneMitFormel(int n) {
	        // Die vereinfachte Formel für Ganzzahlen (verhindert Rundungsfehler durch double)
	        if (n < 3) return 0;
	        
	        long n2 = (long) n * n;
	        long n3 = n2 * n;
	        
	        if (n % 2 == 0) {
	            // Fall: N ist gerade
	            return (8 * n3 - 6 * n2 - 20 * n) / 24;
	        } else {
	            // Fall: N ist ungerade
	            return (8 * n3 - 6 * n2 - 8 * n + 6) / 24;
	        }
	    }
	
	
	
	public static long _sr(int n) { 
		mains(new String[]{n+""}) ;
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _sr(1000) );
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
