/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0008_largest_product_in_a_series {

	/**
	 * 
	 */
	public ProjectEuler_0008_largest_product_in_a_series() {
		// TODO Auto-generated constructor stub
	}


	final public static String s = 
					( "73167176531330624919225119674426574742355349194934\r\n"
					+ "96983520312774506326239578318016984801869478851843\r\n"
					+ "85861560789112949495459501737958331952853208805511\r\n"
					+ "12540698747158523863050715693290963295227443043557\r\n"
					+ "66896648950445244523161731856403098711121722383113\r\n"
					+ "62229893423380308135336276614282806444486645238749\r\n"
					+ "30358907296290491560440772390713810515859307960866\r\n"
					+ "70172427121883998797908792274921901699720888093776\r\n"
					+ "65727333001053367881220235421809751254540594752243\r\n"
					+ "52584907711670556013604839586446706324415722155397\r\n"
					+ "53697817977846174064955149290862569321978468622482\r\n"
					+ "83972241375657056057490261407972968652414535100474\r\n"
					+ "82166370484403199890008895243450658541227588666881\r\n"
					+ "16427171479924442928230863465674813919123162824586\r\n"
					+ "17866458359124566529476545682848912883142607690042\r\n"
					+ "24219022671055626321111109370544217506941658960408\r\n"
					+ "07198403850962455444362981230987879927244284909188\r\n"
					+ "84580156166097919133875499200524063689912560717606\r\n"
					+ "05886116467109405077541002256983155200055935729725\r\n"
					+ "71636269561882670428252483600823257530420752963450"   );

	final public static String[] si = s.split(""); 


	public static long largestProduct(String number, int k) {
	    long maxProduct = 0;
	    char[] digits = number.toCharArray();

	    // Wir laufen durch den String
	    for (int i = 0; i <= digits.length - k; i++) {
	        long currentProduct = 1;
	        boolean zeroFound = false;

	        // Multipliziere k aufeinanderfolgende Ziffern
	        for (int j = 0; j < k; j++) {
	            int digit = digits[i + j] - '0'; // Char zu Int konvertieren
	            if (digit == 0) {
	                // Wenn eine 0 kommt, kann das ganze Fenster nur 0 sein
	                zeroFound = true;
	                i = i + j; // Springe direkt hinter die Null
	                break;
	            }
	            currentProduct *= digit;
	        }

	        if (!zeroFound && currentProduct > maxProduct) {
	            maxProduct = currentProduct;
	        }
	    }
	    return maxProduct;
	}

	
	
	public static long product(int n) {
		long prod = 1, maxprod;
		int start=0, end=n;
		int containsZero = 0;
		
		//create first product
		for (int curr=0;curr <end;curr++) {
			if ( (si[curr].compareTo("0") >= 0)  &&  (si[curr].compareTo("9") <= 0) ) {
				byte b = Byte.parseByte(si[curr]);
				if (b==0) containsZero++;
				else prod *= b;
			}else {
				end++;
				continue;
			}
		}

		//make products
		maxprod=prod;

//		for (int curr=start;curr <end;curr++) {
//			System.out.print(s.charAt(curr) + "*");
//			}
//		System.out.print(" "+ prod + " " + maxprod + " "+ containsZero +"\n");
		
		for (;end <s.length();) {
			if ( (si[start].compareTo("0") >= 0)  &&  (si[start].compareTo("9") <= 0) ) {
			}else {
				start++;
				continue;
			}
			if ( (si[end].compareTo("0") >= 0)  &&  (si[end].compareTo("9") <= 0) ) {
			}else {
				end++;
				continue;
			}

			byte a = Byte.parseByte(si[start]), b = Byte.parseByte(si[end]);

			if (b == 0) {
				containsZero++;
			} else prod *= b;
			if (a == 0) {
				containsZero--;
			} else prod /= a;

			end++;
			start++;
			if (containsZero==0 && prod > maxprod) maxprod = prod;
			
//			for (int curr=start;curr <end;curr++) {
//				System.out.print(s.charAt(curr) + "*");
//				}
//			System.out.print(" "+ prod + " " + maxprod + " "+ containsZero +"\n");

		}
		
		return maxprod;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the maximal product of a 13digit substring

		long startTime = System.nanoTime();
		System.out.println( product(13) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

				startTime = System.nanoTime();
				System.out.println( largestProduct(s,13) );
				endTime = System.nanoTime();
				duration = (endTime - startTime);
				System.out.println(duration/1000000);


	}

}
