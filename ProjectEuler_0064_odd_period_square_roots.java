/**
 * 
 */
import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 */
public class ProjectEuler_0064_odd_period_square_roots {

	/**
	 * 
	 */
	public ProjectEuler_0064_odd_period_square_roots() {
		// TODO Auto-generated constructor stub
	}

	public static class Token{
		int a, b;
		Token (int a, int b){
			this.a=a;
			this.b=b;
		}
		@Override
		public int hashCode() {
			return Objects.hash(a, b);
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
	
	
	public static long opsr(int n) {
		int count = 0;
		while (n>1) {
			n--;
			ArrayList<Token> f = new ArrayList<Token>();
			int ai , bi, a0;
			a0 = (int)Math.floor(Math.sqrt(n));
			if (a0*a0 == n) continue;
			ai=a0;
			bi=1;
			Token t = new Token (ai,bi);
			System.out.println("n " +n + " ai " + ai + " bi " + bi);
			while(!f.contains(t)) {
				System.out.println("Token added " + " ai " + ai + " bi " + bi);
				f.add(t);
				bi=(n-ai*ai)/bi;
				ai = a0-(a0+ai)%bi;
				t = new Token (ai,bi);
			}
			if (f.size()%2==1) count++;
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
		System.out.println( opsr(100) );
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
