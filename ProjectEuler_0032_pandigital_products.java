/**
 * 
 */

import java.util.PriorityQueue;

/**
 * 
 */
public class ProjectEuler_0032_pandigital_products {

	/**
	 * 
	 */
	public ProjectEuler_0032_pandigital_products() {
		// TODO Auto-generated constructor stub
	}

	public static long pp(int n) {
		long sum=0;
		PriorityQueue<Integer> pq= new PriorityQueue<Integer>();
		//case 1:
		for (byte i=2;i<=9;i++) {
			for1:
				for (int j=1234;j<=9876;j++) {
					for2:
					if (Math.log10(i*j)<5) {
						boolean[] checked = new boolean[10];
						byte tmpi = i;
						checked[i]=true;
						byte count=1;
						int tmp=j;
						while(tmp>0) {
							if (tmp%10==0 || checked[tmp%10])break for2;
							checked[tmp%10]=true;
							count++;
							tmp/=10;
						}
						tmp = i*j;
						while(tmp>0) {
							if (tmp%10==0 || checked[tmp%10])break for2;
							checked[tmp%10]=true;
							count ++;
							tmp/=10;
						}
						if (count==9 && !pq.contains(i*j)) {
							pq.add(i*j);
							sum+=i*j;
							System.err.println(i+" "+j+" "+i*j);
						} else if (pq.contains(i*j))System.out.println(i+" "+j+" "+i*j);
					}
				}
		}

		//case 2:
		for (byte i=12;i<=98;i++) {
			for1:
				for (int j=123;j<=987;j++) {
					for2:
						if (Math.log10(i*j)<5) {
							boolean[] checked = new boolean[10];
							byte tmpi = i;
							if (tmpi%10==0)break for2;
							else checked[tmpi%10]=true;
							tmpi/=10;
							if (checked[tmpi])break for2;
							else checked[tmpi%10]=true;
							byte count=2;
							int tmp=j;
							while(tmp>0) {
								if (tmp%10==0 || checked[tmp%10])break for2;
								checked[tmp%10]=true;
								count++;
								tmp/=10;
							}
							tmp = i*j;
							while(tmp>0) {
								if (tmp%10==0 || checked[tmp%10])break for2;
								checked[tmp%10]=true;
								count ++;
								tmp/=10;
							}
							if (count==9 && !pq.contains(i*j)) {
								pq.add(i*j);
								sum+=i*j;
								System.err.println(i+" "+j+" "+i*j);
							}
						}
				}
		}

		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all products whose multiplicand/multiplier/product identity can be written as a pandigital


		long startTime = System.nanoTime();
		System.out.println( pp(9) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		//		startTime = System.nanoTime();
		//		System.out.println( prime_s(10001) );
		//		endTime = System.nanoTime();
		//		duration = (endTime - startTime);
		//		System.out.println(durati



	}

}
