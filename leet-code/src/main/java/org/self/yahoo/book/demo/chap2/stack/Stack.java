package org.self.yahoo.book.demo.chap2.stack;

public class Stack {
    Node head;
    int size = 0;

    public void push(int item) {
        Node node = new Node(item);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        node.setNextNode(head);
        head = node;
    }

    public int pop() {
        if (head == null) {
            return -1;
        }
        size--;
        int delItem = head.getData();
        Node newHead = head.getNextNode();
        head.setNextNode(null);
        head = newHead;
        return delItem;
    }

    public int peek() {
        if (head == null) {
            return -1;
        }
        return head.getData();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node currentNode = head;
        stringBuilder.append("[ ");

        while (currentNode != null) {
            stringBuilder.append(currentNode.getData());
            currentNode = currentNode.getNextNode();

            if (currentNode != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
