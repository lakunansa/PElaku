/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0021_amicable_numbers {

	/**
	 * 
	 */
	public ProjectEuler_0021_amicable_numbers() {
		// TODO Auto-generated constructor stub
	}

	public static int sopd (int m) {
		int s=1, p=2,tmp=m;
		while (p*p<=m && m>1) {
			if (m%p==0) {
				int j=p*p;
				m/=p;
				while(m%p==0) {
					j*=p;
					m/=p;
				}
				s*=(j-1);
				s/=(p-1);
			}
			if (p==2) p=3; else p+=2;
		}
		if(m>1) s*=(m+1);
		return s-tmp;
	}

	public static long anf(int n) {
		long s=0;
		int curr, pair, count;
		for (int i=2;i<n;i++) {//sollte 2 sein
			curr=i;
			pair=sopd(curr);
			if (pair>curr)
				if (sopd(pair)==curr)
					s+=curr+pair;
		}
		return s;

	}

	public static long an(int n) {
		boolean[] ans= new boolean[n];
		boolean[] che= new boolean[n];
		long s=0;
		int cc, lc, scc;
		ans[0]=(ans[1]=false);
		che[0]=(che[1]=true);
		for (int i=2;i<ans.length;i++) {//sollte 2 sein
			if(che[i]) continue;
			cc=i;
			che[cc]=true;
			do {
				scc=0;
				for(int j=1;j<cc;j++) if (cc%j==0)scc+=j;
				if (scc>n || che[scc]) {
					che[cc] = true;
					break;
				}
				lc=cc;
				cc=scc;
				che[cc]=true;
				scc=0;
				for(int j=1;j<cc;j++) 
					if (cc%j==0)scc+=j;
				if (scc==lc) {
					ans[cc]= true;
					ans[lc] = true;
				}
			} while (true);
		}
		for (int j = 0; j < che.length; j++) 
			if (ans[j]) {
				System.out.println(j);
				s+=j;
			}
		return s;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all the amicable numbers under 10000 (d(a)=b, d(b)=a, d being sum of proper divisors)


		long startTime = System.nanoTime();
		System.out.println( an(10000) );
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(duration/1000000);
		System.out.println();

		startTime = System.nanoTime();
		System.out.println( anf(10000) );
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println(duration/1000000);


	}

}
