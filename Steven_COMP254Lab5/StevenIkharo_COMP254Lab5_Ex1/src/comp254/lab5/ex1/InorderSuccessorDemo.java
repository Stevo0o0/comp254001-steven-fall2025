package comp254.lab5.ex1;

import java.util.*;
//      A
//     / \
//    B   C
//   / \
//  D   E


public class InorderSuccessorDemo {

    static class Node<E> {
        E e;
        Node<E> left, right, parent;
        Node(E e) { this.e = e; }
    }

    static class BinaryTree<E> {
        Node<E> root;

        Node<E> addRoot(E e) { root = new Node<>(e); return root; }
        Node<E> addLeft(Node<E> p, E e) { p.left = new Node<>(e); p.left.parent = p; return p.left; }
        Node<E> addRight(Node<E> p, E e) { p.right = new Node<>(e); p.right.parent = p; return p.right; }

        Node<E> inorderNext(Node<E> p) {
            if (p == null) return null;
            if (p.right != null) {
                // successor = leftmost in right subtree
                Node<E> x = p.right;
                while (x.left != null) x = x.left;
                return x;
            }
            Node<E> child = p, parent = p.parent;
            while (parent != null && parent.right == child) {
                child = parent;
                parent = parent.parent;
            }
            return parent; // may be null if p is the last node in inorder
        }

        void inorder(Node<E> x, List<E> out) {
            if (x == null) return;
            inorder(x.left, out);
            out.add(x.e);
            inorder(x.right, out);
        }
    }

    public static void main(String[] args) {
        BinaryTree<String> T = new BinaryTree<>();
        Node<String> A = T.addRoot("A");
        Node<String> B = T.addLeft(A, "B");
        Node<String> C = T.addRight(A, "C");
        Node<String> D = T.addLeft(B, "D");
        Node<String> E = T.addRight(B, "E");

        List<String> order = new ArrayList<>();
        T.inorder(T.root, order);
        System.out.println("Inorder: " + order);

        System.out.println("next(B) = " + val(T.inorderNext(B)));
        System.out.println("next(E) = " + val(T.inorderNext(E)));
        System.out.println("next(C) = " + val(T.inorderNext(C)));
    }

    private static <E> String val(Node<E> n) { return n == null ? "null" : String.valueOf(n.e); }
}


//Worst-case time: O(h), where h = tree height.