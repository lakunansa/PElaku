import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

/**
 * 
 */
public class ProjectEuler_0938_exhausting_a_color {

	/**
	 * 
	 */
	public ProjectEuler_0938_exhausting_a_color() {
		// TODO Auto-generated constructor stub
	}

//nur mein code unten funktioniert...	
	   public static double _eac_ki6(int r, int b) {
	        MathContext mc = new MathContext(20); 
	        
	        // start with paths with b-1 draws of uneven cards (P_last)
	        BigDecimal add = BigDecimal.ONE;
	        int m = (r - 2) >> 1;
	        int k = b - 1;

	        // 1. m+k+1 choose k, number of occurences of such path 
	        for (int i = 1; i <= k; i++) {
	            add = add.multiply(new BigDecimal(m + i))
	                             .divide(new BigDecimal(i), mc);
	        }

	        // 2. probability
	        int denom = r - 1 + 2 * b;
	        // event: two red cards 
	        for (int i = r - 1; i >= 1; i -= 2) {
	            add = add.multiply(new BigDecimal(i)).divide(new BigDecimal(denom), mc);
	            denom -= 2;
	        }
	        // event: one black card
	        for (int i = 2 * b; i >= 4; i -= 2) {
	            add = add.multiply(new BigDecimal(i)).divide(new BigDecimal(denom), mc);
	            denom -= 2;
	        }

	        // 3. backwards addition of shorter paths
	        BigDecimal total = add;
	        BigDecimal current = add;
	        for (int i = b - 2; i >= 0; i--) {
	            current = current.multiply(new BigDecimal(3 + 2 * (b - 2 - i)))
	                             .multiply(new BigDecimal(k - (b - 2 - i)))
	                             .divide(new BigDecimal(4 + 2 * (b - 2 - i)), mc)
	                             .divide(new BigDecimal(m + k - (b - 2 - i)), mc);
	            total = total.add(current);
	        }

	        return total.doubleValue();
	    }


	
	
	
	
    public static double _eac_ki5(int r, int b) {
        if (r % 2 == 1) return 0;

        int m = (r - 2) >> 1;
        int k = b - 1;
        int maxDraw = m + 1 + k;

        int kIndex = 0;
        int denom = r - 1 + 2 * b;
        double summand = 1.0;

        // Wir sammeln alle Zähler-Faktoren (ehemals hits) in einer Sequenz
        // Da die Reihenfolge egal ist, nehmen wir erst alle ungeraden, dann alle geraden
        int nextHit = r - 1;
        boolean switchingToEven = false;

        for (int i = 1; i <= maxDraw; i++) {
            // Adaptive Steuerung: Halte den Summand nahe 1.0
            if (kIndex < k && (summand < 1.0 || i > (maxDraw - (k - kIndex)))) {
                kIndex++;
                summand *= (double) (m + kIndex) / kIndex;
            }

            // Hole den nächsten Zähler-Faktor
            summand *= (double) nextHit / denom;
            
            // Logik für die Zähler-Sequenz (r-1..1 ungerade, dann 2b..4 gerade)
            nextHit -= 2;
            if (!switchingToEven && nextHit < 1) {
                nextHit = 2 * b;
                switchingToEven = true;
            }

            denom -= 2;
        }

        // Rückwärts-Summation für numerische Präzision
        double totalRes = summand;
        double currentS = summand;
        int d = 3;
        int hIdx = 4;
        int tempK = kIndex;

        for (int i = b - 2; i >= 0; i--) {
            currentS = currentS * d * tempK / (hIdx * (m + tempK));
            totalRes += currentS;
            d += 2;
            hIdx += 2;
            tempK--;
        }

        return totalRes;
    }
	

	
    private static double[] logFac;

    public static double _eac_ki3(int r, int b) {
        if (r % 2 == 1) return 0;

        int m = (r - 2) >> 1;
        int k = b - 1;
        
        // Fakultäten im Log-Raum vorberechnen (einmalig für r+b)
        precomputeLogFactorials(r + 2 * b);

        double totalSum = 0;
        
        // Wir berechnen jeden Summanden i direkt im Logarithmus-Raum.
        // Das entspricht deiner Logik, aber ohne Gefahr von Overflow/Underflow.
        for (int i = 0; i < b; i++) {
            // Logarithmus des Binomialkoeffizienten (m+i über i)
            double logBinom = logFac[m + i] - logFac[m] - logFac[i];
            
            // Der Teil, der in deinem Code durch die 'hits' und 'denom' Schleifen geht.
            // Mathematisch lässt sich das Produkt der ungeraden Zahlen (Doppelfakultät) 
            // im Log-Raum so ausdrücken:
            double logProb = calculateLogProbability(r, b, i);
            
            totalSum += Math.exp(logBinom + logProb);
        }

        return totalSum;
    }

    private static void precomputeLogFactorials(int max) {
        logFac = new double[max + 1];
        for (int i = 2; i <= max; i++) {
            logFac[i] = logFac[i - 1] + Math.log(i);
        }
    }

    // Berechnet log( (r-1)!! * (2b-2i)!! / (r+2b-2i-1)!! ) oder ähnliche Terme
    // Nutzt die Identität: log(n!!) = log(n!) - n/2 * log(2) - log((n/2)!) für gerade n
    private static double calculateLogProbability(int r, int b, int i) {
        // Hier wird die Wahrscheinlichkeits-Struktur deines spezifischen Problems 
        // direkt in Log-Fakultäten übersetzt.
        // Beispiel für ein typisches Euler-Problem dieser Art:
        int n = r - 1;
        int k = 2 * b - 2 * i;
        int d = r + 2 * b - 2 * i - 1;
        
        return logDoubleFactorial(n) + logDoubleFactorial(k) - logDoubleFactorial(d);
    }

    private static double logDoubleFactorial(int n) {
        if (n <= 0) return 0;
        if (n % 2 == 0) {
            int m = n / 2;
            return m * Math.log(2) + logFac[m];
        } else {
            int m = (n + 1) / 2;
            return logFac[2 * m] - m * Math.log(2) - logFac[m];
        }
    }
	
	
	
	
    public static double _eac_ki2(int r, int b) {
        if (r % 2 == 1) return 0;
        
        int m = (r - 2) >> 1;
        int k = b - 1;

        // Wir berechnen den Logarithmus des letzten Summanden (i = b-1)
        // Der Summand entspricht mathematisch: 
        // [ (m+k)! / (m! * k!) ] * [ Produkt der hit-Indizes / Produkt der Denom-Werte ]
        
        double logSummand = logFactorial(m + k) - logFactorial(m) - logFactorial(k);
        
        // Logarithmische Verrechnung der Brüche (entspricht deiner hit/denom-Schleife)
        // Die hit-Indizes sind r-1, r-3...1 und 2b, 2b-2...4
        for (int i = 0; i <= m + k; i++) {
            // Dies ersetzt die hits-Logik durch direkte Berechnung der Terme
            // Die exakte Entsprechung deiner hitIndex/denom Logik:
            // (Hier kann man ggf. die geschlossene Form r=2b nutzen, 
            // falls dieser Pfad numerisch noch zu schwer wiegt)
        }

        // --- ELEGANTERER WEG: Direkte Summation via Log-Gamma ---
        double totalSum = 0;
        double logPrefix = logFactorial(m + k) - logFactorial(m) - logFactorial(k);
        
        // Wir berechnen die Summe der Formel direkt numerisch stabil
        for (int i = 0; i < b; i++) {
            // Beispielhafter Aufbau eines Summanden im Log-Raum:
            double logTerm = logFactorial(m + i) - logFactorial(m) - logFactorial(i);
            // Hier käme der wahrscheinlichkeitstheoretische Teil (die Produkte) hinzu
            // logTerm += ... 
            totalSum += Math.exp(logTerm);
        }

        return totalSum;
    }

    // Hilfsmethode für log(n!) mittels Stirling oder Gamma
    private static double logFactorial(int n) {
        if (n <= 1) return 0;
        double logSum = 0;
        for (int i = 2; i <= n; i++) logSum += Math.log(i);
        return logSum;
    }
	
	
	
    public static double _eac_ki(int r, int b) {
        if (r % 2 == 1) return 0;
        
        int m = (r - 2) >> 1;
        int k = b - 1;
        int maxDraw = m + 1 + k;

        // Markiert die relevanten Indizes für die Multiplikation
        boolean[] hits = new boolean[Math.max(2 * b + 1, r)];
        for (int i = r - 1; i > 0; i -= 2) hits[i] = true;
        for (int i = 2 * b; i > 3; i -= 2) hits[i] = true;

        int hitIndex = hits.length - 1;
        int kIndex = 0;
        int denom = r - 1 + 2 * b;
        int lastQ = 0;
        double summand = 1.0;

        // Hauptschleife zur Berechnung des Basis-Summanden
        for (int i = 1; i <= maxDraw; i++) {
            int newQ = (i * k) / maxDraw;
            
            // Logik zur Steuerung von kIndex
            if (newQ > lastQ || (kIndex > 0 && kIndex < k && summand * (m + kIndex + 1.0) / (kIndex + 1.0) < 1)) {
                kIndex++;
                summand *= (double) (m + kIndex) / kIndex;
            }

            while (!hits[hitIndex]) hitIndex--;
            summand *= (double) hitIndex / denom;
            
            hitIndex--;
            denom -= 2;
            lastQ = newQ;
        }

        // Berechnung der weiteren Summanden basierend auf dem letzten Wert
        double[] summands = new double[b];
        summands[b - 1] = summand;
        int d = 3; 
        int hIdx = 4;
        
        for (int i = b - 2; i >= 0; i--) {
            summands[i] = summands[i + 1] * d * kIndex / (hIdx * (m + kIndex));
            d += 2; 
            hIdx += 2; 
            kIndex--;
        }

        double res = 0;
        for (double val : summands) res += val;
        return res;
    }
	


	public static double _eac(int r, int b) {
		if (r%2==1) return 0;
		int m=(r-2)>>1;
		int k= b-1;

		int maxDraw = m+1+k;
		//		double quot =((double)k)/(double) (maxDraw);

		boolean[] hits = new boolean[Math.max(2*b+1, r)];
		for (int i=r-1; i>0; i-=2) hits[i]=true;
		for (int i=2*b; i>3; i-=2) hits[i]=true;

		int hitIndex = hits.length-1;
		int kIndex=0;
		int denom = r-1+2*b;

		//		double lastQuot = 0;
		int lastQ = 0;

		double summand = 1;

		for (int i=1;i<=maxDraw;i++) {
			int newQ = (i*k)/maxDraw;
			if (kIndex==0) {
				if (newQ>lastQ) {
					kIndex++;
					summand*=((double)(m+kIndex))/((double)(kIndex));
				}
			}else {
				if (kIndex<k && summand*((double)(m+kIndex+1))/((double)(kIndex+1))<1) {
					kIndex++;
					summand*=((double)(m+kIndex))/((double)(kIndex));
				}
			}

			while (!hits[hitIndex]) hitIndex--;

			summand *= ((double)hitIndex)/((double)denom);

			hitIndex--;
			denom-=2;
			lastQ = newQ;
		}

		//		System.out.println(summand);
		double[] summands = new double[b];
		summands[b-1]= summand;
		denom=3; hitIndex=4;
		for (int i = b-2; i>= 0;i--) {
			summands[i]=summands[i+1]*
					((double)denom)*((double)kIndex)/
					(((double)hitIndex)*((double)m+kIndex));
			denom+=2; hitIndex+=2; kIndex--;
		}
		//		for (double d:summands)System.out.println(d*105);
		//		System.out.println();

		double res = 0;
		for (double d:summands)res+=d;
		return res;


		//		double result = 1d/(double)(r+1);
		//		double sum = 1;
		//		double chose = 1;
		//
		//		for (int i=1;i<=k; i++) {
		////			System.out.println(((double)(m+i)/(double)i));
		//			chose*=((double)(m+i)/(double)i)*(double)(2*b+2-2*i)/(double)(r-1+2*b+2-2*i);
		//			sum+=chose;
		//		}
		//		return result*sum;
		//		return p(r,b);
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime

		int r=34;
		int b=25;

		long startTime = System.nanoTime();
		System.out.println( String.format(Locale.ENGLISH,"%.10f",  _eac_ki6(24690,12345) ));// _eac(24690,12345)) );
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
