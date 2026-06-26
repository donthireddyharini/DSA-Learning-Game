package dsa;
import java.util.*;

/**
 * WORLD 3 — STACK VOLCANO
 * Push, pop, peek, overflow/underflow, and postfix expression evaluation.
 */
public class StackVolcano {
    private final int[] data; private int top = -1;
    public StackVolcano(int capacity) { data = new int[capacity]; }

    public void push(int v) {                      // overflow guard
        if (top == data.length - 1) throw new RuntimeException("Stack Overflow");
        data[++top] = v;
    }
    public int pop() {                              // underflow guard
        if (top == -1) throw new RuntimeException("Stack Underflow");
        return data[top--];
    }
    public int peek() {
        if (top == -1) throw new RuntimeException("Empty");
        return data[top];
    }
    public boolean isEmpty() { return top == -1; }

    /* Evaluate a space-separated postfix expression, e.g. "5 3 + 2 *" -> 16 */
    public static int evalPostfix(String expr) {
        Deque<Integer> st = new ArrayDeque<>();
        for (String tok : expr.trim().split("\\s+")) {
            if (tok.matches("-?\\d+")) { st.push(Integer.parseInt(tok)); continue; }
            int b = st.pop(), a = st.pop();
            switch (tok) {
                case "+": st.push(a + b); break;
                case "-": st.push(a - b); break;
                case "*": st.push(a * b); break;
                case "/": st.push(a / b); break;
                default: throw new RuntimeException("Bad token: " + tok);
            }
        }
        return st.pop();
    }
}
