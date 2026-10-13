
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DynamicArray array = new DynamicArray(10);
    private static final StackDS stack = new StackDS(10);
    private static final QueueDS queue = new QueueDS(10);
    private static final LinkedListDS linkedList = new LinkedListDS();
    private static final Graph graph = new Graph();

    public static void main(String[] args) {
        seedDemoData();
        boolean running = true;
        System.out.println("\n=============================================");
        System.out.println("     DATA STRUCTURE & GRAPH ANALYZER");
        System.out.println("=============================================");
        System.out.println("Demo data has been loaded. You may clear or modify it.");

        while (running) {
            printMainMenu();
            int choice = InputHelper.readInt(scanner, "Enter your choice: ", 1, 9);

            
            switch (choice) {
                case 1 -> arrayMenu();
                case 2 -> stackMenu();
                case 3 -> queueMenu();
                case 4 -> linkedListMenu();
                case 5 -> searchingMenu();
                case 6 -> graphMenu();
                case 7 -> {
    PerformanceAnalyzer.displayPerformance(array, graph);

    System.out.println("\nPress Enter to continue...");
    scanner.nextLine();
}
                case 8 -> {
                    System.out.println("\n========== ALL CURRENT RESULTS ==========");
                    System.out.println("\nARRAY:");
                    array.display();

                    System.out.println("\nSTACK:");
                    stack.display();

                    System.out.println("\nQUEUE:");
                    queue.display();

                    System.out.println("\nLINKED LIST:");
                    linkedList.display();

                    System.out.println("\nGRAPH:");
                    graph.display();

                    System.out.println("\n=========================================");
                    System.out.println("\nPress Enter to return to Main Menu...");
                    scanner.nextLine();
}
                case 9 -> {
                    running = false;
                    System.out.println("Thank you for using Data Structure & Graph Analyzer.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=============================================");
        System.out.println("              MAIN MENU");
        System.out.println("=============================================");
        System.out.println("1. Array Operations");
        System.out.println("2. Stack Operations");
        System.out.println("3. Queue Operations");
        System.out.println("4. Linked List Operations");
        System.out.println("5. Searching Operations");
        System.out.println("6. Graph Operations");
        System.out.println("7. Performance Comparison");
        System.out.println("8. Display All Results");
        System.out.println("9. Exit");
    }

    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Clear");
            System.out.println("6. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 6);
            if (c == 6) return;

            switch (c) {
                case 1 -> {
                    int value = InputHelper.readInt(scanner, "Enter value: ");
                    int index = InputHelper.readInt(scanner, "Enter index (0-" + array.size() + "): ", 0, array.size());
                    System.out.println(array.insert(index, value) ? "Inserted successfully." : "Insertion failed.");
                }
                case 2 -> {
                    if (array.isEmpty()) { System.out.println("Array is empty."); break; }
                    int index = InputHelper.readInt(scanner, "Enter index (0-" + (array.size()-1) + "): ", 0, array.size()-1);
                    System.out.println("Deleted value: " + array.delete(index));
                }
                case 3 -> {
                    int value = InputHelper.readInt(scanner, "Enter value to search: ");
                    int index = array.linearSearch(value);
                    System.out.println(index >= 0 ? "Found at index " + index : "Value not found.");
                }
                case 4 -> array.display();
                case 5 -> { array.clear(); System.out.println("Array cleared."); }
            }
        }
    }

    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Clear");
            System.out.println("6. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 6);
            if (c == 6) return;
            switch (c) {
                case 1 -> stack.push(InputHelper.readInt(scanner, "Enter value: "));
                case 2 -> {
                    Integer v = stack.pop();
                    System.out.println(v == null ? "Stack underflow: stack is empty." : "Popped: " + v);
                }
                case 3 -> {
                    Integer v = stack.peek();
                    System.out.println(v == null ? "Stack is empty." : "Top: " + v);
                }
                case 4 -> stack.display();
                case 5 -> { stack.clear(); System.out.println("Stack cleared."); }
            }
        }
    }

    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Front/Peek");
            System.out.println("4. Display");
            System.out.println("5. Clear");
            System.out.println("6. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 6);
            if (c == 6) return;
            switch (c) {
                case 1 -> queue.enqueue(InputHelper.readInt(scanner, "Enter value: "));
                case 2 -> {
                    Integer v = queue.dequeue();
                    System.out.println(v == null ? "Queue underflow: queue is empty." : "Dequeued: " + v);
                }
                case 3 -> {
                    Integer v = queue.peek();
                    System.out.println(v == null ? "Queue is empty." : "Front: " + v);
                }
                case 4 -> queue.display();
                case 5 -> { queue.clear(); System.out.println("Queue cleared."); }
            }
        }
    }

    private static void linkedListMenu() {
        while (true) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Delete by value");
            System.out.println("4. Search");
            System.out.println("5. Display");
            System.out.println("6. Clear");
            System.out.println("7. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 7);
            if (c == 7) return;
            switch (c) {
                case 1 -> linkedList.insertFirst(InputHelper.readInt(scanner, "Enter value: "));
                case 2 -> linkedList.insertLast(InputHelper.readInt(scanner, "Enter value: "));
                case 3 -> {
                    int value = InputHelper.readInt(scanner, "Enter value to delete: ");
                    System.out.println(linkedList.delete(value) ? "Deleted successfully." : "Value not found.");
                }
                case 4 -> {
                    int value = InputHelper.readInt(scanner, "Enter value to search: ");
                    int pos = linkedList.search(value);
                    System.out.println(pos >= 0 ? "Found at position " + pos : "Value not found.");
                }
                case 5 -> linkedList.display();
                case 6 -> { linkedList.clear(); System.out.println("Linked list cleared."); }
            }
        }
    }

    private static void searchingMenu() {
        while (true) {
            System.out.println("\n-------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search (sorted array)");
            System.out.println("3. Compare Linear vs Binary");
            System.out.println("4. Display sorted array");
            System.out.println("5. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 5);
            if (c == 5) return;

            switch (c) {
                case 1 -> {
                    int value = InputHelper.readInt(scanner, "Enter value: ");
                    SearchResult r = SearchAlgorithms.linearSearch(array.toArray(), value);
                    System.out.println(r);
                }
                case 2 -> {
                    int value = InputHelper.readInt(scanner, "Enter value: ");
                    int[] sorted = array.toArray();
                    Arrays.sort(sorted);
                    SearchResult r = SearchAlgorithms.binarySearch(sorted, value);
                    System.out.println("Sorted data: " + Arrays.toString(sorted));
                    System.out.println(r);
                }
                case 3 -> {
                    int value = InputHelper.readInt(scanner, "Enter value: ");
                    PerformanceAnalyzer.compareSearch(array.toArray(), value);
                }
                case 4 -> {
                    int[] sorted = array.toArray();
                    Arrays.sort(sorted);
                    System.out.println("Sorted array: " + Arrays.toString(sorted));
                }
            }
        }
    }

    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Search Vertex");
            System.out.println("7. Clear Graph");
            System.out.println("8. Return");
            int c = InputHelper.readInt(scanner, "Enter choice: ", 1, 8);
            if (c == 8) return;

            switch (c) {
                case 1 -> {
                    String v = InputHelper.readString(scanner, "Enter vertex label: ");
                    System.out.println(graph.addVertex(v) ? "Vertex added." : "Vertex already exists.");
                }
                case 2 -> {
                    String a = InputHelper.readString(scanner, "Enter source vertex: ");
                    String b = InputHelper.readString(scanner, "Enter destination vertex: ");
                    System.out.println(graph.addEdge(a, b) ? "Edge added." : "Unable to add edge. Check vertices.");
                }
                case 3 -> graph.display();
                case 4 -> {
                    String start = InputHelper.readString(scanner, "Enter start vertex: ");
                    List<String> result = graph.bfs(start);
                    System.out.println(result.isEmpty() ? "Start vertex not found." : "BFS: " + result);
                }
                case 5 -> {
                    String start = InputHelper.readString(scanner, "Enter start vertex: ");
                    List<String> result = graph.dfs(start);
                    System.out.println(result.isEmpty() ? "Start vertex not found." : "DFS: " + result);
                }
                case 6 -> {
                    String v = InputHelper.readString(scanner, "Enter vertex to search: ");
                    System.out.println(graph.containsVertex(v) ? "Vertex exists." : "Vertex not found.");
                }
                case 7 -> { graph.clear(); System.out.println("Graph cleared."); }
            }
        }
    }

    private static void displayAllResults() {
        System.out.println("\n================ ALL CURRENT RESULTS ================");
        System.out.println("ARRAY:"); array.display();
        System.out.println("STACK:"); stack.display();
        System.out.println("QUEUE:"); queue.display();
        System.out.println("LINKED LIST:"); linkedList.display();
        System.out.println("GRAPH:"); graph.display();
        System.out.println("======================================================");
    }

    private static void seedDemoData() {
        int[] values = {42, 17, 8, 31, 56, 23, 14, 65, 39, 72};
        for (int i = 0; i < values.length; i++) array.insert(i, values[i]);
        for (int v : new int[]{10, 20, 30, 40}) stack.push(v);
        for (int v : new int[]{100, 200, 300, 400}) queue.enqueue(v);
        linkedList.insertLast(11); linkedList.insertLast(22); linkedList.insertLast(33);
        for (String v : new String[]{"A","B","C","D","E","F"}) graph.addVertex(v);
        graph.addEdge("A","B"); graph.addEdge("A","C"); graph.addEdge("B","D");
        graph.addEdge("B","E"); graph.addEdge("C","F"); graph.addEdge("E","F");
  graph.display();
  }
}
