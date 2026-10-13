
import java.util.Arrays;

public class DynamicArray {
    private int[] data;
    private int size;

    public DynamicArray(int capacity) {
        data = new int[Math.max(2, capacity)];
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > size) return false;
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
        return true;
    }

    public int delete(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid array index");
        int removed = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removed;
    }

    public int linearSearch(int value) {
        for (int i = 0; i < size; i++) if (data[i] == value) return i;
        return -1;
    }

    public void display() {
        System.out.println("Array: " + Arrays.toString(toArray()));
    }

    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void clear() { size = 0; }

    private void ensureCapacity() {
        if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
    }
}
