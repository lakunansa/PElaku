import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class ProjectEuler_0961_removing_digits {

	/**
	 * 
	 */
	public ProjectEuler_0961_removing_digits() {
		// TODO Auto-generated constructor stub
	}

    // Statisches Array für die Zweierpotenzen: twos[j] = 2^j
    public static long[] twos = new long[63]; 
    static {
        for (int j = 0; j < 63; j++) twos[j] = 1L << j;
    }

    // Memoization-Map: Speichert für eine Zahl i, ob der Spieler, 
    // der gerade am Zug ist, gewinnt (true) oder verliert (false).
    public static Map<Long, Boolean> memo = new HashMap<>();

    public static boolean _game(long i) {
        // Basisffall: Wenn i == 0, hat der Spieler, der gerade dran ist, 
        // keine Züge mehr und verliert (daher false).
        if (i == 0) return false;

        // Memoization-Check
        if (memo.containsKey(i)) return memo.get(i);

        // Wir prüfen alle möglichen Züge
        for (int j = 0; twos[j] <= i; j++) {
            // Berechne den nächsten Zustand nach dem Zug
            long nextState = (i % twos[j]) + (i / twos[j + 1]) * twos[j];
            
            // Wenn der GEGNER im nächsten Zustand verliert (!game), 
            // gewinnt der aktuelle Spieler.
            if (!_game(nextState)) {
                memo.put(i, true);
                return true;
            }
        }

        // Wenn kein Zug zum Sieg führt, verliert der aktuelle Spieler.
        memo.put(i, false);
        return false;
    }
	
	
	public static long _c(int needParity, int lastD, int maxDig, int curDig) {
		//computes the number of combinations with given zeroParity between 0(i) and lastDig*10^numDigits(e)

		if (curDig==maxDig) {
			if (needParity==1) return lastD-1;
			else return 1;
		}
		//first: if nonzero is implugged in currDig, second: zero is implugged in currDig
		return 9*_c(1-needParity, lastD, maxDig, curDig+1) + _c(needParity, lastD, maxDig, curDig+1); 
	}
	
	public static long _try(long n) {
		//analyse number
		ArrayList<Integer> digs = new ArrayList<Integer>();
		ArrayList<Integer> par0 = new ArrayList<Integer>();
		ArrayList<Integer> par1 = new ArrayList<Integer>();
		if (true) {//collects all digits and stores the parity of the number ahead
			long copyN = n;
			int soFar1=0;
			int soFar0=0;
			while (copyN>0) {
				int tmp = (int)(copyN%10);
				if (tmp>0) soFar1=1-soFar1;
				else soFar0=1-soFar0;
				digs.add(tmp);
				par1.add(soFar1);
				par0.add(soFar0);
				copyN/=10;
			}
		}
		long sum=0;
		int switchParity=0;
		int numDigs = digs.size();
		//first
		for (int i=0; i<numDigs; i++) {
			int curD=digs.get(i);
			if (curD==0) continue;
			int parityLast = xor(par1.getLast(),switchParity);
			sum += _c(parityLast, curD, i, 0);
			switchParity=1-switchParity;
		}
		return sum;
	}
	
	
	public static int xor(int i, int j) {
		return i+j-2*i*j;
	}

//	public static long[] twos=new long[20];
	public static long[] nines=new long[20];
	public static long[] tens=new long[20];

	
	
//	public static Map<Integer,Boolean> memo = new HashMap<>();
	
	public static boolean _game(long i, boolean player ) {
		//can do with memoisation, then it is possible to solve 2^18
		if (i==0) return !player;
		for (int j=0; i%twos[j]!=i; j++) {
			if ( _game(i%twos[j] + (i/twos[j+1])*twos[j], !player)==player  ) {
				return player;
			}
		}
		return !player;
	}
	
	public static long _rd(long n) {
		if (true) {
			long tmp=1;
			for (int i=0; i<20; i++) {
				twos[i]=tmp;
				tmp*=2;
			}
			tmp=1;
			for (int i=0; i<20; i++) {
				nines[i]=tmp;
				tmp*=9;
			}
			tmp=1;
			for (int i=0; i<20; i++) {
				tens[i]=tmp;
				tmp*=10;
			}
		}
		long count=0;
		long countAlt=0;
		for (int i=1; i<=n;i++) {
			if (i%2==1) {
				long copyCount=count;
				int countD=0;
				while (copyCount>0) {
					copyCount/=10;
					countD++;
				}
				countAlt=count;
				count=9*tens[countD]+count;
			}
			else {
				long tmp=countAlt;
				countAlt=count;
				count= 2*count-10*tmp;
			}
		}
		return count;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _rd(18) );
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
