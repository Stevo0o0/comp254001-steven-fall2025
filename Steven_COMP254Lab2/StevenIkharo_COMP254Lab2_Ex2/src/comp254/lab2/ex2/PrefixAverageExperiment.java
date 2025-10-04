package comp254.lab2.ex2;

import java.util.*;
import java.io.*;

public class PrefixAverageExperiment {
    // O(n^2)
    public static double[] prefixAverage1(double[] X) {
        int n = X.length;
        double[] A = new double[n];
        for (int i = 0; i < n; i++) {
            double total = 0.0;
            for (int j = 0; j <= i; j++) total += X[j];
            A[i] = total / (i + 1);
        }
        return A;
    }

    // O(n)
    public static double[] prefixAverage2(double[] X) {
        int n = X.length;
        double[] A = new double[n];
        double total = 0.0;
        for (int i = 0; i < n; i++) {
            total += X[i];
            A[i] = total / (i + 1);
        }
        return A;
    }

    private static double[] randomArray(int n, Random rng) {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) arr[i] = rng.nextDouble();
        return arr;
    }

    private static long timeMillis(Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        return (t1 - t0) / 1_000_000;
    }

    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400};
        Random rng = new Random(42);
        StringBuilder sb = new StringBuilder("n,pa1_ms,pa2_ms\n");

        for (int n : sizes) {
            double[] X = randomArray(n, rng);
            long t1 = timeMillis(() -> prefixAverage1(X));
            long t2 = timeMillis(() -> prefixAverage2(X));
            System.out.printf("n=%-6d pa1=%-6d ms pa2=%-6d ms%n", n, t1, t2);
            sb.append(n).append(",").append(t1).append(",").append(t2).append("\n");
        }

        try (PrintWriter out = new PrintWriter(new File("prefix_avg_times.csv"))) {
            out.print(sb.toString());
        }
        System.out.println("CSV written (import into Excel to make a log-log plot).");
    }
}
