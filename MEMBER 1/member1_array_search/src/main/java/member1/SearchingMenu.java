package member1;

import java.io.PrintStream;

/** Console submenu for Searching Operations. Reads from the shared IntArray. */
public final class SearchingMenu {

    private final IntArray array;
    private final ConsoleInput in;
    private final PrintStream out;

    public SearchingMenu(IntArray array, ConsoleInput in, PrintStream out) {
        this.array = array;
        this.in = in;
        this.out = out;
    }

    public void run() {
        try {
            while (true) {
                out.println("\n=============================================");
                out.println("            SEARCHING OPERATIONS");
                out.println("=============================================");
                out.println("1. Linear Search");
                out.println("2. Binary Search");
                out.println("3. Compare Searching Algorithms");
                out.println("4. Return to Main Menu");
                switch (in.readInt("\nEnter your choice: ")) {
                    case 1: linear(); break;
                    case 2: binary(); break;
                    case 3: compare(); break;
                    case 4: return;
                    default: out.println("Invalid choice. Please select 1 to 4.");
                }
            }
        } catch (ConsoleInput.InputClosedException e) {
            out.println("\nInput closed. Leaving Searching Operations.");
        }
    }

    private boolean arrayIsUsable() {
        if (array.isEmpty()) {
            out.println("Array is empty. Insert elements in Array Operations first.");
            return false;
        }
        return true;
    }

    private void linear() {
        if (!arrayIsUsable()) return;
        int target = in.readInt("Search target: ");
        int[] snapshot = array.toArray();
        out.println("\nData (original order): " + java.util.Arrays.toString(snapshot));
        out.println(SearchAlgorithms.linearSearch(snapshot, target).describe());
    }

    private void binary() {
        if (!arrayIsUsable()) return;
        int target = in.readInt("Search target: ");
        int[] original = array.toArray();
        int[] sorted = SearchAlgorithms.sortedCopy(original); // preparation step
        out.println("\nOriginal Array: " + java.util.Arrays.toString(original));
        out.println("Sorted Copy   : " + java.util.Arrays.toString(sorted));
        out.println("(Binary Search needs sorted data, so it searches the sorted copy.)\n");
        out.println(SearchAlgorithms.binarySearch(sorted, target).describe());
    }

    private void compare() {
        if (!arrayIsUsable()) return;
        int target = in.readInt("Search target: ");
        out.println();
        out.println(SearchComparison.run(array.toArray(), target).toTable());
    }
}
