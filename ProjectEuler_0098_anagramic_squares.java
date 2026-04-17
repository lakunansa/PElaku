/**
 * 
 */

import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */
public class ProjectEuler_0098_anagramic_squares {

	/**
	 * 
	 */
	public ProjectEuler_0098_anagramic_squares() {
		// TODO Auto-generated constructor stub
	}

	

	public static class Problem98 {
	    public static void main(String[] args) throws IOException {
	        String data = readFile("C:\\Users\\Dell\\Downloads\\0098_words.txt");
	        String[] words = data.replace("\"", "").split(",");
	        
	        // 1. Anagramm-Gruppen finden
	        Map<String, List<String>> anagramGroups = new HashMap<>();
	        for (String w : words) {
	            char[] chars = w.toCharArray();
	            Arrays.sort(chars);
	            String key = new String(chars);
	            anagramGroups.computeIfAbsent(key, k -> new ArrayList<>()).add(w);////TODO
	        }

	        long maxSquare = 0;

	        // 2. Gruppen mit mindestens 2 Wörtern prüfen
	        for (List<String> group : anagramGroups.values()) {
	            if (group.size() < 2) continue;

	            int len = group.get(0).length();
	            List<Long> squares = getSquaresOfLength(len);

	            for (int i = 0; i < group.size(); i++) {
	                for (int j = i + 1; j < group.size(); j++) {
	                    maxSquare = Math.max(maxSquare, findMaxAnagramSquare(group.get(i), group.get(j), squares));
	                }
	            }
	        }
	        System.out.println("Das Ergebnis ist: " + maxSquare);
	    }

	    private static long findMaxAnagramSquare(String w1, String w2, List<Long> squares) {
	        long currentMax = 0;
	        for (long s1 : squares) {
	            Map<Character, Integer> mapping = getMapping(w1, s1);////TODO
	            if (mapping == null) continue;

	            long s2 = applyMapping(w2, mapping);
	            if (s2 != -1 && isSquare(s2) && String.valueOf(s2).length() == w2.length()) {
	                currentMax = Math.max(currentMax, Math.max(s1, s2));
	            }
	        }
	        return currentMax;
	    }

	    private static Map<Character, Integer> getMapping(String word, long square) {
	        String sStr = String.valueOf(square);
	        if (word.length() != sStr.length()) return null;
	        
	        Map<Character, Integer> charToDigit = new HashMap<>();
	        Map<Integer, Character> digitToChar = new HashMap<>();

	        for (int i = 0; i < word.length(); i++) {
	            char c = word.charAt(i);
	            int d = sStr.charAt(i) - '0';////TODO
	            if (charToDigit.getOrDefault(c, d) != d || digitToChar.getOrDefault(d, c) != c) return null;////TODO
	            charToDigit.put(c, d);
	            digitToChar.put(d, c);
	        }
	        return charToDigit;
	    }

	    private static long applyMapping(String word, Map<Character, Integer> mapping) {
	        if (mapping.get(word.charAt(0)) == 0) return -1; // Führende Null
	        long res = 0;
	        for (char c : word.toCharArray()) {
	            res = res * 10 + mapping.get(c);
	        }
	        return res;
	    }

	    private static List<Long> getSquaresOfLength(int len) {
	        List<Long> res = new ArrayList<>();
	        long start = (long) Math.ceil(Math.sqrt(Math.pow(10, len - 1)));
	        long end = (long) Math.floor(Math.sqrt(Math.pow(10, len) - 1));
	        for (long i = start; i <= end; i++) res.add(i * i);
	        return res;
	    }

	    private static boolean isSquare(long n) {
	        long sqrt = (long) Math.sqrt(n);
	        return sqrt * sqrt == n;
	    }

	    private static String readFile(String path) throws IOException {
	        // Hilfsmethode zum Einlesen der Datei
	        Scanner sc = new Scanner(new File(path));
	        return sc.hasNext() ? sc.next() : "";
	    }
	}

	
	
	
	public static class CharCount{
		long n1=-1;
		String s1;
		byte C, n;
	}

	public static class Pair{
		int length;
		ArrayList<String> sL = new ArrayList<String>(2);
		ArrayList<CharCount> ccL = new ArrayList<CharCount>();//words
		ArrayList<Long> lL = new ArrayList<Long>();
		ArrayList<ArrayList<CharCount>> combine = new ArrayList<ArrayList<CharCount>>();
	}

	public static long _as(int n) {
		String content=null;
		if (true){//parse text file
			String inputPath = "C:\\Users\\Dell\\Downloads\\0098_words.txt";
			Path path = Paths.get(inputPath);
			//1. Text laden
			//File inputFile = new File(inputPath);
			try {
				content = Files.readString(path, StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2. Matrix parsen aus String content
		}
		String[] words = content.split("[\n,\"\r]");
		Arrays.sort(words, (String a, String b)-> b.compareTo(a));
		int maxLength = 0;
		for (String a: words) {
			if (a.length() > maxLength) maxLength=a.length(); 
		}
		ArrayList<ArrayList<String>> wLL = new ArrayList<ArrayList<String>>(maxLength+1);//list of words ordered by wordlength
		for (int i=0;i<=maxLength;i++) wLL.add(new ArrayList<String>());
		for (String a: words) {
			int len=a.length();
			if (!a.isEmpty()) wLL.get(len).add(a);
		}
		ArrayList<Pair> aL = new ArrayList<Pair>();
		ArrayList<ArrayList<Pair>> pLL = new ArrayList<ArrayList<Pair>>(maxLength+1);//list(wordlength) of list (Pair)// pLL = wLL
		forWL: for (ArrayList<String> wL : wLL) {
			ArrayList<Pair> pL = new ArrayList<Pair>(wL.size()); //list (string) of list(Pair) // pL = wL
			pLL.add(pL);
			forS: for (String a:wL) {//word as String // p = w
				Pair p1 = new Pair();
				p1.sL.add(a);
				p1.length=a.length();
				ArrayList<CharCount> ccL=p1.ccL;
				forB: for (byte b:a.getBytes()) {//iterates through all chars/bytes in word and creates charvount for corresponding pair
					int I=-1;
					forI:for (int ccI=0; ccI<ccL.size();ccI++){
						if (b==ccL.get(ccI).C) {
							I=ccI;
							break forI; 
						}
					}
					if (I==-1) {
						I=ccL.size();
						CharCount cc = new CharCount();
						cc.C = b;
						cc.n = 1;
						ccL.add(cc);
					}else {
						ccL.get(I).n++;
					}
				}
				ccL.sort( (CharCount x, CharCount y)-> x.n-y.n==0?x.C-y.C:x.n-y.n);//here the list of chars is done and ordered
				boolean added=false;
				forp2:for (Pair p2 : pL) {//finds pairs of strings
					if (p2.ccL.size() == ccL.size()) {
						for (int cur=0;cur<ccL.size();cur++) {
							if (!(p2.ccL.get(cur).C==ccL.get(cur).C && p2.ccL.get(cur).n==ccL.get(cur).n)) {
								continue forp2;
							}
						}
						p2.sL.add(a);
						added = true;
						aL.add(p2);
						for (String b:p2.sL) System.out.print(b+" ");
						System.out.println();
					} 
				}
				if (pL.isEmpty() || !added) pL.add(p1);
			}
		}
		System.out.println("here squares");
		int limit = (int) Math.sqrt(Math.pow(10, 10));
		long[] squares = new long[limit+1];//builds squares
		for (int i=0;i<=limit;i++) {
			long l=  squares[i]=((long)i)*((long)i);
		}
		for(Pair p:aL) {//looks for matches in words and squsers
			int max=(int) Math.sqrt(Math.pow(10,p.sL.getFirst().length()));
			int min=(int) Math.sqrt(Math.pow(10,p.sL.getFirst().length()-1))+1;
			fori: for (int i=min;i<=max;i++) {//compares squares with pairs
				fors: for (String s: p.sL) {
					//					System.out.print(" "+s+" ");
					long sq = squares[i];
					//reverses number
					long rsq=0;
					while (sq>0) {
						rsq= 10*rsq + sq%10; 
						sq /= 10;
					}
					ArrayList<CharCount> combined = new ArrayList<CharCount>();
					int cur = 0;
					boolean[] checkout = new boolean[10];
					forwhile: while (cur < s.length()) {
						byte b = (byte)(rsq%10);
						char c = s.charAt(cur);
						boolean hasB =false;
						forcc: for (CharCount lcc : combined) {
							if (lcc.C == (byte)c) {
								if (lcc.n==b) {
									hasB =true;
									break forcc; 
								}else continue fors;
							} 
						}
						if (!hasB){
							if (!checkout[b]) {
								CharCount cc= new CharCount();
								cc.C=(byte)c;
								cc.n=b;
								cc.n1=(long)i*(long)i;
								cc.s1=s;
								checkout[b]=true;
								combined.add(cc);
							} else continue fors;
						}
						rsq/=10;
						cur++;
					}
					//					System.out.println(i*i + " test " + s);
					if (!p.lL.contains((long) i*(long) i)) {
						p.lL.add((long) i*(long) i);
					}
					p.combine.add(combined);
				}
			}
			System.out.println(p.combine.size() + " combine " + p.sL.getFirst() + " " + p.lL.getFirst());
			for(ArrayList<CharCount> lastL:p.combine) {
				int count=0;
				long max1=-1;
				forlastS:for (String LastS: p.sL) {
					long num=0;
					CharCount lastCC=null;
					for (int j=0;j<LastS.length();j++) {
						boolean hasC = false;
						forCC: for (CharCount cc: lastL) {
							if (cc.C == (byte) LastS.charAt(j)) {
								hasC =true;
								lastCC=cc;
								break forCC;
							}
						}
						if (hasC) {
							num=10*num+lastCC.n;
						} else continue forlastS;
					}
//					System.out.println(num + " " + LastS);
					if (num!=lastCC.n1 && p.lL.contains(num)) System.err.println(num + " solution");
				}
			}
		}

		/**		ArrayList<ArrayList<CharCount>> sqccL= new ArrayList<ArrayList<CharCount>>(limit+1);//creates squares
		for (int i=0;i<=limit;i++) {
			long l=  squares[i]=((long)i)*((long)i);
			ArrayList<CharCount> sqCC = new ArrayList<CharCount>(10);
			sqccL.add(sqCC);
			while (l>0) {
				byte c=(byte)(l%10);
				boolean hasC=false;
				forCC:for (CharCount cc:sqCC) {
					if (cc.C == c) {
						cc.n++;
						hasC = true;
						break forCC;
					}
				}
				if (!hasC) {
					CharCount cc = new CharCount();
					cc.C=c;
					cc.n=1;
					sqCC.add(cc);
				}
				l/=10;
			}
			sqCC.sort((a,b)->b.n-a.n);
			//			System.out.println(i+": ");
			//			for (CharCount cc : sqCC )System.out.print(cc.C + "/"+ cc.n+ " ");
			//			System.out.println();
		}
		long max1 = -1;
		for(Pair p:aL) {//looks for matches in words and squsers
			System.out.println("here last");
			long max2 = -1;
			int max=(int) Math.sqrt(Math.pow(10,p.sL.getFirst().length()));
			int min=(int) Math.sqrt(Math.pow(10,p.sL.getFirst().length()-1)+1);
			fori: for (int i=min;i<=max;i++) {
				ArrayList<CharCount> ccL = sqccL.get(i);
				if (ccL.size() == p.ccL.size()) {
					boolean isMatch=true;
					forj:for (int j=0;j<ccL.size();j++) {

						if (!(ccL.get(j).n == p.ccL.get(j).n)) {
							isMatch=false;
							continue fori; 
						}
					}
					if (isMatch) {
						System.out.println(i*i+": "+p.sL.getFirst()+ " ");
						for (CharCount cc : ccL )System.out.print(cc.C + "/"+ cc.n+ " ");
						System.out.println();
						for (CharCount cc : p.ccL )System.out.print(cc.C + "/"+ cc.n+ " ");
						System.out.println();


						fors: for (String s: p.sL) {
							System.out.print(" "+s);
							long sq = ((long)i)*((long)i);
							//reverses number
							long reverse=0;
							while (sq>0) {
								reverse= 10*reverse + sq%10; 
								sq /= 10;
							}
							ArrayList<CharCount> last = new ArrayList<CharCount>();
							int cur = 0;
							forwhile: while (reverse>0) {
								byte b = (byte)(reverse%10);

								char c = s.charAt(cur);
								boolean hasB =false;
								forcc: for (CharCount lcc : last) {
									if (lcc.C == (byte)c) {
										if (lcc.n==b) {
											hasB =true;
											break forcc; 
										}else continue fors;
									} 
								}
								if (!hasB){
									CharCount cc= new CharCount();
									cc.C=(byte)c;
									cc.n=b;
									last.add(cc);
								}
								reverse/=10;
								cur++;
							}
							System.out.println(i*i);
							p.lL.add((long) i);
							p.combine.add(last);
						}

//						p.lL.add((long)i);
						if ((long)i>max2) max2 =(long)i;
						if (p.lL.size()>1 && max2>max1) max1=max2; 
					}
				}
			}
		}
		 */

//		for (ArrayList<String> wL : wLL) {
//			for (String a:wL) System.err.print(a+", ");
//			System.err.println();
//		}
//		System.out.println(maxLength);
		int numWords = words.length;
		int solution=0;


		return 0;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
//		System.out.println( _as(10001) );
		try {
			Problem98.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
