/**
 * 
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0059_xor_cipher {

	/**
	 * 
	 */
	public ProjectEuler_0059_xor_cipher() {
		// TODO Auto-generated constructor stub
	}


	public static long XORd(int n) {
		Path filePath = Path.of("C:\\Users\\Dell\\Downloads\\0059_cipher.txt");
		String content=null; 
		try {
			content = Files.readString(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] contentSplit = content.split("[\",]",0);
		ArrayList<Character> ci = new ArrayList<Character>();
		for (String cs:contentSplit) ci.add((char)Byte.parseByte(cs));
		class Token implements Comparable<Token>{
			byte mod3;
			char c;
			int f;
			Token( byte mod3, char c){
				this.mod3=mod3;
				this.c=c;
			}
			boolean isEqual(Token t) {
				return (this.mod3 == t.mod3 && this.c == t.c);
			}
			@Override
			public int compareTo(Token o) {
				return o.f-this.f;
			}
		}
		ArrayList<Token> tl = new ArrayList<Token>(90);
		int count = 0;
		for (Character cci:ci) {
			Token t = new Token((byte)(count%3), (char)cci);
			t.f++;
			boolean isinl = false;
			for (int i=0; i<tl.size();i++) {
				Token u = tl.get(i);
				if (t.isEqual(u)) {
					u.f++;
					isinl = true;
					break;
				}
			}
			if (!isinl) {
				tl.add(t);
			}
			count++;
		}
		for (int i=0;i<tl.size();i++)
			for (int j=i+1;j<tl.size();j++) {
				Token a = tl.get(i), b=tl.get(j);
				if (a.compareTo(b)>0) {
					tl.set(j, a);
					tl.set(i, b);
				}
			}
		for (Token t:tl) {
			System.out.print("c "+ (byte)(t.c) + " mod " + t.mod3 + " freq " + t.f);
			System.out.println();
		}

		int count2, count3, small=Integer.MAX_VALUE, si=256, sj=256, sk=256;
		for (char i=97; i<123; i++)
			for (char j=97; j<123; j++)
				for (char k=97; k<123; k++) {
					count2=0; count3=0;
					boolean stop = false;
					ArrayList<Character> cl = new ArrayList<Character>(ci.size());
					for (char cci : ci) {
						char tmp;
						if (count2%3==0)	tmp = (char)(cci^i);
						else if (count2%3==1) tmp = (char)(cci^j);
						else if (count2%3==2) tmp = (char)(cci^k);
						else break;			
						if (tmp <97 || tmp >122) count3++;
						count2++;
					}
					if (count3 <= small) {
						small=count3;
						si=i;
						sj=j;
						sk=k;
						System.out.println(""+i+j+k+" "+count3+ " "+ small + " " +si+ " "+sj+ " "+sk);
					}
//					System.out.println(""+i+j+k+" "+count3+ " "+ small + " " +si+ " "+sj+ " "+sk);
//					if (stop) continue;
//					for (char ccl : cl) System.out.print(ccl);
//					System.out.println();
				}
		ArrayList<Character> cl = new ArrayList<Character>(ci.size());
		count=0;
		int result=0;
		for (char cci : ci) {
			char tmp=0;
			if (count%3==0)	tmp = (char)(cci^'e');
			else if (count%3==1) tmp = (char)(cci^'x');
			else if (count%3==2) tmp = (char)(cci^'p');
			cl.add(tmp);
			result +=tmp;
			count++;
		}
		for (char ccl : cl) System.out.print(ccl);
		System.out.println();


		return result;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes deciphers the cipher given,
		//key is computed by freq analysis

		long startTime = System.nanoTime();
		System.out.println( XORd(10001) );
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
