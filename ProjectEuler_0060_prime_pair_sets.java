/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0060_prime_pair_sets {

	/**
	 * 
	 */
	public ProjectEuler_0060_prime_pair_sets() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Token{
		long n;
		long tens;
		Token (long n){
			this.n = n;
			this.tens = (long) Math.pow(10, Math.ceil (Math.log10((double)n)));
		}
	}
	
	public static long pps(long n) {
		ArrayList<Token> pl1 = new ArrayList<Token>();
		long count = 1;
		ArrayList<Token[]> pl2,pl3,pl4,pl5; 
		pl2 = new ArrayList<Token[]>();
		pl3 = new ArrayList<Token[]>();
		pl4 = new ArrayList<Token[]>();
		pl5 = new ArrayList<Token[]>();
		while (pl5.isEmpty()) {
			count+=2;
			while(!ProjectEuler_Library.isPrime(count)) count+=2;
			System.out.println(count);
			Token tn = new ProjectEuler_0060_prime_pair_sets.Token(count);
			for (Token[] t4:pl4)
				if (ProjectEuler_Library.isPrime(tn.n * t4[0].tens+t4[0].n) && 
					ProjectEuler_Library.isPrime(t4[0].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t4[1].tens+t4[1].n) && 
					ProjectEuler_Library.isPrime(t4[1].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t4[2].tens+t4[2].n) && 
					ProjectEuler_Library.isPrime(t4[2].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t4[3].tens+t4[3].n) && 
					ProjectEuler_Library.isPrime(t4[3].n * tn.tens+tn.n)) 
					pl5.add( new Token[] {t4[0],t4[1],t4[2],t4[3],tn} );
			for (Token[] t3:pl3)
				if (ProjectEuler_Library.isPrime(tn.n * t3[0].tens+t3[0].n) && 
					ProjectEuler_Library.isPrime(t3[0].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t3[1].tens+t3[1].n) && 
					ProjectEuler_Library.isPrime(t3[1].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t3[2].tens+t3[2].n) && 
					ProjectEuler_Library.isPrime(t3[2].n * tn.tens+tn.n)) 
					{
					pl4.add( new Token[] {t3[0],t3[1],t3[2],tn} );
					System.out.println("four added " + t3[0].n + " " + t3[1].n + " " + t3[2].n + " " + tn.n);
					}
			for (Token[] t2:pl2)
				if (ProjectEuler_Library.isPrime(tn.n * t2[0].tens+t2[0].n) && 
					ProjectEuler_Library.isPrime(t2[0].n * tn.tens+tn.n) &&
					ProjectEuler_Library.isPrime(tn.n * t2[1].tens+t2[1].n) && 
					ProjectEuler_Library.isPrime(t2[1].n * tn.tens+tn.n) ) 
					{
					pl3.add( new Token[] {t2[0],t2[1],tn} );
					System.out.println("three added " + t2[0].n + " " + t2[1].n + " " + tn.n);
					}
			for (Token t1:pl1)
				if (ProjectEuler_Library.isPrime(tn.n * t1.tens+t1.n) && 
					ProjectEuler_Library.isPrime(t1.n * tn.tens+tn.n) ) 
					{
					pl2.add( new Token[] {t1,tn} );
					System.out.println("two added " + t1.n + " " + tn.n);
					}
			pl1.add(tn);
			System.out.println("prime added " + tn.n + " " + tn.tens);
		}
		Token[] t5 = pl5.getFirst();
		return t5[0].n+t5[1].n+t5[2].n+t5[3].n+t5[4].n;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( pps(10001) );
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
