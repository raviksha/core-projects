package org.self.yahoo.book.demo.chap2.queue;

public class DoubleLinkedListNode {
    private int data;
    private DoubleLinkedListNode next;
    private DoubleLinkedListNode previous;

    public DoubleLinkedListNode(int data, DoubleLinkedListNode next, DoubleLinkedListNode previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(DoubleLinkedListNode nextNode) {
        this.next = nextNode;
    }

    public int getData() {
        return this.data;
    }

    public DoubleLinkedListNode getNext() {
        return next;
    }

    public DoubleLinkedListNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleLinkedListNode previous) {
        this.previous = previous;
    }
}
