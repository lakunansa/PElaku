/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0086_cuboid_route {

	/**
	 * 
	 */
	public ProjectEuler_0086_cuboid_route() {
		// TODO Auto-generated constructor stub
	}

	
	public static long cr_faster(int n) {
		int M = 0;
		int totalCount = 0;
		while (totalCount < n) {
		    M++;
		    // Prüfe alle S = a + b für das aktuelle M
		    for (int S = 2; S <= 2 * M; S++) {
		        double dist = Math.sqrt(S * S + M * M);
		        if (dist == (int) dist) { // Ganzzahlig?
		            if (S <= M) {
		                totalCount += S / 2;
		            } else {
		                totalCount += (M - (S + 1) / 2 + 1);
		            }
		        }
		    }
		}
		return M;
	}
	
	public static class Token{
		Token(int M, int other){
			this.M = M;
			this.other = other;
		}

		int M;
		int other;
	}


	public static long cr(int n) {
//		n=2000;
		int M=3;
		ArrayList<ArrayList<Token>> counts = new ArrayList<ArrayList<Token>>();
		for (int i=0;i<3;i++) counts.add(new ArrayList<ProjectEuler_0086_cuboid_route.Token>());
		long realCount=0;
		do {
//			System.out.println("M "+M);
			realCount=0;
			ArrayList<Token> l = new ArrayList<ProjectEuler_0086_cuboid_route.Token>();
			for1: for (int i=2;;i++) {
				if (2*i-1>M) break for1;
				for2: for (int j=1;j<i;j++) {
					int first=(i*i-j*j), second = 2*i*j;
					if (first > M && second >M) continue for2;
					else if ( (i>j) && (i+j)%2==1 && ProjectEuler_Library.gcd(i,j)==1) {
//						System.out.println(i + " " + j + " "+ M);

						if (first == M && second <= 2*M) {
							l.add(new Token(M, second));
							System.out.println("token added " + M + " " + second);
//							if (second <= first+1) countM+=second/2;
//							else if (second <= 2*first ) countM+=(2*first+2-second)/2;
						}else if (second==M && first <= 2*M) {
							l.add(new Token(M, first));
							System.out.println("token added " + M + " " + first);
//							if (first <= second+1) countM+=first/2;
//							else if (first <= 2*second) countM+=(2*second+2-first)/2;
						}
					}
				}
			}
			counts.add(l);
			for(ArrayList<Token> m : counts) 
				for (Token t: m ){
					for (int i=1; i<=(M/t.M); i++) {
						realCount += Math.max(0,Math.floor((i*t.other)/2)-Math.max(1, i*(t.other-t.M))+1);
						
//						for (int j=1; 2*j<=i*t.other; j++)
//							if (((i*t.other-j) <= i*t.M) ){
//
//									System.out.println("add " + i*t.M + " " + i*t.other + " " + j + " " + (i*t.other-j) + " " + ((i*t.other-j) <=i*t.M) );
//									realCount+=1;
//							}
					}
				}
			System.out.println(realCount);
			M++;
		}while (realCount<n);
		//		for(int i=3;i<M;i++) System.out.println(counts.get(i) + " " + i + " " + M);

		return realCount;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( cr_faster(1_000_000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/

	}

}
