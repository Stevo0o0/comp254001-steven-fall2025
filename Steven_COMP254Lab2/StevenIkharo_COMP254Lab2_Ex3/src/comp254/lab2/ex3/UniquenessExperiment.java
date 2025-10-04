package comp254.lab2.ex3;

import java.util.*;
import java.util.function.Function;

public class UniquenessExperiment {
    // O(n^2)
    public static boolean unique1(int[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (A[i] == A[j]) return false;
            }
        }
        return true;
    }

    // O(n log n)
    public static boolean unique2(int[] A) {
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) if (A[i] == A[i - 1]) return false;
        return true;
    }

    private static int[] distinctArray(int n, Random rng) {
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = i;  // distinct by construction
        for (int i = n - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            int tmp = A[i]; A[i] = A[j]; A[j] = tmp;
        }
        return A;
    }

    private static long timeMillis(Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000;
    }

    private static int maxNUnderBudgetMs(Function<int[], Boolean> fn,
                                         long budgetMs,
                                         int startN,
                                         Random rng) {
        int low = 0, high = startN;
        // Doubling phase
        while (true) {
            int[] A = distinctArray(high, rng);
            long t = timeMillis(() -> fn.apply(A));
            if (t > budgetMs) break;
            low = high;
            high *= 2;
            if (high <= 0 || high > 1_000_000_000) break;
        }
        // Binary search
        int best = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int[] A = distinctArray(mid, rng);
            long t = timeMillis(() -> fn.apply(A));
            if (t <= budgetMs) { best = mid; low = mid + 1; }
            else { high = mid - 1; }
        }
        return best;
    }

    public static void main(String[] args) {
        long budgetMs = 60_000;
        Random rng = new Random(123);

        int best1 = maxNUnderBudgetMs(A -> unique1(A), budgetMs, 10_000, rng);
        int best2 = maxNUnderBudgetMs(A -> unique2(A), budgetMs, 100_000, rng);

        System.out.printf("Max n for unique1 ≤ 60s: %,d%n", best1);
        System.out.printf("Max n for unique2 ≤ 60s: %,d%n", best2);
    }
}
