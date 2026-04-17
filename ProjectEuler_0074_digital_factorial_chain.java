/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0074_digital_factorial_chain {

	/**
	 * 
	 */
	public ProjectEuler_0074_digital_factorial_chain() {
		// TODO Auto-generated constructor stub
	}

	public static int[] facs={1,1,2,6,24,120,720,5040,40320,362880};

	public static int computeDigitFactorial(int n) {
		int nr=n, df=0;
		while (nr>0) {
			df+=facs[nr%10];
			nr/=10;
		}
		return df;
	}

	public static int permutateNumber(int n) {
		int nr=n, np=0;
		ArrayList<Integer> nDigits = new ArrayList<Integer>();
		while(nr>0) {
			nDigits.add((int)(nr%10));
			nr/=10;
		}
		nDigits.sort( (a,b) -> b-a );
		for(int d:nDigits) np=np*10+d;
		return np;
	}


	
	
	public static long dfc(int n) {
		/**
		 * Strategy:
		 * 1. take next number
		 * 2. compute 
		 */
		boolean[] checked = new boolean[10000000];
		ArrayList<Integer> caches = new ArrayList<Integer>();
		ArrayList<Integer> indxsOfP = new ArrayList<Integer>();
		ArrayList<Integer> chainSizes = new ArrayList<Integer>();

		int curr=1, cacheSize=0;
		while (curr<n) {//all numbers below n are accounted for
			System.out.println("curr is smaller than n " + curr);
			if(!checked[curr]) {//else do nothing
//				System.out.println("cache does not contain curr " + curr);
				int currP = permutateNumber(curr);
				if (checked[currP]) {//currP might already be in cache, then easy clean-up
//					System.out.println("but cache does contain currP " + curr);
					int indexCurrP = caches.indexOf(currP);
					caches.add(curr);
					checked[curr]=true;
					indxsOfP.add(indexCurrP); 
					chainSizes.add(chainSizes.get(indexCurrP));
					cacheSize++;
				} else {//need to compute chain
//					System.out.println(" so we need to compute loop of " + curr);
					ArrayList<Integer> chain = new ArrayList<Integer>();
					ArrayList<Integer> chainP = new ArrayList<Integer>();
					int next=curr, nextP=currP;
					while(!chain.contains(next) && !checked[nextP]) {
//						System.out.print(" loop " + next + " P " + nextP);
						chain.add(next);
						chainP.add(nextP);
						next=computeDigitFactorial(next);
						nextP=permutateNumber(next);
//						System.out.println(" evaluate next " + next + " "+ nextP);
					}
					if (checked[nextP]) {//now there were two conditions for chainbreak, first if end of loop is known
//						System.out.println(" loop break <- nextP is already cached " + nextP);
						int indexOfNextP = caches.indexOf(nextP);
						int chainSize = chain.size()+chainSizes.get(indexOfNextP);
						for (int i=0;i<chain.size();i++) {
							int num=chain.get(i), nump=chainP.get(i);
							if (num!=nump) {
//								System.out.println(" num to add != numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize);
								chainSizes.add(chainSize);
								caches.add(num);	
								checked[num]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								chainSize--;
								cacheSize+=2;
							}else {
//								System.out.println(" num to add == numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								chainSize--;
								cacheSize+=1;
							}
						}
						if (!checked[next]) {
//							System.out.println(" need to also add next " + next);
							caches.add(next);	
							checked[next]=true;
							indxsOfP.add(indexOfNextP); 
							chainSizes.add(chainSize);
							cacheSize++;
						}
					}else {//second this is sufficient that a true circle is found
//						System.out.println(" loop break <- new loopfound " + nextP);
						int indexOfNext = chain.indexOf(next);
						int chainSize = chain.size();
						for (int i=0;i<indexOfNext;i++) {
							int num=chain.get(i), nump=chainP.get(i);
							if (num!=nump) {
//								System.out.println(" num to add != numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize);
								chainSizes.add(chainSize);
								caches.add(num);	
								checked[num]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								chainSize--;
								cacheSize+=2;
							}else {
//								System.out.println(" num to add == numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								chainSize--;
								cacheSize+=1;
							}
						}
						for (int i=indexOfNext;i<chain.size();i++) {
							int num=chain.get(i), nump=chainP.get(i);
							if (num!=nump) {
//								System.out.println(" num to add != numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize);
								chainSizes.add(chainSize);
								caches.add(num);	
								checked[num]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								cacheSize+=2;
							}else {
//								System.out.println(" num to add == numP " + num + " size " + chainSize);
								caches.add(nump);	
								checked[nump]=true;
								indxsOfP.add(cacheSize); 
								chainSizes.add(chainSize);
								cacheSize+=1;
							}
						}
					}
				}//else !cache.contains currP
			}//if !cache.contains curr
			curr++;
		}//while curr<n
		int max=0, num=0, size;
		for (int i=0; i<caches.size();i++) {
//			System.out.println(""
//					+ " i " + i 
//					+ " caches " + caches.get(i) 
//					+ " indexP " + indxsOfP.get(i) + " P " + caches.get(indxsOfP.get(i)) 
//					+ " chainSize " + chainSizes.get(i) 
//					);
			if ((size=chainSizes.get(i))>max) {
				max=size;
				num=1;
			}else if (size==max) num++;
		}
		System.out.println(max);
		return num;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( dfc(1000000) );
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
