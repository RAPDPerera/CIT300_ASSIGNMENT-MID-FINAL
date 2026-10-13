import java.util.ArrayList;

public class Queue {
    private ArrayList<String> items = new ArrayList<>();

    // Add an item to the rear
    public void enqueue(String item) {
        items.add(item);
        System.out.println(item + " added to Queue.");
    }

    // Remove the front item
    public void dequeue() {
        if (items.isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }

        String removed = items.remove(0);
        System.out.println(removed + " removed from Queue.");
    }

    // View the front item
    public void peek() {
        if (items.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Front item: " + items.get(0));
    }

    // Display all items
    public void display() {
        if (items.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        System.out.println("Queue (front to rear): " + items);
    }
}