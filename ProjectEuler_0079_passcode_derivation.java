/**
 * 
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * 
 */
public class ProjectEuler_0079_passcode_derivation {

	/**
	 * 
	 */
	public ProjectEuler_0079_passcode_derivation() {
		// TODO Auto-generated constructor stub
	}


	public static long _9(int n) {
        try {
            // Pfade anpassen (Julia nutzt relative Pfade zum Skript)
            String inputPath = "C:\\Users\\Dell\\Downloads\\0079_keylog.txt";
            Path path = Paths.get(inputPath);

            // 1. Text laden
//            File inputFile = new File(inputPath);
            String content = Files.readString(path, StandardCharsets.UTF_8);
    		String[] lines = content.split("[\\n\\r]");
    		int[] nums = new int[lines.length];
    		for (int i=0;i<nums.length;i++) nums[i]=Integer.parseInt(lines[i]);
    		
    		int[] anaI=new int[10], anaM=new int[10], anaF=new int[10];
    		for (int num : nums) {
    			anaI[num/100]++; anaM[(num/10)%10]++; anaF[num%10]++; 
    		}

/**
 * 
 */
 
    		//assume unique numbers, so simple incidence array is enough
    		int dim = nums.length;
    		int[][] table = new int[10][10];
    		for (int i=0;i<dim;i++) {
    			int num = nums[i], in=num/100, mi=(num/10)%10, fi=num%10;
    			if (table[in][mi]==0) 
    				table[in][mi] = 1; 
    			else{
    				if (table[in][mi] == -1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    			if (table[mi][in]==0)
    				table[mi][in] = -1; 
    			else {
    				if (table[mi][in] == 1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    			if (table[in][fi]==0)
    				table[in][fi] = 1; 
    			else {
    				if (table[in][fi] == -1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    			if (table[fi][in]==0)
    				table[fi][in] = -1; 
    			else {
    				if (table[fi][in] == 1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    			if (table[mi][fi]==0)
    				table[mi][fi] = 1; 
    			else {
    				if (table[mi][fi] == -1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    			if (table[fi][mi]==0)
    				table[fi][mi] = -1; 
    			else {
    				if (table[fi][mi] == 1) System.out.println("conflict " + i + " in " + in + " mi " + mi + " fi " + fi);
    			}
    		}
    		//if conflicts then some number is double
    		//otherwise we could find possible firsts and lasts and go from there, but
    		//simple summing strategy does the job here
    		
    		//find numbers not in keysequence, if any
    		int[] noOccurence = new int[10];
    		for1:for (int i=0;i<10;i++)
    			for (int j=0;j<10;j++)
    				if ( table[i][j] != 0 ) {
    					noOccurence[i]=-1;
    					continue for1;
    				}
    		for (int i=0;i<10;i++) noOccurence[i]=1+noOccurence[i];
    		
    		//find sum outgoing edges - incoming edges
    		int[] directedEdgeSums = new int[10]; 
    		for (int i=0;i<10;i++)
				if (noOccurence[i] != 1)
					for (int j=0;j<10;j++) directedEdgeSums[i] += table[i][j];

    		//sort indices via sums
    		ArrayList<int[]> sortableList = new ArrayList<int[]>();
    		for (int i=0;i<10;i++)
				if (noOccurence[i] != 1)
					sortableList.add( new int[] {i,directedEdgeSums[i]} );
    		sortableList.sort( (a,b)->b[1]-a[1] );
    		
    		//key is then simple combination of numbers
    		int key = 0;
    		for (int[] pair : sortableList) key = 10*key + pair[0];
    		
    		
//    		int[] possibleFirsts = new int[10];
//    		for1:for (int i=0;i<10;i++)
//    			for (int j=0;j<10;j++)
//    				if ( (noOccurence[i]==0) && (table[i][j] == -1) ) {
//    					possibleFirsts[i]=-1;
//    					continue for1;
//    				}
//    		for (int i=0;i<10;i++) 
//    			if (noOccurence[i]==0) possibleFirsts[i]=1+possibleFirsts[i];

//    		for (int pf : possibleFirsts) System.out.println(pf);
//    		System.out.println();
    		
    		System.out.println(key + "\n");
    		
    		for (int nO : noOccurence) System.out.println(nO);
    		System.out.println();

    		
    		
    		for (int i=0;i<10;i++) {
    			for (int j=0;j<10;j++)
    				System.out.print( table[i][j] + " ");
    				System.out.println();    			
    		}
    		
//    		System.out.println("inits");
//    		for (int in:anaI )System.out.println(in); 
//    		System.out.println("mids");
//    		for (int mi:anaM )System.out.println(mi); 
//    		System.out.println("finits");
//    		for (int fi:anaF )System.out.println(fi); 
//    		System.out.println("sums");
//    		for (int i=0;i<10;i++) System.out.println(anaI[i]+anaM[i]+anaF[i]);
//    		System.out.println();
            
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
