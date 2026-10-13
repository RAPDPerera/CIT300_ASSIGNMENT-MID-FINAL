public class StackTest {
    public static void main(String[] args) {

        Stack stack = new Stack();

        System.out.println("=== STACK TEST ===");

        stack.push("Student A");
        stack.push("Student B");
        stack.push("Student C");

        stack.display();
        stack.peek();

        stack.pop();
        stack.display();

        stack.pop();
        stack.pop();

        stack.display();
        stack.pop();
    }
}