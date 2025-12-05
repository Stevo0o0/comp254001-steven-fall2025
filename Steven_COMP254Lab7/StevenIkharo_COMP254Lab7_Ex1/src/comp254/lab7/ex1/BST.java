package comp254.lab7.ex1;

public class BST {

    static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    // ITERATIVE treeSearch
    public Node treeSearch(Node root, int k) {
        Node current = root;

        while (current != null) {
            if (k == current.key) {
                return current;
            } else if (k < current.key) {
                current = current.left;
            } else { // k > current.key
                current = current.right;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        BST tree = new BST();

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(9);
        root.left.left = new Node(1);
        root.left.right = new Node(4);

        Node result = tree.treeSearch(root, 5);
        System.out.println(result != null ? "Found" : "Not Found");
    }
}

