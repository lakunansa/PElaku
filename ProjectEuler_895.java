/**
 * 
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Gold & Silver Coin Game II
Problem 895
Gary and Sally play a game using gold and silver coins arranged into a number of vertical stacks, alternating turns. On Gary's turn he chooses a gold coin and removes it from the game along with any other coins sitting on top. Sally does the same on her turn by removing a silver coin. The first player unable to make a move loses.

An arrangement is called fair if the person moving first, whether it be Gary or Sally, will lose the game if both play optimally.

An arrangement is called balanced if the number of gold and silver coins are equal.

Define 
 to be the number of fair and balanced arrangements consisting of three non-empty stacks, each not exceeding n
 in size. Different orderings of the stacks are to be counted separately



 */

/**
 * 
 */
public class ProjectEuler_895 {

	/**
	 * 
	 */
	public ProjectEuler_895() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * this is the init string for games of length n, should be initialized by call of following method 
	 * maybe can be reduced to dummystring?
	 */
	static String init_id;
	static String generate_init_id(int n) {
		char[] init=new char[n];
		init[0]=87+1;
		for (int j=1; j<n;j++) init[j]=77; 
		return new String(init);
	}
	static String generate_id(byte[][] stacks, int i, int j, int k) {
		int n;
		char[] id = new char[n=stacks[0].length];
		for (int a=0; a<n;a++) id[a]= 
				(char) (77 
						+ ( (a<=i)? 1*stacks[0][a] : 0 ) 
						+ ( (a<=j)? 3*stacks[1][a] : 0)
						+ ( (a<=k)? 9*stacks[2][a] : 0)
						);
		return new String(id);
	}
	static byte pmod (byte cp) {
		switch (cp) {
		case -1: return 0;
		default: return 1;
		}
	}
	static byte np (byte cp) {
		switch (cp) {
		case 0: return 1;
		default: return 0;
		}
	}


	/**
	 * generates a new game object with 3stacks of length n, initiated with a,b,b (~~ -1,1,1) 
	 * to be stored in queue
	 */
	public static class Gold_and_Silver_Game_II{
		String id;
		byte[][] stacks;
		byte value = 12;
		ArrayList<ArrayList<Integer>> representation_by_count = new ArrayList<ArrayList<Integer>>(3);
		int[] rbc_last=new int[3];
		public Gold_and_Silver_Game_II(int n) {
			stacks = new byte[3][n];
			for (int i=0;i<3;i++) {
				stacks[i][0]=(byte)(2*((i*i)%3)-1);
				representation_by_count.add(new ArrayList<Integer>());
			}
			this.id = init_id;
			//			System.out.println(this.id);
		}
		/**
		 * highest n is try again with double
		 * @param game
		 */
		public static void convert_to_intstacks(Gold_and_Silver_Game_II game) {
			game.representation_by_count.get(0).add(0);
			for (int i=0;i<3;i++) {
				game.representation_by_count.get(i).add(0);
				byte currentPlayer=game.stacks[i][0];
				int count=0;
				for (int j:game.stacks[i]) {
					if (j==0) {
						game.representation_by_count.get(i).addFirst(count);
						break;
					} else {
						if (j==currentPlayer ) {
							count++;
						} else {
							game.representation_by_count.get(i).addFirst(count);
							count++;
							currentPlayer *=-1;
						}
						if (count==game.stacks[0].length) {
							game.representation_by_count.get(i).addFirst(count);
						}
					}
				}
				if (game.representation_by_count.get(i).size()%2 != 1)
					game.representation_by_count.get(i).addFirst(game.representation_by_count.get(i).get(0));
				game.rbc_last[i]=game.representation_by_count.get(i).getFirst();
			}
		}
	}

	/**
	 * increments a permutation of numbers -1 and 1
	 * Notabene: for later only as scaffold for intstacks
	 * @param perm
	 */
	public static void increment (byte[] perm) {
		int index=perm.length-1, indexLastUnit=index;
		byte tmp;
		//looks for first number smaller than predecessor 
		for (;index>0 && perm[index]<=perm[index-1];index--);
		//looks for next smallest to switch
		//switches variation with next biggest
		if (index > 0) {
			for (indexLastUnit=index; indexLastUnit+1<perm.length && perm[index-1]<perm[indexLastUnit+1];indexLastUnit++);
			tmp=perm[index-1];
			perm[index-1]=perm[indexLastUnit];
			perm[indexLastUnit]=tmp;
		}
		//reverses tail
		for (int k=0; perm[index+k] > perm[perm.length-1-k] && index+k+k+1 < perm.length; k++){
			tmp=perm[index+k];
			perm[index+k]=perm[perm.length-1-k];
			perm[perm.length-1-k]=tmp;
		}
		//		for (byte a:perm) System.out.print(a+",");
		//		System.out.println();
	}

	/**
	 * checks whether all numbers are orderes monotonously decreasing
	 * @param perm
	 * @return
	 */
	public static boolean isReversed(byte[] perm) {
		for (int i=1;i<perm.length;i++)
			if (perm[i-1]<perm[i]) return false;
		return true;
	}
	/**
	 * checks whether all numbers are ordered monotonously increasing
	 * @param perm
	 * @return
	 */
	public static boolean isInOrder(byte[] perm) {
		for (int i=1;i<perm.length;i++)
			if (perm[i-1]>perm[i]) return false;
		return true;
	}



	/**
	 * possibly interesting, but not yet needed
	 * @param zeros
	 * @param game
	 */
	public static void addZeros(int zeros, Gold_and_Silver_Game_II game) {
	}

	/**
	 * this is currently the main method
	 * increments through all games and stores them in games ArrayList
	 * @param n
	 * @return
	 */
	public static long g_and_s_gII(int n) {
		ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II> al = new ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II>(0);
		ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II> alm = new ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II>(0);
		ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II> alp = new ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II>(0);
		ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II> alf = new ArrayList<ProjectEuler_895.Gold_and_Silver_Game_II>(0);
		//generates all possible games given blockheight n
		for (int zeros=3*n-4;zeros >= 0; zeros-=2) { //first iterates through possible zeroes (lowest row must be filled nonzero)
			for (int i = Math.min( zeros, n-1 ); i >=0 ;i--) { //only need values for first through rows (i,j), given sum of zeros (zeros)
				for (int j = Math.min(zeros-i, n-1); j>=0 && zeros-i-j <= n-1;j--) {
					int k=zeros-i-j;
					//skip games that permute
					if (k<j) {
						System.out.println( "skip if zeroes 3<2: " + i+j+k+"\n"  );
						continue;
					}//third row contains more zeroes
					if (i*j*k>0) {
						System.out.println( "skip all rows contain zero: " + i+j+k+"\n"  );
						continue;
					}//third row contains more zeroes
					System.out.println("zeroes "+zeros + ", in stacks: " + i+j+k);
					byte[] perm = new byte[3*n-3-zeros];//this later iterates through all games
					for (int l=perm.length/2;l>=0;l--) perm[l]=-1;
					for (int l=perm.length-1;l>-1&&perm[l]==0;l--) perm[l]=1;
					//iterates all possible 1/-1 games
					do {
						Gold_and_Silver_Game_II game = new Gold_and_Silver_Game_II(n); //creates new game
						int m=0,count=0;
						for (m=1;m<=n-1-i;m++) game.stacks[0][m] = perm[count++];
						for (m=1;m<=n-1-j;m++) game.stacks[1][m] = perm[count++];
						for (m=1;m<=n-1-k;m++) game.stacks[2][m] = perm[count++];

						for (byte a:game.stacks[0]) System.out.print(a+","); System.out.println();
						for (byte a:game.stacks[1]) System.out.print(a+","); System.out.println();
						for (byte a:game.stacks[2]) System.out.print(a+","); System.out.println();
						//						System.out.println();
						increment(perm);
						if (j==k) {
							for (m=1;m<n && game.stacks[1][m]==game.stacks[2][m] ;m++);
							if (m==n) {
								System.out.println("no test: take but half, symmetry");
								game.value/=2;
							} else if (game.stacks[1][m] != game.stacks[1][m-1] ) {
								System.out.println( "no test: skip if zeros 2=3 but stack 2<3 ");
								continue;
							} else {
								System.out.println("no test: take assymetry");
							}
						}//tests if second and third row have equal zeroes
						Gold_and_Silver_Game_II.convert_to_intstacks(game);
						//						for (ArrayList<Integer> a:game.representation_by_count) {
						//							System.out.print("intstacks: ");
						//							for (Integer b:a) {
						//								System.out.print(b+" ");
						//							} System.out.println();
						//						}//syso inststacks
						System.out.println("lasts:"   + game.rbc_last[0]   + game.rbc_last[1]   + game.rbc_last[2]);
						game.id=generate_id(game.stacks, n, n, n);
						System.out.println(game.id);
						al.add(game);
						class Node {
							//new instance: init cp and poses and eval=1-cp
							byte cp;//0 or 1
							int[] poses;
							int children;
							int childrenChecked;
							boolean expanded, evaldone;
							byte eval;
							boolean[] eva= new boolean[]{false,false};
							ArrayList<Node> parents = new ArrayList<Node>();
							Node(byte cp, int[] po){
								this.cp=cp;
								this.poses=po;
								this.eval=np(cp);
								eva[cp]=true;
							}
							byte evaluateNode(Gold_and_Silver_Game_II game) {
								Node[][][][] createdNode = new Node[2][game.rbc_last[0]+1][game.rbc_last[1]+1][game.rbc_last[2]+1]; 
								Queue<Node> gq = new LinkedList<Node>();
								createdNode[this.cp][this.poses[0]][this.poses[1]][this.poses[2]] = this;
								gq.add(this);
								while(!gq.isEmpty()) {
									Node node = gq.poll();
									if (!node.expanded) {
										System.out.println("expand"+gq.size()+" "+node.cp+node.poses[0]+node.poses[1]+node.poses[2] + " eval "+node.evaldone);
										node.expanded = true;
										byte ncp = np(node.cp);
										for (int a=0;a<3;a++) {
											ArrayList<Integer> b = game.representation_by_count.get(a);
											int c = node.poses[a];
											/*here children*/
											for (int d=node.cp; d<b.size()-1; d+=2) {
												int curr = b.get(d);
												int next = b.get(d+1);
												if (curr==0) break;
												if (next >= c || curr==10000) continue;
												int[]nps = new int[]{node.poses[0],node.poses[1],node.poses[2]};
												if (curr>c) nps[a]=c-1;
												else nps[a]=curr-1;
												node.children++;
												Node nnode;
												if (createdNode[ncp][nps[0]][nps[1]][nps[2]]==null) {
													nnode = new Node(ncp,nps);
													//													created[ncp][nps[0]][nps[1]][nps[2]]=true;
													createdNode[ncp][nps[0]][nps[1]][nps[2]]=nnode;
													nnode.cp=ncp;
													nnode.poses = nps;
													nnode.eval = node.cp;
													nnode.eva[this.cp]=true;
													gq.add(nnode);
													//													System.out.println("add child to gq "+gq.size()+" "+nnode.cp+nnode.poses[0]+nnode.poses[1]+nnode.poses[2]);
												}//new child created
												else {
													nnode=createdNode[ncp][nps[0]][nps[1]][nps[2]];
												}
											} //goes through all possible next moves in stack for childs;
											/*here parents*/
											for (int d=ncp; d<b.size()-1; d+=2) {
												int curr = b.get(d);
												int next = b.get(d+1);
												if (curr<=c) break;
												if (next>c || curr == next) continue;
												if (c==curr-1) {
													next = (b.get(0)==10000)?b.get(1):b.get(0);
												} else {
													next = c+2;
												}
												while (c++ < next) {
													int[]nps = new int[]{node.poses[0],node.poses[1],node.poses[2]};
													nps[a]=c;
													Node parent;
													if (createdNode[ncp][nps[0]][nps[1]][nps[2]]==null) {
														continue;
//														created[ncp][nps[0]][nps[1]][nps[2]]=true;
//														parent = new Node(ncp,nps);
//														createdNode[ncp][nps[0]][nps[1]][nps[2]]=parent;
//														parent.cp=ncp;
//														parent.poses = nps;
//														parent.eval=node.cp;
//														parent.eva[this.cp]=true;
//														gq.add(parent);
//														System.out.println("parent added in gq "+gq.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2]);
													} else {
														parent = createdNode[ncp][nps[0]][nps[1]][nps[2]];
														//														System.out.println("parent added in gq already expanded or created"+gq.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2] + " eval "+parent.evaldone);
													}
													node.parents.add(parent);
												}//through all possible parents in given int in int stack
											}//through intstack for parents
										}//through all intstacks for children and parents
										//										gq.add(node);
									}//nodeexpansion done (number of children and parentlist), node reinserted in gq
									if (node.children==node.childrenChecked || node.eval==node.cp) {
										//										System.out.println("no children or node won"+gq.size()+" "+node.cp+node.poses[0]+node.poses[1]+node.poses[2] + " eval "+node.evaldone);
										Queue<Node> s = new LinkedList<Node>();
										s.add(node);
										while (!s.isEmpty()) {
											Node parent=s.poll();
											System.out.println("pull next parent node "+parent.cp+ parent.poses[0] + parent.poses[1] + parent.poses[2] +" expanded "+parent.expanded + " evaldone "+parent.evaldone+ " number of children " + parent.children + " childrenchecked " + parent.childrenChecked);
											if (!parent.evaldone) {
												if (node.eval == parent.cp) {
													parent.eval = node.eval;
													if (parent.expanded) {
														if (parent.children>parent.childrenChecked) parent.childrenChecked++;
														parent.evaldone = true;
														for (Node p2:parent.parents) {
															s.add(p2);
														}
														parent.parents=null;
													} else {
														parent.childrenChecked++;
													}
													System.out.println("won parent"+s.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2] + " eval "+parent.evaldone +" size "+ s.size());
												}//won node, add parents, allchildren checked
												else {
													if (parent.expanded) {
														if (parent.children>parent.childrenChecked) parent.childrenChecked++;
														if (parent.children == parent.childrenChecked) {
															parent.evaldone = true;
															s.addAll(parent.parents);
															parent.parents=null;
															System.out.println("lost expanded parent, last child "+ s.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2] + " eval "+parent.evaldone +  " children " + parent.children + " childrenchecked " + parent.childrenChecked);
														} else {
															System.out.println("lost expanded parent, child checked "+ s.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2] + " eval "+parent.evaldone+  " children " + parent.children + " childrenchecked " + parent.childrenChecked);
														}
													} else {
														parent.childrenChecked++;
														System.out.println("lost parent to be expanded, child checked "+ s.size()+" "+parent.cp+parent.poses[0]+parent.poses[1]+parent.poses[2] + " eval "+parent.evaldone +  " children " + parent.children + " childrenchecked " + parent.childrenChecked);
													}
												}//lost games are only counted, if final child add parents
											}//parent is evalutd?
										}//go through all parents
									}//when no children, evaluate paretns, nullify node
								}//gq.empty 
								if (!this.evaldone) System.err.println("error");
								return this.eval;
							}
						}//class Node
						if (game.id.equals("XIP")){
							Node topm=new Node((byte)0,game.rbc_last);
							System.out.println("topm\n"+topm.cp+" "+topm.evaluateNode(game));
							Node topp=new Node((byte)1,game.rbc_last);
							System.out.println("topp\n"+topp.cp+" "+topp.evaluateNode(game));
							if (!topm.evaldone || !topp.evaldone) System.err.println("error");
							if (topm.eval == topp.eval) {
								if (topm.eval == 0) alm.add(game);
								else alp.add(game);
							} else alf.add(game);
							System.out.println("control "+al.size()+" "+alm.size()+" " + alp.size()+" "  + alf.size());
						}
					} while (!isInOrder(perm));
				}//j
			}//i
		}//zeros
		System.out.print("here main return " );
		return 0;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		//		byte[] test = {1,2,1,-1,2,2};
		//		increment(test);
		int n=3;
		init_id = generate_init_id(n);
		System.out.println( g_and_s_gII(n) );
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


/**
if (j==k) {
	System.out.println("case j==K");
	m=n-i-1;
	if (m==perm.length) {
		System.out.println("test: case all zeroes");
		System.out.println("test: take but half");
		//do nothing
	}else if (m+2==perm.length) {
		System.out.println("test: case only 1 row");
		if (perm[m]<perm[m+1]) {
			System.out.println("test: skip stack 2<3");
		}else if (perm[m]==perm[m+1]) {
			System.out.println( "test: take, 2=3, half ");
			game.value/=2;
		}else {
			System.out.println("test: 2>3, take");
		}
	}else {
		System.out.println(al.size()+ " test: two or more rows m: "+ m + " highest: " + (perm.length-m)/2);
		if (perm[m]<perm[m+n-j-1]) {
			System.out.println("test: skip stack 2<3 in first row " + perm[m] + " " + perm[m+n-j-1] );
		} else if (perm[m]>perm[m+n-j-1]) {
			System.out.println("test: take, 2>3 in first row " + perm[m] + " " + perm[m+n-j-1] );
		} else {
			for (++m;m+n-j-1<perm.length && !(perm[m]*perm[m-1]==-1 && perm[m+n-j-1]*perm[m+n-j-2]==1) ;m++) {
				System.out.println("count test m: "+ m + " stack2m " + perm[m]  + " stack2m- " + perm[m-1] + " stack3m " + perm[m+n-j-1] + " stack3m- " + perm[m+n-j-2]);
			}
			if (m+n-j-1==perm.length) {
				System.out.println("last row");
				if (perm[m-1]*perm[m-2]==-1 && perm[m+n-j-2]*perm[m+n-j-3]==1) {
					System.out.println( "test: skip if zeros 2=3 but stack 2<3 ");
					//										continue;
				} else {
					System.out.println( "test: take, asymmetry ");
				}
			} else {
				System.out.println( "test: take, symmetry ");
				//										game.value/=2;
			}

		}

	}
}
 */


/**
class LostGameNode implements Comparable<LostGameNode>{
	int[] colums; //points to first non-coin in stack 0..2, CAVEAT: max value is n! 
	byte currentPlayer; //player who loses , i.e. has no moves or has no winning move
	@Override
	public int compareTo(LostGameNode o) {
		return 
				//						Math.max(Math.max(((LostGameNode)o).colums[0],((LostGameNode)o).colums[1]),((LostGameNode)o).colums[2])
				//						-Math.max(Math.max(this.colums[0],this.colums[1]),this.colums[2]);
				((LostGameNode)o).colums[0]-this.colums[0] +
				((LostGameNode)o).colums[1]-this.colums[1] +
				((LostGameNode)o).colums[2]-this.colums[2];
	}
	@Override
	public boolean equals(Object o) {
		return 
				this.colums[0]==((LostGameNode)o).colums[0] &&
				this.colums[1]==((LostGameNode)o).colums[1] &&
				this.colums[2]==((LostGameNode)o).colums[2] &&
				this.currentPlayer==((LostGameNode)o).currentPlayer;
	}
}
PriorityQueue<LostGameNode> lpl = new PriorityQueue<LostGameNode>();
class WinGameNode extends LostGameNode {
	int lastParent; //points to last parent that has been evaluated;
	boolean finished=false; //stores the state of this node 
	@Override
	public int compareTo(LostGameNode o) {
		return 
				Math.max(this.lastParent,finished?3*n:0)
				-Math.max(((WinGameNode)o).lastParent,((WinGameNode)o).finished?3*n:0);
	}
	@Override
	public boolean equals(Object o) {
		int[][] s = new int[2][3];
		s[0][this.lastParent%3]=this.lastParent/3;
		s[1][((WinGameNode)o).lastParent%3]=((WinGameNode)o).lastParent/3;
		return 
				this.colums[0]+s[0][0]==((WinGameNode)o).colums[0]+s[1][0] &&
				this.colums[1]+s[0][1]==((WinGameNode)o).colums[1]+s[1][1] &&
				this.colums[2]+s[0][2]==((WinGameNode)o).colums[2]+s[1][2] &&
				this.currentPlayer==((WinGameNode)o).currentPlayer;
	}
}
PriorityQueue<WinGameNode> wpl = new PriorityQueue<WinGameNode>();
class EvalGameNode extends LostGameNode {
	ArrayList<EvalGameNode> children;//makes pointers to all children
	@Override
	public int compareTo(LostGameNode o) {
		return 
				this.children.size()
				-((EvalGameNode)o).children.size();
	}
}
PriorityQueue<EvalGameNode> epl = new PriorityQueue<EvalGameNode>();

class GameNode implements Comparable<GameNode>{
	GameNode (boolean l, boolean e, byte cp, int[] c){
		colums=c;
		currentPlayer=cp;
		leaf=l;
		expanded=e;
		this.pqC = new PriorityQueue<GameNode>(); 
	}
}
ArrayList<GameNode> allNodes = new ArrayList<GameNode>();
allNodes.add( new GameNode(false,false,(byte)1, new int[] {n,n,n} ) );

for (byte c=-1;c <= 1 ;c+=2) {//generates first games (all coins cleared, either player to move)
	LostGameNode lp = new LostGameNode();
	lp.colums= new int[]{0,0,0};
	lp.currentPlayer=c;
	lpl.add(lp);
}
for (int c=0;c < n && game.stacks[0][c]==-1;c++) {//generates all LPs für player 1
	LostGameNode lp = new LostGameNode();
	lp.colums= new int[]{c+1,0,0};
	lp.currentPlayer=1;
	lpl.add(lp);
}
for (int c=0;c < n && game.stacks[1][c]==1;c++) {//generates all LPs für player 1
	LostGameNode lp = new LostGameNode();
	lp.colums= new int[]{0,c+1,0};
	lp.currentPlayer=-1;
	lpl.add(lp);
}
for (int c=0;c < n && game.stacks[2][c]==1;c++) {//generates all LPs für player 1
	LostGameNode lp = new LostGameNode();
	lp.colums= new int[]{0,0,c+1};
	lp.currentPlayer=-1;
	lpl.add(lp);
}
for (int c1=0;c1 < n && game.stacks[1][c1]==1;c1++) {//generates all LPs für player -1
	for (int c2=0;c2 < n && game.stacks[2][c2]==1;c2++) {
		LostGameNode lp = new LostGameNode();
		lp.colums= new int[]{0,c1+1,c2+1};
		lp.currentPlayer=-1;
		lpl.add(lp);
	}
}
for (LostGameNode lp:lpl) {//generates all winning games for opponent
	for (int c=0;c<3;c++) {
		if (game.stacks[c][lp.colums[c]] == -lp.currentPlayer) {
			for (int d=lp.colums[c]; d<n && game.stacks[c][lp.colums[c]] != 0; d++) {
				WinGameNode wp = new WinGameNode();
				wp.colums = Arrays.copyOf(lp.colums, 3);
				wp.colums[c]=d+1;
				wp.currentPlayer=(byte)-lp.currentPlayer;
				wp.lastParent=0;
				wp.finished=false;
				if (!wpl.contains(wp))wpl.add(wp);
			}
		}
	}
}


// list of all nodes;
// list of leafs
//initiate gameGraph;

 */
