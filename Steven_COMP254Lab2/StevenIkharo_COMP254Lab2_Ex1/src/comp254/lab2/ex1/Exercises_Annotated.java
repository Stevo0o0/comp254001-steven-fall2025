package comp254.lab2.ex1;

/**
 * Annotated Lesson 4 examples with Big-Oh comments.
 */
public class Exercises_Annotated {
    // Example1 — O(1): constant time (fixed prints).
    public static void example1(int n) {
        System.out.println("Hey - your input is: " + n);
        System.out.println("Hmm.. I'm doing more stuff with: " + n);
        System.out.println("And more: " + n);
    }

    // Example2 — O(log n): i doubles each iteration.
    public static void example2(int n) {
        for (int i = 1; i < n; i = i * 2) {
            System.out.println("Looking at: " + i);
        }
    }

    // Example3 — O(n log n): outer runs n times, inner ~log n.
    public static void example3(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < n; j = j * 2) {
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }

    // Example4 — O(n^2): nested loop sums prefixes.
    public static double[] example4(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        for (int j = 0; j < n; j++) {
            double total = 0;
            for (int i = 0; i <= j; i++) total += x[i];
            a[j] = total / (j + 1);
        }
        return a;
    }

    // Example5 — O(n): single loop with running sum.
    public static double[] example5(double[] x) {
        int n = x.length;
        double[] a = new double[n];
        double total = 0;
        for (int j = 0; j < n; j++) {
            total += x[j];
            a[j] = total / (j + 1);
        }
        return a;
    }
}
