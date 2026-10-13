package member1;

import java.util.Scanner;

/**
 * TEMPORARY standalone demo entry point for Member 1 only.
 * It is NOT the group's main application. Member 5 integrates by creating one
 * IntArray and passing it to ArrayMenu and SearchingMenu (see MEMBER1_GUIDE.md).
 */
public final class Member1Demo {

    private static final int MAX_CAPACITY = 1_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // shared; never closed here
        ConsoleInput in = new ConsoleInput(scanner, System.out);
        System.out.println("=============================================");
        System.out.println(" MEMBER 1 DEMO (standalone: Array + Searching)");
        System.out.println(" Temporary entry point - not the group's main app");
        System.out.println("=============================================");
        try {
            int capacity = in.readIntInRange(
                    "Enter array capacity (1-" + MAX_CAPACITY + "): ", 1, MAX_CAPACITY);
            IntArray array = new IntArray(capacity);          // ONE shared instance
            ArrayMenu arrayMenu = new ArrayMenu(array, in, System.out);
            SearchingMenu searchingMenu = new SearchingMenu(array, in, System.out);

            while (true) {
                System.out.println("\n1. Array Operations");
                System.out.println("2. Searching Operations");
                System.out.println("3. Exit");
                switch (in.readInt("Enter your choice: ")) {
                    case 1: arrayMenu.run(); break;
                    case 2: searchingMenu.run(); break;
                    case 3: System.out.println("Goodbye."); return;
                    default: System.out.println("Invalid choice. Please select 1 to 3.");
                }
            }
        } catch (ConsoleInput.InputClosedException e) {
            System.out.println("\nInput closed. Goodbye.");
        }
    }
}
