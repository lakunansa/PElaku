import java.util.*;


import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class ProjectEuler_Bonus_ContFrac6 {

	public static void main(String[] args) {
		int p = 11;
		long start = System.currentTimeMillis();
		long totalCount = findTupelParallel(p);
		long duration = System.currentTimeMillis() - start;

		System.out.println("Ergebnis p=" + p + ": " + totalCount);
		System.out.println("Dauer: " + duration + " ms");
	}

	public static long findTupelParallel(int p) {
		LongAdder total = new LongAdder();
		// Parallelisierung über den Startwert b0
		IntStream.rangeClosed(0, p + 1).parallel().forEach(b0 -> {
			int countP1 = (b0 == p + 1) ? 1 : 0;
			// Initiale Matrix für b0: [b0, -1; 1, 0]
			long m11 = b0, m12 = -1, m21 = 1, m22 = 0;
			total.add(generate(1, p, new int[p], b0, countP1, b0, m11, m12, m21, m22));
		});
		return total.sum() * p;
	}

	private static long generate(int index, int p, int[] current, int b0, int cP1, int maxA, 
			long m11, long m12, long m21, long m22) {
		current[0] = b0;
		if (index == p) {
			// Die Spur-Prüfung ist hier m11 + m22
			if (Math.abs(m11 + m22) < 2 && isLexMax(current) && isPrimitive(current)) {
				return 1;
			}
			return 0;
		}

		// PRUNING: Da b_i >= 0, wächst die Spur tendenziell. 
		// Ein sehr grobes Pruning (m11 + m22 > Schwellenwert) könnte hier Pfade kappen.
		// Für exakte elliptische Fixpunkte ist der Suchraum aber ohnehin klein.

		long count = 0;
		for (int v = 0; v <= maxA; v++) {
			int nP1 = cP1 + (v == p + 1 ? 1 : 0);
			if (nP1 > 1) continue;

			current[index] = v;
			// Matrix-Update: NewM = OldM * [v, -1; 1, 0]
			long n11 = m11 * v + m12;
			long n12 = -m11;
			long n21 = m21 * v + m22;
			long n22 = -m21;

			count += generate(index + 1, p, current, b0, nP1, maxA, n11, n12, n21, n22);
		}
		return count;
	}

	// isLexMax und isPrimitive bleiben wie gehabt...

	// Prüft, ob die Kette unter allen Rotationen lexikographisch die größte ist
	private static boolean isLexMax(int[] a) {
		int n = a.length;
		for (int shift = 1; shift < n; shift++) {
			for (int i = 0; i < n; i++) {
				int original = a[i];
				int rotated = a[(i + shift) % n];
				if (rotated > original) return false;
				if (rotated < original) break;
			}
		}
		return true;
	}

	private static boolean isElliptic(int[] a) {
		long m11 = 1, m12 = 0, m21 = 0, m22 = 1;
		for (int val : a) {
			long n11 = m11 * val + m12;
			long n12 = -m11;
			long n21 = m21 * val + m22;
			long n22 = -m21;
			m11 = n11; m12 = n12; m21 = n21; m22 = n22;
		}
		return Math.abs(m11 + m22) < 2;
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
