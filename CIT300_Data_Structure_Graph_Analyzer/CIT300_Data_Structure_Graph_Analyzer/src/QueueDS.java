
import java.util.Arrays;

public class QueueDS {
    private final int[] data;
    private int front = 0, rear = -1, size = 0;

    public QueueDS(int capacity) { data = new int[capacity]; }

    public boolean enqueue(int value) {
        if (size == data.length) {
            System.out.println("Queue overflow: queue is full.");
            return false;
        }
        rear = (rear + 1) % data.length;
        data[rear] = value;
        size++;
        return true;
    }

    public Integer dequeue() {
        if (size == 0) return null;
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public Integer peek() { return size == 0 ? null : data[front]; }

    public void display() {
        if (size == 0) { System.out.println("Queue: []"); return; }
        int[] values = new int[size];
        for (int i = 0; i < size; i++) values[i] = data[(front + i) % data.length];
        System.out.println("Queue (front -> rear): " + Arrays.toString(values));
    }

    public void clear() { front = 0; rear = -1; size = 0; }
}
