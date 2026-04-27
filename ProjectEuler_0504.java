/**
 * 
 */
public class ProjectEuler_0504 {

	/**
	 * 
	 */
	public ProjectEuler_0504() {
		// TODO Auto-generated constructor stub
	}

	
public static long _soti_ki(int n) {
    long num = 0;
    int[][] gcdTable = new int[n + 1][n + 1];
    for (int a = 1; a <= n; a++) {
        for (int b = 1; b <= n; b++) gcdTable[a][b] = (int) gcd(a, b);
    }

    // Wir iterieren über alle Paare (i, j) und (k, l)
    // Symmetrie i < j nutzen wir durch Gewichtung, aber i,j < k,l ist hier gefährlich
    // Daher optimieren wir primär die inneren Abläufe:
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            long ijSumTerm = (long)(i + j - 1);
            int ijBase = i + j - 4 + 1; // Zusammengefasste Konstanten

            for (int k = 1; k <= n; k++) {
                // Vorberechnen für die innerste Schleife
                int g_ik_jk = gcdTable[i][k] + gcdTable[j][k];
                int ijkSum = ijBase + k;

                for (int l = 1; l <= n; l++) {
                    // Die Formel exakt wie in deinem Original
                    long numerator = ijSumTerm * (k + l - 1) 
                                   - (g_ik_jk + gcdTable[i][l] + gcdTable[j][l] - 4) 
                                   + (ijkSum + l);

                    // Da numerator immer gerade ist
                    long tmp = numerator >> 1;
                    if (tmp >= 0) {
                        long root = (long) Math.sqrt(tmp);
                        if (root * root == tmp) {
                            num++;
                        }
                    }
                }
            }
        }
    }
    return num;
}

	
	
	
	
	public static long gcd(long a, long b) {
	    while (b != 0) {
	        long t = a % b;
	        a = b;
	        b = t;
	    }
	    return a;
	}

	public static long lcm(long a, long b) {
	    if (a == 0 || b == 0) return 0;
	    return Math.abs(a * b) / gcd(a, b);
	}

	public static long _soti(int n) {
		long num=0;
		for (int i=1;i<=n;i++)
			for (int j=1;j<=n;j++)
				for (int k=1;k<=n;k++)
					for(int l=1;l<=n;l++) {
						double tmp = 
								(((i+j-1)*(k+l-1)) - 
								 (gcd(i,k) + gcd(j,k) + gcd(i,l) + gcd(j,l) -4 ) +
								 (i+j+k+l-4) +
								 (1))/2.0;
//						System.out.println(i + " " +j+ " " + k + " " +l +" " + tmp);
						int tmp2 = (int)Math.round(Math.sqrt(tmp));
						if (tmp2*tmp2==tmp) num++;
					}
		return num;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _soti_ki(100) );
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
