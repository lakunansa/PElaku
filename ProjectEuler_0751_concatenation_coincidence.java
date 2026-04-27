	/**
	 * 
	 */
	
	import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
	
	/**
	 * 
	 */
	public class ProjectEuler_0751_concatenation_coincidence {
	
		/**
		 * 
		 */
		public ProjectEuler_0751_concatenation_coincidence() {
			// TODO Auto-generated constructor stub
		}
	
	    public static String solve() {
	        // Wir brauchen ca. 24-25 Stellen nach dem Komma.
	        MathContext mc = new MathContext(50, RoundingMode.HALF_UP);
	        BigDecimal theta = new BigDecimal("2.22"); // Startwert

	        // Fixpunkt-Iteration: Das neue Theta ergibt sich aus der Konkatenation der b_n
	        for (int i = 0; i < 10; i++) { // Konvergiert sehr schnell
	            theta = getNextTheta(theta, mc);
	        }

	        return theta.toPlainString().substring(0, 26); // "2." + 24 Nachkommastellen
	    }

	    private static BigDecimal getNextTheta(BigDecimal theta, MathContext mc) {
	        StringBuilder sb = new StringBuilder();
	        BigDecimal a = theta;
	        
	        // Generiere b_1, b_2, ... bis wir genug Ziffern haben
	        for (int n = 0; n < 25; n++) {
	            BigDecimal floorA = a.setScale(0, RoundingMode.FLOOR);
	            sb.append(floorA.toPlainString());
	            if (n == 0) sb.append("."); // Nach b_1 kommt das Komma
	            
	            // a_{n+1} = floor(a_n) * (a_n - floor(a_n) + 1)
	            BigDecimal fractional = a.subtract(floorA);
	            a = floorA.multiply(fractional.add(BigDecimal.ONE), mc);
	            
	            if (sb.length() > 30) break; // Genug Präzision erreicht
	        }
	        
	        return new BigDecimal(sb.toString());
	    }
		
		
		public static int[] tens = {1, (int)1E1,(int)1E2,(int)1E3,(int)1E4,(int)1E5,(int)1E6,(int)1E7,(int)1E8};
	
		public static class Token{
			int tens;
			int entry;
		}
	
		public static boolean reeval (Token[] theta, int steps) {
			int n = theta.length;
			Token[] reeval = new Token[n];
			for (int i=0;i<n && theta[i]!=null;i++) {
				Token newT = new Token();
				newT.tens=theta[i].tens;
				newT.entry=theta[i].entry;
				reeval[i]=newT;
			}
//			System.out.println("dort");
			for(int i=1;i<=steps;i++) {
				int carry=0; int floor = reeval[0].entry;
				for (int d=n-1;d>=1; d--) {
					reeval[d].entry=reeval[d].entry*floor + carry;
					carry=reeval[d].entry/tens[reeval[d].tens];
					reeval[d].entry%=tens[reeval[d].tens];
				}
				reeval[0].entry+=carry;
				while (reeval[0].entry>=tens[reeval[0].tens]) reeval[0].tens++;
//				for (Token t:reeval) {
//					System.out.print(i + " " + " " + t.entry + " " + t.tens + " ");
//				}
//				System.out.println();
				if (reeval[0].entry != theta[i].entry) return false;
			}
			return true;
		}
	
	
		public static String _cc(int n) {
			n=26;
			Token[] digit = new Token[n];
			for (int i=0; i<n;i++) {
				digit[i]=new Token();
				digit[i].tens=1; 
				digit[i].entry=2;
			}
	
	
			int count=1;
			int dig =1;
			while (count <n && dig<n) {
				while(!reeval(digit,count)) {
//					System.out.println("hier2 " + count+ " "+digit[count].entry +  " "+digit[count].tens);
					for (int i=count; i<n;i++) {
						digit[i].entry++;
						if (digit[i].entry >= tens[digit[i].tens]) {
							digit[i].tens++;
						}
					}
				}
				dig+=digit[count].tens;
				count++; 	
			}
//			System.out.println("hier" + count);
			String s= "";
			for (Token i:digit) s+=i.entry;
			s=digit[0].entry+"."+s.substring(1,n);
			return s;
	
		}
	
	
	
	
		/**
		 * @param args
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//computes nth prime
	
	
			long startTime = System.nanoTime();
			System.out.println( _cc(27) );
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
