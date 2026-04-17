/**
 * 
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 
 */
public class ProjectEuler_0054_poker_hands {

	/**
	 * 
	 */
	public ProjectEuler_0054_poker_hands() {
		// TODO Auto-generated constructor stub
	}

	public static byte eval(String a){
		//first is player 1, second player 2
		byte[] cR1 = new byte[15], cR2 = new byte[15], cC1 = new byte[4], cC2 = new byte[4];
		//parses string
		for (int i=0;i<5;i++) {
//			System.out.println("here");
			char b = a.charAt(3*i);
			switch (b) {
			case 'T': cR1[10]++; break;
			case 'J': cR1[11]++; break;
			case 'Q': cR1[12]++; break;
			case 'K': cR1[13]++; break;
			case 'A': cR1[1]++; cR1[14]++; break;
			default: cR1[b-48]++;
			}
			b = a.charAt(3*i+15);
			switch (b) {
			case 'T': cR2[10]++; break;
			case 'J': cR2[11]++; break;
			case 'Q': cR2[12]++; break;
			case 'K': cR2[13]++; break;
			case 'A': cR2[1]++; cR2[14]++; break;
			default: cR2[b-48]++;
			}
			b = a.charAt(3*i+1);
			switch (b) {
			case 'C': cC1[0]++; break;
			case 'S': cC1[1]++; break;
			case 'H': cC1[2]++; break;
			case 'D': cC1[3]++; break;
			}
			b = a.charAt(3*i+16);
			switch (b) {
			case 'C': cC2[0]++; break;
			case 'S': cC2[1]++; break;
			case 'H': cC2[2]++; break;
			case 'D': cC2[3]++; break;
			}
		}
		//builds up histogram
		byte[] histo1 = new byte[9], histo2  = new byte[9];
		for (byte i=14, ones1=4, ones2=4,twos1=2, twos2=2;i>1;i--) {
			switch (cR1[i]) {
			case 4: histo1[0]=i; break;
			case 3: histo1[1]=i; break;
			case 2: histo1[twos1]=i; twos1++; break;
			case 1: histo1[ones1]=i; ones1++; break;
			}
			switch (cR2[i]) {
			case 4: histo2[0]=i; break;
			case 3: histo2[1]=i; break;
			case 2: histo2[twos2]=i; twos2++; break;
			case 1: histo2[ones2]=i; ones2++; break;
			}
		}
		byte fl=0; //fl indicate flushs
		for (int i=0;i<4;i++) {
			if (cC1[i]==5) fl++;
			if (cC2[i]==5) fl--;
		}
		byte st=0; //st indicate straights
		for (byte i=1;i<11;i++) {
			if (cR1[i]*cR1[i+1]*cR1[i+2]*cR1[i+3]*cR1[i+4]==1) st++;
			if (cR2[i]*cR2[i+1]*cR2[i+2]*cR2[i+3]*cR2[i+4]==1) st--;
		}
		byte hi=0; //beats flush and co...
		if ( histo1[0]>0 || (histo1[1]*histo1[2]>0 ) ) hi++;
		if ( histo2[0]>0 || (histo2[1]*histo2[2]>0 ) ) hi--;
		byte hr=0; //wins histogram 1,0,-1 --- 1,draw,2
		{
			int j = 0;
			for (j=0;j<9;j++) {
				if (histo1[j]>histo2[j]) {
					hr=1;
					break;
				}
				if (histo2[j]>histo1[j]) {
					hr=-1;
					break;
				} 
			}
			//to correct in cases of "small" fullhouse VS "big" threeoak, or "small" twopair V "big" one pair
			if (j==1 || j==2) {
				if ( hr>0 && histo2[j] > 0 && histo2[j+1] > 0 && histo1[j+1] == 0 ) {
					hr=-1;
				} else if ( hr<0 && histo1[j] > 0 && histo1[j+1] > 0 && histo2[j+1] == 0 ) {
					hr=1;
				}
			}
		}
		for (int i=0;i<9;i++) {
			System.out.print(histo1[i]+" ");
		}
		System.out.println(hi + "" + fl + st + hr);
		for (int i=0;i<9;i++) {
			System.out.print(histo2[i]+" ");
		}
//		System.out.println("");

		if (st*fl ==1) return st;
		if (hi!=0) return hi;
		if (fl!=0) return fl;
		if (st!=0) return st;
		return hr;
	}

	public static long ph(int n) {
		Path filePath = Path.of("C:\\Users\\Dell\\Downloads\\0054_poker.txt");
		String content; 
		String[] hands = null; 
		try {
			content = Files.readString(filePath);
			hands = content.split("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count=0;
		for (String a:hands) {
			System.out.println(a);
			byte d= eval(a);
			System.out.println("  "+d+"\n");
			if (d>0) count++;
		}
		return count;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of poker wins of player 1 in the poker.txt file 


		long startTime = System.nanoTime();
		System.out.println( ph(10001) );
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
