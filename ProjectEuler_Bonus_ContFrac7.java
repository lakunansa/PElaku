import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class ProjectEuler_Bonus_ContFrac7 {

    public static void main(String[] args) {
        int p = 12;
        long start = System.currentTimeMillis();
        
        long totalCount = findTupelSuperParallel(p);
        
        long duration = System.currentTimeMillis() - start;
        System.out.println("Gesamtanzahl für p=" + p + ": " + totalCount);
        System.out.println("Dauer: " + duration / 1000 + " Sekunden");
    }

    private static int getSymmetryWeight(int[] a, int p) {
        int n = a.length;

        // 1. Standard LexMax (Rotationen)
        for (int shift = 1; shift < n; shift++) {
            for (int i = 0; i < n; i++) {
                if (a[(i + shift) % n] > a[i]) return 0;
                if (a[(i + shift) % n] < a[i]) break;
            }
        }

        // 2. Spiegelung prüfen
        int[] mirror = new int[n];
        for (int i = 0; i < n; i++) mirror[i] = a[n - 1 - i];

        // Wir müssen die LexMax-Form der Spiegelung finden
        int[] mirrorLexMax = getLexMaxRotation(mirror);

        int cmp = compareArrays(a, mirrorLexMax);
        if (cmp < 0) return 0;       // Die Spiegelung hat eine "größere" LexMax-Form -> ignorieren
        if (cmp > 0) return 2 * p;  // Diese Form ist größer als die der Spiegelung -> beide zählen
        return p;                   // Kette ist (zyklisch) ein Palindrom
    }

    private static int[] getLexMaxRotation(int[] a) {
        int n = a.length;
        int[] max = a.clone();
        for (int shift = 1; shift < n; shift++) {
            for (int i = 0; i < n; i++) {
                int rot = a[(i + shift) % n];
                if (rot > max[i]) {
                    // Neue max rotation gefunden
                    for (int j = 0; j < n; j++) max[j] = a[(j + shift) % n];
                    break;
                }
                if (rot < max[i]) break;
            }
        }
        return max;
    }

    private static int compareArrays(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) return 1;
            if (a[i] < b[i]) return -1;
        }
        return 0;
    }

    
    
    public static long findTupelSuperParallel(int p) {
        LongAdder total = new LongAdder();

        // Parallelisierung über den Startwert b0
        IntStream.rangeClosed(0, p + 1).parallel().forEach(b0 -> {
            long subTotal = 0;
            int[] current = new int[p];
            current[0] = b0;
            int initialP1 = (b0 == p + 1 ? 1 : 0);
            
            // Matrix initialisieren: [b0, -1; 1, 0]
            subTotal += generate(1, p, current, b0, initialP1, b0, b0, -1, 1, 0);
            total.add(subTotal);
        });

        return total.sum();
    }

    private static long generate(int index, int p, int[] current, int b0, int cP1, int maxA, 
                                 long m11, long m12, long m21, long m22) {
        if (index == p) {
            if (Math.abs(m11 + m22) < 2 && isPrimitive(current)) {
                // Symmetrie-Check: LexMax + Spiegelung
                int symmetry = getSymmetryWeight(current, p);
                return symmetry; 
            }
            return 0;
        }

        long count = 0;
        for (int v = 0; v <= maxA; v++) {
            int nP1 = cP1 + (v == p + 1 ? 1 : 0);
            if (nP1 > 1) continue;

            current[index] = v;
            // Matrix-Update
            long n11 = m11 * v + m12;
            long n12 = -m11;
            long n21 = m21 * v + m22;
            long n22 = -m21;

            // Pruning: Wenn m11 zu groß wird (da alle v >= 0), wird Spur kaum < 2
            // m11 wächst sehr schnell. Bei p=12 ist 10^7 ein sicherer Cutoff.
            if (n11 > 10000000 && v > 0) continue; 

            count += generate(index + 1, p, current, b0, nP1, maxA, n11, n12, n21, n22);
        }
        return count;
    }

    /**
    private static int getSymmetryWeight(int[] a, int p) {
        // 1. LexMax check (Rotationen)
        int n = a.length;
        for (int shift = 1; shift < n; shift++) {
            for (int i = 0; i < n; i++) {
                int reg = a[i];
                int rot = a[(i + shift) % n];
                if (rot > reg) return 0;
                if (rot < reg) break;
            }
        }

        // 2. Spiegelungs-Check
        boolean isMirrorSame = true;
        for (int i = 0; i < n; i++) {
            int original = a[i];
            int mirror = a[(n - i) % n]; // Zyklische Spiegelung
            if (mirror > original) return 0; // Spiegelung ist lexikographisch größer
            if (mirror < original) {
                isMirrorSame = false;
                break;
            }
        }

        // Wenn wir hier sind, ist die Kette LexMax gegenüber Rotationen UND Spiegelung
        // Wenn identisch mit Spiegelung: p Resultate. Wenn nicht: 2p Resultate.
        return isMirrorSame ? p : 2 * p;
    }
    */

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
