/**
 * 
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * 
 */
public class ProjectEuler_0089_roman_numerals {

	/**
	 * 
	 */
	public ProjectEuler_0089_roman_numerals() {
		// TODO Auto-generated constructor stub
	}

	

	    // Map für die Konvertierung von Symbolen zu Werten
	    private static final Map<Character, Integer> ROMAN_VALUES = Map.of(
	        'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
	    );

	    // LinkedHashMap für die Generierung (Reihenfolge ist wichtig!)
	    private static final Map<Integer, String> DEC_TO_ROMAN = new LinkedHashMap<>();
	    static {
	        DEC_TO_ROMAN.put(1000, "M");  DEC_TO_ROMAN.put(900, "CM");
	        DEC_TO_ROMAN.put(500, "D");   DEC_TO_ROMAN.put(400, "CD");
	        DEC_TO_ROMAN.put(100, "C");   DEC_TO_ROMAN.put(90, "XC");
	        DEC_TO_ROMAN.put(50, "L");    DEC_TO_ROMAN.put(40, "XL");
	        DEC_TO_ROMAN.put(10, "X");    DEC_TO_ROMAN.put(9, "IX");
	        DEC_TO_ROMAN.put(5, "V");     DEC_TO_ROMAN.put(4, "IV");
	        DEC_TO_ROMAN.put(1, "I");
	    }

	    public static void rn_ki(String[] args) {
	        // Beispielhafte Liste (im echten Problem aus roman.txt einlesen)
	        String[] romanNumbers = {"XXXXVIIII", "IIIIIIIIIIIIIIII", "CCCCLXXXXVIIII"}; 
	        
			String inputPath = "C:\\Users\\Dell\\Downloads\\0089_roman.txt";
			Path path = Paths.get(inputPath);

			// 1. Text laden
			//            File inputFile = new File(inputPath);
			String content=null;
			try {
				content = Files.readString(path, StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//2. Matrix parsen aus String content
			String[] romans = content.split("[\\n\\r]");

			romanNumbers=romans;
	        
	        int totalSaved = 0;

	        for (String original : romanNumbers) {
	            int decimalValue = romanToDecimal(original);
	            String minimalRoman = decimalToMinimalRoman(decimalValue);
	            totalSaved += (original.length() - minimalRoman.length());
	        }

	        System.out.println("Gesparte Zeichen: " + totalSaved);
	    }

	    // Schritt 1: Römisch zu Dezimal
	    public static int romanToDecimal(String s) {
	        int total = 0;
	        for (int i = 0; i < s.length(); i++) {
	            int current = ROMAN_VALUES.get(s.charAt(i));
	            if (i + 1 < s.length() && ROMAN_VALUES.get(s.charAt(i + 1)) > current) {
	                total -= current;
	            } else {
	                total += current;
	            }
	        }
	        return total;
	    }

	    // Schritt 2: Dezimal zu Minimal-Römisch
	    public static String decimalToMinimalRoman(int num) {
	        StringBuilder sb = new StringBuilder();
	        for (Map.Entry<Integer, String> entry : DEC_TO_ROMAN.entrySet()) {
	            while (num >= entry.getKey()) {
	                sb.append(entry.getValue());
	                num -= entry.getKey();
	            }
	        }
	        return sb.toString();
	    }


	public static long rn(int n) {
		try {
			// Pfade anpassen (Julia nutzt relative Pfade zum Skript)
			String inputPath = "C:\\Users\\Dell\\Downloads\\0089_roman.txt";
			Path path = Paths.get(inputPath);

			// 1. Text laden
			//            File inputFile = new File(inputPath);
			String content = Files.readString(path, StandardCharsets.UTF_8);

			//2. Matrix parsen aus String content
			String[] romans = content.split("[\\n\\r]");
			int nums = romans.length;

			int count=0;
			for (int i=0; i<nums;i++) {
				if (romans[i].indexOf("IIII")>=0) {
					if (romans[i].indexOf("VIIII")>=0) {
						count+=3;
					}
					else {
						count+=2;
					}
				}
				if (romans[i].indexOf("XXXX")>=0) {
					if (romans[i].indexOf("LXXXX")>=0) {
						count+=3;
					}
					else {
						count+=2;						
					}
				}
				if (romans[i].indexOf("CCCC")>=0) {
					if (romans[i].indexOf("DCCCC")>=0) {
						count+=3;
					}
					else {
						count+=2;
					}
				}
			} 
			return count;
		} catch (IOException e) {
			System.err.println("Fehler: Textdatei nicht gefunden oder schreibgeschützt.");
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
		System.out.println( rn(10001) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);

				startTime = System.nanoTime();
				rn_ki(null);
//				System.out.println(""+  );
				endTime = System.nanoTime();
				duration = (endTime - startTime);
				System.out.println(duration/1000000);


	}

}
