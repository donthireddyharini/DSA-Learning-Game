package dsa;
import java.util.*;

/**
 * WORLD 4 — QUEUE CITY
 * FIFO queue, circular queue (no shifting), and a priority queue.
 */
public class QueueCity {

    /* Circular queue — reuses freed front space via modulo wrap. */
    static class CircularQueue {
        private final int[] data; private int front = 0, size = 0;
        CircularQueue(int cap) { data = new int[cap]; }
        boolean enqueue(int v) {
            if (size == data.length) return false;          // full
            data[(front + size) % data.length] = v; size++; return true;
        }
        Integer dequeue() {
            if (size == 0) return null;                      // empty
            int v = data[front]; front = (front + 1) % data.length; size--; return v;
        }
    }

    /* Priority queue — highest priority leaves first (min-heap by key). */
    public static void priorityDemo() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]); // [priority, job]
        pq.add(new int[]{3, 100}); pq.add(new int[]{1, 200}); pq.add(new int[]{2, 300});
        while (!pq.isEmpty()) {
            int[] j = pq.poll();
            System.out.println("Serve job " + j[1] + " (priority " + j[0] + ")");
        }
    }
}
