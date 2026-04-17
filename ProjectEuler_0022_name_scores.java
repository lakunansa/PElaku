/**
 * 
 */
	import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;

/**
 * 
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order, COLIN, which is worth 
, is the 
th name in the list. So, COLIN would obtain a score of 
.

What is the total of all the name scores in the file?h 


Path filePath = Path.of("c:/temp/demo.txt");

String content = Files.readString(fileName);
 * 
 */
public class ProjectEuler_0022_name_scores {

	/**
	 * 
	 */
	public ProjectEuler_0022_name_scores() {
		// TODO Auto-generated constructor stub
	}


	public static long _9(int n) {
		Path filePath = Path.of("C:\\Users\\Dell\\Downloads\\0022_names.txt");
		String content; 
		String[] names = new String[0]; 
		try {
			content = Files.readString(filePath);
			names = content.split("\",\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PriorityQueue<String> p = new PriorityQueue<String>(5000);
		names[0]=names[0].substring(1);
		names[names.length-1]=names[names.length-1].substring(0,names[names.length-1].length()-1);

		for (String a: names) p.add(a);
//		String mary=p.poll();
//		p.add(mary.substring(1));
		int index=0;
		long sum = 0;
		String q="";
		while (!p.isEmpty()) {
			index++;
			String c=p.poll();
			if (c.equals("COLIN")) {
				System.out.println(index);
			}
			if (true) {
//				System.out.println(q + "q  c"+ c);
			}

			for (int i=0;i<c.length();i++) {
//				System.out.println(c+" "+i+ " "+ ((byte)c.charAt(i)-64) );
				
				sum+=(index)*((byte)c.charAt(i)-64);
				
			}
			q=c;
//			if (index==1000) break;
		}
		System.out.println((byte)'A' + " " + index);
		for (int i=0;i<names.length;i++) {
		}
		
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the total sum of all name "scores"


		long startTime = System.nanoTime();
		System.out.println( _9(10001) );
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
