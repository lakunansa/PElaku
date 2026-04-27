import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class ProjectEuler_0124_ordered_radicals {

	/**
	 * 
	 */
	public ProjectEuler_0124_ordered_radicals() {
		// TODO Auto-generated constructor stub
	}

	
	public static class Token{
		int i;
		long rad=1;
	}

	public static void ermittlePrimfaktoren(Token t) {
        
        // 1. Alle Faktoren von 2 entfernen (einzige gerade Primzahl)
        int n = t.i;
        if(n % 2 == 0) {
        	t.rad *= 2;
    		do n /= 2; while (n % 2 == 0);
        }

        // 2. Ungerade Teiler ab 3 bis sqrt(n) prüfen
        // Wir springen in 2er Schritten (i += 2), da n jetzt ungerade ist
        for (int i = 3; i * i <= n; i += 2) {
            if(n % i == 0) {
            	t.rad *= i;
        		do n /= i; while (n % i == 0);
            }
        }

        // 3. Wenn n am Ende > 2 ist, ist der verbleibende Rest selbst eine Primzahl
        if (n > 2) t.rad *= n;
    }
	
	
	
	public static long _or(int n, int o) {
		List<Token> tl = new ArrayList<Token>(n);
		for (int i=1; i<=n;i++) {
			Token t= new Token();
			tl.add(t);
			t.i = i;
			ermittlePrimfaktoren(t);
		}
		tl.sort((a,b)-> 2*Long.signum(a.rad-b.rad) + Long.signum(a.i-b.i) );
		int count=1;
		for(Token t:tl) {
			System.out.println(count + " " + t.i +" "+ t.rad);
			count++;
		}
		return tl.get(o-1).i;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _or(100_000, 10_000) );
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
