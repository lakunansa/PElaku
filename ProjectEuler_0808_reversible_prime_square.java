/**
 * 
 */

import java.util.ArrayList;
import java.util.*;

/**
 * 
 */
public class ProjectEuler_0808_reversible_prime_square {

	/**
	 * 
	 */
	public ProjectEuler_0808_reversible_prime_square() {
		// TODO Auto-generated constructor stub
	}

	
	

	public static class Solver {
	    public static long solve808() {
	        int LIMIT = 40_000_000; // Estimated range to find 50 reversible prime squares
	        BitSet isPrime = sieve(LIMIT);
	        Set<Long> primeSquares = new HashSet<>();
	        List<Long> results = new ArrayList<>();

	        // 1. Generate all prime squares in range
	        for (int p = 2; p < LIMIT; p++) {
	            if (isPrime.get(p)) {
	                primeSquares.add((long) p * p);
	            }
	        }

	        // 2. Check each prime square for the reversible property
	        for (long sq : primeSquares) {
	            long rev = reverse(sq);
	            if (rev != sq && primeSquares.contains(rev)) {
	                results.add(sq);
	            }
	            if (results.size() == 50) break;
	        }

	        return results.stream().mapToLong(Long::longValue).sum();
	    }

	    private static BitSet sieve(int n) {
	        BitSet prime = new BitSet(n + 1);
	        prime.set(2, n + 1);
	        for (int p = 2; p * p <= n; p++) {
	            if (prime.get(p)) {
	                for (int i = p * p; i <= n; i += p)
	                    prime.clear(i);
	            }
	        }
	        return prime;
	    }

	    private static long reverse(long n) {
	        long rev = 0;
	        while (n > 0) {
	            rev = rev * 10 + n % 10;
	            n /= 10;
	        }
	        return rev;
	    }
	}

	
	
	

	public static class Pair{
		long prime, priMul;
	}

	public static long _9(long n) {
		//		ps(100);
		long sum =0;
		ArrayList<Pair> primes = new ArrayList<Pair>((int)1E6);
		ArrayList<Long> priSqu = new ArrayList<Long>((int)1E6);
		ArrayList<Long> rePrSq = new ArrayList<Long>((int)1E6);
		
		long cur=3; long curSq = 9;
		{
			Pair p=new Pair();
			p.prime=cur; p.priMul=curSq;
			primes.add(p);
			priSqu.add(curSq);
		}

		cur = 5; curSq = 25;
		long div = 1, tendiv = 10;
		long sqdiv = 10, sqtendiv = 100;
		
		while (rePrSq.size()<50) {
			while (cur<tendiv) {
//				if (cur >96720  ) {
//					System.out.println("here");
//					System.out.println("doppel her");
//				}
				if (priSqu.contains(cur) && (cur/div)%2==1 && (cur/div)>=cur%10) {
					long tmp = cur;
					long rev = 0;
					while(tmp>0) {
						rev=rev*10+tmp%10;
						tmp/=10;
					}
					if (rev<cur) {
						if (priSqu.contains(rev)) {
							System.out.println(rev + " " + cur);
							rePrSq.add(rev); rePrSq.add(cur);
						}
					}
				}
				boolean isPrime = true;
				forP:for (Pair p:primes) {
					if (cur<p.priMul) continue forP;
//					if (cur==p.priMul) {
						p.priMul+=2*p.prime;
						if (isPrime) isPrime=false;
//					}
				}
				if (isPrime) {
					Pair p=new Pair();
					p.prime=cur; p.priMul=3*cur;
					primes.add(p);
					if ((curSq/sqdiv)%2==1) priSqu.add(curSq);
				}
				curSq += 4*cur + 4; cur+=2;
//				System.out.println(curSq + " num " + rePrSq.size());
				if (curSq > sqtendiv) {
					sqdiv*=10; sqtendiv*=10;
				}
			}
			while(!priSqu.isEmpty() && priSqu.getFirst()< tendiv) priSqu.removeFirst();
			div*=10; tendiv*=10;
			/**
			 * 
			 */
		}
		

		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
//		System.out.println( _9((long)1E16) );
		System.out.println(Solver.solve808());
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
