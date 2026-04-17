/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0093_arithmetic_expressions {

	/**
	 * 
	 */
	public ProjectEuler_0093_arithmetic_expressions() {
		// TODO Auto-generated constructor stub
	}

	public static int[] cycle(int[] p) {
//		System.out.print(""+p[0]+p[1]+p[2]+p[3]+ " ");
		boolean pequals =true;
		for (int i = 2; i >=0; i--) {
			if (p[i] < p[i+1]) {
				pequals=false;
				break;
			}
		} 
		if (pequals) {
			for(int cur=0, ruc = 3; ruc>cur; cur++,ruc--) {
				int tmp=p[ruc];
				p[ruc]= p[cur];
				p[cur]=tmp;
			}
//			System.out.println(""+p[0]+p[1]+p[2]+p[3]+ " ");
			return p;
		}
		int cur=2;
		while (p[cur]>p[cur+1]) cur--;
		int ruc =3;
		while (p[ruc] < p[cur])ruc--;
		int tmp = p[cur];
		p[cur] = p[ruc];
		p[ruc] =tmp;
		cur++;
		for(ruc = 3; ruc>cur; cur++,ruc--) {
			tmp=p[ruc];
			p[ruc]= p[cur];
			p[cur]=tmp;
		}
//		System.out.println(""+p[0]+p[1]+p[2]+p[3]+ " ");
		return p;
	}
	
	public enum Op{
		ADD,
		SUB,
		MUL,
		DIV
	}

	public static double comp(double x, double y, Op o) {
		switch (o) {
		case ADD: return x+y;
		case SUB: return x-y;
		case MUL: return x*y;
		case DIV: return x/y;
		default: return Double.NaN;
		}
	}
	
	public static String _ae(int n) {
		int res=0;
		String abcd=null;
		for (int a=1;a<6;a++)
			for (int b=a+1;b<7;b++)
				for (int c=b+1;c<8;c++)
					for (int d=c+1;d<9;d++) {
						int[]nums = {a,b,c,d};
						boolean[] chain = new boolean[126*64*24];
						int max=1;
						for (Op o1:Op.values())
							for (Op o2:Op.values())
								for (Op o3:Op.values()) {
									int tmp=0;
									double r= 0;
									for (int p=0;p<24;p++) {
										//((..).).
										r = comp( comp( comp( nums[0],nums[1],o1 ),nums[2],o2 ),nums[3],o3 );
										if (r%1==0) {
											tmp =(int)r;
											if (tmp>0)chain[tmp]=true;
											if (tmp==max) while (chain[max])max++;
										}
										//(.(..)).
										r= comp(comp( nums[0],comp( nums[1],nums[2],o2 ),o1 ),nums[3],o3);
										if (r%1==0) {
											tmp =(int)r;
											if (tmp>0)chain[tmp]=true;
											if (tmp==max) while (chain[max])max++;
										}
										//.((..).)
										r= comp( nums[0],comp( comp( nums[1],nums[2],o2 ),nums[3],o3 ),o1 );
										if (r%1==0) {
											tmp =(int)r;
											if (tmp>0)chain[tmp]=true;
											if (tmp==max) while (chain[max])max++;
										}
										//.(.(..))
										r= comp( nums[0],comp( nums[1],comp( nums[2],nums[3],o3 ),o2 ),o1 );
										if (r%1==0) {
											tmp =(int)r;
											if (tmp>0)chain[tmp]=true;
											if (tmp==max) while (chain[max])max++;
										}
										//(..)(..)
										r= comp( comp( nums[0],nums[1],o1 ),comp( nums[2],nums[3],o3 ),o2 );
										if (r%1==0) {
											tmp =(int)r;
											if (tmp>0)chain[tmp]=true;
											if (tmp==max) while (chain[max])max++;
										}
										cycle(nums);
									}
								}
						if (max>res) {
							res=max;
							abcd=""+a+b+c+d;
							System.out.println(res+"resneu");
						}

					}
					
		return abcd;
	}








	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _ae(10001) );
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
