package org.self.yahoo.book.demo.chap3.binaryTree.worksheet;

public class BinaryTreeNode {

    int key;
    String value;

    public BinaryTreeNode(int key, String data) {
        this.key = key;
        this.value = data;
    }

    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
