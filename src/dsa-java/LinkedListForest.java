package dsa;

/**
 * WORLD 2 — LINKED LIST FOREST
 * Node, pointer, traversal, insertion, deletion, reverse, cycle detection.
 */
public class LinkedListForest {
    static class Node { int val; Node next; Node(int v){ val = v; } }

    private Node head;

    public void insertEnd(int v) {                 // O(n)
        Node n = new Node(v);
        if (head == null) { head = n; return; }
        Node cur = head; while (cur.next != null) cur = cur.next;
        cur.next = n;
    }

    public void insertFront(int v) {               // O(1)
        Node n = new Node(v); n.next = head; head = n;
    }

    public void delete(int v) {                    // O(n)
        if (head == null) return;
        if (head.val == v) { head = head.next; return; }
        Node cur = head;
        while (cur.next != null && cur.next.val != v) cur = cur.next;
        if (cur.next != null) cur.next = cur.next.next;   // bypass pointer
    }

    public void reverse() {                         // O(n)
        Node prev = null, cur = head;
        while (cur != null) { Node nx = cur.next; cur.next = prev; prev = cur; cur = nx; }
        head = prev;
    }

    /* Floyd's tortoise & hare — O(n) time, O(1) space */
    public static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public void print() {
        Node cur = head;
        while (cur != null) { System.out.print(cur.val + " -> "); cur = cur.next; }
        System.out.println("null");
    }
}
