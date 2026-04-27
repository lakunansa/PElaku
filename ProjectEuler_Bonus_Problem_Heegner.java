import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import ch.obermuhlner.math.big.BigDecimalMath;


/**
 * 
 */
public class ProjectEuler_Bonus_Problem_Heegner {

	/**
	 * 
	 */
	public ProjectEuler_Bonus_Problem_Heegner() {
		// TODO Auto-generated constructor stub
	}

	

	public static long _bph_ki(int n) {
	    int min = 2;
	    MathContext mc = new MathContext(100); // 100 Stellen Präzision
	    BigDecimal minDiff = BigDecimal.ONE;
	    
	    BigDecimal PI = BigDecimalMath.pi(mc);

	    for (int i = 0; i <= n; i++) {
	        BigDecimal bigI = BigDecimal.valueOf(i);
	        BigDecimal sqrI = BigDecimalMath.sqrt(bigI, mc);

	        // Quadratzahl-Check ohne Double: 
	        // Wenn sqrI eine ganze Zahl ist, ist sqrI.remainder(1) == 0
	        if (sqrI.remainder(BigDecimal.ONE).signum() == 0) continue;

	        // Argument für die Funktionen: sqrt(i) * PI
	        BigDecimal arg = sqrI.multiply(PI, mc);

	        // Cosinus berechnen
	        BigDecimal cosVal = BigDecimalMath.cos(arg, mc).abs();
	        
	        // 1 - cos
	        BigDecimal diff1 = BigDecimal.ONE.subtract(cosVal, mc).abs();
	        
	        // Cosh berechnen
	        BigDecimal coshFull = BigDecimalMath.cosh(arg, mc);
	        // Abstand zur nächsten ganzen Zahl: |cosh - round(cosh)|
//	        BigDecimal coshDiff = coshFull.subtract(coshFull.setScale(0, BigDecimal.ROUND_HALF_UP), mc).abs();
	     // Anstatt: coshFull.setScale(0, BigDecimal.ROUND_HALF_UP)
	        BigDecimal coshDiff = coshFull.subtract(coshFull.setScale(0, RoundingMode.HALF_UP)).abs();

	        // Minimum der drei Werte finden
	        BigDecimal localMin = cosVal.min(diff1).min(coshDiff);

	        if (localMin.compareTo(minDiff) < 0) {
	            min = i;
	            minDiff = localMin;
	            System.out.println(i + ": " + minDiff.toPlainString());
	        }
	    }
	    return min;
	}
	

	public static long _bph(int n) {
		int min=2;
		double minDiff=1;
		for (int i=0 ; i<=n;i++) {
			double sqrI = Math.sqrt(i);
			if ( (int)Math.pow(Math.round(sqrI),2) != i ) {
				double cos = Math.abs(Math.cos(sqrI*Math.PI));
				double diff1 = 1- cos;
				double cosh = Math.abs( Math.cosh(sqrI*Math.PI) - Math.round( Math.cosh(sqrI*Math.PI)));
				if ( Math.min( cos, Math.min( diff1, cosh) ) < minDiff) {
					min=i;
					System.out.print(i + ": ");
					if (cos < minDiff) {
						System.out.println("to 0: "+cos);
						minDiff = cos;
					} else
					if (diff1 < minDiff){
						System.out.println("to 1: "+diff1);
						minDiff = diff1;
					} 
					if (cosh < minDiff){
						System.out.println("to ?: "+cosh);
						minDiff = cosh;
					} 
				}
			}
		}
		return min;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _bph_ki(1000) );
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
