/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0205_dice_game {

	/**
	 * 
	 */
	public ProjectEuler_0205_dice_game() {
		// TODO Auto-generated constructor stub
	}


	public static double _dg() {
	    // Wahrscheinlichkeitsverteilungen berechnen
	    long[] fours = getDistributions(9, 4); // 9 Würfel mit 4 Seiten
	    long[] sixes = getDistributions(6, 6); // 6 Würfel mit 6 Seiten

	    long max4 = (long) Math.pow(4, 9);
	    long max6 = (long) Math.pow(6, 6);

	    // Präfix-Summe für die Sechser-Würfel (Peter gewinnt, wenn seine Summe > Colins Summe)
	    long[] sixdist = new long[37];
	    for (int i = 1; i <= 36; i++) {
	        sixdist[i] = sixes[i] + sixdist[i - 1];
	    }

	    long wins = 0;
	    for (int i = 1; i < 37; i++) {
	        // Peter hat Summe i, er gewinnt gegen alle Colin-Summen von 0 bis i-1
	        wins += fours[i] * sixdist[i - 1];
	    }

	    return (double) wins / (max4 * max6);
	}

	private static long[] getDistributions(int dice, int sides) {
	    long[] counts = new long[dice * sides + 1];
	    counts[0] = 1; // Basis: 0 Würfel ergeben Summe 0 auf genau 1 Weise

	    for (int d = 1; d <= dice; d++) {
	        long[] nextCounts = new long[dice * sides + 1];
	        for (int s = 1; s <= sides; s++) {
	            for (int sum = 0; sum + s < nextCounts.length; sum++) {
	                nextCounts[sum + s] += counts[sum];
	            }
	        }
	        counts = nextCounts;
	    }
	    return counts;
	}

	
	
	
	public static String _dg(int n) {
		long[] fours=new long[37], sixs=new long[37];
		long max4=(long)Math.pow(4, 9), max6=(long)Math.pow(6, 6);
		long d,tmp, count; int sum;
		for (int i=0;i<max4;i++) {
			tmp=i; count=9; sum=9;
			while (count>=1) {
				sum+=tmp%4;
				tmp/=4;
				count--;
			}
			fours[sum]++;
		}
		for (int i=0;i<max6;i++) {
			tmp=i; count=6; sum=6;
			while (count>=1) {
				sum+=tmp%6;
				tmp/=6;
				count--;
			}
			sixs[sum]++;
		}
//		for (int i=0;i<37;i++) System.out.println(fours[i] + " " + sixs[i]);
//		System.out.println();
//		long[] fourdist = new long[37];
//		fourdist[36]=0;
//		for (int i=35;i>=0;i--) fourdist[i]=fourdist[i+1]+fours[i+1];

		long[] sixdist = new long[37];
		sixdist[0]=0;
		for (int i=1;i<=36;i++) sixdist[i]=sixs[i] + sixdist[i-1];

//		for (int i=1;i<37;i++) System.out.println(i+ " "+fours[i]+ " " + sixdist[i-1]);

		long prob=0;
		for (int i=1;i<37;i++) prob += fours[i]*sixdist[i-1];
		
		return String.format("%.7f", ((double)prob)/(max4*max6));
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _dg(1) );
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
