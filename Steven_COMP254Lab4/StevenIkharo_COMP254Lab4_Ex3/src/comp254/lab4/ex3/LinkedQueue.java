package comp254.lab4.ex3;


public class LinkedQueue<E> {
    private static class Node<E> {
        E element;
        Node<E> next;
        Node(E e, Node<E> n) { element = e; next = n; }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }


    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) head = newest;
        else tail.next = newest;
        tail = newest;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) return null;
        E answer = head.element;
        head = head.next;
        size--;
        if (size == 0) tail = null;
        return answer;
    }

    public E first() {
        return isEmpty() ? null : head.element;
    }

    public void concatenate(LinkedQueue<E> Q2) {
        if (Q2 == null || Q2 == this || Q2.isEmpty()) return;

        if (this.isEmpty()) {
            // this <- Q2
            this.head = Q2.head;
            this.tail = Q2.tail;
            this.size = Q2.size;
        } else {
            // link this.tail -> Q2.head
            this.tail.next = Q2.head;
            this.tail = Q2.tail;
            this.size += Q2.size;
        }
        // empty Q2
        Q2.head = Q2.tail = null;
        Q2.size = 0;
    }


    public static void main(String[] args) {
        LinkedQueue<Integer> q1 = new LinkedQueue<>();
        LinkedQueue<Integer> q2 = new LinkedQueue<>();
        q1.enqueue(1); q1.enqueue(2);
        q2.enqueue(3); q2.enqueue(4);

        q1.concatenate(q2);  // q1 = [1,2,3,4], q2 = []

        while (!q1.isEmpty()) {
            System.out.print(q1.dequeue() + " "); // 1 2 3 4
        }
        System.out.println("\nq2 empty? " + q2.isEmpty()); // true
    }
}
