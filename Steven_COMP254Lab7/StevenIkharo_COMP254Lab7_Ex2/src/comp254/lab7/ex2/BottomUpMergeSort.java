package comp254.lab7.ex2;

import java.util.*;

public class BottomUpMergeSort {

    public static Queue<Integer> merge(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> result = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek() <= q2.peek())
                result.add(q1.poll());
            else
                result.add(q2.poll());
        }

        result.addAll(q1);
        result.addAll(q2);

        return result;
    }

    public static Queue<Integer> bottomUpMergeSort(List<Integer> list) {
        Queue<Queue<Integer>> queueOfQueues = new LinkedList<>();

        for (int x : list) {
            Queue<Integer> single = new LinkedList<>();
            single.add(x);
            queueOfQueues.add(single);
        }

        while (queueOfQueues.size() > 1) {
            Queue<Integer> q1 = queueOfQueues.poll();
            Queue<Integer> q2 = queueOfQueues.poll();
            Queue<Integer> merged = merge(q1, q2);
            queueOfQueues.add(merged);
        }

        return queueOfQueues.poll();
    }

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(7, 2, 9, 4, 1);
        Queue<Integer> sorted = bottomUpMergeSort(items);
        System.out.println(sorted);
    }
}

