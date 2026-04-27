import java.util.*;

public class ProjectEuler_Bonus_ContFrac4 {

    public static void main(String[] args) {
        int p = 12;
        int limitA = 13;

        List<int[]> results = findOptimized(p, limitA);

        System.out.println("Gültige Tupel (Limit " + limitA + " kommt max. einmal vor):");
        System.out.println("Gesamtanzahl: " + results.size());
    }

    public static List<int[]> findOptimized(int p, int limit) {
        List<int[]> results = new ArrayList<>();
        int[] current = new int[p];

        // 1. Suche: Limit kommt GAR NICHT vor (Werte 0 bis limit-1)
        searchRecursive(0, p, limit, current, results, 1, 0, 0, 1, false);

        // 2. Suche: Limit kommt GENAU EINMAL vor
        // Wir setzen das Limit an jede mögliche Position i und füllen den Rest mit 0 bis limit-1
        for (int i = 0; i < p; i++) {
            int[] currentWithLimit = new int[p];
            currentWithLimit[i] = limit;
            
            // Wir müssen die Matrix bis zur Position i vorbereiten
            // Da das komplex ist, nutzen wir eine Hilfsmethode, die das Limit an Position i erzwingt
            searchWithFixedLimit(0, i, p, limit, currentWithLimit, results, 1, 0, 0, 1);
        }

        return results;
    }

    // Standard-Suche für Werte < limit
    private static void searchRecursive(int index, int p, int limit, int[] current, List<int[]> results, 
                                        long m11, long m12, long m21, long m22, boolean limitUsed) {
        if (index == p) {
            if (Math.abs(m11 + m22) < 2 && isPrimitive(current)) {
                results.add(current.clone());
            }
            return;
        }

        for (int a = 0; a < limit; a++) {
            current[index] = a;
            long n11 = m11 * a + m12;
            long n12 = -m11;
            long n21 = m21 * a + m22;
            long n22 = -m21;
            searchRecursive(index + 1, p, limit, current, results, n11, n12, n21, n22, limitUsed);
        }
    }

    // Spezial-Suche, die an einer festen Position 'posLimit' den Wert 'limit' setzt
    private static void searchWithFixedLimit(int index, int posLimit, int p, int limit, int[] current, 
                                             List<int[]> results, long m11, long m12, long m21, long m22) {
        if (index == p) {
            if (Math.abs(m11 + m22) < 2 && isPrimitive(current)) {
                results.add(current.clone());
            }
            return;
        }

        if (index == posLimit) {
            int a = limit;
            current[index] = a;
            searchWithFixedLimit(index + 1, posLimit, p, limit, current, results, 
                                 m11 * a + m12, -m11, m21 * a + m22, -m21);
        } else {
            for (int a = 0; a < limit; a++) {
                current[index] = a;
                searchWithFixedLimit(index + 1, posLimit, p, limit, current, results, 
                                     m11 * a + m12, -m11, m21 * a + m22, -m21);
            }
        }
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
