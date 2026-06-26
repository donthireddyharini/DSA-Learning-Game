package dsa;
import java.util.*;

/**
 * WORLD 6 — GRAPH GALAXY
 * Adjacency-list graph with BFS, DFS, and Dijkstra's shortest path.
 */
public class GraphGalaxy {
    private final Map<Integer, List<int[]>> adj = new HashMap<>(); // node -> [neighbor, weight]

    public void addEdge(int u, int v, int w) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, w});
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, w});
    }

    /* BFS — explore level by level using a queue. */
    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(start); seen.add(start);
        while (!q.isEmpty()) {
            int u = q.poll(); order.add(u);
            for (int[] e : adj.getOrDefault(u, List.of()))
                if (seen.add(e[0])) q.add(e[0]);
        }
        return order;
    }

    /* DFS — plunge deep using recursion (an implicit stack). */
    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        dfs(start, new HashSet<>(), order);
        return order;
    }
    private void dfs(int u, Set<Integer> seen, List<Integer> order) {
        if (!seen.add(u)) return;
        order.add(u);
        for (int[] e : adj.getOrDefault(u, List.of())) dfs(e[0], seen, order);
    }

    /* Dijkstra — cheapest weighted path from src, using a priority queue. */
    public Map<Integer, Integer> dijkstra(int src) {
        Map<Integer, Integer> dist = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, dist]
        pq.add(new int[]{src, 0}); dist.put(src, 0);
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); int u = cur[0], d = cur[1];
            if (d > dist.getOrDefault(u, Integer.MAX_VALUE)) continue;
            for (int[] e : adj.getOrDefault(u, List.of())) {
                int nd = d + e[1];
                if (nd < dist.getOrDefault(e[0], Integer.MAX_VALUE)) {
                    dist.put(e[0], nd); pq.add(new int[]{e[0], nd});
                }
            }
        }
        return dist;
    }
}
