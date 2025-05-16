package org.self.yahoo.book.demo.chap3.binaryTree.worksheet2;

public class BinaryNode {

    int key;
    BinaryNode left;
    BinaryNode right;

    public BinaryNode(int data) {
        this.key = data;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return this.key + "";
    }
}
