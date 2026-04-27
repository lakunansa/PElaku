import java.util.*;

public class ProjectEuler_Bonus_ContFrac5 {

    public static void main(String[] args) {
        int p = 9; 
        long totalCount = findTupelOptimized(p);
        System.out.println("Gesamtanzahl für p = " + p + ": " + totalCount);
    }

    public static long findTupelOptimized(int p) {
        long count = 0;
        // Der erste Wert b0 bestimmt das Maximum für alle folgenden Werte
        for (int b0 = 0; b0 <= p + 1; b0++) {
            // countP1 ist 1, wenn der erste Wert bereits p+1 ist
            count += generate(1, p, new int[p], b0, b0 == p + 1 ? 1 : 0, b0);
        }
        return count;
    }

    private static long generate(int index, int p, int[] current, int b0, int countPPlusOne, int maxAllowed) {
        current[0] = b0;
        if (index == p) {
            // Da wir nur primitive Ketten suchen, ist die Klassenstärke immer p.
            // Die Bedingung "kein Eintrag größer als b0" fängt genau einen Repräsentanten ein.
            // Aber: Wir müssen sicherstellen, dass die Kette zyklisch maximal ist (lexikographisch).
            if (isLexMax(current) && isPrimitive(current) && isElliptic(current)) {
                return p;
            }
            return 0;
        }

        long localCount = 0;
        for (int v = 0; v <= maxAllowed; v++) {
            int nextP1 = countPPlusOne + (v == p + 1 ? 1 : 0);
            if (nextP1 > 1) continue;

            current[index] = v;
            localCount += generate(index + 1, p, current, b0, nextP1, maxAllowed);
        }
        return localCount;
    }

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
