public class QueueTest {
    public static void main(String[] args) {

        Queue queue = new Queue();

        System.out.println("=== QUEUE TEST ===");

        queue.enqueue("Student A");
        queue.enqueue("Student B");
        queue.enqueue("Student C");

        queue.display();
        queue.peek();

        queue.dequeue();
        queue.display();

        queue.dequeue();
        queue.dequeue();

        queue.display();

        // Test empty queue condition
        queue.dequeue();
    }
}
