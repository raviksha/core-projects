package org.self.yahoo.book.demo.chap2.stack;

public class StringNode {
    String data;
    StringNode nextNode;

    public StringNode(String data) {
        this.data = data;
        this.nextNode = null;
    }

    public StringNode getNextNode() {
        return nextNode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNextNode(StringNode nextNode) {
        this.nextNode = nextNode;
    }
}
