/**
 * 
 */

import java.util.PriorityQueue;

/**
 * 
 */
public class ProjectEuler_0018_maximum_path_sum {



	/**
	 * 
	 */
	public ProjectEuler_0018_maximum_path_sum() {
		// TODO Auto-generated constructor stub
	}

	public static String s =
					  "75\r\n"
					+ "95 64\r\n"
					+ "17 47 82\r\n"
					+ "18 35 87 10\r\n"
					+ "20 04 82 47 65\r\n"
					+ "19 01 23 75 03 34\r\n"
					+ "88 02 77 73 07 63 67\r\n"
					+ "99 65 04 28 06 16 70 92\r\n"
					+ "41 41 26 56 83 40 80 70 33\r\n"
					+ "41 48 72 33 47 32 37 16 94 29\r\n"
					+ "53 71 44 65 25 43 91 52 97 51 14\r\n"
					+ "70 11 33 28 77 73 17 78 39 68 17 57\r\n"
					+ "91 71 52 38 17 14 91 43 58 50 27 29 48\r\n"
					+ "63 66 04 68 89 53 67 30 73 16 69 87 40 31\r\n"
					+ "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";

	public static int[][] ia(String s){
		int z= (-7+(int)Math.sqrt(49+12*(2*s.length()+4)))/6;
		int count=0;
		int[][] ia = new int[z+1][];
		for (int i=0;i<=z;i++) {
			ia[i] = new int[i+1];
			for (int j = 0; j <= i; j++) {
				ia[i][j] = 100-Integer.parseInt(s.substring(count, count+2));
//				System.out.println(i+" "+j+ " "+count + " "+ s.substring(count, count+2));
				if (j < i) count+=3;
			}
			count+=4;
		}
		return ia;
	}


	public static long mps(String s) {
		long mps=Integer.MAX_VALUE;
		int count = 0;
		int[][] ia = ia(s);
		//length of N
		for (int i=0;i<ia.length;i++) {
			for (int j = 0; j < ia[i].length; j++) {
				count++;
			}
		}
		//initialize costs
		int[] costs = new int[count];

		//Dijkstra
		//		PriorityQueue<Ab> q = new PriorityQueue<Ab>();
		int index=0;
		for (int i=0;i<ia.length;i++) 
			for (int j = 0; j < ia[i].length; j++,index++) {
				int costl=ia[i][j];
				int costr=ia[i][j];
				if (j>0) costl+=costs[index-i-1];
				else if(i!=0)costl=Integer.MAX_VALUE;
				if (j<i) costr+=costs[index-i];
				else if(i!=0)costr=Integer.MAX_VALUE;
				if(costl>costr) costs[index]=costr;
				else costs[index]=costl;
				System.out.println(i+" "+j+" "+costl+" "+costr+" "+costs[index]);
			}
		for (int i=index-1 ; i> index - ia.length; i--) {
			if (costs[i]<mps) mps=costs[i];
			System.out.println(index-i);
		}


		return mps;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the maximum path (by using minimum path)

		//		int z= (-7+(int)Math.sqrt(49+12*(2*s.length()+4)))/6;
		//		System.out.println(z);
		//		
		//		for (int[] a:ia(s)) {
		//			for(int b:a)System.out.print(b+ " ");
		//			System.out.println();
		//		}


		long startTime = System.nanoTime();
		System.out.println( 1500-mps(s) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/100000
	}

}
