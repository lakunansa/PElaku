import java.util.*;

public class ProjectEuler_Bonus_ContFrac3 {

    public static void main(String[] args) {
        int p = 4; // Hier p für N0(p) anpassen
        int limitA = 15; 

        Set<String> uniqueClasses = findUniqueN0Classes(p, limitA);

        System.out.println("Eindeutige N0-Klassen (isolierte Nullen) für p = " + p + ":");
        List<String> sortedResults = new ArrayList<>(uniqueClasses);
        Collections.sort(sortedResults);
        for (String s : sortedResults) {
            System.out.println(s);
        }
        System.out.println("\nGesamtanzahl N0(" + p + ") Klassen: " + uniqueClasses.size());
    }

    public static Set<String> findUniqueN0Classes(int p, int limit) {
        Set<String> uniqueResults = new HashSet<>();
        int[] current = new int[p];
        
        // Wir starten die Suche und erzwingen mindestens eine Null an Position 0
        current[0] = 0;
        backtrack(1, p, limit, current, uniqueResults);
        
        return uniqueResults;
    }

    private static void backtrack(int index, int p, int limit, int[] current, Set<String> results) {
        if (index == p) {
            // Zyklische Prüfung der isolierten Null: a[p-1] darf nicht 0 sein, da a[0]=0
            if (current[p - 1] != 0) {
                if (isElliptic(current) && isPrimitive(current)) {
                    results.add(getCanonical(current));
                }
            }
            return;
        }

        for (int a = 0; a <= limit; a++) {
            // Bedingung: Keine benachbarten Nullen
            if (a == 0 && current[index - 1] == 0) continue;

            current[index] = a;
            backtrack(index + 1, p, limit, current, results);
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

    private static String getCanonical(int[] a) {
        int[] best = a.clone();
        int[] rotated = new int[a.length];
        
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                rotated[j] = a[(i + j) % a.length];
            }
            if (compare(rotated, best) < 0) {
                best = rotated.clone();
            }
        }
        return Arrays.toString(best);
    }

    private static int compare(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return a[i] - b[i];
        }
        return 0;
    }
}
