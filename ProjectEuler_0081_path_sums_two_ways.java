/**
 * 
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0081_path_sums_two_ways {

	/**
	 * 
	 */
	public ProjectEuler_0081_path_sums_two_ways() {
		// TODO Auto-generated constructor stub
	}


	public static long ps2w(int n) {
		
        try {
            // Pfade anpassen (Julia nutzt relative Pfade zum Skript)
            String inputPath = "C:\\Users\\Dell\\Downloads\\0081_matrix.txt";
            Path path = Paths.get(inputPath);

            // 1. Text laden
//            File inputFile = new File(inputPath);
            String content = Files.readString(path, StandardCharsets.UTF_8);
            
            //2. Matrix parsen aus String content
            String[] lines = content.split("[\\n\\r]");
    		int rows = lines.length;
    		int[][] matrix = new int[rows][];
    		for (int i=0;i<rows;i++) {
    			String[] lineNums = lines[i].split("[,]");
    			matrix[i]=new int[lineNums.length];
    			for (int j=0;j<lineNums.length;j++) 
    				matrix[i][j]=Integer.parseInt(lineNums[j]);
    		}
    		int cols = matrix[0].length;
    		
    		//3
    		for (int i=0;i<rows;i++)
    			for (int j=0;j<cols;j++) {
    				if (i==0 && j==0) continue;
    				else if (i==0) matrix[i][j]+=matrix[i][j-1];
    				else if (j==0) matrix[i][j]+=matrix[i-1][j];
    				else matrix[i][j] += (matrix[i][j-1] < matrix[i-1][j])?matrix[i][j-1]:matrix[i-1][j];
    			}
    		return matrix[rows-1][cols-1];
    		
        } catch (IOException e) {
            System.err.println("Fehler: Bilddatei nicht gefunden oder schreibgeschützt.");
        }
		
		return -1;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( ps2w(10001) );
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
