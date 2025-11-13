package comp254.lab5.ex3;

import java.util.ArrayList;
import java.util.List;

public class RecursiveUpheapDemo {

    static class MinHeap {
        private final List<Integer> heap = new ArrayList<>();

        public int size() { return heap.size(); }
        public boolean isEmpty() { return heap.isEmpty(); }

        public void insert(int x) {
            heap.add(x);
            upheap(size() - 1);
        }

        public Integer min() { return isEmpty() ? null : heap.get(0); }

        public Integer removeMin() {
            if (isEmpty()) return null;
            int ans = heap.get(0);
            int last = heap.remove(size() - 1);
            if (!isEmpty()) {
                heap.set(0, last);
                downheap(0);
            }
            return ans;
        }

        private void upheap(int j) {
            if (j == 0) return;
            int p = parent(j);
            if (heap.get(j) < heap.get(p)) {
                swap(j, p);
                upheap(p);
            }
        }

        private void downheap(int j) {
            while (true) {
                int left = left(j), right = right(j), small = j;
                if (left < size() && heap.get(left) < heap.get(small)) small = left;
                if (right < size() && heap.get(right) < heap.get(small)) small = right;
                if (small == j) break;
                swap(j, small);
                j = small;
            }
        }

        private int parent(int j) { return (j - 1) / 2; }
        private int left(int j) { return 2 * j + 1; }
        private int right(int j) { return 2 * j + 2; }
        private void swap(int i, int k) {
            int t = heap.get(i);
            heap.set(i, heap.get(k));
            heap.set(k, t);
        }

        @Override public String toString() { return heap.toString(); }
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        int[] vals = { 7, 3, 5, 2, 9, 1, 4 };
        for (int v : vals) {
            h.insert(v);
        }
        System.out.println("Heap array: " + h);
        System.out.println("min() = " + h.min());

        while (!h.isEmpty()) {
            System.out.print(h.removeMin() + " ");
        }
        System.out.println();
    }
}
