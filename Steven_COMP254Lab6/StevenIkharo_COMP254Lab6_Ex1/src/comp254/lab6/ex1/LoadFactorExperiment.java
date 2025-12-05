package comp254.lab6.ex1;

import comp254.lab6.ex1.ChainHashMap;
import comp254.lab6.ex1.ProbeHashMap;
import comp254.lab6.ex1.AbstractHashMap;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Experiment program:
 * - Try different load factors (0.5, 0.75, 0.9)
 * - Insert N random integers, time insertions and gets
 * - Output CSV lines: mapType,loadFactor,n,insertMs,getMs,capacity
 */
public class LoadFactorExperiment {

    public static void main(String[] args) throws Exception {
        int[] sizes = { 1000, 5000, 10000, 20000, 50000, 100000 };
        double[] loads = { 0.5, 0.75, 0.9 };
        String[] maps = { "Chain", "Probe" };

        PrintWriter csv = new PrintWriter(new FileWriter("loadfactor_results.csv", false));
        csv.println("map,loadFactor,n,insertMs,getMs,capacity");

        Random rnd = new Random(42);

        for (String mapType : maps) {
            for (double load : loads) {
                for (int n : sizes) {
                    // create map with initial capacity roughly proportional to n/load
                    int initialCap = Math.max(101, (int)(n / load) + 1);
                    AbstractHashMap<Integer,Integer> map;
                    if (mapType.equals("Chain")) {
                        map = new ChainHashMap<>(initialCap, 109345121, load);
                    } else {
                        map = new ProbeHashMap<>(initialCap, 109345121, load);
                    }

                    // prepare keys
                    int[] keys = new int[n];
                    for (int i = 0; i < n; i++) keys[i] = rnd.nextInt(Integer.MAX_VALUE);

                    // measure inserts
                    long s1 = System.nanoTime();
                    for (int k : keys) map.put(k, k);
                    long e1 = System.nanoTime();

                    // measure gets (random order)
                    long s2 = System.nanoTime();
                    for (int i = 0; i < n; i++) {
                        int k = keys[rnd.nextInt(n)];
                        Integer v = map.get(k);
                        if (v == null) { /* should not happen */ }
                    }
                    long e2 = System.nanoTime();

                    long insertMs = (e1 - s1) / 1_000_000;
                    long getMs = (e2 - s2) / 1_000_000;
                    int cap = map.getCapacity();

                    csv.println(mapType + "," + load + "," + n + "," + insertMs + "," + getMs + "," + cap);
                    csv.flush();

                    System.out.println("Done: " + mapType + " load=" + load + " n=" + n + " insertMs=" + insertMs + " getMs=" + getMs + " cap=" + cap);
                }
            }
        }

        csv.close();
        System.out.println("Results written to loadfactor_results.csv");
    }
}
