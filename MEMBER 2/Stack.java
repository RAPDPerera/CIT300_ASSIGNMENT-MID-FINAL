import java.util.ArrayList;

public class Stack {
    private ArrayList<String> items = new ArrayList<>();

    // Add an item to the top
    public void push(String item) {
        items.add(item);
        System.out.println(item + " added to Stack.");
    }

    // Remove the top item
    public void pop() {
        if (items.isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return;
        }

        String removed = items.remove(items.size() - 1);
        System.out.println(removed + " removed from Stack.");
    }

    // View the top item
    public void peek() {
        if (items.isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        System.out.println("Top item: " + items.get(items.size() - 1));
    }

    // Display all items
    public void display() {
        if (items.isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }

        System.out.println("Stack (bottom to top): " + items);
    }
}