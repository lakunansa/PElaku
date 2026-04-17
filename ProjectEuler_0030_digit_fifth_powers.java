/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0030_digit_fifth_powers {

	/**
	 * 
	 */
	public ProjectEuler_0030_digit_fifth_powers() {
		// TODO Auto-generated constructor stub
	}


	public static long dfp(int n) {
		//find maximal length
		int i,j, cnst=(int)9*9*9*9*9;
		for (i=1, j=10; i*cnst >=j; i++, j*=10 ) {

		}

		int 	sum=0, 
				sum0=0,
				sum0b=0;
		for (int i1=0;i1<=9;i1++) { 
			int sum1=sum0+												i1*i1*i1*i1*i1;
			int sum1b=									(sum0b+i1)*10;
			if (sum1+5*9*9*9*9*9>=sum1b*10000 && sum1<=sum1b*10000 + 99999)
				for (int i2=0;i2<=9;i2++) {
					int sum2 = sum1	+										i2*i2*i2*i2*i2;
					int sum2b=								(sum1b+i2)*10;
					if (sum2+4*9*9*9*9*9>=sum2b*1000 && sum2<=sum2b*1000 + 9999)
						for (int i3=0;i3<=9;i3++) {
							int sum3=  sum2+									i3*i3*i3*i3*i3;
							int sum3b=							(sum2b+i3)*10;
							if (sum3+3*9*9*9*9*9>=sum3b*100 && sum3<=sum3b*100 + 999)
								for (int i4=0;i4<=9;i4++) {
									int sum4=sum3+									i4*i4*i4*i4*i4;
									int sum4b=						(sum3b+i4)*10;
									if (sum4+2*9*9*9*9*9>=sum4b*10 && sum4<=sum4b*10 + 99)
										for (int i5=0;i5<=9;i5++) {
											int sum5=sum4+								i5*i5*i5*i5*i5;
											int sum5b=					(sum4b+i5)*10;
											if (sum5+1*9*9*9*9*9>=sum5b && sum5<=sum5b+ 9)
												for (int i6=0;i6<=9;i6++) {
													int sum6=sum5+							i6*i6*i6*i6*i6;
													int sum6b=				(sum5b+i6);
//													System.out.print(""+i1+i2+i3+i4+i5+i6);
													if (sum6 == sum6b) {
														sum+=sum6b;
														System.out.print(""+i1+i2+i3+i4+i5+i6);
														System.out.println("sum ");
													}
//													System.out.println();
												}
										}
								}						
						}
				}
		}
		return sum;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all the numbers that can be written as the sum of fifth powers of their digits


		long startTime = System.nanoTime();
		System.out.println( dfp(5) );
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
