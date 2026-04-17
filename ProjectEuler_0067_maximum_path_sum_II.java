/**
 * 
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0067_maximum_path_sum_II {

	/**
	 * 
	 */
	public ProjectEuler_0067_maximum_path_sum_II() {
		// TODO Auto-generated constructor stub
	}


	public static long mpsII(int n) {
		Path filePath = Path.of("C:\\Users\\Dell\\Downloads\\0067_triangle.txt");
		String content; 
		String[] numbers = new String[0]; 
		try {
			content = Files.readString(filePath);
			numbers = content.split("[\\n\\r\\s]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int l = numbers.length, sql = (int)Math.floor(Math.sqrt(2*l)), count=0;
		//n*N+1 /2 = (nn + n)/2
		int[][] grid = new int[sql][];
		for (int i=0;i<sql;i++) {
			grid[i]=new int[i+1];
			for (int j=0; j<grid[i].length; j++) {
				grid[i][j]=Integer.parseInt(numbers[count++]);
				if(i==0)continue;
				else if(j==0) grid[i][j]+=grid[i-1][j];
				else if(j==grid[i].length-1) grid[i][j]+=grid[i-1][j-1];
				else if (grid[i-1][j]>grid[i-1][j-1]) grid[i][j]+=grid[i-1][j];
				else grid[i][j]+=grid[i-1][j-1];
			}
		}
		int max=0;
		for (int j=0; j<sql; j++)
			if (max< grid[sql-1][j]) max=grid[sql-1][j];
//		for (String s:numbers)System.out.println(s);
		for (int i=0;i<sql;i++) {
			for (int j=0; j<grid[i].length; j++) {
				System.out.println(grid[i][j]);
			}
		}
		return max;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( mpsII(10001) );
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
