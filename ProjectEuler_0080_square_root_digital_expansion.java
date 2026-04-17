/**
 * 
 */
import java.math.BigInteger;


/**
 * 
 */
public class ProjectEuler_0080_square_root_digital_expansion {

	/**
	 * 
	 */
	public ProjectEuler_0080_square_root_digital_expansion() {
		// TODO Auto-generated constructor stub
	}

	

	public static class JarvisSquareRoot {
	    public static void main(String[] args) {
	        // Example: Square root of 2 to 100 decimal digits
	        System.out.println("Sqrt(2) first 100 digits: " + calculateJarvisSqrt(2, 100));
	    }

	    public static String calculateJarvisSqrt(int n, int digits) {
	        BigInteger a = BigInteger.valueOf(5).multiply(BigInteger.valueOf(n));
	        BigInteger b = BigInteger.valueOf(5);
	        BigInteger ten = BigInteger.TEN;
	        BigInteger hundred = BigInteger.valueOf(100);

	        while (b.toString().length() <= digits + 1) {
	            if (a.compareTo(b) >= 0) {
	                a = a.subtract(b);
	                b = b.add(ten);
	            } else {
	                a = a.multiply(hundred);
	                b = b.divide(ten).multiply(hundred).add(BigInteger.valueOf(5));
	            }
	        }
	        String res = b.toString();
	        return res.substring(0, 1) + "." + res.substring(1, digits);
	    }
	}

	
	
	
	//will be expanded upon as we go along, 
	public static class BigInt10 {
		//has to be initialized with a number lower than 10^18; 
		//at this time, only positive numbers are allowed
		public static int MAX_DIM = 2_000_000_000;
		public static long MAX_ENTRY = 1_000_000_000_000_000_000L;
		public static long MAX_ENTRY_TENTH = 100_000_000_000_000_000L;

		long[] bigInt;
		int lastIndexUsed;
		public BigInt10(long i) {
			if (0<=i && i<MAX_ENTRY) {
				this.bigInt = new long[MAX_DIM];
				bigInt[0]=i;
				this.lastIndexUsed=0;
			}
		}
		public void add(long o) {
			
			if (o<0 || o>=MAX_ENTRY) return;
			long tmp=o;
			int i=0;
			while (tmp != 0) {
				bigInt[i] += tmp;
				if (bigInt[i]>=MAX_ENTRY) {
					bigInt[i] -=MAX_ENTRY;
					tmp=1;
				} else tmp=0;
				i++;
			}
			if (i>lastIndexUsed)lastIndexUsed++;
		}
		
		/**
		 * TODO
		 * @param o
		 */
		public void subtract(long o) {
			
		}
		/**
		 * TODO
		 * @param o
		 * @return
		 */
		public boolean isNotSmaller(BigInt10 o) {
			if (this.lastIndexUsed > o.lastIndexUsed) return true;
			if (this.lastIndexUsed < o.lastIndexUsed) return false;
			return false;
		}
		
		public void times10() {
			long tmp=0, tmp2=0;
			for (int i=0;i<=lastIndexUsed;i++) {
				tmp=bigInt[i] / MAX_ENTRY_TENTH;
				bigInt[i]=bigInt[i]%MAX_ENTRY_TENTH * 10 + tmp2;
				tmp2=tmp;
			}
			if (tmp2 != 0) {
				lastIndexUsed++;
				bigInt[lastIndexUsed]=tmp2;
			}
		}
	}
	
	public static long srde_Newton(int n, int d) {
		n=55;
		BigInteger N = new BigInteger(new byte[]{(byte)n});
		N=N.multiply(BigInteger.TEN.pow(d));
		BigInteger S = new BigInteger(N.toString());
		N= N.multiply(BigInteger.TEN.pow(d));
//		System.out.println(N + " " + S);
		for (int i = 1;i<=d/10;i++) {
			BigInteger copyS = new BigInteger(S.toString());
			BigInteger copyN = new BigInteger(N.toString());
			copyN=copyN.divide(copyS);
			S=S.add(N.divide(S)).divide(BigInteger.TWO);
		}
		System.out.println(S + " " + S.equals(N.sqrt()));
		
		return 0;
	}

	
	
	public static long srde_faster(int n, int d) {
		JarvisSquareRoot.main(null);
		return 0;
	}
	
	
	public static void computeD(int[] digs, int i, int[] r) {
		
System.out.print("compute: i " + i + " r " +(10*r[i-1]+r[i]) );
		
		r[i] += 10*r[i-1];
		int digT = r[i]/(2*digs[0])+1;
		int cT=-1;
System.out.println(" ri =  " + r[i] );
		while (cT <=0){
			digT--;
			cT = -digT*digT;
			for (int k=2*i-1; k>=i;k--) {
				cT /= 10;
				cT += r[k]-2* digs[k-i]*digT;
			}
System.out.println(" testC  " + cT + " testD " + (digT));
		}
		digs[i]=digT;
		r[2*i] -= digT*digT;
		for (int k=2*i-1; k>=i;k--) {
			r[k] -= 2*digs[k-i]*digT;
		}
System.out.println();
	}
	
	


	public static long srde(int n,int d) {
//		n=2;
//		d=100;
		int sum=0;
		int safe=2*d+1; 
		// 1. preluding calculations, logic s.t.s.
		for (int m=2; m<=n;m++) {
			
			int sqrtM = (int)Math.floor(Math.sqrt(m));
			int[] r= new int[safe];
			if ( (r[0] = m-sqrtM*sqrtM) == 0) {
System.out.println(" perfect square " + m);
				continue;
			}
System.out.println(" r0 " + r[0]);
			int[] digs = new int[safe];
			digs[0]=sqrtM;
			sum+=sqrtM;
			for (int i=1; i<d;i++) {
				computeD(digs, i, r);
System.out.println("dig " + i + " = " + digs[i] + " rest " + (10*r[i]+r[i+1]));
				sum+=digs[i];
			}
			for(int i=0; i<100;i++) System.out.println(digs[i]);
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
		System.out.println( srde_Newton(2,100) );
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
