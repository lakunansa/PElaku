/**
 * 
 */

import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0055_lychrel_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0055_lychrel_numbers() {
		// TODO Auto-generated constructor stub
	}

	public static long reverse (long n) {
		//reverses number
		long reverse=0;
		while (n>0) {
			reverse= 10*reverse + n%10; 
			n /= 10;
		}
		return reverse;
	}


	public static long ln(int n) {
		//for some reason slower with checks
//		byte[] checked = new byte[n];
		int count =0;
		for (int i=1;i<n;i++) {
//			if (checked[i]==-1) {
//				count++;
//				continue;
//			}
//			if (checked[i]==1) continue;
			ArrayList<Byte> nu = new ArrayList<Byte>(0);
			boolean isP=false;
			int tmp=i; 
			while (tmp>0) {
				nu.add((byte)(tmp%10));
				tmp/=10;
			}
			byte carry=0;
			long tmp2=0;
			ArrayList<Long> tmp3 = new ArrayList<Long>(0);
			for (tmp=0;!isP && tmp<50;tmp++){
				ArrayList<Byte> nutmp = new ArrayList<Byte>(0);
				//add
				for (byte j=0, k=(byte)(nu.size()-1); j<nu.size(); j++, k--) {
					carry=(byte)(nu.get(j)+nu.get(k)+carry); 
					nutmp.add((byte)(carry%10));
					carry/=10;
				}
				if (carry>0) {
					nutmp.add(carry);
					carry=0;
				}
				nu=nutmp;
				//check for palindrome
				isP=true;
				for (int j=0, k=nu.size()-1; j<k; j++, k--) {
					if (nu.get(j) != nu.get(k)) {
						isP=false;
						break;
					}
				}
//				tmp2=0;
//				for (byte j=(byte)(nu.size()-1); j>=0 && tmp2<n;j--) {
//					tmp2 = 10*tmp2+nu.get(j);
//				};
//				if (tmp2<n) {
//					if (checked[(int)tmp2]==-1) {
//						isP=false;
//						tmp=50;
//						continue;
//					}
//					if (checked[i]==1) {
//						isP=true;
//						tmp=50;
//						continue;
//					}
//					tmp3.add(tmp2);
//				}
			}
			if (!isP) {
//				for (int l=0; l<tmp3.size()-1;l++) checked[(int) ( tmp3.get(l)%n ) ] = -1;
				count++;
		
			}
//			else
//				for (int l=0; l<tmp3.size()-1;l++) checked[(int) ( tmp3.get(l)%n ) ] = 1;
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes how many lychrel numbers are below 10000;


		long startTime = System.nanoTime();
		System.out.println( ln(10001) );
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
