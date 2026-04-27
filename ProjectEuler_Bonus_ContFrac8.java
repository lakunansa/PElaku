import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import java.util.*;

public class ProjectEuler_Bonus_ContFrac8 {

	public static void main(String[] args) {
		int p = 11; // Teste mit 7 (6811) oder 4 (80)
		long start = System.currentTimeMillis();
		long totalCount = findTupelParallel(p);
		long duration = System.currentTimeMillis() - start;
		System.out.println("Gesamtanzahl für p=" + p + ": " + totalCount);
		System.out.println("Dauer: " + duration + " ms");
	}

	public static long findTupelParallel(int p) {
		LongAdder total = new LongAdder();
		IntStream.rangeClosed(0, p + 1).parallel().forEach(b0 -> {
			int[] current = new int[p];
			current[0] = b0;
			total.add(generate(1, p, current, b0, b0 == p + 1 ? 1 : 0, b0, b0, -1, 1, 0));
		});
		return total.sum();
	}

	private static long generate(int index, int p, int[] current, int b0, int cP1, int maxA, 
			long m11, long m12, long m21, long m22) {
		if (index == p) {
			if (Math.abs(m11 + m22) < 2 && isPrimitive(current)) {
				return getSymmetryWeight(current, p);
			}
			return 0;
		}

		long count = 0;

		// Wir teilen die Schleife auf, um die Monotonie für v >= 2 zu nutzen
		// 1. Bereich: 0 und 1 (hier kann die Spur noch oszillieren)
		for (int v = 0; v <= Math.min(1, maxA); v++) {
			int nP1 = cP1 + (v == p + 1 ? 1 : 0); // Relevant falls p=0 oder p+1=1
			if (nP1 > 1) continue;

			current[index] = v;
			long n11 = m11 * v + m12;
			long n12 = -m11;
			long n21 = m21 * v + m22;
			long n22 = -m21;
			count += generate(index + 1, p, current, b0, nP1, maxA, n11, n12, n21, n22);
		}

		// 2. Bereich: v >= 2 (Monotonie-Bereich)
		for (int v = 2; v <= maxA; v++) {
			int nP1 = cP1 + (v == p + 1 ? 1 : 0);
			if (nP1 > 1) continue;

			long n11 = m11 * v + m12;
			long n12 = -m11;
			long n21 = m21 * v + m22;
			long n22 = -m21;

			// Gradienten-Abbruch: 
			// Wenn die Spur bei v >= 2 bereits deutlich über 2 liegt,
			// wird sie für alle v' > v nur noch weiter steigen.
			// Ein Schwellenwert von 100 ist bei p=12 mathematisch absolut sicher.
			if (n11 + n22 > 10000) {
				break; // Beendet die v-Schleife für diesen Zweig komplett
			}

			current[index] = v;
			count += generate(index + 1, p, current, b0, nP1, maxA, n11, n12, n21, n22);
		}

		return count;
	}


	/**   
    private static long generate(int index, int p, int[] current, int b0, int cP1, int maxA, 
                                 long m11, long m12, long m21, long m22) {
        if (index == p) {
            if (Math.abs(m11 + m22) < 2 && isPrimitive(current)) {
                return getSymmetryWeight(current, p);
            }
            return 0;
        }

        long count = 0;
        for (int v = 0; v <= maxA; v++) {
            int nP1 = cP1 + (v == p + 1 ? 1 : 0);
            if (nP1 > 1) continue;

            long n11 = m11 * v + m12;
            long n12 = -m11;
            long n21 = m21 * v + m22;
            long n22 = -m21;

            // DEFENSIVES PRUNING: 
            // Nur abbrechen, wenn der Wert absolut astronomisch ist.
            // Bei p=12 und n11 > 10^9 kann keine Folge von 0/1 die Spur je wieder unter 2 bringen.
            if (n11 > 1000000000L && v > 1) continue; 

            current[index] = v;
            count += generate(index + 1, p, current, b0, nP1, maxA, n11, n12, n21, n22);
        }
        return count;
    }
	 */

	private static int getSymmetryWeight(int[] a, int p) {
		int n = a.length;

		// 1. Rotation: Ist 'a' bereits die LexMax-Form?
		for (int shift = 1; shift < n; shift++) {
			for (int i = 0; i < n; i++) {
				int rot = a[(i + shift) % n];
				if (rot > a[i]) return 0;
				if (rot < a[i]) break;
			}
		}

		// 2. Spiegelung: Ist 'a' lexikographisch >= als JEDE Rotation der Spiegelung?
		// Wir müssen nicht die volle LexMax-Form der Spiegelung bauen,
		// wir prüfen einfach alle n Spiegelungs-Achsen.
		boolean isPalindromic = false;
		for (int shift = 0; shift < n; shift++) {
			for (int i = 0; i < n; i++) {
				// Spiegelung an Position 'shift' mit Rückwärtsindex
				int mir = a[(n - 1 - i + shift) % n];
				if (mir > a[i]) return 0; // Es gibt eine gespiegelte Rotation, die größer ist
				if (mir < a[i]) break;
				if (i == n - 1) isPalindromic = true; // Kette ist identisch zu einer gespiegelten Rotation
			}
		}

		return isPalindromic ? p : 2 * p;
	}


	private static int[] getLexMaxRotation(int[] a) {
		int n = a.length;
		int[] max = a.clone();
		for (int shift = 1; shift < n; shift++) {
			int[] currentRot = new int[n];
			for (int i = 0; i < n; i++) currentRot[i] = a[(i + shift) % n];
			if (compare(currentRot, max) > 0) max = currentRot;
		}
		return max;
	}

	private static int compare(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > b[i]) return 1;
			if (a[i] < b[i]) return -1;
		}
		return 0;
	}

	private static boolean isPrimitive(int[] a) {
		int n = a.length;
		for (int i = 1; i <= n / 2; i++) {
			if (n % i == 0) {
				boolean match = true;
				for (int j = 0; j < n; j++) {
					if (a[j] != a[j % i]) { match = false; break; }
				}
				if (match) return false;
			}
		}
		return true;
	}
}
