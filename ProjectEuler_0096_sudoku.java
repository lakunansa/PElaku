/**
 * 
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class ProjectEuler_0096_sudoku {

	/**
	 * 
	 */
	public ProjectEuler_0096_sudoku() {
		// TODO Auto-generated constructor stub
	}


	public static void mains(String[] args) throws IOException {//KI main method
		// Pfad zur Datei 0096_sudoku.txt anpassen
		List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Dell\\Downloads\\0096_sudoku.txt"));
		int totalSum = 0;

		for (int i = 0; i < lines.size(); i += 10) {
			int[][] grid = new int[9][9];
			for (int r = 0; r < 9; r++) {
				String rowLine = lines.get(i + 1 + r);
				for (int c = 0; c < 9; c++) {
					grid[r][c] = rowLine.charAt(c) - '0';
				}
			}

			if (solve(grid)) {
				// Die Zahl aus den ersten drei Ziffern der ersten Zeile bilden
				totalSum += grid[0][0] * 100 + grid[0][1] * 10 + grid[0][2];
			}
		}
		System.out.println("Gesamtsumme: " + totalSum);
	}

	private static boolean solve(int[][] grid) {//KI method
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (grid[row][col] == 0) { // Leeres Feld gefunden
					for (int num = 1; num <= 9; num++) {
						if (isValid(grid, row, col, num)) {
							grid[row][col] = num;
							if (solve(grid)) return true; // Rekursion
							grid[row][col] = 0; // Backtrack
						}
					}
					return false; // Keine Zahl passt -> Pfad falsch
				}
			}
		}
		return true; // Alles gefüllt
	}

	private static boolean isValid(int[][] grid, int row, int col, int num) {//KI method
		for (int i = 0; i < 9; i++) {
			// Check Zeile, Spalte und 3x3 Box gleichzeitig
			if (grid[row][i] == num) return false;
			if (grid[i][col] == num) return false;
			if (grid[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) return false;
		}
		return true;
	}

	public static Integer[] ints = new Integer[] {(Integer)0,(Integer)1,(Integer)2,(Integer)3,(Integer)4,(Integer)5,(Integer)6,(Integer)7,(Integer)8,(Integer)9}; 

	public static class Node{//maybe more fields generated
		int id;
		int[] unitId;//rococe
		int[] unitPl;
		Integer entry = 0;
		ArrayList<Integer> candidates = new ArrayList<Integer>(9);
		ArrayList<Integer> excluded = new ArrayList<Integer>(9);
		ArrayList<Node> neighbors = new ArrayList<Node>(20);
		ArrayList<ArrayList<Node>> notShared = new ArrayList<ArrayList<Node>>(3);  
		ArrayList<ArrayList<Node>> shared = new ArrayList<ArrayList<Node>>(2);  
		int[][] candidatesNotShared = new int[][] {{},{6,6,4},{6,6,4},{6,6,4},{6,6,4},{6,6,4},{6,6,4},{6,6,4},{6,6,4},{6,6,4}}; 
		int[][] candidatesShared =    new int[][] {{},  {2,2},  {2,2},  {2,2},  {2,2},  {2,2},  {2,2},  {2,2},  {2,2},  {2,2}}; 

		public void exclude (Integer entry) {//delete candidate, tell all neighbors to racalculate array
			//						if (this.id==60) System.out.println("node 60 exclude " + entry);
			if(this.candidates.remove(entry)) {
				for (int i=0;i<3;i++)
					for(Node neigh : this.notShared.get(i))
						neigh.candidatesNotShared[entry]
								[i]--;
				for (int i=0;i<2;i++)
					for(Node neigh : this.shared.get(i))
						neigh.candidatesShared[entry][i]--;
			}
		}

		public void place (Integer entry) {//here an entry is done
			//						if (this.id==60) System.out.println("node 60 place " + entry);
			this.entry=entry;
			while(!this.candidates.isEmpty()) exclude (this.candidates.getFirst());
			for (Node neigh : this.neighbors) neigh.exclude(entry);
		}

	}

	public static class Clique{//currently not needed
		int id, type;
		Node[] nodes = new Node[10];
		boolean[] placed = new boolean[10];
	}

	public static class Grid{
	}




	public static long _s(int n) {
		String content=null;
		if (true){//parse text file
			String inputPath = "C:\\Users\\Dell\\Downloads\\0096_sudoku.txt";
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
		String[] grids = content.split("[\\n\\r]");
		int numGrids = grids.length;
		int solution=0;
		
		for1: for (int sudoku=0; sudoku< numGrids; sudoku+=10) {//solve each sudoku in order, sudoku in 10er Schritten
			System.out.println(sudoku);
			Integer[] grid = new Integer[81];
			if (true){//builds grid
				for (int row=0; row<9;row++   ) { //build grid
					int line = Integer.parseInt(grids[sudoku+row+1]);
					int col = 8;
					while (line>0) {
						Integer entry = ints[line%10];
						line/=10;
						grid[9*row+col]=entry; 
						col--;
					}
					while (col>=0) {
						grid[9*row+col]=ints[0];
						col--;
					}
				}
			}//build done

			Node[] nodes = new Node[81];
			int notPlaced=81;

			if (true){//builds logic and constructs sudoku as NodeSudoku
				for (int row=0; row<9;row++) {//create Nodes
					for (int col=0; col<9;col++) {
						int id = 9*(row)+(col);
						int box = 3*((row)/3) + col/3;
						int bof = 3*((row)%3) + col%3;
						Node node = nodes[id] = new Node();
						node.id= id+1;
						node.unitId = new int[]{row+1,col+1,box+1};
						node.unitPl = new int[]{col+1,row+1,bof+1};
						node.notShared.add(new ArrayList<Node>(6));//0:row
						node.notShared.add(new ArrayList<Node>(6));//1:col
						node.notShared.add(new ArrayList<Node>(4));//2:box
						node.shared.add(new ArrayList<Node>(2));//0:row+box
						node.shared.add(new ArrayList<Node>(2));//1:col+box
						for (Integer e: ints) node.candidates.add(e);
						node.candidates.removeFirst();
					}
				}
				for (int id=0;id<81;id++) {//create NodeLogic, maybe erweitern, if necessary
					Node node = nodes[id];
					int 
					rowStart = 9*(id/9),
					rowEnd = rowStart+9,
					colStart = (id%9),
					boxStart = 27*(id/27)+3*(((id)%9)/3),
					boxEnd = boxStart+21;
					for (int idRow = rowStart; idRow<rowEnd; idRow++) {
						if (idRow != id ) {
							node.neighbors.add(nodes[idRow]); 
							if (idRow < id-id%3 || idRow > id-id%3+2)
								node.notShared.get(0).add(nodes[idRow]);
							else
								node.shared.get(0).add(nodes[idRow]);
						}
					}
					for (int idCol = colStart; idCol<81; idCol+=9) {
						if (idCol != id ) {
							node.neighbors.add(nodes[idCol]);
							if (idCol < boxStart || idCol > boxEnd)
								node.notShared.get(1).add(nodes[idCol]);
							else
								node.shared.get(1).add(nodes[idCol]);

						}
					}
					for (int idBox= boxStart; idBox<boxEnd; idBox+=6,boxStart=idBox) {
						for (;idBox < boxStart+3; idBox+=1) {
							if (idBox!= id) {
								if (id/9!=idBox/9 && id%9 != idBox%9 ) {
									node.neighbors.add(nodes[idBox]);
									node.notShared.get(2).add(nodes[idBox]);
								}
							}
						}
					}
					//debugging
					//					int row=0, col=0, box=0;
					//					for (Node nod: node.neighbors) {
					//						if((nod.id-1)/9==(node.id-1)/9)row++;
					//						if((nod.id-1)%9==(node.id-1)%9)col++;
					//						if((nod.id-1)/9/3==(node.id-1)/9/3 && (nod.id-1)%9/3==(node.id-1)%9/3)box++;
					//					}
					//					System.out.println("node:"+node.id +" "+ row + col+ box);
					//for (ArrayList<Node>a : node.notShared) {
					//for (Node nod: a) System.out.print(nod.id+" ");
					//System.out.println("not shared");
					//}
					//for (ArrayList<Node>a : node.shared) {
					//for (Node nod: a) System.out.print(nod.id+" ");
					//System.out.println("shared");
					//}
				}



				for (int id=0;id<81;id++) {//sets entries in NodeArray  using "place" and "elim" methods of Node class 
					if (grid[id]>0) {
						System.out.println("place initial integer: grid id"+ id + " nodoid "+ nodes[id].id + " entry " + grid[id]);
						nodes[id].place((Integer)grid[id]);
						notPlaced--;


					}
				}
			}


			System.out.println();
			for (int i=0;i<81;i++) {
				if (nodes[i].entry==0) {
					System.out.flush();
					System.err.flush();
					System.out.print(nodes[i].candidates.size()+"("+nodes[i].entry+") ");
					System.out.flush();
					System.err.flush();
				}
				else {
					System.err.flush();
					System.out.flush();
					System.out.print(nodes[i].candidates.size()+"("+nodes[i].entry+") ");
					System.err.flush();
					System.out.flush();
				}
				if (i%9==8) System.out.println();
			}

			System.out.println("\nhier sol "+sudoku);

			boolean iterate=true;
			while1: while (iterate) {
				iterate=false;
				if (notPlaced==0) break while1;	


				//one candidate left
				for (int i=0; i<81;i++) {//simple check, whether sole candidate left
					if (nodes[i].candidates.size()==1) {
						System.out.println("here size=1 " + i + " " + nodes[i].id + " entry " + nodes[i].candidates.getFirst());
						nodes[i].place(nodes[i].candidates.getFirst());
						notPlaced--;
						iterate=true;
						continue while1;
					}
				if (notPlaced==0) break while1;	
				
				}//end simple 
				
				//hidden single
				for (int i=0; i<81;i++){//simple check, whether no other candidate left for rocobo
					if (nodes[i].candidates.size()>0) {
						for (Integer e: ints) {
							if (e!=ints[0] && nodes[i].candidates.contains(e)) {
								int rowN = nodes[i].candidatesNotShared[e][0];
								int rowS = nodes[i].candidatesShared[e][0];
								int colN = nodes[i].candidatesNotShared[e][1];
								int colS = nodes[i].candidatesShared[e][1];
								int boxN = nodes[i].candidatesNotShared[e][2];
								if (rowS==0 && colS==0 && boxN==0) {
									System.out.println("here last in box: " + nodes[i].unitId[2] + " node: " + nodes[i].id + " " + e);
									nodes[i].place((Integer)e);
									notPlaced--;
									iterate=true;
									continue while1;
								}
								if (rowN==0 && rowS==0) {
									System.out.println("here last in row: " + nodes[i].unitId[0] + " node: "  + nodes[i].id + " " + e);
									nodes[i].place((Integer)e);
									notPlaced--;
									iterate=true;
									continue while1;
								}
								if (colN==0 && colS==0) {
									System.out.println("here last in col: " + nodes[i].unitId[1] + " node: "  + nodes[i].id + " " + e);
									nodes[i].place((Integer)e);
									notPlaced--;
									iterate=true;
									continue while1;
								}
							}
						}
					}
				}//end hidden single
				if (notPlaced==0) break while1;	

				//check, wether some neighbors can be nulled
				for (int i=0; i<81;i++){//
					for (Integer e: ints) {
						if (e!=ints[0]) {//box4 (notshared)=0 +                boxRowShared = 0,                          boxColShared >0              boxColSimple >0 -> exclude ColSimple  
							if (nodes[i].candidatesNotShared[e][2]==0 && nodes[i].candidatesShared[e][0] == 0 && nodes[i].candidatesShared[e][1]>0 && nodes[i].candidatesNotShared[e][1]>0) {
								for (Node j: nodes[i].notShared.get(1)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in notshared col:" + nodes[i].unitId[1] + " node: "   + nodes[i].id + " colSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								iterate=true;
								continue while1;
							}//exclude Colsimple 
							//box4 (notshared)=0           boxColShared = 0,                     boxRowShared >0                      boxRowSimple >0 -> exclude RowSimple  
							if (nodes[i].candidatesNotShared[e][2]==0 && nodes[i].candidatesShared[e][1] == 0 && nodes[i].candidatesShared[e][0]>0 && nodes[i].candidatesNotShared[e][0]>0) {
								for (Node j: nodes[i].notShared.get(0)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in notshared row:" + nodes[i].unitId[0] + " node: "   + nodes[i].id + " rowSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								iterate=true;
								continue while1;
							}//exclude rowsimple

							//row not shared is 0
							if (nodes[i].candidatesNotShared[e][0]==0 && nodes[i].candidatesShared[e][0]>0 && (nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][2]>0)) {
								for (Node j: nodes[i].notShared.get(2)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in notshared box (row):" + nodes[i].unitId[2] + " node: "   + nodes[i].id + " boxSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								for (Node j: nodes[i].shared.get(1)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in shared boxcol (row):" + nodes[i].unitId[2] + " node: "   + nodes[i].id + " boxSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								iterate=true;
								continue while1;
							}//exclude box
							if (nodes[i].candidatesNotShared[e][1]==0 && nodes[i].candidatesShared[e][1]>0 && (nodes[i].candidatesShared[e][0]+nodes[i].candidatesNotShared[e][2]>0)) {
								for (Node j: nodes[i].notShared.get(2)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in notshared box (col):" + nodes[i].unitId[2] + " node: "   + nodes[i].id + " boxSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								for (Node j: nodes[i].shared.get(0)) {
									if (j.candidates.contains(e)) {
										System.out.println("here exclude in shared boxcol (col):" + nodes[i].unitId[2] + " node: "   + nodes[i].id + " boxSimpleNeigh  " +j.id + " entry "+ e);
										j.exclude(e);
									}
								}
								iterate=true;
								continue while1;
							}//exclude Colsimple 


						}
					}
				}//end exclude neighbors

				//start find hidden double
				for (Node n1:nodes) {
					for (Node n2: n1.neighbors) {
						for (Integer e1:ints) {
							for (Integer e2: ints) {
								if (e1!=e2 && n1.candidates.contains(e1) && n1.candidates.contains(e2) && n2.candidates.contains(e1) && n2.candidates.contains(e2)) {
									//same only two candidates
									if (n1.candidates.size()==2 && n2.candidates.size()==2) {

										//same in row and entries in row left
										if (n1.unitId[0] == n2.unitId[0] 
												&& ((n1.candidatesShared[e1][0]+n1.candidatesNotShared[e1][0])>1 
														|| (n1.candidatesShared[e2][0]+n1.candidatesNotShared[e2][0])>1)) {
											for (Node n3:n1.notShared.get(0)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude row (notshared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude row (notshared) : " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}
											for (Node n3:n1.shared.get(0)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude row (shared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude row (shared):" + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}//
											iterate=true;
											continue while1;
										}//end same open double in row


										//same in col and entries in col left
										if (n1.unitId[1] == n2.unitId[1] 
												&& ((n1.candidatesShared[e1][1]+n1.candidatesNotShared[e1][1])>1 
														|| (n1.candidatesShared[e2][1]+n1.candidatesNotShared[e2][1])>1)) {
											for (Node n3:n1.notShared.get(1)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude col (notshared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude col (notshared) : " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}
											for (Node n3:n1.shared.get(1)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude col (shared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude col (shared):" + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}//
											iterate=true;
											continue while1;
										}//end same open double in col


										//same in col and entries in col left
										if (n1.unitId[2] == n2.unitId[2] 
												&& ((n1.candidatesShared[e1][0]+n1.candidatesShared[e1][1]+n1.candidatesNotShared[e1][2])>1 
														|| (n1.candidatesShared[e2][0]+n1.candidatesShared[e2][1]+n1.candidatesNotShared[e2][2])>1)) {
											for (Node n3:n1.notShared.get(2)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude box (notshared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude box (notshared) : " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}
											for (Node n3:n1.shared.get(0)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude boxrow (shared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude boxrow (shared):" + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}//
											for (Node n3:n1.shared.get(1)) {
												if (n3 != n2) {
													if (n3.candidates.contains(e1)) {
														System.out.println("here cand double exclude boxcol (shared): " + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e1);
														n3.exclude(e1);
													}
													if (n3.candidates.contains(e2)) {
														System.out.println("here cand double exclude boxcol (shared):" + n1.unitId[0] + " nodes: "   + n1.id + " " + n2.id + " " + n3.id + " entry "+ e2);
														n3.exclude(e2);
													}
												}
											}//
											iterate=true;
											continue while1;
										}//end same open double in col


									}
								}
							}
						}
					}
				}
				//end find double
				//here can also enter "hidden double"

				//start dinf triple
				//end find triple

				//start Xwing, single
				for (int i=0; i<78;i++){
					for (int j=i+1;j<79;j++) {
						for (int k=j+1;k<80;k++) {
							for (int l=k+1;l<81;l++) {
								for (Integer e: ints) {
									if (e!=ints[0]) {
										if (nodes[i].candidates.contains(e) & nodes[j].candidates.contains(e) & nodes[k].candidates.contains(e) & nodes[l].candidates.contains(e)) {
											//same row and col
											if(nodes[i].unitId[0]==nodes[j].unitId[0] && nodes[k].unitId[0]==nodes[l].unitId[0] && nodes[i].unitId[1]==nodes[k].unitId[1] && nodes[j].unitId[1]==nodes[l].unitId[1]) {
												if ((nodes[i].candidatesShared[e][0]+nodes[i].candidatesNotShared[e][0]==1) && (nodes[l].candidatesShared[e][0]+nodes[l].candidatesNotShared[e][0]==1)
														&& ((nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][1]>1) || (nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][1]>1))) {
													for (Node m: nodes[i].notShared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X R->C exclude notshared col:" + nodes[i].unitId[1] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X R->C exclude shared col:" + nodes[i].unitId[1] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X R->C exclude notshared col:" + nodes[l].unitId[1] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X R->C exclude shared col:" + nodes[l].unitId[1] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: R->C
												if (((nodes[i].candidatesShared[e][0]+nodes[i].candidatesNotShared[e][0]>1) || (nodes[l].candidatesShared[e][0]+nodes[l].candidatesNotShared[e][0]>1) )
														&& (nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][1]==1) && (nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][1]==1) ) {
													for (Node m: nodes[i].notShared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X C->R exclude notshared row:" + nodes[i].unitId[0] + " nodes: "   + nodes[i].id + " " + nodes[j].id  + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X C->R exclude shared row:" + nodes[i].unitId[0] + " nodes: "   + nodes[i].id + " " + nodes[j].id  + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X C->R exclude notshared row:" + nodes[l].unitId[0] + " nodes: "   + nodes[k].id + " " + nodes[l].id  + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X C->R exclude shared row:" + nodes[l].unitId[0] + " nodes: "   + nodes[k].id + " " + nodes[l].id  + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: C->R
											}//end same row and col
											//same row and box
											if(nodes[i].unitId[0]==nodes[j].unitId[0] && nodes[k].unitId[0]==nodes[l].unitId[0] && nodes[i].unitId[2]==nodes[k].unitId[2] && nodes[j].unitId[2]==nodes[l].unitId[2]) {
												//row fix, box open
												if (((nodes[i].candidatesShared[e][0]+nodes[i].candidatesNotShared[e][0]==1) && 
														(nodes[l].candidatesShared[e][0]+nodes[l].candidatesNotShared[e][0]==1)) &&
														((nodes[i].candidatesShared[e][0] + nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][2]>1) 
																|| (nodes[l].candidatesShared[e][0] + nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][2]>1))) {
													for (Node m: nodes[i].notShared.get(2)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude notshared box:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude shared boxrow:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude shared boxcol:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(2)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude notshared box:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude shared boxrow:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X R->B exclude shared boxcol:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: R->B
												//box fix, row open
												if (((nodes[i].candidatesShared[e][0]+nodes[i].candidatesNotShared[e][0]>1) || 
														(nodes[l].candidatesShared[e][0]+nodes[l].candidatesNotShared[e][0]>1)) &&
														((nodes[i].candidatesShared[e][0] + nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][2]==1) 
																&& (nodes[l].candidatesShared[e][0] + nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][2]==1))) {
													for (Node m: nodes[i].notShared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X B->R exclude notshared row:" + nodes[i].unitId[0] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X B->R exclude shared boxrow:" + nodes[i].unitId[0] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X B->R exclude notshared row:" + nodes[l].unitId[0] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X B->R exclude shared boxrow:" + nodes[l].unitId[0] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: B->R

											}//end same row and box
											//same box and col
											if(nodes[i].unitId[1]==nodes[k].unitId[1] && nodes[j].unitId[1]==nodes[l].unitId[1] && nodes[i].unitId[2]==nodes[k].unitId[2] && nodes[j].unitId[2]==nodes[l].unitId[2]) {
												//col fix, box open
												if (((nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][1]==1) && 
														(nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][1]==1)) &&
														((nodes[i].candidatesShared[e][0] + nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][2]>1) 
																|| (nodes[l].candidatesShared[e][0] + nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][2]>1))) {
													for (Node m: nodes[i].notShared.get(2)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude notshared box:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(0)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude shared boxrow:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude shared boxcol:" + nodes[i].unitId[2] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(2)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude notshared box:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(0)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude shared boxrow:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X C->B exclude shared boxcol:" + nodes[l].unitId[2] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: C->B
												//box fix, row open
												if (((nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][1]>1) || 
														(nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][1]>1)) &&
														((nodes[i].candidatesShared[e][0] + nodes[i].candidatesShared[e][1]+nodes[i].candidatesNotShared[e][2]==1) 
																&& (nodes[l].candidatesShared[e][0] + nodes[l].candidatesShared[e][1]+nodes[l].candidatesNotShared[e][2]==1))) {
													for (Node m: nodes[i].notShared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X B->C exclude notshared col:" + nodes[i].unitId[1] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[i].shared.get(1)) {
														if (m!=nodes[k] && m.candidates.contains(e)) {
															System.out.println("here X B->C exclude shared boxcol:" + nodes[i].unitId[1] + " nodes: "   + nodes[i].id + " " + nodes[k].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].notShared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X B->C exclude notshared col:" + nodes[l].unitId[1] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													for (Node m: nodes[l].shared.get(1)) {
														if (m!=nodes[j] && m.candidates.contains(e)) {
															System.out.println("here X B->C exclude shared boxcol:" + nodes[l].unitId[1] + " nodes: "   + nodes[j].id + " " + nodes[l].id + " " + m.id + " entry "+ e);
															m.exclude(e);
														}
													}
													iterate=true;
													continue while1;
												}//end X: B->R

											}//end same col and box

										}//end all contain e
									}
								}
							}
						}
					}
				}//end xwing


			}//endwhile


			boolean test=false;
			for (int i=0;i<81;i++) {
				if (nodes[i].entry==0) {
					System.err.flush();
					System.out.flush();
					System.err.print(nodes[i].candidates.size()+"("+nodes[i].entry+","+grid[i]+") ");
					System.err.flush();
					System.out.flush();
					test=true;
				}
				else {
					System.out.flush();
					System.err.flush();
					System.out.print(nodes[i].candidates.size()+"("+nodes[i].entry+","+grid[i]+") ");
					System.out.flush();
					System.err.flush();
				}
				if (i%9==8) System.out.println();
			}
			if (test) {
				System.err.println("\nnot solved "+sudoku);
				break for1;
			}
			System.out.println();
			solution+=nodes[0].entry*100 + nodes[1].entry*10 + nodes[2].entry;
		}
		return solution;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _s(1) );
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
