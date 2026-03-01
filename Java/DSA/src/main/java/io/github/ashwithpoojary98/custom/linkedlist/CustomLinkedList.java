package main.java.io.github.ashwithpoojary98.custom.linkedlist;

public class CustomLinkedList<T> {

    private Node<T> head;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

    }

    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node<>(data);
    }

    public void reverse() {
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        while (currentNode != null) {
            Node<T> nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head=previousNode;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<T> node = head;
        builder.append("[");
        while (node != null) {
            builder.append(node.data);
            if (node.next != null) {
                builder.append(",");
            }
            node = node.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add((3));
        list.add(4);

        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
