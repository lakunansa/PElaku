import java.util.*;

public class ProjectEuler_Bonus_ContFrac2 {

    public static void main(String[] args) {
        int p = 4;        // Gewünschte Periodenlänge
        int limitA = 5;   // Suchlimit für die einzelnen Koeffizienten a_k

        List<int[]> results = findPrimitiveEllipticTuples(p, limitA);

        System.out.println("Gültige Tupel mit exakter Periode " + p + ":");
        for (int[] t : results) {
            System.out.println(Arrays.toString(t));
        }
        System.out.println("\nGesamtanzahl: " + results.size());
    }

    public static List<int[]> findClassB(int pTotal, int limitA) {
        List<int[]> results = new ArrayList<>();
        int[] current = new int[pTotal];
        current[pTotal - 1] = 0; // a_p+2 ist immer 0
        
        // Starte Backtracking, aber erzwinge a_p+1 > 0
        searchClassB(0, pTotal, limitA, current, results);
        return results;
    }

    private static void searchClassB(int index, int pTotal, int limit, int[] current, List<int[]> results) {
        if (index == pTotal - 1) { // Wir sind beim vorletzten Element (a_p+1)
            for (int a = 1; a <= limit; a++) { // a_p+1 > 0
                current[index] = a;
                if (isElliptic(current) && isPrimitive(current)) {
                    results.add(current.clone());
                }
            }
            return;
        }

        for (int a = 0; a <= limit; a++) {
            current[index] = a;
            searchClassB(index + 1, pTotal, limit, current, results);
        }
    }

    
    
    private static List<int[]> findPrimitiveEllipticTuples(int p, int limit) {
        List<int[]> results = new ArrayList<>();
        int[] current = new int[p];
        generate(0, p, limit, current, results);
        return results;
    }

    private static void generate(int index, int p, int limit, int[] current, List<int[]> results) {
        if (index == p) {
            // Nur primitive Tupel und elliptische Spuren zulassen
            if (isPrimitive(current) && isElliptic(current)) {
                results.add(current.clone());
            }
            return;
        }

        for (int a = 0; a <= limit; a++) {
            current[index] = a;
            generate(index + 1, p, limit, current, results);
        }
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
        long trace = m11 + m22;
        return Math.abs(trace) < 2;
    }

    private static boolean isPrimitive(int[] a) {
        int n = a.length;
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
