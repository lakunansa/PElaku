/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0820_nth_digit_of_reciprocals {

	/**
	 * 
	 */
	public ProjectEuler_0820_nth_digit_of_reciprocals() {
		// TODO Auto-generated constructor stub
	}

	public static long a_power_b_mod_c(long a, long b, long c)
	{
	    long result = 1;
	    while(b>0)
	    {
	        if(b % 2 ==1)
	        {
	            result = (result*a)%c;
	        }
	        a = (a*a)%c;
	        b/=2;
	    }
	    return result;
	}

	public static long d_820(long i, long t){
	    long ans = a_power_b_mod_c(10,t-1,i);
	    ans*=10;
	    ans/=i;
	    ans%=10;
	    return ans;
	}

	public static void problem_820(int t){//KI
	    long sum = 0;
	    for(int i=1;i<=t;i++){
	        sum+=d_820(i,t);
	    }
	    System.out.println(sum);
	}

	
	
	
	public class PeriodenOptimierungFinal {
	    public static void main(String[] args) {
	        long N = 10_000_000;
	    	long k = N; // Die gesuchte Stelle
	        long gesamtSumme = 0;

	        for (int n = 1; n <= N; n++) {
	        	System.out.println(n);
	            gesamtSumme += getKteStelle(n, k);
	        }
	        System.out.println("Korrekte Summe der " + k + ". Stellen: " + gesamtSumme);
	    }

	    public static int getKteStelle(int n, long k) {
	        if (n == 1) return 0;

	        int[] restAnPosition = new int[n]; // Speichert: Bei welchem i trat dieser Rest auf?
	        int[] ziffern = new int[n + 1];    // Speichert: Welche Ziffer wurde an Position i erzeugt?
	        
	        int rest = 1;
	        for (int i = 1; i <= n; i++) {
	            int dividend = rest * 10;
	            int ziffer = dividend / n;
	            int naechsterRest = dividend % n;

	            // 1. Fall: Abbrechend (z.B. 1/2, 1/5)
	            if (naechsterRest == 0) {
	                return (k == i) ? ziffer : 0;
	            }

	            // 2. Fall: Periode erkannt
	            if (restAnPosition[rest] != 0) {
	                int startPos = restAnPosition[rest];
	                int periodenLaenge = i - startPos;
	                
	                if (k < startPos) return ziffern[(int)k]; // Stelle liegt noch vor der Periode
	                
	                int offset = (int)((k - startPos) % periodenLaenge);
	                return ziffern[startPos + offset];
	            }

	            // Speichern und weiter
	            ziffern[i] = ziffer;
	            restAnPosition[rest] = i;
	            rest = naechsterRest;

	            if (i == k) return ziffer;
	        }
	        return 0;
	    }
	}

	
	

	public static long _nthdor(int n) {
		n=100;
		int m=100;
		int limitN=n;

		int count = 0;// this will be returned
		int[] aug = {2,4,2,2};//this is the step function
		int[] m10 = {0,0,0,1,0,0,0,2,0,3};//needed if startpoint is different from 1

		boolean[] checked = new boolean[n+1];//only needed if 3,9 etc are implemented

		/**
		 * this block is for multiples of two and five
		 */
		long[] pow2 = new long[24]; pow2[0]=1;
		for (int i=1;i<24;i++) pow2[i] = 2*pow2[i-1];
		long[] pow5 = new long[11]; pow5[0]=1;
		for (int i=1;i<11;i++) pow5[i] = 5*pow5[i-1];
		for (int i=0; i<24;i++)
			forj: for (int j=0; j<11;j++)
				if (pow2[i]*pow5[j] <= n)
					checked[(int)(pow2[i]*pow5[j])]=true;
				else break forj;



		int i = 3; //iterates through numbers up to n, except multiples of two and five
		for (int a=m10[i%10];i<=limitN; i+=aug[a], a=(a+1)%4) {
//			System.out.print(i + " ");
			//alle potenzen von 2 und 5 und deren produkte
			//3 und 9 
			//bei gerader länge: 11 und deren komplement bis zu 111_111
			byte[] digits = new byte[i];
			int cur =0, period=0;
			int l=1;
			do {
				period++;
				l*=10;
				digits[cur] = (byte)(l/i);
//				System.out.print(digits[cur]);
				l%=i;
				cur++;
			}while (l!=1);
//			System.out.println(" "+ period);


			int periodMod = m%period;
			count+=digits[periodMod];
			System.out.println(i +": "+digits[periodMod]);
			int max2=0;
			int max5=0;

			byte[][] iTimesPow2 = new byte[24][period];
			byte[][] iTimesPow5 = new byte[24][period];
			for (l=0;l<period;l++) {
				iTimesPow2[0][l]=digits[l];
			}
			for (cur=1;cur<24;cur++) {
//					System.out.print(i*pow2[cur] +" ");
					max2++;
					for (l=0;l<period-1;l++) {
						iTimesPow2[cur][l]=(byte)(((5*(iTimesPow2[cur-1][l]*10+iTimesPow2[cur-1][l+1]))/10)%10);
//						System.out.print(iTimesPow2[cur][l]);
					}
					iTimesPow2[cur][l]=(byte)(((5*(iTimesPow2[cur-1][l]*10+iTimesPow2[cur-1][0]))/10)%10);
//					System.out.println(iTimesPow2[cur][l]);
			}

			for (l=0;l<period;l++) {
				iTimesPow5[0][l]=digits[l];
			}
			for (cur=1;cur<24;cur++) {
//					System.out.print(i*pow5[cur] +" ");
					max5++;
					for (l=0;l<period-1;l++) {
						iTimesPow5[cur][l]=(byte)(((2*(iTimesPow5[cur-1][l]*10+iTimesPow5[cur-1][l+1]))/10)%10);
//						System.out.print(iTimesPow5[cur][l]);
					}
					iTimesPow5[cur][l]=(byte)(((2*(iTimesPow5[cur-1][l]*10+iTimesPow5[cur-1][0]))/10)%10);
//					System.out.println(iTimesPow5[cur][l]);
			}

			int ten=1;
			for (int tens=0; ten*i<=limitN;tens++,ten*=10) {
				for (cur=1;ten*i*pow5[cur]<=limitN;cur++) {
					count+=iTimesPow5[cur][(((periodMod-tens-cur)%period)+period)%period];
					System.out.println(ten*i*pow5[cur] +": "+iTimesPow5[cur][(((periodMod-tens-cur)%period)+period)%period]);
//					System.out.print("test5 :");
//					for (int z:iTimesPow5[cur]) System.out.print(z);
//					System.out.println();
				}
				for (cur=1;ten*i*pow2[cur]<=limitN;cur++) {
					count+=iTimesPow2[cur][(((periodMod-tens-cur)%period)+period)%period];
					System.out.println(ten*i*pow2[cur] +": "+iTimesPow2[cur][(((periodMod-tens-cur)%period)+period)%period]);
//					System.out.print("test2 :");
//					for (int z:iTimesPow2[cur]) System.out.print(z);
//					System.out.println();
				}
			}
		}
		return count;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime

		long[] ones =
			{
											1L,	//
										   11L,	//(11) (2)
										  111L,	//3, (37) (3)
										1_111L,	//11, (101) (4)
									   11_111L,	//(41, 271) (5)
									  111_111L, //3,11,37,(7,13) (6)
									1_111_111L,	//(239, 4649) (7)
								   11_111_111L,	//11,101,(73,137) (8)
								  111_111_111L,	//3^3, 37, (333667) (9)
								1_111_111_111L,	//11,41,271,(9091) (10)
							   11_111_111_111L,	//(21649, 513239) (11)
							  111_111_111_111L,	//3,7,11,13,37,101,(9901) (12)
							1_111_111_111_111L,	//(53,79,265371653) (13)
						   11_111_111_111_111L,
						  111_111_111_111_111L,
						1_111_111_111_111_111L,
					   11_111_111_111_111_111L,
					  111_111_111_111_111_111L,
					1_111_111_111_111_111_111L
			};
		

		long startTime = System.nanoTime();
//		System.out.println( _nthdor(10_000_000) );
		PeriodenOptimierungFinal .main(null) ;
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

//				startTime = System.nanoTime();
//				NachkommastellenSumme.main(null) ;
//				endTime = System.nanoTime();
//				duration = (endTime - startTime);
//				System.out.println(duration/1000000);


	}
}