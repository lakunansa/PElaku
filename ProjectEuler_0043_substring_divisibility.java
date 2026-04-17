/**
 * 
 */

/**
 * 
 */
public class ProjectEuler_0043_substring_divisibility {

	/**
	 * 
	 */
	public ProjectEuler_0043_substring_divisibility() {
		// TODO Auto-generated constructor stub
	}


	public static long sd(int n) {
		byte three, four, five, seven, eight, nine, ten;
		boolean[] isUsed = new boolean[10];
		isUsed[5]=true;
		long count = 0;
		//case 11: seven and eight (six)
		for (seven=0; seven<10; seven++) {
			if (seven==5) continue;
			isUsed[seven]=true;
			{
				eight =  (byte) ( (seven + 6)%11 );
				if (eight < 10) {
					isUsed[eight]=true;
					{
						//case 13: nine (seven eight)
						nine = (byte) ( (3*eight + 4*seven)%13 );
						if (nine < 10 && !isUsed[nine]) {
							isUsed[nine]=true;
							{
								//case 17: ten (eight nine)
								ten = (byte) ( (7*nine + 2*eight)%17 );
								if (ten < 10 && !isUsed[ten]) {
									isUsed[ten]=true;
									{
										//case 7: five (six seven)
										five = (byte) ( (3*seven + 3)%7 );
										for (;five<10;five+=7) 
											if (!isUsed[five]) {
												isUsed[five]=true;
												{
													//case 2: four (two and three) 
													for (four = 0;four<10;four+=2) 
														if (!isUsed[four]) {
															isUsed[four]=true;
															{
																//case 3: three (four and five) 
																three = (byte) ( (2*four + 2*five)%3 );
																for (;three<10;three+=3) 
																	if (!isUsed[three]) {
																		isUsed[three]=true;
																		{
																			//one: 
																			for (byte one=0;one<10;one++) 
																				if (!isUsed[one]) {
																					isUsed[one]=true;
																					//two
																					for (byte two=0; two<10;two++)
																						if (!isUsed[two]) {
																								count = count+
																										1000000000L*one +
																										100000000L*two +
																										10000000L*three +
																										1000000L*four +
																										100000L*five +
																										50000L +
																										1000L*seven +
																										100L*eight +
																										10L*nine +
																										ten
																										;
//																								System.out.println(""+one+two+three+four+five+"5"+seven+eight+nine+ten +" "+count);
																						}
																					isUsed[one]=false;
																				}
																		}
																		isUsed[three]=false;
																	}
															}
															isUsed[four]=false;
														}
												}
												isUsed[five]=false;
											}
									}
									isUsed[ten]=false;
								}
							}
							isUsed[nine]=false;
						}
					}
					isUsed[eight]=false;
				}
			}
			isUsed[seven]=false;
		}
		return count;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes the sum of all pandigital numbers with substring division property (of 0-9pandigital numbers)


		long startTime = System.nanoTime();
		System.out.println( sd(10001) );
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
