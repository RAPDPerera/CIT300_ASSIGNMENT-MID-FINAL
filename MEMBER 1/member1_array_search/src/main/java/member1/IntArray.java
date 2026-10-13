package member1;

import java.util.Arrays;

/**
 * Fixed-capacity integer array with a separately tracked logical size.
 * Indexing is zero-based. Only positions 0..size-1 hold real elements.
 * Every failed operation throws BEFORE changing any data, so the array
 * is never left in a corrupted state.
 */
public final class IntArray {

    private final int[] data;   // backing storage (never exposed)
    private int size;           // number of active elements

    public IntArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.data = new int[capacity];
        this.size = 0;
    }

    // ---------- insert ----------

    /** Adds a value after the last element. O(1). */
    public void append(int value) {
        insertAt(size, value);
    }

    /** Adds a value at index 0 (shifts everything right). O(n). */
    public void insertFirst(int value) {
        insertAt(0, value);
    }

    /**
     * Inserts at a valid index 0..size (index == size means append).
     * Elements from the insert position onward are shifted one step right
     * to open a free slot. O(n) in the worst case.
     */
    public void insertAt(int index, int value) {
        if (isFull()) {
            throw new IllegalStateException("Array is full (capacity " + data.length + ").");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index " + index + ". Valid insert range is 0 to " + size + ".");
        }
        // Shift right, starting from the END so no value is overwritten.
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    // ---------- delete ----------

    /** Removes and returns the element at index. Shifts later elements left. O(n). */
    public int deleteAt(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty. Nothing to delete.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index " + index + ". Valid delete range is 0 to " + (size - 1) + ".");
        }
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = 0; // clear the stale slot so old data cannot leak
        return removed;
    }

    /**
     * Deletes the FIRST element equal to value.
     * @return the index it was removed from, or -1 if the value is absent
     *         (in which case nothing is deleted).
     */
    public int deleteFirstOccurrence(int value) {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty. Nothing to delete.");
        }
        int index = indexOf(value);
        if (index != -1) {
            deleteAt(index);
        }
        return index;
    }

    // ---------- search / access ----------

    /** Manual linear search. Returns the FIRST matching index, or -1. O(n). */
    public int indexOf(int target) {
        for (int i = 0; i < size; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Invalid index " + index + ". Valid range is 0 to " + (size - 1) + ".");
        }
        return data[index];
    }

    // ---------- state ----------

    public int size() { return size; }

    public int capacity() { return data.length; }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == data.length; }

    /** Defensive snapshot of the active elements only. Changing it never affects this array. */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /** Active elements only, e.g. "[10, 20, 30]". */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        return sb.append(']').toString();
    }
}
