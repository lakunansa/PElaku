/**
 * 
 */
import java.util.PriorityQueue;

/**
 * 
 */
public class ProjectEuler_0029_distinct_powers {

	/**
	 * 
	 */
	public ProjectEuler_0029_distinct_powers() {
		// TODO Auto-generated constructor stub
	}

	public static int rem(int i, int j) {
		for (int k=2;k<=(int)Math.sqrt(j); k++) {
			while (i%k == 0 && j%k == 0) {
				i/=k;
				j/=k;
			}
		}
		return i;
	}

	public static int rem2(int i, int j) {
		for (int k=2;k<=(j); k++) {
			while (i%k == 0 && j%k == 0) {
				i/=k;
				j/=k;
			}
		}
		return i;
	}

	public static long dpn(int n, int base) {
		int barr = (int)Math.floor( Math.log(n)/Math.log(base) );
		boolean[][] grid = new boolean [barr+1][n+1];
		for (int i=barr;i>1;i--) {
			for (int j=i-1;j>=1;j--) {
				int k = rem2(i,j);
				for (int L= (i%j==0) ? 2*k : k; L <=n ;L+=k) {
					System.out.println(j+" "+k+" "+L +" "+( j*L) );
					grid[j][L] = true;
				}
			}
		}
		System.out.println();
		int count=0;
		//counts not checked entries
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) {
					count++;
					//					if (!grid[i][j]) System.out.println(i+" "+j + " "+ i*j);
				}
			}
		}
		//checkes, whether there are checked entries in grid that are not counted...
		int count2=0;
		boolean[] abs=new boolean[barr*n+1];
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) { //if not ckecked double
					abs[i*j]=true; // if not in abs
					count2++;
				}
			}
		}
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (grid[i][j]) { //if in checked
					if (!abs[i*j]) System.err.println(i+" "+j + " "+ i*j);
				}
			}
		}
		System.out.println(count2);

		return count;
	}


	public static long dp3(int n) {
		int barr = (int)Math.floor( Math.log(n)/Math.log(3) );
		boolean[][] grid = new boolean [barr+1][n+1];
		for (int i=barr;i>1;i--) {
			for (int j=i-1;j>=1;j--) {
				int k = rem2(i,j);
				for (int L= (i%j==0) ? 2*k : k; L <=n ;L+=k) {
					System.out.println(j+" "+k+" "+L +" "+( j*L) );
					grid[j][L] = true;
				}
			}
		}
		System.out.println();
		int count=0;
		//counts not checked entries
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) {
					count++;
					if (!grid[i][j]) System.out.println(i+" "+j + " "+ i*j);
				}
			}
		}
		//checkes, whether there are checked entries in grid that are not counted...
		int count2=0;
		boolean[] abs=new boolean[401];
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) { //if not ckecked double
					abs[i*j]=true; // if not in abs
					count2++;
				}
			}
		}
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (grid[i][j]) { //if in checked
					if (!abs[i*j]) System.out.println(i+" "+j + " "+ i*j);
				}
			}
		}
		System.out.println(count2);

		return count;
	}


	public static long dp2(int n) {
		int barr = (int)Math.floor( Math.log(n)/Math.log(2) );
		boolean[] abs = new boolean[(barr)*(n)+1];
		boolean[][] grid = new boolean [barr+1][n+1];
		for (int i=barr;i>=1;i--) {
			for (int j=1;j<i;j++) {
				int k = rem2(i,j);
				for (int L= (i%j==0) ? 2*k : k; L <=n ;L+=k) {
					//					System.out.println(j+" "+k+" "+L +" "+( j*L) );
					grid[j][L] = true;
					//					if (abs[j*L]) {
					//						//					System.err.println(j*L);
					//					}
					//					else {
					//						abs[j*L]=true;
					//					}
					//					count1++;
				}
			}
		}
		int count=0;
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) {
					count++;
					//					System.out.println(i + " "+j);
				}
			}
		}
		//checkes, whether there are checked entries in grid that are not counted...
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) { //if not ckecked double
					if (!abs[i*j])abs[i*j]=true;
					else System.err.println("here");// if not in abs
				}
			}
		}
		for (int i=barr; i>=1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (grid[i][j]) { //if in checked
					if (!abs[i*j]) System.out.println(i+" "+j + " "+ i*j);
				}
			}
		}
		System.out.println(abs[4]+""+rem2(2,1));

		return count;
	}
	public static long dp(int n) {
		boolean[][] grid = new boolean [n+1][n+1];
		int count=0, count1=0, count2=0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(100);
		for (int i=n; i>1;i--) { //iterates over base
			for1:
				//find out whether i is a perfect power:
				for (int p=2, q=(int)Math.round(Math.log(i)/Math.log(p)), r=(int)Math.round(Math.pow(p,q)); q>=2;) {				
					//				System.out.println(i+" "+p+" "+q+" "+r);
					if (r==i) {
						if (pq.contains(p)) break for1;
						else pq.add(p);
						System.out.println(p);
						for (int j=2;j<=q;j++) {
							int newb = p;
							for (int j2=1;j2<j;j2++) {
								int k = rem(j,j2);
								for (int L=(j%j2==0) ? 2*k : k; L <=n ;L+=k) {
									if (!grid[newb][L]) {
										System.out.println(i+" "+newb +" "+j2+" "+k+" "+L +" ");
										grid[newb][L] = true;
										count1++;
									}else {
										System.err.println(i+" "+newb +" "+j2+" "+L +" ");
									}
									count2++;
								}
								newb*=p;
							}
						}
					}
					p++;
					q=(int)Math.round(Math.log(i)/Math.log(p));
					r=(int)Math.round(Math.pow(p,q));
				}
		}
		count=0;
		for (int i=n; i>1;i--) { //iterates over base
			for (int j = n; j > 1; j--) { //iterates over exponent
				if (!grid[i][j]) {
					count++;
					//					System.out.println(i + " "+j);
				}
			}
		}
		System.out.println(count1+" "+count2 + " "+ (count1+count==(n-1)*(n-1)));

		return count;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes many distinct terms are in the sequence generated by a^b, from 2 to n


		long startTime = System.nanoTime();
		System.out.println( dp(100));
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		//		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/1000000);

		//dp2(100):32



	}

}
