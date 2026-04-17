/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0003_largest_prime_factor {

	/**
	 * 
	 */
	public ProjectEuler_0003_largest_prime_factor() {
		// TODO Auto-generated constructor stub
	}


	public static long larPriKI(long l) {//better factor 10
		long lastFactor = 1;

		// 2 separat behandeln
		if (l % 2 == 0) {
			lastFactor = 2;
			while (l % 2 == 0) l /= 2;
		} else {
			lastFactor = 1;
		}

		// Nur ungerade Zahlen prüfen
		for (long i = 3; i * i <= l; i += 2) {
			if (l % i == 0) {
				lastFactor = i;
				while (l % i == 0) l /= i;
			}
		}

		// Wenn l am Ende > 1 ist, ist der Rest der größte Primfaktor
		return (l > 1) ? l : lastFactor;
	}


	public static long larPri(long l){//logischer fehler? ja! gibt 1 aus, wenn larpri in der schleife war
		while (l%2==0) l /= 2;
		long target = (long) Math.sqrt(l);
		for (int i=3;i<=target;i+=2) {
			while (l%i == 0) l /= i;
			target = (long) Math.sqrt(l);
		}
		return l;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//finds largest prime factor of given number

		{
			long startTime = System.nanoTime();
			System.out.println( larPri( 600851475143L ) );
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration);
		}
		{
			long startTime = System.nanoTime();
			System.out.println( larPriKI( 600851475143L ) );
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration);
		}
	}

}
