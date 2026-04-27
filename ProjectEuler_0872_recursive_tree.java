import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0872_recursive_tree {

	/**
	 * 
	 */
	public ProjectEuler_0872_recursive_tree() {
		// TODO Auto-generated constructor stub
	}
	//"(a,b)->a.id>b.id"
	public static class Node{
		long id;
		ArrayList<Node> nl = new ArrayList<Node>();
	}


	public static long _rt_ki(long n, long k) {
		long diff = n - k;
		long current = n;
		long sum = n;

		// Wir gehen die Bits von der kleinsten Potenz (2^0) aufwärts durch
		for (int i = 0; i <= 62; i++) {
			long bitValue = 1L << i;
			if ((diff & bitValue) != 0) {
				current -= bitValue;
				sum += current;
			}
		}
		return sum;
	}


	public static long _rt(long n, long k) {

		/**		
		n=2; long count = 1;
		Node root = new Node();
		root.id=count;
		for (int i=2; i<=n;i++) {
			Node newRoot=new Node();			
			newRoot.id=i;
			newRoot.nl.add(root);
			if (true) {
				Node cur = root;
				while(!cur.nl.isEmpty()) {
					Node next = cur.nl.removeFirst();
					newRoot.nl.add(next);
					cur=next;
				}
			}
			root=newRoot;
			root.nl.sort( (a,b)-> (a.id>b.id)?1:-1 );
		}
		//Tree iterator schreiben, am besten: eingabe von ZielNode: Pfad zur Zielnode: Sume zur Zielnode
		 */
		//		n=32; k=6;
//		System.out.println(n + " n:k " + k);
		ArrayList<Integer> al= new ArrayList<Integer>();
		if (true) {//calculates the diffs of n-k
			long copyNsubK = n-k;
			int count = 0;
			while (copyNsubK%2==0) {
				count++;
				copyNsubK/=2;
			}
			al.add(count);
			while (copyNsubK>1) {
				copyNsubK=(copyNsubK-1);
				count = 0;
				while (copyNsubK%2==0) {
					count++;
					copyNsubK/=2;
				}
				al.add(count);
			}
		}
//		for (Integer a:al) System.out.println(a);
//		System.out.println();
		long[] twos = new long[63]; twos[0]=1;
		if (true) {//fills two array
			for (int i=1; i<=62; i++) twos[i]=twos[i-1]<<1;
		}
		long sum = n;
		if (true) {// computes the sum (and writes to syso)
			long copyN=n;
			int pow=0;
			for (int a:al) { 
//				System.out.println(pow+ " " +twos[pow]+ " " + copyN + " " + sum);
				copyN-=twos[pow+=a];
				sum+=copyN;
			}
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime

		long n = (long)(1E17), k = (long)Math.pow(9, 17);
		k=1; for (int i=0;i<17;i++) k*=9;
//		n = 32; k = 6;


		long startTime = System.nanoTime();
		System.out.println( _rt(  n, k ) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

		startTime = System.nanoTime();
		System.out.println();
		System.out.println( _rt_ki( n,k ) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);
	}
}
