/**
 * 
 */
import java.util.ArrayList;

/**
 * 
 */
public class ProjectEuler_0061_cyclical_figurate_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0061_cyclical_figurate_numbers() {
		// TODO Auto-generated constructor stub
	}

	public static class TokenTriple{
		TokenTuple[] tt;
		TokenTriple(TokenTuple[] tt){
			this.tt = tt;
		}
	}
	public static class TokenTuple{
		byte hi,lo,i;
		int n;
		TokenTuple(int n, byte i){
			this.n = n;
			this.hi=(byte)(n/100);
			this.lo=(byte)(n%100);
			this.i=i;
		}
	}


	public static long cfn(int n) {
		//generate Number list;
		ArrayList<ArrayList<TokenTuple>> gl = new ArrayList<ArrayList<TokenTuple>>();
		for (int i=0;i<6;i++) gl.add(new ArrayList<TokenTuple>());
		for (int i = 19 ; i<141 ; i++) {
			{
				int tri = ( i * (i+1) ) / 2;
				if (tri > 1110 && tri < 10000) {
					gl.get(0).add(new TokenTuple(tri, (byte)1));
					System.out.println("tri added " + tri);
				}
			}{
				int sqi = i*i;
				if (sqi > 1110 && sqi < 10000) {
					gl.get(1).add(new TokenTuple(sqi, (byte)2));
					System.out.println("sqi added " + sqi);
				}
			}{
				int pei = (i*(3*i-1)) / 2;
				if (pei > 1110 && pei < 10000) {
					gl.get(2).add(new TokenTuple(pei, (byte)4));
					System.out.println("pei added " + pei);
				}
			}{
				int hxi = (i*(2*i-1));
				if (hxi > 1110 && hxi < 10000) {
					gl.get(3).add(new TokenTuple(hxi, (byte)8));
					System.out.println("hxi added " + hxi);
				}
			}{
				int hpi = (i*(5*i-3)) / 2;
				if (hpi > 1110 && hpi < 10000) {
					System.out.println("hpi added " + hpi);
					gl.get(4).add(new TokenTuple(hpi, (byte)16));
				}
			}{
				int oci = (i*(3*i-2));
				if (oci > 1110 && oci < 10000) {
					gl.get(5).add(new TokenTuple(oci, (byte)32));
					System.out.println("oci added " + oci);
				}
			}
		}
		ArrayList<ArrayList<TokenTriple>> tl = new ArrayList<ArrayList<TokenTriple>>();
		for (int i=0; i<64;i++) tl.add(new ArrayList<TokenTriple>());
		for (int i=0; i<4;i++)
			for(int j=i+1; j<5; j++)
				for(int k=j+1; k<6;k++)
					for (TokenTuple tti:gl.get(i))
						for (TokenTuple ttj: gl.get(j))
							for (TokenTuple ttk:gl.get(k) ) {
								if (tti.hi == ttj.lo && tti.lo==ttk.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {ttk,tti,ttj} ));
									System.out.println("triple added " + ttk.n + " " + tti.n + " " + ttj.n + " " + ttk.i + " " + tti.i + " " + ttj.i );
								}
								if (tti.hi == ttk.lo && tti.lo==ttj.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {ttj,tti,ttk} ));
									System.out.println("triple added " + ttj.n + " " + tti.n + " " + ttk.n + " " + ttj.i + " " + tti.i + " " + ttk.i );
								}
								if (ttj.hi == tti.lo && ttj.lo==ttk.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {ttk,ttj,tti} ));
									System.out.println("triple added " + ttk.n + " " + ttj.n + " " + tti.n + " " + ttk.i + " " + ttj.i + " " + tti.i );
								}
								if (ttj.hi == ttk.lo && ttj.lo==tti.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {tti,ttj,ttk} ));
									System.out.println("triple added " + tti.n + " " + ttj.n + " " + ttk.n + " " + tti.i + " " + ttj.i + " " + ttk.i );
								}
								if (ttk.hi == tti.lo && ttk.lo==ttj.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {ttj,ttk,tti} ));
									System.out.println("triple added " + ttj.n + " " + ttk.n + " " + tti.n + " " + ttj.i + " " + ttk.i + " " + tti.i );
								}
								if (ttk.hi == ttj.lo && ttk.lo==tti.hi) {
									tl.get(tti.i+ttj.i+ttk.i).add( new TokenTriple( new TokenTuple[] {tti,ttk,ttj} ));
									System.out.println("triple added " + tti.n + " " + ttk.n + " " + ttj.n + " " + tti.i + " " + ttk.i + " " + ttj.i );
								}
							}
		for(int i=0, j=63; i<j; i++,j--)
			for (TokenTriple tti:tl.get(i))
				for (TokenTriple ttj:tl.get(j)) {
					if(tti.tt[0].lo == ttj.tt[2].hi && ttj.tt[0].lo == tti.tt[2].hi) {
						System.out.println("triple added " + tti.tt[0].n + " " + tti.tt[1].n + " " + tti.tt[2].n + " " + ttj.tt[0].n + " " + ttj.tt[1].n + " " + ttj.tt[2].n + " " 
								+ tti.tt[0].i + " " + tti.tt[1].i + " " + tti.tt[2].i + " " + ttj.tt[0].i + " " + ttj.tt[1].i + " " + ttj.tt[2].i );
						return tti.tt[0].n +tti.tt[1].n+tti.tt[2].n+ttj.tt[0].n+ttj.tt[1].n+ttj.tt[2].n; 
					}
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
		System.out.println( cfn(10001) );
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
