/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0004_largest_palindrome_product {

	/**
	 * 
	 */
	public ProjectEuler_0004_largest_palindrome_product() {
		// TODO Auto-generated constructor stub
	}

	public static int reverse (int n) {
		//reverses number
		int reverse=0;
		while (n>0) {
			reverse= 10*reverse + n%10; 
			n /= 10;
		}
		return reverse;
	}


	public static int larPali_KI() {//middle fast, but basically same alg as sample
	    int maxPali = 0;
	    
	    // Äußere Schleife rückwärts von 999
	    for (int i = 999; i >= 100; i--) {
	        int j, step;
	        
	        // Wenn i durch 11 teilbar ist, kann j jede Zahl sein.
	        // Wenn nicht, muss j durch 11 teilbar sein!
	        if (i % 11 == 0) {
	            j = 999;
	            step = 1;
	        } else {
	            j = 990; // Größte 3-stellige Zahl teilbar durch 11
	            step = 11;
	        }
	        
	        for (; j >= i; j -= step) {
	            int prod = i * j;
	            if (prod <= maxPali) break; // Optimierung: Produkt bereits zu klein
	            
	            if (isPalindrome(prod)) {
	                maxPali = prod;
	            }
	        }
	    }
	    return maxPali;
	}

	private static boolean isPalindrome(int n) {
	    // Deine reverse-Funktion ist super!
	    return n == reverse(n);
	}

	
	public static int larPali(){
		int tmpP, larP=888888, curI=979, curJ, minJ;
		int count=0;
		while(curI>=880) {
			curJ=999;
			minJ = larP/curI;
			//			Label_I:
			while ( curJ > minJ ) {
				count++;
				tmpP = curI*curJ;
				if(tmpP==reverse(tmpP)) {
					larP=tmpP;
					break; //Label_I;
				}
				curJ--;
//				if( (curJ % 11==0) && (curJ < curI) ) curJ--;
			}
			curI-=11;
		};
//		System.out.println(100001/11);
		return larP;
	}


	public static int sample_method() {//faster f3
		int	largestPalindrome = 0, a = 999, b, db,tmp;
		int count=0;
		while (a >= 100) {
			if (a % 11 == 0) {
				b = 999;
				db = 1;
			}else {
				b = 990;
				db = 11;
			};
			while (b >= a) {
				count++;
				if ((tmp=a*b) <= largestPalindrome)
					break;
				if (tmp==reverse(tmp) ) largestPalindrome = tmp;
				b -= db;				
			};
			a--;
		}
//		System.out.println(count);

		return largestPalindrome;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//finds largest palindrome that is product of two threedigits numbers
		// for some reason, sample code is faster...

		{
		long startTime = System.nanoTime();
		System.out.println( larPali() );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);

		startTime = System.nanoTime();
		System.out.println( sample_method() );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration);
		}

		{
		long startTime = System.nanoTime();
		System.out.println( larPali_KI() );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration);

		}

	}

}
