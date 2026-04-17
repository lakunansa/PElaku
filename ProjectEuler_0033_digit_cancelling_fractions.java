/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0033_digit_cancelling_fractions {

	/**
	 * 
	 */
	public ProjectEuler_0033_digit_cancelling_fractions() {
		// TODO Auto-generated constructor stub
	}


	public static long dcf(int n) {
		ArrayList<Integer> pq = new ArrayList<Integer>(8);
		for (int i=12;i<=97;i++)
			for (int j=i+1;j<=98;j++) {
//				System.out.println(i + " "+ j);
				int A=i/10, B=i%10, C=j/10, D=j%10;
				if (A==B || C==D || A==C || B==D)continue;
				if (A!=D && B!=C) continue;
				if (A==D && B*j==C*i) {
//					System.err.println(i+" "+j);
					pq.add(B); pq.add(C);
				}
				if (B==C && A*j==D*i) {
					System.out.println(i+" "+j);
					pq.add(A); pq.add(D);
				}
			}
		long prodd=1,prodn=1;
		for (int i=0;i<pq.size();i++) {
			if (i%2==0)prodd*=pq.get(i);
			else prodn*=pq.get(i);
			System.out.print(pq.get(i)+" ");
		}
		System.out.println();
		for (int k=2; k<prodn;k++)
			while (prodd%k==0 && prodn%k==0) {
				prodd/=k;
				prodn/=k;
			}
		return prodn;	
	}




	/**
	 * @par()args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the product of these four fractions is given in its lowest common terms, find the value of the denominator


		long startTime = System.nanoTime();
		System.out.println( dcf(2) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);
	}
}
