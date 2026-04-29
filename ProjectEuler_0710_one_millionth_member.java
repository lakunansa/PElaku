import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0710_one_millionth_member {

	/**
	 * 
	 */
	public ProjectEuler_0710_one_millionth_member() {
		// TODO Auto-generated constructor stub
	}


	public static long _omm_ki3() {
		long mod = 1_000_000;

		// Wir brauchen die Glieder a(n) für A384153
		// Die kombinierte Rekursion aus der G.f. (Nenner-Produkt) ist:
		// a(n) = 4*a(n-1) - 5*a(n-2) + 3*a(n-3) - a(n-4)

		// Startwerte für A384153:
		long a_4 = 1; // n=1
		long a_3 = 2; // n=2
		long a_2 = 4; // n=3
		long a_1 = 9; // n=4
		long u_1 = 7; // für curr=9
		
		
		
		// Check: n=10 muss 824 sein
		// n=5: 20, n=6: 43, n=7: 91, n=8: 191, n=9: 398, n=10: 824

		for (int n = 5; n <= 20_000_000; n++) {
			long next = (4 * a_1 - 5 * a_2 + 3 * a_3 - 2*a_4) % mod;
			if (next < 0) next += mod;
			
			u_1+=a_1; u_1 %=mod;

			if (next == 0) return 2*n; // n ist hier der Index in der Folge
			if (u_1 == 0) return 2*n+1; // n ist hier der Index in der Folge

			a_4 = a_3;
			a_3 = a_2;
			a_2 = a_1;
			a_1 = next;
			
//			long next_u = (a_n_minus_1 + u_n_minus_1) % mod;
			if (n<=22) System.out.println(2*n + " "+ next + " " + (2*n+1) + " "+ u_1);
		}
		return -1;
	}




	public static long _omm_ki2(int nMax) {
		int[] pNo = new int[nMax / 2 + 1];
		int[] pIs = new int[nMax / 2 + 1];
		int[] sumPNo = new int[nMax / 2 + 1]; // Prefix-Summe für partsNo2
		int[] sumPIs = new int[nMax / 2 + 1]; // Prefix-Summe für partsIs2

		// Initialisierung wie in deinem Code
		pNo[2] = 1;
		sumPNo[2] = 1; // sumPNo[i] = pNo[0] + ... + pNo[i]

		pIs[0] = 1; pIs[1] = 1; pIs[2] = 2;
		sumPIs[0] = 1; sumPIs[1] = 2; sumPIs[2] = 4; // sumPIs[i] = pIs[0] + ... + pIs[i]

		int soFarNo = 2;
		int soFarIs = 2;
		int mod = 1_000_000;

		for (int curr = 2; curr <= nMax; curr++) {
			int cur2 = curr / 2;
			long sum;

			if (curr % 2 == 0) {
				// 1. computePartsNo2(cur2) inline
				if (cur2 > soFarNo) {
					// Original: sumCalc = pNo[sum-1] + pIs[sum-2] + sum(pNo[sum-i] für i=3..sum)
					// Entspricht: pNo[cur2-1] + pIs[cur2-2] + sumPNo[cur2-3]
					int p0 = pNo[cur2 - 1] + pIs[cur2 - 2];
					if (cur2 - 3 >= 0) p0 += sumPNo[cur2 - 3];
					pNo[cur2] = p0 % mod;
					sumPNo[cur2] = (sumPNo[cur2 - 1] + pNo[cur2]) % mod;
					soFarNo++;
				}

				// 2. computePartsIs2(cur2-1) inline
				int targetIs = cur2 - 1;
				if (targetIs > soFarIs) {
					// Original: sumCalc = 1 + sum(pIs[sum-i] für i=1..sum-1)
					// Entspricht: 1 + sumPIs[targetIs-1] - pIs[0] -> da pIs[0]=1: sumPIs[targetIs-1]
					int p2 = sumPIs[targetIs - 1] % mod;
					pIs[targetIs] = p2;
					sumPIs[targetIs] = (sumPIs[targetIs - 1] + p2) % mod;
					soFarIs++;
				}

				// 3. Finale Summe für curr (gerade)
				// Original: sum = p0 + p2 + sum(pNo[cur2-i] für i=2..cur2)
				// Entspricht: pNo[cur2] + pIs[cur2-1] + sumPNo[cur2-2]
				sum = pNo[cur2] + (cur2 - 1 >= 0 ? pIs[cur2 - 1] : 0);
				if (cur2 - 2 >= 0) sum += sumPNo[cur2 - 2];
			} else {
				// Finale Summe für curr (ungerade)
				// Original: sum = sum(pNo[cur2-i] für i=0..cur2)
				// Entspricht: sumPNo[cur2]
				sum = sumPNo[cur2];
			}

			sum %= mod;

			if (curr == 42) System.out.println("Check n=20: " + sum); // Muss 824 sein
			System.out.println("curr="+curr+" sum=" + sum);

			if (sum > 0 && sum % mod == 0) {
				System.out.println("Treffer bei n = " + curr);
				return curr;
			}
		}
		return -1;
	}




	public static long _omm_ki(int n) {
		long[] partsNo2 = new long[n / 2 + 1];
		long[] partsIs2 = new long[n / 2 + 1];
		long[] sumNo2 = new long[n / 2 + 1]; // sumNo2[k] = sum_{i=0}^{k} partsNo2[i]

		// Initialwerte laut deinem Code
		partsNo2[2] = 1;
		partsIs2[0] = 1;
		partsIs2[1] = 1;
		partsIs2[2] = 2;

		// Initialisierung der Prefix-Summe für partsNo2
		sumNo2[0] = partsNo2[0];
		sumNo2[1] = sumNo2[0] + partsNo2[1];
		sumNo2[2] = sumNo2[1] + partsNo2[2];

		long mod = 1_000_000;
		int soFarNo = 2;
		int soFarIs = 2;

		for (int curr = 2; curr <= n; curr++) {
			int cur2 = curr / 2;
			long sum = 0;

			if (curr % 2 == 0) {
				// 1. partsNo2[cur2] berechnen (Ersatz für computePartsNo2)
				if (cur2 > soFarNo) {
					// Logik: pNo[c-1] + pIs[c-2] + sum(pNo[0...c-3])
					long p0 = partsNo2[cur2 - 1] + partsIs2[cur2 - 2];
					if (cur2 - 3 >= 0) p0 += sumNo2[cur2 - 3];
					partsNo2[cur2] = p0 % mod;
					sumNo2[cur2] = (sumNo2[cur2 - 1] + partsNo2[cur2]) % mod;
					soFarNo++;
				}

				// 2. partsIs2[cur2-1] berechnen (Ersatz für computePartsIs2)
				int targetIs = cur2 - 1;
				if (targetIs > soFarIs) {
					// Logik: 1 + sum(pIs[1...target-1])
					// Da wir keine Prefix-Summe für Is2 haben, nutzen wir hier die 
					// Beobachtung: computeIs2(k) = computeIs2(k-1) + partsIs2[k-1]
					long p2 = (partsIs2[targetIs - 1] + (targetIs - 1 > 0 ? getIsSum(targetIs - 1, partsIs2) : 1)) % mod;
					// Einfacher: Das Ergebnis von computePartsIs2 folgt der Regel: 
					// partsIs2[k] = 2 * partsIs2[k-1] (für k > 2 in diesem speziellen Setup)
					// Aber bleiben wir näher an deinem Code:
					long sumIsRec = 1;
					for(int j=1; j < targetIs; j++) sumIsRec = (sumIsRec + partsIs2[targetIs-j]) % mod;
					partsIs2[targetIs] = sumIsRec;
					soFarIs++;
				}

				long p0 = partsNo2[cur2];
				long p2 = (cur2 - 1 >= 0) ? partsIs2[cur2 - 1] : 0;
				sum = (p0 + p2);

				// Schleife: for (int i=2; i<=cur2; i++) sum += partsNo2[cur2-i]
				if (cur2 - 2 >= 0) sum += sumNo2[cur2 - 2];
			} else {
				// Ungerade: sum = sum_{i=0}^{cur2} partsNo2[cur2-i]
				sum = sumNo2[cur2];
			}

			sum %= mod;
			if (curr % 10000 == 0) System.out.println(curr + " " + sum);
			if (sum > 0 && sum % mod == 0) {
				System.out.println("Gefunden bei " + curr + ": " + sum);
				return curr;
			}
			if (curr == n) return sum; // Für deinen Test n=20 -> 824
		}
		return -1;
	}

	// Hilfsmethode für die Is2 Summe, falls man kein zweites Prefix-Array will
	private static long getIsSum(int k, long[] partsIs2) {
		long s = 1;
		for (int i = 1; i < k; i++) s += partsIs2[k - i];
		return s;
	}





	public static long computePartsIs2(int sum, long[] partsIs2) {
		long sumCalc=1;//center =0 or >0 : nur sumCsum is not in PartsList
		for (int i=1; i<sum; i++) {
			sumCalc+=partsIs2[sum-i];
		}
		return sumCalc;
	}



	public static long computePartsNo2(int sum, long[] partsNo2,  long[] partsIs2) {
		long sumCalc=0; //center =0 or >0 : nur sumCsum is not in PartsList
		sumCalc+=partsNo2[sum-1];
		sumCalc+=partsIs2[sum-2];
		for (int i=3; i<sum; i++) sumCalc+=partsNo2[sum-i];
		return sumCalc;
	}

	public static long _omm(int n) {
		long[] twoPals = new long[n+1];
		long[] partsNo2 = new long[n/2+1]; partsNo2[2]=1;
		long[] partsIs2 = new long[n/2+1]; partsIs2[0]=1; partsIs2[1]=1; partsIs2[2]=2;
		int curr = 1, soFarIs=2, soFarNo = 2; //
		long sum;
		do {	
			curr++;
			sum=0;
			int cur2=curr/2, start;
			if (curr%2==0) {
				long p0,p2;
				if (cur2<=soFarNo) {
					p0 = partsNo2[cur2];
				}
				else {
					p0=computePartsNo2(cur2, partsNo2, partsIs2);//"center"=0
					partsNo2[cur2]=p0%1_000_000;
					soFarNo++;
				}
				if (cur2-1<=soFarIs) {
					p2 = partsIs2[cur2-1];
				}
				else {
					p2=computePartsIs2(cur2-1, partsIs2);//"center"=2
					partsIs2[cur2-1]=p2%1_000_000;
					soFarIs++;
				}
				sum=p0+p2;
				start=2;
			}else {
				start = 0;
			}
			for (int i=start; i<=cur2;i++)
				sum+=partsNo2[cur2-i];//center 2*i
			twoPals[curr]=sum;
			if (curr % 10000 == 0) System.out.println(curr + " " + sum);
		}while (!(sum>0 && sum%1_000_000 == 0) && curr < n);
		System.out.println(curr + " " + sum);
		return curr;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _omm_ki3() );
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
