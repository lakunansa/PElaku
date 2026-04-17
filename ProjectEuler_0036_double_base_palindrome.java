/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0036_double_base_palindrome {

	/**
	 * 
	 */
	public ProjectEuler_0036_double_base_palindrome() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 *
	 *
	 *Function isPalindrome(n,base)
 reversed := 0
 k := n
 while k > 0
 reversed := base*reversed + k mod base
 k := k div base
 return (n = reversed) 
	 *
	 *
	 *
	 *Function makePalindrome(n,base,oddlength)
res := n
if oddlength then n := n div base
while n > 0
 res := base*res + n mod base
 n := n div base
return res 
	 *
	 * 
	 * Function makePalindromeBase2(n,oddlength)
res := n
if oddlength then n := n shiftRight 1
while n>0
 res := res shiftLeft 1 + n and 1
 n := n shiftRight 1
return res
	 * 
	 * 
	 * limit := 1000000
sum := 0
i := 1
p := makePalindromeBase2(i,true)
while p < limit
 if isPalindrome(p,10) then sum := sum + p
 i := i+1
 p := makePalindromeBase2(i,true)
i := 1
p := makePalindromeBase2(i,false)
while p < limit
 if isPalindrome(p,10) then sum := sum + p
 i := i+1
 p := makePalindromeBase2(i,false)
output sum
	 * 
	 * 
	 */
	
	
	public static long reverse (long n) {
		//reverses number
		long reverse=0;
		while (n>0) {
			reverse= 10*reverse + n%10; 
			n /= 10;
		}
		return reverse;
	}

	public static long base2 (long n) {
		//reverses number
		long base2=0;
		while (n>0) {
			base2= 2*base2 + n%2; 
			n /= 2;
		}
		return base2;
	}
	public static long rbase2 (long n) {
		//reverses number
		long rbase2=0;
		while (n>0) {
			rbase2= 2*rbase2 + n%2; 
			n /= 2;
		}
		return rbase2;
	}

	
	public static long dbp(int n) {
		int sum =0;
		for (long i=1; i<n;i++) {
			if (i%2==0 || i%10==0) continue;
			long reverse = reverse(i);
			if (i==reverse && i<= reverse) {
//				System.out.println(i);
				long base2=base2(i), rbase2=rbase2(base2);
				if (base2==rbase2) {
					System.out.println(i + " "+base2);
					sum+=i;
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
		//computes the sum of all numbers, less than one million, which are palindromic in base 2 and 10

		long startTime = System.nanoTime();
		System.out.println( dbp(1000000) );
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
