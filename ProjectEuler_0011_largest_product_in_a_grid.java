/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0011_largest_product_in_a_grid {

	/**
	 * 
	 */
	public ProjectEuler_0011_largest_product_in_a_grid() {
		// TODO Auto-generated constructor stub
	}


	public static String s=
					  "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\r\n"
					+ "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\r\n"
					+ "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\r\n"
					+ "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\r\n"
					+ "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\r\n"
					+ "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\r\n"
					+ "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\r\n"
					+ "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\r\n"
					+ "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\r\n"
					+ "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\r\n"
					+ "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\r\n"
					+ "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\r\n"
					+ "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\r\n"
					+ "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\r\n"
					+ "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\r\n"
					+ "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\r\n"
					+ "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\r\n"
					+ "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\r\n"
					+ "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\r\n"
					+ "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48\r\n";

	public static byte[][] cs(String s){
		byte[][]cs = new byte[20][20];
		for (int i=0;i<20;i++)
			for (int j=0;j<20;j++)
				cs[j][i] = Byte.parseByte(s.substring(j*61+3*i, j*61+3*i+2) );
		return cs;
	}
	public static byte[][] sl (byte[][] ba){
		byte[][] sl = new byte[39][20];
		for (int i=0;i<20;i++)
			for (int j=0;j<20;j++) 
				sl[i+j][j] = ba[i][j];
		return sl;
	}
	public static byte[][] sr (byte[][] ba){
		byte[][] sr = new byte[39][20];
		for (int i=0;i<20;i++)
			for (int j=0;j<20;j++) 
				sr[i-j+19][i] = ba[i][j];
		return sr;
	}
	public static byte[][] ro (byte[][] ba){
		byte[][] ro = new byte[20][20];
		for (int i=0;i<20;i++)
			for (int j=0;j<20;j++) 
				ro[j][i] = ba[i][j];
		return ro;
	}

	public static long largest_product(String s) {

		long maxpr=0;
		long[] pr = {1,1,1,1};
		byte[] cz = {0,0,0,0};
		//h, diag1, diag2, v
		byte[][] cs = cs(s);
		byte[][][] b= {cs,sl(cs), sr(cs), ro(cs)};
//		byte[][][] b= {cs};

		for (int i=0; i<b.length;i++)
			for (int j=0;j<b[i].length;j++) {
//				System.out.println(" "+i+" "+j+" "+ pr[i] + " "+ cz[i]);
				pr[i]=1;
				cz[i]=0;
				for (int k=0; k<4; k++)
					if(b[i][j][k] == 0)cz[i]++;
					else {
						pr[i]*=b[i][j][k];
//						if(pr[i]==0)System.out.println("fehler");
					}
				if(cz[i]==0 && pr[i]>maxpr) {
//					for (int l=0;l<4;l++) System.out.print(b[i][j][l]+" ");
//					System.out.println(3 +" "+i+" "+j+" "+ pr[i] + " "+ maxpr);
					maxpr=pr[i];
				}
				for (int k=4;k<b[i][j].length;k++) {
					if(b[i][j][k] == 0)cz[i]++;
					else {
						pr[i]*=b[i][j][k];
//						if(pr[i]==0)System.out.println("fehler");
					}
					if(b[i][j][k-4] == 0)cz[i]--;
					else pr[i]/=b[i][j][k-4];
					if(cz[i]==0 && pr[i]>maxpr) {
//						for (int l=0;l<4;l++) System.out.print(b[i][j][k-l]+" ");
//						System.out.println(k +" "+i+" "+j+" "+ pr[i] + " "+ maxpr);
						maxpr=pr[i];
					}
				}
			}
		return maxpr;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes greatest product of 4 adjacent numbers in all 6 directions in the given grid

		byte[][] cs = (cs(s));
		for(int i=0;i<cs.length;i++) {
			for(int j=0;j<cs[i].length;j++) {
				System.out.print(cs[i][j]+" ");
			}
			System.out.println();
		}
		
		
		long startTime = System.nanoTime();
		System.out.println( largest_product(s) );
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
