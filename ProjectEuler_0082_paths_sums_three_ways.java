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
public class ProjectEuler_0082_paths_sums_three_ways {

	/**
	 * 
	 */
	public ProjectEuler_0082_paths_sums_three_ways() {
		// TODO Auto-generated constructor stub
	}


	public static long ps3w(int n) {

		try {
			// Pfade anpassen (Julia nutzt relative Pfade zum Skript)
			String inputPath = "C:\\Users\\Dell\\Downloads\\0082_matrix.txt";
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
System.out.println(rows + " " + cols);
			
			//3 shortest paths matrix, because cannot work in place for most of the time
			int[][] shortestPath = new int[rows][cols];
			for (int a=0;a<rows;a++) shortestPath[a][0] = matrix[a][0];

System.out.println("first column ");
for (int a=0;a<rows;a++) System.out.print(shortestPath[a][0] + " ");
System.out.println();
			
			//4. iterate through colums 2 to cols-1
			for (int a=1;a<(cols-1);a++) {
				
System.out.println("column " + a);

				
				//4.1 calculate rowpaths in that column
				int[][] rowPaths = new int [rows][rows];//each column gets its own rowpaths
				for (int b=0;b<rows;b++) {
					rowPaths[b][b]=matrix[b][a];
					for (int c=(b+1)%rows;c!=b;c=(c+1)%rows) {
						rowPaths[b][c] = rowPaths[b][(rows+c-1)%rows]+matrix[c][a];
					}
				}
System.out.println(" rowPaths ");
for (int c=0;c<rows;c++) {
	for (int b=0;b<rows;b++) {
		System.out.print(rowPaths[c][b] + " ");
	}
	System.out.println();
}
System.out.println();
				
				
				
				//find shortest path for each entry in that column
				for (int b=0;b<rows;b++) {//now shortest path is 
					int min=shortestPath[b][a-1] + matrix[b][a];
int minbc=b;					
					for (int c=(b+1)%rows;c!=b;c=(c+1)%rows) {
						int sum = 
								shortestPath[c][a-1] + 
								( rowPaths[b][c]<rowPaths[c][b] ? rowPaths[b][c]:rowPaths[c][b]);
						if (sum<min) {
							min=sum;
minbc=c;					
						}
					}
					shortestPath[b][a]=min;
System.out.println(" row " + b + " col " + a + " shortest from " + minbc +" length " +shortestPath[b][a]);					
				}
			}
			int min=Integer.MAX_VALUE;
			for (int a=0;a<rows;a++){
				int sum=shortestPath[a][cols-2]+matrix[a][cols-1];
				if (sum < min) min = sum;
			}


			
			
			return min;

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
		System.out.println( ps3w(10001) );
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
