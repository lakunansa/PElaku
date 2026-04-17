/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0078_coin_partitions {

	/**
	 * 
	 */
	public ProjectEuler_0078_coin_partitions() {
		// TODO Auto-generated constructor stub
	}


	public static long cs(int n) {
		int m=100;
		ArrayList<Integer>
				parts = new ArrayList<Integer>();
		ArrayList<Integer> 
				pP= new ArrayList<Integer>(), 
				pN= new ArrayList<Integer>(); 
		parts.add(1);
		pP.add(0); pP.add(1);
		pN.add(0); pN.add(2);
		int temp;
		int count=0, p=1;
		do{
			count++;
			temp=0;
//			System.out.println(" count " +count);
			while (count>=pP.get(p-1)) {
				p++;
				pP.add( (p*(3*p-1))/2 );
				pN.add( (p*(3*p+1))/2 );
//				System.out.println("count " + count+ " pents added " + p);
			}
			int zap=1;
			while(count >= pP.get(zap)) {
				int a1,a2,a3,a4;
				a1=parts.get(count-pP.get(zap) );
				if (count < pN.get(zap)) a2=0; else a2=parts.get(count-pN.get(zap) );
				if (count < pP.get(zap+1)) a3=0; else a3=parts.get(count-pP.get(zap+1) );
				if (count < pN.get(zap+1)) a4=0; else a4=parts.get(count-pN.get(zap+1) );
//				System.out.println(" as " + a1 + " " + a2 + " " + a3 + " " + a4);
//				System.out.println(" zaps " + pP.get(zap) + " " + pN.get(zap) + " " + pP.get(zap+1) + " " + pN.get(zap+1) );
				
				temp += a1+a2-a3-a4;
				temp%=n;
//				System.out.println(" temp " + temp);
				zap+=2;
			} 
			parts.add(temp);
//			if (count==m) {
//				System.out.println("BreakCondition");
//				break;
//			}
		}while (temp!=0);
		for (int i=0;i<=count;i++) {
			System.out.println(i +" "+parts.get(i));
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
		System.out.println( cs(1000000) );
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
