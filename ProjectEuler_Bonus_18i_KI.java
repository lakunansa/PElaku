/**
 * 
 */


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 */
public class ProjectEuler_Bonus_18i_KI {

	/**
	 * 
	 */
	public ProjectEuler_Bonus_18i_KI() {
		// TODO Auto-generated constructor stub
	}


	public class Euler18i {

	    // Modulare Potenzierung für long
	    /**
	     * muss ich mir merken
	     * @param base
	     * @param exp
	     * @param mod
	     * @return
	     */
	    public static long power(long base, long exp, long mod) {
	        long res = 1;
	        base %= mod;
	        while (exp > 0) {
	            if (exp % 2 == 1) res = multiplyMod(res, base, mod);
	            base = multiplyMod(base, base, mod);
	            exp /= 2;
	        }
	        return res;
	    }

	    // Sicherer Umgang mit (a * b) % mod um Overflow zu vermeiden
	    public static long multiplyMod(long a, long b, long m) {
	        return Math.multiplyExact(a, b) % m; // Nutzt Java 8+, wirft Fehler bei Overflow
	        // Für extrem große p nahe Long.MAX_VALUE müsste man BigInteger nutzen.
	    }

	    // Polynom-Multiplikation modulo (x^3 - 3x + 4, p)
	    // Ein Polynom ax^2 + bx + c wird als long[] {a, b, c} gespeichert
	    public static long[] polyMul(long[] p1, long[] p2, long p) {
	        long a1 = p1[0], b1 = p1[1], c1 = p1[2];
	        long a2 = p2[0], b2 = p2[1], c2 = p2[2];

	        // Intermediate Produkte (Grad 4 bis 0)
	        long d4 = (a1 * a2) % p;
	        long d3 = (a1 * b2 + b1 * a2) % p;
	        long d2 = (a1 * c2 + c1 * a2 + b1 * b2) % p;
	        long d1 = (b1 * c2 + c1 * b2) % p;
	        long d0 = (c1 * c2) % p;

	        // Reduktion: x^3 = 3x - 4 und x^4 = 3x^2 - 4x
	        long resA = (d2 + 3 * d4) % p;
	        long resB = (d1 - 4 * d4 + 3 * d3) % p;
	        long resC = (d0 - 4 * d3) % p;

	        return new long[] { (resA + p) % p, (resB + p) % p, (resC + p) % p };
	    }

	    public static long[] polyPow(long[] poly, long exp, long p) {
	        long[] res = {0, 0, 1}; // Identität
	        while (exp > 0) {
	            if (exp % 2 == 1) res = polyMul(res, poly, p);
	            poly = polyMul(poly, poly, p);
	            exp /= 2;
	        }
	        return res;
	    }

	    public static long solve18i(long p) {
	        if (p < 5) return 0; // f(x) hat Wurzeln für 2, 3
	        if (p % 4 == 3) return 0; // f(x) hat immer Wurzeln wenn p = 3 mod 4

	        // Schritt B: Wurzeln im Erweiterungskörper bestimmen
	        long[] alpha1 = {0, 1, 0};      // x
	        long[] alpha2 = polyPow(alpha1, p, p); // x^p
	        long[] alpha3 = polyPow(alpha2, p, p); // x^(p^2)

	        // Schritt C: (a1 - a2) * (a2 - a3) * (a3 - a1)
	        long[] diff1 = {(alpha1[0]-alpha2[0]+p)%p, (alpha1[1]-alpha2[1]+p)%p, (alpha1[2]-alpha2[2]+p)%p};
	        long[] diff2 = {(alpha2[0]-alpha3[0]+p)%p, (alpha2[1]-alpha3[1]+p)%p, (alpha2[2]-alpha3[2]+p)%p};
	        long[] diff3 = {(alpha3[0]-alpha1[0]+p)%p, (alpha3[1]-alpha1[1]+p)%p, (alpha3[2]-alpha1[2]+p)%p};

	        long[] resPoly = polyMul(polyMul(diff1, diff2, p), diff3, p);
	        
	        // Das Ergebnis ist der konstante Term (Index 2), da a und b 0 werden
	        return resPoly[2];
	    }

	    public static void main(String[] args) {
	        long p = 13; // Beispiel: p=13 -> R(13) = 18*i mod 13. i=5 (5^2=25=-1), 18*5=90=12 mod 13.
	        System.out.println("R(" + p + ") = " + solve18i(p));
	    }
	}

	
	
	
	
	public static long _b18i(int n) {
/**
 * die idee hier, ist es eine Methode für die Polynommultiplikation mod p zu haben
 * bzw, 
 */
		
		
		
		String inputPath = "C:\\Users\\Dell\\Downloads\\0000_bonus18i.txt";
		Path path = Paths.get(inputPath);

		// 1. Text laden
        File inputFile = new File(inputPath);
		String content=null;
		try {
			content = Files.readString(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//2. Matrix parsen aus String content
		String[] primes = content.split("[,\\n\\r]");

		int pl=primes.length;
        long sum=0;
        
        for (int i=0;i<pl;i++) {
        	int k= Integer.parseInt(primes[i]);
        	sum+=Euler18i.solve18i(k);
        	System.out.println((i/pl)*100);
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
		System.out.println( _b18i(10001) );
//		ProjectEuler_Bonus_2KI.Euler18i.main(null); 
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
