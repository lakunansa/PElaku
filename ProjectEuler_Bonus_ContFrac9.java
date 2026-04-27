import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class ProjectEuler_Bonus_ContFrac9 {

    public static void main(String[] args) {
        int p = 12; // Zielwert
        long start = System.currentTimeMillis();
        
        long totalCount = findTupelParallel(p);
        
        long duration = System.currentTimeMillis() - start;
        System.out.println("Gesamtanzahl für p=" + p + ": " + totalCount);
        System.out.println("Dauer: " + duration + " ms (" + (duration / 1000.0) + " s)");
    }

    public static long findTupelParallel(int p) {
        LongAdder total = new LongAdder();
        // Parallelisierung über den Startwert b0
        IntStream.rangeClosed(0, p + 1).parallel().forEach(b0 -> {
            int[] current = new int[p];
            current[0] = b0;
            int initialP1 = (b0 == p + 1 ? 1 : 0);
            // Matrix initial: [b0, -1; 1, 0]
            total.add(generate(1, p, current, b0, initialP1, b0, b0, -1, 1, 0));
        });
        return total.sum();
    }

    private static long generate(int index, int p, int[] current, int b0, int cP1, int maxA, 
                                 long m11, long m12, long m21, long m22) {
        if (index == p) {
            // Spur-Check: m11 + m22
            if (m11 + m22 >= -1 && m11 + m22 <= 1 && isPrimitive(current)) {
                return getSymmetryWeight(current, p);
            }
            return 0;
        }

        long count = 0;
        long limit = 100000; // Dein Gradienten-Limit für p=12

        // 1. Bereich: v = 0 und 1 (Oszillations-Bereich)
        for (int v = 0; v <= Math.min(1, maxA); v++) {
            int nP1 = cP1 + (v == p + 1 ? 1 : 0);
            if (nP1 > 1) continue;
            current[index] = v;
            count += generate(index + 1, p, current, b0, nP1, maxA, 
                              m11 * v + m12, -m11, m21 * v + m22, -m21);
        }

        // 2. Bereich: v >= 2 (Monotonie-Bereich)
        for (int v = 2; v <= maxA; v++) {
            int nP1 = cP1 + (v == p + 1 ? 1 : 0);
            if (nP1 > 1) continue;

            long n11 = m11 * v + m12;
            long n22 = -m21; 

            // Gradienten-Abbruch
            if (n11 + n22 > limit) break; 

            current[index] = v;
            count += generate(index + 1, p, current, b0, nP1, maxA, 
                              n11, -m11, m21 * v + m22, n22);
        }
        return count;
    }

    private static int getSymmetryWeight(int[] a, int p) {
        int n = a.length;

        // 1. LexMax (Rotationen)
        for (int shift = 1; shift < n; shift++) {
            for (int i = 0; i < n; i++) {
                int rot = a[(i + shift) % n];
                if (rot > a[i]) return 0;
                if (rot < a[i]) break;
            }
        }

        // 2. Spiegelungs-Check (Kanonische Form Vergleich)
        boolean isPalindromic = false;
        for (int shift = 0; shift < n; shift++) {
            for (int i = 0; i < n; i++) {
                int mir = a[(n - 1 - i + shift) % n];
                if (mir > a[i]) return 0; // Es gibt eine gespiegelte Form, die lexikographisch größer ist
                if (mir < a[i]) break;
                if (i == n - 1) isPalindromic = true; 
            }
        }

        return isPalindromic ? p : 2 * p;
    }

    private static boolean isPrimitive(int[] a) {
        int n = a.length;
        // Nur Teiler prüfen
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = 0; j < n; j++) {
                    if (a[j] != a[j % i]) {
                        match = false;
                        break;
                    }
                }
                if (match) return false;
            }
        }
        return true;
    }
}
/**
 *1:2
2:4
3:18
4:80
5:350	
6:1524	
7:6811
8:30400
9:137142
10:621170
11:2828969
12:	8044332

12930000
12928920 
*/