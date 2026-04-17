/**
 * 
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;

/**
 * 
 */
public class ProjectEuler_0042_coded_triangle_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0042_coded_triangle_numbers() {
		// TODO Auto-generated constructor stub
	}


	public static long ctn(int n) {
		Path filePath = Path.of("C:\\Users\\Dell\\Downloads\\0042_words.txt");
		String content; 
		String[] words = new String[0]; 
		try {
			content = Files.readString(filePath);
			words = content.split("[\",]",0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count=0;
		for (String a: words) {
			System.out.println(count + " "+ a + " "+ words.length);
			if (a.isEmpty()) continue;
			int sum = 0;
			for (int i=0;i<a.length();i++) {
				sum += ((byte)a.charAt(i)) -64;
			}
			sum <<=1;
			int sim = (int) Math.sqrt(sum);
			
			if (sim*(sim+1) == sum) count++;;
		}
		return count;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes how many are triangle words


		long startTime = System.nanoTime();
		System.out.println( ctn(10001) );
//		System.out.println("peter "+ (((byte)"A".charAt(0)) -64));
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
