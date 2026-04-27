/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0719_number_splitting {

	/**
	 * 
	 */
	public ProjectEuler_0719_number_splitting() {
		// TODO Auto-generated constructor stub
	}
	
	public static long solve(long limit) {
        long totalSum = 0;
        long sqrtLimit = (long) Math.sqrt(limit);

        // Wir starten bei 9, da die Aufgabe i > 1 impliziert (4, 9, 16...)
        for (long i = 9; i <= sqrtLimit; i++) {
            // Modulo 9 Optimierung: Nur Reste 0 und 1 sind möglich
            long rem = i % 9;
            if (rem == 0 || rem == 1) {
                if (canPartition(i, i * i)) {
                    totalSum += i * i;
                }
            }
        }
        return totalSum;
    }

    /**
     * Prüft rekursiv, ob die Zahl 'square' so aufgeteilt werden kann, 
     * dass die Summe der Teile 'target' ergibt.
     */
    private static boolean canPartition(long target, long square) {
        // Basis-Fälle
        if (target < 0 || square < target) return false;
        if (square == target) return true;

        // Probiere Schnitte an jeder Zehnerpotenz
        // m=10 -> letzte Ziffer, m=100 -> letzte zwei Ziffern, etc.
        for (long m = 10; m <= square; m *= 10) {
            long part = square % m;
            long rest = square / m;
            
            // Wenn wir den restlichen Teil weiter zerlegen können
            if (canPartition(target - part, rest)) {
                return true;
            }
            
            // Sicherheit: square / m wird irgendwann 0, 
            // aber m kann bei long-Überlauf Probleme machen
            if (rest < 10 && rest < (target - part)) break; 
        }
        return false;
    }
	
	
	public static long[] tens = {1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000, 10_000_000_000L, 100_000_000_000L, 1_000_000_000_000L};

	public static long _nus(long n) {
		int lim = (int)Math.sqrt(n)+1;
		int digs = (int)Math.log10(n+1);
		long twoPowDigs=(long)Math.pow(2, digs);
		long[] sqs = new long[lim];
		boolean[] snum = new boolean[lim];
		for (int i=0; i<lim;i++) sqs[i]=(long)i*(long)i;
//		System.out.println(n + " "+ digs);
		long sum=0;
		fori:for (int i=4;i<lim;i++) {
			if (i%10000==0)System.out.println(i);
			if (i%9>1)continue;
			int control=1; long two=2;
			for (int d=1;d<twoPowDigs && tens[control] <= sqs[i];d++) {
				if (two%(d+1)==0) {
					control++; two*=2;
				}
				long sumd=0;
				long tmpi=sqs[i];
				long tmpd=d;
				int[] bits = new int[digs]; int count=0, place=0; 
				while (tmpd>0) {
					count++;
					if (tmpd%2==1) {
						bits[place]=count;
						place++;
					}
					tmpd/=2;
				}
				int oldbit=0;
				for (int newbit:bits) {
					int diffbit= newbit-oldbit;
					oldbit=newbit;
					if (diffbit>0) {
						sumd+=tmpi%tens[diffbit];
//						System.out.print(tmpi%tens[diffbit]+" " );
						tmpi/=tens[diffbit];
					}
				}
				sumd+=tmpi;
//				System.out.print(tmpi+" " );
//				System.out.print(i + " " +sqs[i]+ " "+ d + " " + sumd + " " + (sumd==i) + " ");
//				for (int j:bits) System.out.print(j);
//				System.out.println();
				if (sumd==i) {
					sum+=sqs[i];
//					System.out.println(sqs[i]);
					continue fori;
				}
			}
		}
//		for (int i=4;i<lim;i++) if (snum[i])sum+=sqs[i];
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _nus((long)1E12+1) );
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
