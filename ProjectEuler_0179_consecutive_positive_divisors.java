/**
 * 
 */
public class ProjectEuler_0179_consecutive_positive_divisors {

	/**
	 * 
	 */
	public ProjectEuler_0179_consecutive_positive_divisors() {
		// TODO Auto-generated constructor stub
	}

	public static class Token{
		int i;
		int rad;
	}
	
	public static int ermittlePrimfaktoren(int n) {
        int num = 1;
        int count = 0;
        // 1. Alle Faktoren von 2 entfernen (einzige gerade Primzahl)
        if(n % 2 == 0) {
    		do {
    			count++;
    			n /= 2; 
    		}while (n % 2 == 0);
            num*=(count+1);
        }

        // 2. Ungerade Teiler ab 3 bis sqrt(n) prüfen
        // Wir springen in 2er Schritten (i += 2), da n jetzt ungerade ist
        for (int i = 3; i * i <= n; i += 2) {
            if(n % i == 0) {
            	count=0;
        		do {
        			count++;
        			n /= i; 
        		}while (n % i == 0);
                num*=(count+1);
            }
        }

        // 3. Wenn n am Ende > 2 ist, ist der verbleibende Rest selbst eine Primzahl
        if (n > 2) num*=2;
        return num;
    }

	

	public static long _9(int n) {
		int count = 0;
//		int[] numF = new int[n+1];
		int lastNum =2;
		for (int i=3; i<=n;i++) {
			int curNum = ermittlePrimfaktoren(i);
//			System.out.println(i+ " " + curNum + " "+lastNum);
			if (curNum == lastNum) count++;
			lastNum=curNum;
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _9((int)10000000) );
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
