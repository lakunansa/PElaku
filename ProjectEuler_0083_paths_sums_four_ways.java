/**
 * 
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 
 */
public class ProjectEuler_0083_paths_sums_four_ways {

	/**
	 * 
	 */
	public ProjectEuler_0083_paths_sums_four_ways() {
		// TODO Auto-generated constructor stub
	}

	public static class Token implements Comparable<Token>{

		Token(int i, int j, int[][] sPath){
			this.i=i;
			this.j=j;
			this.sPath=sPath;
		}
		int i, j;
		int[][] sPath;

		@Override
		public int compareTo(ProjectEuler_0083_paths_sums_four_ways.Token o) {
			return this.sPath[this.i][this.j] - o.sPath[o.i][o.j];
		}		
	}


	public static long ps4w(int n) {

		try {
			// Pfade anpassen (Julia nutzt relative Pfade zum Skript)
			String inputPath = "C:\\Users\\Dell\\Downloads\\0083_matrix.txt";
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

			//inti: all weights are max, sauf first col
			ArrayList<Token> q = new ArrayList<Token>();
			int[][] sPath = new int[rows][cols];
			int min=1;
			for (int i=0; i< rows; i++)
				for (int j=0; j< cols; j++) 
					min+=matrix[i][j];
			for (int i=0; i< rows; i++)
				for (int j=0; j< cols; j++) 
					sPath[i][j]=min;
			sPath[0][0]=matrix[0][0];
			for (int i=0; i< rows; i++)
				for (int j=0; j< cols; j++) 
					q.add(new Token(i, j, sPath));
			q.sort( (a,b)-> { return sPath[a.i][a.j] - sPath[b.i][b.j]; });
			while(!q.isEmpty()) {
				Token t = q.removeFirst();
				int i=t.i, j=t.j;
				if (i!=0) {
					int p = sPath[i][j]+matrix[i-1][j];
					if (p < sPath[i-1][j]) sPath[i-1][j] = p;  					
				}
				if (i!=rows-1) {
					int p = sPath[i][j]+matrix[i+1][j];
					if (p < sPath[i+1][j]) sPath[i+1][j] = p;  					
				}
				if (j!=0) {
					int p = sPath[i][j]+matrix[i][j-1];
					if (p < sPath[i][j-1]) sPath[i][j-1] = p;  					
				}
				if (j!=cols-1) {
					int p = sPath[i][j]+matrix[i][j+1];
					if (p < sPath[i][j+1]) sPath[i][j+1] = p;  					
				}
				q.sort( (a,b)-> { return sPath[a.i][a.j] - sPath[b.i][b.j]; });
			}

			for (int i=0; i< rows; i++) {
				for (int j=0; j< cols; j++) 
					System.out.print(matrix[i][j] + " " +  sPath[i][j] + "  ");
				System.out.println();
			}



			return sPath[rows-1][cols-1];

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
		System.out.println( ps4w(10001) );
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
