
public class LinkedListDS {
    private static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private int size;

    public void insertFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
    }

    public void insertLast(int value) {
        Node node = new Node(value);
        if (head == null) head = node;
        else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = node;
        }
        size++;
    }

    public boolean delete(int value) {
        if (head == null) return false;
        if (head.value == value) {
            head = head.next; size--; return true;
        }
        Node current = head;
        while (current.next != null && current.next.value != value) current = current.next;
        if (current.next == null) return false;
        current.next = current.next.next;
        size--;
        return true;
    }

    public int search(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.value == value) return index;
            current = current.next; index++;
        }
        return -1;
    }

    public void display() {
        StringBuilder sb = new StringBuilder("Linked List: ");
        Node current = head;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.next;
        }
        sb.append("NULL");
        System.out.println(sb);
    }

    public void clear() { head = null; size = 0; }
    public int size() { return size; }
}
