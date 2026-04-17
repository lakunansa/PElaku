/**
 * 
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Gold & Silver Coin Game II
Problem 895
Gary and Sally play a game using gold and silver coins arranged into a number of vertical stacks, alternating turns. On Gary's turn he chooses a gold coin and removes it from the game along with any other coins sitting on top. Sally does the same on her turn by removing a silver coin. The first player unable to make a move loses.

An arrangement is called fair if the person moving first, whether it be Gary or Sally, will lose the game if both play optimally.

An arrangement is called balanced if the number of gold and silver coins are equal.

Define 
 to be the number of fair and balanced arrangements consisting of three non-empty stacks, each not exceeding n
 in size. Different orderings of the stacks are to be counted separately



 */

/**
 * 
 */
public class ProjectEuler_895b {

	/**
	 * 
	 */
	public ProjectEuler_895b() {
		// TODO Auto-generated constructor stub
	}

	/**Tasks
	 *  1. Generate List of Games
	 *  1.1 generate partitions of all numbers smaller than n
	 *  1.1.1 store partition together with difference d, order by natural order
	 *  1.2 find all triples (A,B,C) of partitions that such d(A) = d(B)+d(C), where at least one length equals n  
	 */
	

	
	public static ArrayList<ArrayList<Integer>> createPartitionList(int n){
		ArrayList<ArrayList<Integer>> 
			list = new ArrayList<ArrayList<Integer>>(), 
			finallist = new ArrayList<ArrayList<Integer>>();
		//track sum, pos
		for (int i=n; i>0;i--) {
			ArrayList<Integer> listi = new ArrayList<Integer>();
			listi.add(i); listi.add(i); listi.add(0); listi.add(i);//size, diff,last,entries...	
			list.add(listi);
		}
		finallist.add(list.removeLast());
		do {
			ArrayList<Integer> listi = list.removeFirst();
			finallist.add(listi);
			Integer listiLast = listi.getLast();
			for (int i=listiLast-1; i>0;i--) {
				ArrayList<Integer> listj = new ArrayList<Integer>();
				list.add(listj);
				for (Integer iI : listi) listj.add(iI);
				listj.removeLast();
				listj.add(listiLast-i);
				listj.add(i);
				Integer listjDiff = listj.get(1);
				if (listj.get(2) == 0) {
					listj.set(1, listjDiff-2*i);
					listj.set(2, 1);
				} else {
					listj.set(1, listjDiff+2*i);
					listj.set(2, 0);
				}
			}
			finallist.add(list.removeLast());
		} while (!list.isEmpty());
		finallist.sort(partitionComparator);
		return finallist;
	}
	public static Comparator<ArrayList<Integer>> partitionComparator = new Comparator<ArrayList<Integer>>() {
		@Override
		public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
			int o1Size=o1.size(), o2Size=o2.size();
			Iterator<Integer> o1Iterator = o1.iterator(), o2Iterator = o2.iterator();
			Integer o1Next, o2Next;
			o1Next = o1Iterator.next(); 
			o2Next = o2Iterator.next();
			if (o1Next > o2Next) return +1;
			if (o1Next < o2Next) return -1;
			for (int i = 2; i<=3;i++) {
				o1Iterator.next();
				o2Iterator.next();
			}
			 do{
				o1Next = o1Iterator.next(); 
				o2Next = o2Iterator.next();
				if (o1Next > o2Next) return +1;
				if (o1Next < o2Next) return -1;
			} while (o1Iterator.hasNext() && o2Iterator.hasNext() );
			 if (o1Size > o2Size) return +1;
			 if (o1Size < o2Size) return -1;
			 return 0;
		}
	};
	
	public static long g_and_s_gII(int n) {
		ArrayList<ArrayList<Integer>> finallist = createPartitionList(n);
		System.out.println("list size " + finallist.size());
		for (ArrayList<Integer> listi:finallist) {
			for (Integer i : listi)
				System.out.print(" "+i);
			System.out.println();
		}

		return 0L;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		//		byte[] test = {1,2,1,-1,2,2};
		//		increment(test);
		int n=4;
		System.out.println( g_and_s_gII(n) );
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


