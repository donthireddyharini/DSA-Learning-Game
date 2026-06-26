package dsa;
import java.util.*;

/**
 * DSA LEGENDS — runnable demo of every world's algorithms.
 * Compile:  javac dsa/*.java        (run from src/dsa-java's parent, or adjust)
 * Run:      java dsa.Main
 */
public class Main {
    public static void main(String[] args) {
        line("WORLD 1 — ARRAY KINGDOM");
        int[] a = {5, 2, 9, 1, 7, 3};
        System.out.print("original:   "); ArrayKingdom.traverse(a);
        a = ArrayKingdom.insert(a, 2, 99);
        System.out.print("insert@2:   "); ArrayKingdom.traverse(a);
        a = ArrayKingdom.delete(a, 2);
        System.out.print("delete@2:   "); ArrayKingdom.traverse(a);
        System.out.println("linear find 7 -> index " + ArrayKingdom.linearSearch(a, 7));
        int[] b = a.clone(); ArrayKingdom.quickSort(b, 0, b.length - 1);
        System.out.print("quickSorted:"); ArrayKingdom.traverse(b);
        System.out.println("binary find 7 -> index " + ArrayKingdom.binarySearch(b, 7));

        line("WORLD 2 — LINKED LIST FOREST");
        LinkedListForest ll = new LinkedListForest();
        for (int v : new int[]{1, 2, 3, 4}) ll.insertEnd(v);
        System.out.print("list:    "); ll.print();
        ll.reverse();
        System.out.print("reversed:"); ll.print();

        line("WORLD 3 — STACK VOLCANO");
        System.out.println("postfix \"5 3 + 2 *\" = " + StackVolcano.evalPostfix("5 3 + 2 *"));

        line("WORLD 4 — QUEUE CITY");
        QueueCity.priorityDemo();

        line("WORLD 5 — TREE KINGDOM");
        TreeKingdom t = new TreeKingdom();
        for (int v : new int[]{8, 3, 10, 1, 6, 14}) t.insert(v);
        System.out.print("inorder (sorted): "); t.inorder(t.getRoot()); System.out.println();
        System.out.print("heap drain:       "); TreeKingdom.heapDemo();

        line("WORLD 6 — GRAPH GALAXY");
        GraphGalaxy g = new GraphGalaxy();
        g.addEdge(0, 1, 4); g.addEdge(0, 2, 1); g.addEdge(2, 1, 2); g.addEdge(1, 3, 1); g.addEdge(2, 3, 5);
        System.out.println("BFS from 0: " + g.bfs(0));
        System.out.println("DFS from 0: " + g.dfs(0));
        System.out.println("Dijkstra from 0: " + g.dijkstra(0));

        line("WORLD 7 — you are ready for the Algorithm Emperor.");
    }
    static void line(String s){ System.out.println("\n=== " + s + " ==="); }
}
