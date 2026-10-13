package member1;

import java.io.PrintStream;

/** Console submenu for Array Operations. Works on a shared IntArray. */
public final class ArrayMenu {

    private final IntArray array;
    private final ConsoleInput in;
    private final PrintStream out;

    public ArrayMenu(IntArray array, ConsoleInput in, PrintStream out) {
        this.array = array;
        this.in = in;
        this.out = out;
    }

    /** Runs until the user chooses "Return to Main Menu" or input ends. */
    public void run() {
        try {
            while (true) {
                out.println("\n=============================================");
                out.println("              ARRAY OPERATIONS");
                out.println("=============================================");
                out.println("1. Insert Element");
                out.println("2. Delete Element");
                out.println("3. Search Element");
                out.println("4. Display Array");
                out.println("5. Return to Main Menu");
                switch (in.readInt("\nEnter your choice: ")) {
                    case 1: insert(); break;
                    case 2: delete(); break;
                    case 3: search(); break;
                    case 4: display(); break;
                    case 5: return;
                    default: out.println("Invalid choice. Please select 1 to 5.");
                }
            }
        } catch (ConsoleInput.InputClosedException e) {
            out.println("\nInput closed. Leaving Array Operations.");
        }
    }

    private void insert() {
        if (array.isFull()) {
            out.println("Array is full (" + array.size() + "/" + array.capacity()
                    + "). Delete an element first.");
            return;
        }
        out.println("\nCurrent Array: " + array);
        out.println("1. Insert at beginning");
        out.println("2. Insert at index");
        out.println("3. Insert at end");
        int where = in.readIntInRange("Choose position: ", 1, 3);
        int value = in.readInt("Insert value: ");
        try {
            if (where == 1) {
                array.insertFirst(value);
            } else if (where == 2) {
                int index = in.readInt("Insert index (0 to " + array.size() + "): ");
                array.insertAt(index, value);
            } else {
                array.append(value);
            }
            out.println("\nUpdated Array: " + array);
        } catch (IndexOutOfBoundsException | IllegalStateException e) {
            out.println("Error: " + e.getMessage() + " Array unchanged.");
        }
    }

    private void delete() {
        if (array.isEmpty()) {
            out.println("Array is empty. Nothing to delete.");
            return;
        }
        out.println("\nCurrent Array: " + array);
        out.println("1. Delete by index");
        out.println("2. Delete first matching value");
        int mode = in.readIntInRange("Choose method: ", 1, 2);
        try {
            if (mode == 1) {
                int index = in.readInt("Delete index (0 to " + (array.size() - 1) + "): ");
                int removed = array.deleteAt(index);
                out.println("\nDeleted value: " + removed);
            } else {
                int value = in.readInt("Value to delete: ");
                int index = array.deleteFirstOccurrence(value);
                if (index == -1) {
                    out.println("Value " + value + " not found. Array unchanged.");
                    return;
                }
                out.println("\nDeleted value " + value + " from index " + index);
            }
            out.println("Updated Array: " + array);
        } catch (IndexOutOfBoundsException | IllegalStateException e) {
            out.println("Error: " + e.getMessage() + " Array unchanged.");
        }
    }

    private void search() {
        if (array.isEmpty()) {
            out.println("Array is empty. Nothing to search.");
            return;
        }
        int target = in.readInt("Search value: ");
        int index = array.indexOf(target);
        if (index == -1) {
            out.println("\nResult: Element " + target + " not found.");
        } else {
            out.println("\nResult: Element found at index " + index + " (first match).");
        }
    }

    private void display() {
        if (array.isEmpty()) {
            out.println("\nArray is empty.");
            return;
        }
        out.println("\nCurrent Array: " + array);
        out.println("Total Elements: " + array.size() + " (capacity " + array.capacity() + ")");
    }
}
