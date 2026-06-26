package dsa;
import java.util.*;

/**
 * WORLD 5 — TREE KINGDOM
 * Binary Search Tree: insert, search, in/pre/post-order. Plus a heap demo.
 */
public class TreeKingdom {
    static class Node { int val; Node left, right; Node(int v){ val = v; } }
    private Node root;

    public void insert(int v) { root = insert(root, v); }     // O(h)
    private Node insert(Node n, int v) {
        if (n == null) return new Node(v);
        if (v < n.val) n.left = insert(n.left, v);
        else           n.right = insert(n.right, v);
        return n;
    }

    public boolean search(int v) {                            // O(h)
        Node n = root;
        while (n != null) {
            if (v == n.val) return true;
            n = (v < n.val) ? n.left : n.right;
        }
        return false;
    }

    /* Inorder traversal of a BST yields sorted ascending order. */
    public void inorder(Node n) { if (n == null) return; inorder(n.left); System.out.print(n.val + " "); inorder(n.right); }
    public void preorder(Node n){ if (n == null) return; System.out.print(n.val + " "); preorder(n.left); preorder(n.right); }
    public void postorder(Node n){ if (n == null) return; postorder(n.left); postorder(n.right); System.out.print(n.val + " "); }
    public Node getRoot() { return root; }

    /* Heap demo — Java's PriorityQueue is a binary min-heap. */
    public static void heapDemo() {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int x : new int[]{7, 2, 9, 1, 5}) heap.add(x);
        while (!heap.isEmpty()) System.out.print(heap.poll() + " "); // ascending
        System.out.println();
    }
}
