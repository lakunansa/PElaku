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
public class ProjectEuler_0102_triangle_containment {

	/**
	 * 
	 */
	public ProjectEuler_0102_triangle_containment() {
		// TODO Auto-generated constructor stub
	}


	public static long _tc(int n) {
		
		try {
			// Pfade anpassen (Julia nutzt relative Pfade zum Skript)
			String inputPath = "C:\\Users\\Dell\\Downloads\\0102_triangles.txt";
			Path path = Paths.get(inputPath);

			// 1. Text laden
			//            File inputFile = new File(inputPath);
			String content = Files.readString(path, StandardCharsets.UTF_8);

			//2. Matrix parsen aus String content
			String[] lines = content.split("[\\n\\r]");
			int rows = lines.length;
			int[][][] triangles = new int[rows][3][2];
			for (int i=0;i<rows;i++) {
				String[] line = lines[i].split("[,]");
				for (int p=0;p<3;p++) {
					for (int x=0;x<2;x++) {
						triangles[i][p][x]=
								Integer.parseInt(line[2*p+x]);
					}
				}
			}
			long count=0;
			for (int i=0;i<rows;i++) {
				double D =  triangles[i][0][0]*triangles[i][1][1] 
						-triangles[i][1][1]*triangles[i][2][0] //
						+triangles[i][2][0]*triangles[i][0][1] //
						-triangles[i][0][1]*triangles[i][1][0] 
						+triangles[i][1][0]*triangles[i][2][1] //
						-triangles[i][2][1]*triangles[i][0][0];// 
				
				double l1 = (triangles[i][1][0]*triangles[i][2][1] -triangles[i][1][1]*triangles[i][2][0])/D;
				double l2 = (triangles[i][2][0]*triangles[i][0][1] -triangles[i][2][1]*triangles[i][0][0])/D;
				double l3 = (triangles[i][0][0]*triangles[i][1][1] -triangles[i][0][1]*triangles[i][1][0])/D;
								
				if (0<=l1 && l1<=1 && 0<=l2 && l2<=1  && 0<=l3 && l3<=1) {
					count++;
				}
				
				
			}
			return count;

		} catch (IOException e) {
			System.err.println("Fehler: texxtdatei nicht gefunden oder schreibgeschützt.");
		}

		return 0;
	}

/**
 * 
 * 
 * 
 * 
 */



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _tc(10001) );
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
