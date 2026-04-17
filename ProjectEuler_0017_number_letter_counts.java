/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0017_number_letter_counts {

	/**
	 * 
	 */
	public ProjectEuler_0017_number_letter_counts() {
		// TODO Auto-generated constructor stub
	}



	public static String partsS(int n) {
		long l; 
		switch (n) {
		case -1:
			System.out.println(" and ");
			return "and";//3

		case 1:
			System.out.println(" 1 ");
			return "one";//3
		case 2:
			System.out.println(" 2 ");
			return "two";//3
		case 6:
			System.out.println(" 6 ");
			return "six";//3
		case 10:
			System.out.println(" 10 ");
			return "ten";//3
		case 4:
			System.out.println(" 4 ");
			return "four";//4
		case 5:
			System.out.println(" 5 ");
			return "five";//4
		case 9:
			System.out.println(" 9 ");
			return "nine";//4
		case 3:
			System.out.println(" 3 ");
			return "three";//5
		case 7:
			System.out.println(" 7 ");
			return "seven";//5
		case 8:
			System.out.println(" 8 ");
			return "eight";//5
		case 40:
			System.out.println(" 40 ");
			return "forty";//5
		case 50:
			System.out.println(" 50 ");
			return "fifty";//5
		case 60:
			System.out.println(" 60 ");
			return "sixty";//5
		case 11:
			System.out.println(" 11 ");
			return "eleven";//6
		case 12:
			System.out.println(" 12 ");
			return "twelve";//6
		case 20:
			System.out.println(" 20 ");
			return "twenty";//6
		case 30:
			System.out.println(" 30 ");
			return "thirty";//6
		case 80:
			System.out.println(" 80 ");
			return "eighty";//6
		case 90:
			System.out.println(" 90 ");
			return "ninety";//6
		case 15:
			System.out.println(" 15 ");
			return "fifteen";//7
		case 16:
			System.out.println(" 16 ");
			return "sixteen";//7
		case 70:
			System.out.println(" 70 ");
			return "seventy";//7
		case 100:
			System.out.println(" 100 ");
			return "hundred";//7
		case 13:
			System.out.println(" 13");
			return "thirteen";//8
		case 14:
			System.out.println(" 14 ");
			return "fourteen";//8
		case 18:
			System.out.println(" 18 ");
			return "eighteen";//8
		case 19:
			System.out.println(" 19 ");
			return "nineteen";//8
		case 1000:
			System.out.println(" 1000 ");
			return "thousand";//8
		case 17:
			System.out.println(" 17");
			return "seventeen";//9
		}

		return "";

	}

	public static long nlcS(int n) {
		ArrayList<String> a = new ArrayList<String> (25000);
		int l=0;
		for(int i=1;i<=n;i++) {
			if (i==1000 ) {
				a.add(partsS(1));
				a.add(partsS(1000));
				continue;
			}
			if (i/100 != 0 ) {
				a.add(partsS(i/100));
				a.add(partsS(100));
				if (i%100 != 0)	a.add(partsS(-1));
				else continue;
			}
			if (i%100 >=20) {
				a.add(partsS(((i%100)/10)*10));
				if (i%10 >0)a.add(partsS(i%10));
			}else {
				a.add(partsS(i%100));
			}
		}
		for (String s:a) {
			System.out.println(s);
			l+=s.length();
		}
		return l;
	}

	//	public static int count(int k) {
	//	int num = 0;
	//		switch (k) {	
	//	case 1:
	//		if ()
	//		return num;
	//		}
	//		return num;
	//	}


	public static long parts(int n) {
		long l; 
		switch (n) {
		case 1,2,6,10:
			System.out.println(" 3:one,two,six,ten ");
		return "one".length();//3
		case 4,5,9:
			System.out.println(" 4:four,five,nine ");
		return "four".length();//4
		case 3,7,8,40,50,60:
			System.out.println(" 5:three,seven,eight,forty,fifty,sixty ");
		return "three".length();//5
		case 11,12,20,30,80,90:
			System.out.println(" 6:eleven,twelve,twenty,thirty,eighty,ninety ");
		return "eleven".length();//6
		case 15,16,70,100:
			System.out.println(" 7:fifteen,sixteen,seventy,hundred ");
		return "fifteen".length();//7
		case 13,14,18,19,1000:
			System.out.println(" 8:thirteen,fourteen,eighteen, nineteen,thousand ");
		return "thirteen".length();//8
		case 17:
			System.out.println(" 9:seventeen ");
			return "seventeen".length();//9
		}

		return 0;

	}

	public static long nlc(int n) {
		int l=0;
		for(int i=1;i<=n;i++) {
			System.out.print(i+" ");
			if (i==1000 ) l+=parts(i/1000)+parts(i);
			else {
				if (i/100 != 0 ) {
					l+=parts(i/100);
					l+=parts(100);
					if(i%100 >0) {
						l+=3;
						System.out.println("and");
					}
				}
				if (i%100 >=20) {
					l+=parts(((i%100)/10)*10);
					l+=parts(i%10);
				}else l+=parts(i%100);
			}
		}

		return l;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the number of letters for all numbers 1-1000


		long startTime = System.nanoTime();
		System.out.println( nlc(1000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(duration/100


	}

}
