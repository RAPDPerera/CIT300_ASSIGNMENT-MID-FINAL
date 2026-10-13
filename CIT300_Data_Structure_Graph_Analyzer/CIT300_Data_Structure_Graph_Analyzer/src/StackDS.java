
import java.util.Arrays;

public class StackDS {
    private final int[] data;
    private int top = -1;

    public StackDS(int capacity) { data = new int[capacity]; }

    public boolean push(int value) {
        if (top == data.length - 1) {
            System.out.println("Stack overflow: stack is full.");
            return false;
        }
        data[++top] = value;
        return true;
    }

    public Integer pop() {
        if (top == -1) return null;
        return data[top--];
    }

    public Integer peek() {
        return top == -1 ? null : data[top];
    }

    public void display() {
        if (top == -1) { System.out.println("Stack: []"); return; }
        int[] values = Arrays.copyOf(data, top + 1);
        System.out.println("Stack (bottom -> top): " + Arrays.toString(values));
    }

    public void clear() { top = -1; }
}
