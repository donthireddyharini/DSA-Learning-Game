package dsa;
import java.util.*;

/**
 * WORLD 1 — ARRAY KINGDOM
 * Reference implementations for every array topic the game teaches:
 * indexing, traversal, insertion, deletion, linear/binary search, and the
 * four "sorting blades": bubble, selection, merge, quick.
 */
public class ArrayKingdom {

    /* Traversal — visit each cell once: O(n) */
    public static void traverse(int[] a) {
        for (int i = 0; i < a.length; i++) System.out.print(a[i] + " ");
        System.out.println();
    }

    /* Insertion at index pos — shift right: O(n) */
    public static int[] insert(int[] a, int pos, int val) {
        int[] out = new int[a.length + 1];
        for (int i = 0; i < pos; i++) out[i] = a[i];
        out[pos] = val;
        for (int i = pos; i < a.length; i++) out[i + 1] = a[i];
        return out;
    }

    /* Deletion at index pos — shift left: O(n) */
    public static int[] delete(int[] a, int pos) {
        int[] out = new int[a.length - 1];
        for (int i = 0, j = 0; i < a.length; i++) if (i != pos) out[j++] = a[i];
        return out;
    }

    /* Linear search — scan in order: O(n) */
    public static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) if (a[i] == target) return i;
        return -1;
    }

    /* Binary search — sorted input, halve each step: O(log n) */
    public static int binarySearch(int[] a, int target) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) return mid;
            if (a[mid] < target) lo = mid + 1; else hi = mid - 1;
        }
        return -1;
    }

    /* ---- Sorting blades ---- */
    public static void bubbleSort(int[] a) {            // O(n^2)
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j] > a[j + 1]) swap(a, j, j + 1);
    }

    public static void selectionSort(int[] a) {         // O(n^2)
        for (int i = 0; i < a.length; i++) {
            int m = i;
            for (int j = i + 1; j < a.length; j++) if (a[j] < a[m]) m = j;
            swap(a, i, m);
        }
    }

    public static void quickSort(int[] a, int lo, int hi) { // avg O(n log n)
        if (lo >= hi) return;
        int p = a[hi], i = lo;
        for (int j = lo; j < hi; j++) if (a[j] < p) swap(a, i++, j);
        swap(a, i, hi);
        quickSort(a, lo, i - 1);
        quickSort(a, i + 1, hi);
    }

    public static void mergeSort(int[] a, int l, int r) {   // O(n log n)
        if (r - l <= 1) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m, r);
        int[] tmp = new int[r - l];
        int i = l, j = m, k = 0;
        while (i < m && j < r) tmp[k++] = (a[i] <= a[j]) ? a[i++] : a[j++];
        while (i < m) tmp[k++] = a[i++];
        while (j < r) tmp[k++] = a[j++];
        System.arraycopy(tmp, 0, a, l, tmp.length);
    }

    private static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
}
