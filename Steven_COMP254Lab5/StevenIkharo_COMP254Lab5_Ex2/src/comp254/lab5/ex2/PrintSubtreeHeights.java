package comp254.lab5.ex2;

import java.util.*;

public class PrintSubtreeHeights {

    static class Node<E> {
        E e; Node<E> left, right;
        Node(E e) { this.e = e; }
    }

    static class BinaryTree<E> {
        Node<E> root;
        Node<E> addRoot(E e) { root = new Node<>(e); return root; }
        Node<E> addLeft(Node<E> p, E e) { p.left = new Node<>(e); return p.left; }
        Node<E> addRight(Node<E> p, E e) { p.right = new Node<>(e); return p.right; }

        int printHeights(Node<E> p) {
            if (p == null) return -1;
            int hl = printHeights(p.left);
            int hr = printHeights(p.right);
            int h = 1 + Math.max(hl, hr);
            System.out.println(p.e + ": " + h);
            return h;
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> T = new BinaryTree<>();
        Node<String> A = T.addRoot("A");
        Node<String> B = T.addLeft(A, "B");
        Node<String> C = T.addRight(A, "C");
        Node<String> D = T.addLeft(B, "D");
        Node<String> E = T.addRight(B, "E");

        T.printHeights(T.root);
    }
}
