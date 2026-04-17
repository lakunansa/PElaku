/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0088_product_sum_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0088_product_sum_numbers() {
		// TODO Auto-generated constructor stub
	}


	public static boolean isNum(int[] c) {
		int sum=0, prod=1;
		for (int i = 1; i<=c.length; i++) {
			sum+=i*c[i];
			prod*=(int)Math.pow(i, c[i]);
		}
		return sum==prod;
	}

	public static long solvePSN(int maxK) {//KI
	    int[] minN = new int[maxK + 1];
	    java.util.Arrays.fill(minN, 2 * maxK); 

	    // Start der Rekursion: Produkt, Summe, Anzahl Faktoren, kleinster nächster Faktor
	    findSolutions(1, 0, 0, 2, maxK, minN);

	    // Eindeutige N-Werte summieren
	    return java.util.stream.IntStream.of(minN).skip(2).distinct().sum();
	}

	private static void findSolutions(int prod, int sum, int factors, int start, int maxK, int[] minN) {
	    int k = prod - sum + factors;
	    if (k <= maxK) {
	        if (factors > 1 && prod < minN[k]) minN[k] = prod;
	        
	        for (int i = start; prod * i <= 2 * maxK; i++) {
	            findSolutions(prod * i, sum + i, factors + 1, i, maxK, minN);
	        }
	    }
	}

	
	





	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
//		System.out.println( psn(12000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

				startTime = System.nanoTime();
				System.out.println( solvePSN(12000) );
				endTime = System.nanoTime();
				duration = (endTime - startTime);
				System.out.println(duration/1000000);


	}

}
