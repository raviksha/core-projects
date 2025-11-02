package org.self.yahoo.book.demo.chap2.stack;

public class Node {
    int data;
    Node nextNode;

    public Node(int data) {
        this.data = data;
        this.nextNode = null;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}
