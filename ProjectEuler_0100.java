/**
 * 
 */

import java.util.stream.Stream;

/**
 * 
 */
public class ProjectEuler_0100 {

	/**
	 * 
	 */
	public ProjectEuler_0100() {
		// TODO Auto-generated constructor stub
	}


    record State(long i2, long i1, long j2, long j1) {}

    public static long _ap_ki(int n) {
        State initialState = new State(4L, 1L, 3L, 1L);

        // Stream erzeugt die Sequenz basierend auf deiner Rekursionslogik
        return Stream.iterate(initialState, s -> {
            long nextI = 6 * s.i2 - s.i1 - 2;
            long nextJ = 6 * s.j2 - s.j1 - 2;
            
            // Optional: Konsolenausgabe innerhalb des Streams (Side-Effect)
            System.out.println(s.i2 + " " + s.j2 + " -> " + nextI + " " + nextJ);
            
            return new State(nextI, s.i2, nextJ, s.j2);
        })
        // Abbruchbedingung wie im do-while
        .takeWhile(s -> s.i2 + s.j2 < 1E12)
        // Wir suchen den letzten Wert vor dem Abbruch
        .reduce((first, second) -> second)
        .map(State::j2)
        .orElse(4L);
    }
	
	
	public static long _ap(int n) {
		long i2=4, i1=1, i0,
				j2=3, j1=1, j0;
		do {
			System.out.print(i2+" "+ j2 + " -> ");
			i0=i1; i1=i2; i2= 6*i1 -i0 -2; 
			j0=j1; j1=j2; j2= 6*j1 -j0 -2;
			System.out.println( i2+ " "+j2);
		}while (i2+j2<1E12);
		return j2;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//computes nth prime


		long startTime = System.nanoTime();
		System.out.println( _ap_ki(0) );
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
