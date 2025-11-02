package org.self.yahoo.book.demo.chap2.stack;

public class StringStack {
    StringNode head;
    int size;


    public void push(String item) {
        StringNode node = new StringNode(item);

        if (head == null) {
            head = node;
            return;
        }
        size++;
        node.setNextNode(head);
        head = node;
    }

    public String pop() {
        if (head == null) {
            return null;
        }
        size--;
        String delItem = head.getData();
        StringNode newHead = head.getNextNode();
        head.setNextNode(null);
        head = newHead;
        return delItem;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String peek() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringNode currentNode = head;
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
}
