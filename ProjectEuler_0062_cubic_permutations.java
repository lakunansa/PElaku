/**
 * 
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * 
 */
public class ProjectEuler_0062_cubic_permutations {

	/**
	 * 1. arrange numbers into arraylist
	 * 2. sort arraylist
	 * 3. rearrange number
	 */
	public ProjectEuler_0062_cubic_permutations() {
		// TODO Auto-generated constructor stub
	}
	
	public static class Token {
		long n;
		byte c;
		int o;
		Token (long n, int o){
			this.n=n;
			this.o=o;
		}
		@Override
		public int hashCode() {
			return Objects.hash(n);
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
			return n==other.n;
		}
		
		
	}
	
	public static long cp(int n) {
		ArrayList<Token> checked = new ArrayList<Token>(); //ordered List
		int count = 1000;
		while (count < 8385){//Long.MAX_VALUE) {
			count++;
			System.out.println("count " + count);
			long ccl = (long)(count*count)*(long)count;
			long ccube = ccl;
			ArrayList<Byte> singles = new ArrayList<Byte>(); //ordered List
			while ( ccl>0 ) {
				singles.add( (byte)(ccl%10) );
				ccl/=10L;
//				System.out.println("cube "+ccube + " u:singles.size " + singles.size());
			}
			singles.sort((Byte a, Byte b) -> a-b);
			while (!singles.isEmpty()) {
				ccl *= 10L;
				ccl += singles.removeLast();
//				System.out.println("cube "+ccube + " d:singles.size " + singles.size());
			}
			Token tcc = new Token (ccl, count);
			if (!checked.contains(tcc)) {
				System.out.println("not yet checked in this cube: " + ccl + " " + ccube);
				checked.add(tcc);
//				checked.sort((Token a, Token b) -> a.n.compareTo(b.n) );
			}else {
				Token otherTcc = checked.get(checked.indexOf(tcc));
				System.out.println("checked in this cube bisher " + otherTcc.c + " " + ccl + " " + ccube);
				if (otherTcc.c <3) otherTcc.c++;
				else return otherTcc.o;
			}
		}
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( cp(10001) );
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
