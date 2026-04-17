/**
 * 
 */
import java.util.ArrayList;
import java.util.Objects;
/**
 * 
 */
public class ProjectEuler_0066_diophantine_equation {

	/**
	 * 
	 */
	public ProjectEuler_0066_diophantine_equation() {
		// TODO Auto-generated constructor stub
	}


	public static class Token{
		int a, b, c;
		Token (int a, int b, int c){
			this.a=a;
			this.b=b;
			this.c=c;
		}
		@Override
		public int hashCode() {
			return Objects.hash(a, b, c);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Token other = (Token) obj;
			return a == other.a && b == other.b;
		}
	}


	public static ArrayList<Token> psr(int n) {
		int ai , bi, ci, a0;
		a0 = (int)Math.floor(Math.sqrt(n));
		if (a0*a0 == n) return null;
		ai=a0;
		bi=1;
		ci=ai;
		ArrayList<Token> f = new ArrayList<Token>();
		Token t = new Token (ai,bi,ci);
		System.out.println(" n " +n + " ai " + ai + " bi " + bi);
		while(!f.contains(t)) {
//			System.out.println("Token added " + " ai " + ai + " bi " + bi + " ci " + ci);
			f.add(t);
			bi=(n-ai*ai)/bi;
			ci= (a0+ai)/bi;
			ai = a0-(a0+ai)%bi;
			t = new Token (ai,bi,ci);
		}
		return f;
	}




	public static long de(int n) {
		long xmax = 0, overflowmax=0, d=0;
		for (long D=2; D<1001; D++) {
			long sqD=(long)Math.floor(Math.sqrt(D));
			if (sqD*sqD==D) continue;
			System.out.print("D " + D);
			ArrayList<Token> l = psr((int)D);
			long x1 = 0;
			long x = 1;
			long tmp;
			long overflow = 0;
			for (Token t:l) {
				tmp = x*t.c + x1; 
				x1=x;
				x=tmp;
				if (x<x1) overflow++;
			}
			if (l.size()%2==1) {
				l.get(0).c *=2;
				for (Token t:l) {
					tmp = x*t.c + x1; 
					x1=x;
					x=tmp;
					if (x<x1) overflow++;
				}
			}
			System.out.println(" x " + x + " overflow " + overflow);
			if (overflow>overflowmax || (overflow==overflowmax && x>xmax)) {
				overflowmax=overflow;
				xmax=x;
				d=D;
				System.out.println( " newmax");
			}
		} 
		return d;
	}

	/**
		for (long D=2; D<10; D++) {
			long sqD=(long)Math.floor(Math.sqrt(D));
			long sqD2 = 2*sqD;
			ArrayList<long[]> fracs = new ArrayList<long[]>();
			if (sqD*sqD==D) continue;
			System.out.print("D " + D + " ");
			long[] frac = {D,sqD,0L,1L,sqD};
			do {
				System.out.print(" " + frac[4]);
				fracs.add(frac);
				frac = ProjectEuler_Library.contFrac(frac);
			}while (frac[4]!=sqD2);
			System.out.println(" length " + fracs.size());
		return d;
	}
	 */




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( de(1000) );
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
