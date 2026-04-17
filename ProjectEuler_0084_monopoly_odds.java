/**
 * 
 */


/**
 * 
 */
public class ProjectEuler_0084_monopoly_odds {

	/**
	 * 
	 */
	public ProjectEuler_0084_monopoly_odds() {
		// TODO Auto-generated constructor stub
	}

	public static String[] board = {
			"GO",
			"A1",
			"CC1",
			"A2",
			"T1",
			"R1",
			"B1",
			"CH1",
			"B2",
			"B3",
			"JAIL",
			"C1",
			"U1",
			"C2",
			"C3",
			"R2",
			"D1",
			"CC2",
			"D2",
			"D3",
			"FP",
			"E1",
			"CH2",
			"E2",
			"E3",
			"R3",
			"F1",
			"F2",
			"U2",
			"F3",
			"G2J",
			"G1",
			"G2",
			"CC3",
			"G3",
			"R4",
			"CH3",
			"H1",
			"T2",
			"H2"
	};


	public static String mo(int n) {
		//1. calculate throws
		int[] dR = new int[2*n+1];
		for (int d1=1; d1<=n;d1++) 
			for (int d2=1; d2<=n;d2++) 
				dR[d1+d2]++;

		//has to be normalized by 1/n^2 
		int[][] tN = new int[40][40];
		for (int i=0; i<40;i++)
			for (int d=2; d<dR.length; d++)
				tN[i][(i+d)%40] = dR[d];
		//has to be normalized by 1/16
		int[][] tA = new int[40][40];
		//all squares
		for (int i=0;i<40;i++) tA[i][i]=16;
		//G2J
		tA[30][30]=0; tA[30][10]=16;
		//CCs
		int[] CCs = {2,17,33}; //cc1,cc2,cc3
		int[] tCCs = {0,10}; //go, jail
		for (int cc:CCs) {
			tA[cc][cc]=14;
			for (int tcc:tCCs)
				tA[cc][tcc]=1;
		}
		//CHs
		int[] CHs = {7,22,36}; //ch1, ch2, ch3
		int[] tCHs = {0,5,10,11,24,39};
		int[] Rs = {5,15,25,35};
		int[] Us = {12,28};
		for (int ch:CHs) {
			tA[ch][ch]=6;
			for (int tch:tCHs)
				tA[ch][tch]=1;
			tA[ch][ch-3]=1;
			tA[ch][ ((int)( ( ( Math.ceil( (ch+5)/10.0 ) )*10)-5)  )%40]+=2;
			if (ch<Us[0] || ch>Us[1]) 
				tA[ch][Us[0]]=1;
			else 
				tA[ch][Us[1]]=1;
		}

		double[][] real = new double[40][40];
		for (int i=0; i<40;i++) 
			for (int j=0; j<40; j++)
				for (int k=0; k<40; k++)
					real[i][j] += (tN[i][k]*tA[k][j])/256.0; 	



		// for the ungeduldige: ohne die drei double regel
		//und kondiionieren!!! 10 durchläufe reichen, aber das system degereneriert fast
		for (int m=0; m<10;m++) {
			double[][] realCopy = new double[40][40];
			for (int i=0; i<40;i++) 
				for (int j=0; j<40; j++)
					for (int k=0; k<40; k++)
						realCopy[i][j] += real[i][k]*real[k][j]; 	
			for (int i=0; i<40;i++) 
				for (int j=0; j<40; j++)
					real[i][j]=realCopy[i][j];
		}




		//ab hier 
		double[]sums = new double [40];
		for (int i=0; i<40;i++) 
			for (int j=0; j<40; j++)
				sums[j] += real[i][j];

		double[]sumsW = new double[40];
		for (int i=0; i<40;i++) 
			for (int j=0; j<40; j++)
				sumsW[i] += real[i][j];

		
		double max = 0; int maxi1=-1;
		for (int i=0; i<40;i++) 
			if (sums[i]>max) {
				max = sums[i]; maxi1 = i;
			}
		max = 0; int maxi2 = -1;
		for (int i=0; i<40;i++) 
			if (sums[i]>max && i != maxi1) {
				max = sums[i]; maxi2 = i;
			}
		max = 0; int maxi3=-1;
		for (int i=0; i<40;i++) 
			if (sums[i]>max && i != maxi1 && i != maxi2) {
				max = sums[i]; maxi3 = i;
			}


		for (int i=0; i<40;i++) System.out.print(sums[i] + "," + sumsW[i] + " ");
System.out.println("\n");
		
		for (int i=0; i<40;i++) {
			for (int j=0; j<40; j++)
				System.out.print(real[i][j] + "  ");
			System.out.println();	
		}

		for (int i=0; i< dR.length; i++) System.out.print(i + " " + dR[i] + "   ");
		System.out.println();

		for (int i=0; i<40;i++) 
			System.out.print(i + " " + board[i] + "   ");
		System.out.println();	

		return ""+maxi1+maxi2+maxi3;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( mo(4) );
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
