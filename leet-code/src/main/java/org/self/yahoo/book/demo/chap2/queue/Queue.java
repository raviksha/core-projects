package org.self.yahoo.book.demo.chap2.queue;

public class Queue {

    DoubleLinkedListNode head = null;
    DoubleLinkedListNode tail = null;

    int size;

    public void setHead(DoubleLinkedListNode head) {
        this.head = head;
    }

    public DoubleLinkedListNode getHead() {
        return head;
    }

    public DoubleLinkedListNode getTail() {
        return tail;
    }

    public void setTail(DoubleLinkedListNode tail) {
        this.tail = tail;
    }

    /*
        Add nodes to the tail end of the queue
     */
    public void enqueue(int item) {
        DoubleLinkedListNode node = new DoubleLinkedListNode(item, null, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        if (tail == null) {
            tail = node;
            tail.setPrevious(head);
            head.setNext(tail);
            return;
        }

        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;

    }

    /*
        Removes node from the head of the queue
     */
    public int dequeue() {

        if (head == null) {
            return -1;
        }
        size--;
        if (tail == null) {
            int item =  head.getData();
            head = null;
            return item;
        }

        int itemVal = head.getData();
        head = head.getNext();
        if (head != null) {
            head.setPrevious(null);
        }
        return itemVal;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        DoubleLinkedListNode currentNode = head;
        while (currentNode != null) {
            stringBuilder.append(currentNode.getData());
            currentNode = currentNode.getNext();
            if (currentNode != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public int getSize() {
        return size;
    }
}
