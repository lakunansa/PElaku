/**
 * 
 */
import java.util.*;

/**
 * 
 */
public class ProjectEuler_0692_siegbert_and_jo {

	/**
	 * 
	 */
	public ProjectEuler_0692_siegbert_and_jo() {
		// TODO Auto-generated constructor stub
	}

	

	


	public static long _9(long n) {
		TreeMap<Long,Long> fibos = new TreeMap<Long, Long>();
		TreeMap<Long,Long> fibosInv = new TreeMap<Long, Long>();
		TreeMap<Long,Long> gibos = new TreeMap<Long, Long>();
		
		fibos.put(1L, 1L); fibos.put(2L, 2L);
		fibosInv.put(1L, 1L); fibosInv.put(2L, 2L);
		gibos.put(1L, 1L); gibos.put(2L, 3L);
		long cur = 2;
		while (n>fibos.get(cur)) {
			long fibocurpo = fibos.get(cur)+fibos.get(cur-1);
			fibos.put(cur+1, fibocurpo);
			fibosInv.put(fibocurpo, cur+1);
			gibos.put(cur+1, 
					gibos.get(cur) 
					+ gibos.get(cur-1) 
					+ fibos.get(cur) );
			cur++;
		}
//		for(Long l:gibos.values())System.out.println(l);
		long sum=0;
		long tmp=n;
		while (tmp != 0) {
			long fiboFloor = fibosInv.floorKey(tmp);
			long fiboArg = fibosInv.get(fiboFloor);
			sum += gibos.get(fiboArg);
			tmp-=fiboFloor;
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime

////23416728348467685
		long startTime = System.nanoTime();
		System.err.println( _9(23416728348467685L) );
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
