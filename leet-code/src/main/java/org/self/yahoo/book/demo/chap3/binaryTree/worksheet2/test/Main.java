package org.self.yahoo.book.demo.chap3.binaryTree.worksheet2.test;

import java.util.ArrayDeque;
import java.util.Queue;

// Binary Tree Node Definition
class BinaryNode {
    int key;
    BinaryNode left, right;

    public BinaryNode(int key) {
        this.key = key;
        this.left = this.right = null;
    }
}

// Binary Search Tree Class
class BinarySearchTree {
    BinaryNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // Public method to insert a key using recursion
    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    // Recursive method to insert a key
    private BinaryNode insertRecursive(BinaryNode node, int key) {
        // Base case: If node is null, create a new node
        if (node == null) {
            return new BinaryNode(key);
        }

        // If key is smaller, insert into left subtree
        if (key < node.key) {
            node.left = insertRecursive(node.left, key);
        }
        // If key is greater, insert into right subtree
        else if (key > node.key) {
            node.right = insertRecursive(node.right, key);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // In-order Traversal (Left, Root, Right) - for testing
    public void inOrderTraversal(BinaryNode node) {
        if (node == null) return;
        inOrderTraversal(node.left);
        System.out.print(node.key + " ");
        inOrderTraversal(node.right);
    }

    // Print the tree (helper method)
    public void printTree() {
        System.out.print("In-Order Traversal: ");
        inOrderTraversal(root);
        System.out.println();
    }

    public void printBFS() {
        if (root == null) {
            System.out.println("Tree is empty ....");
            return;
        }

        Queue<BinaryNode> queue = new ArrayDeque<>();
        queue.add(root);

        System.out.println("Level Order:");

        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.poll();
            System.out.print(currentNode.key + " ");

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        System.out.println(); // Ensures output formatting after traversal
    }
}

// Driver Code
public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert elements
        bst.insert(60);
        bst.insert(10);
        bst.insert(50);
        bst.insert(20);
        bst.insert(5);

        // Print in-order traversal
        bst.printTree(); // Output:5 10 20 50 60

        bst.printBFS(); //
    }
}