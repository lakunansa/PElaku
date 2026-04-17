/**
 * 
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 
 */
public class ProjectEuler_0099_largest_exponential {

	/**
	 * 
	 */
	public ProjectEuler_0099_largest_exponential() {
		// TODO Auto-generated constructor stub
	}


	public static long _le(int n) {
		
		try {
			// Pfade anpassen (Julia nutzt relative Pfade zum Skript)
			String inputPath = "C:\\Users\\Dell\\Downloads\\0099_base_exp.txt";
			Path path = Paths.get(inputPath);

			// 1. Text laden
			//            File inputFile = new File(inputPath);
			String content = Files.readString(path, StandardCharsets.UTF_8);

			//2. Matrix parsen aus String content
			String[] lines = content.split("[\\n\\r]");
			int rows = lines.length;
			int[][] base_exp = new int[rows][2];
			for (int i=0;i<rows;i++) {
				String[] line = lines[i].split("[,]");
				base_exp[i][0]=Integer.parseInt(line[0]);
				base_exp[i][1]=Integer.parseInt(line[1]);
			}
			
			int lineMax=-1;
			double max = -1d;
			for (int i=0;i<rows; i++) {
				double ref = Math.log10(base_exp[i][0])*base_exp[i][1];
				if (ref>max) {
					System.out.println(ref-max);
					max = ref;
					lineMax = i;
				} else if (ref==max) System.out.println(" Problem " + max + " " + ref);
			}
			return lineMax+1;

		} catch (IOException e) {
			System.err.println("Fehler: Bilddatei nicht gefunden oder schreibgeschützt.");
		}

		
		
		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _le(10001) );
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
