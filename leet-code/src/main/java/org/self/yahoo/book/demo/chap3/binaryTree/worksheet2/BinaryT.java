package org.self.yahoo.book.demo.chap3.binaryTree.worksheet2;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryT {
    private BinaryNode root;


    /*
        Time complexity :
                        O(h)     : Height of tree
                        O(log n) : Best case when the tree is balanced
                        O(n)     : When the tree is either left/right  skewed

        Space complexity :
                    O(h)     : Auxiliary space for recursion  (Not applicable for Iterative approach)
                    O(log n) : Best case when the Binary Tree is balanced
                    O(n)     : When the tree is right/left skewed
     */
    public boolean get(int key) {
        if (root == null) {
            System.out.println("Tree is empty ...");
            return false;
        }

        return get(key, root);
    }

    public BinaryNode getRoot() {
        return root;
    }

    private boolean get(int key, BinaryNode node) {
        if (node == null) {
            return false;
        }
        int currValue = node.getKey();
        if (currValue == key) {
            System.out.println("Key found in Binary Tree");
            return true;
        }
        if (key < currValue) {
            return get(key, node.getLeft());
        } else {
            return get(key, node.getRight());
        }
    }

    public void put(int data) {
        if (root == null) {
            root = new BinaryNode(data);
            return;
        }
        put(data, root);
    }

    private void put(int data, BinaryNode node) {

        int currKey = node.getKey();

        if (data == node.key) {
            System.out.println("Node already exists");
            return;
        }
        if (data < currKey) {
            if (node.getLeft() != null) {
                put(data, node.left);
            } else {
                BinaryNode tmpNode = new BinaryNode(data);
                node.setLeft(tmpNode);
            }
        } else if (data > currKey) {
            if (node.getRight() != null) {
                put(data, node.getRight());
            } else {
                BinaryNode tmpNode = new BinaryNode(data);
                node.setRight(tmpNode);
            }
        }
    }

    /*
        Time complexity :
                O(H)     : Height of the tree
                O(log n) : Best case for a balanced binary tree
                O(n)     : For a left skewed binary tree

        Space complexity :
                 O(H)     : Auxiliary space for recursion (Auxiliary space for recursion )
                 O(log n) : Best case for a balanced binary tree
                 O(n)     : For left skewed binary tree
     */
    public int getMinKey() {
        if (root == null) {
            System.out.println("Tree is empty ....");
            return -1;
        }
        return getMinKey(root);
    }

    private int getMinKey(BinaryNode node) {

        if (node == null) {
            System.out.println("Illegal state..");
            return -1;
        }

        if (node.getLeft() == null) {
            return node.key;
        }
        // All previous recursive calls simply return that final value as they unwind.
        return getMinKey(node.getLeft());
    }


        /*
        Time complexity :
                O(H)     : Height of the tree
                O(log n) : Best case for a balanced binary tree
                O(n)     : For a right skewed binary tree

        Space complexity :
                 O(H)     : Auxiliary space for recursion (Auxiliary space for recursion)
                 O(log n) : Best case for a balanced binary tree
                 O(n)     : For right skewed binary tree
     */

    public int getMaxKey() {
        if (root == null) {
            System.out.println("Tree is empty..");
            return -1;
        }
        return getMaxKey(root);
    }

    private int getMaxKey(BinaryNode node) {
        while (node.getRight() != null) {
           node = node.getRight();
        }
        return node.key;
    }

    /*
        Level Order Traversal : Print order with Breadth First i.e printing all elements in reach tree height
     */
    public void printBFS() {
        if (root == null) {
            System.out.println("Tree is empty ....");
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.println();
        System.out.println("Level Order: ");

        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.poll();
            System.out.print(currentNode.getKey() + " ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
        System.out.println();

    }

    /*
        Inorder : Left => Root => Right
     */
    public void printInOrder() {

        if (root == null) {
            System.out.println("Tree is empty ...");
            return;
        }
        System.out.println();
        System.out.println("InOrder: ");
        printInOrder(root);
        System.out.println();

    }

    private void printInOrder(BinaryNode root) {

        if (root == null) {
            return;
        }
        printInOrder(root.getLeft());
        System.out.print(root.getKey() + " ");
        printInOrder(root.getRight());
    }


    public static void printPreOrder(BinaryNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getKey() + " ");
        printPreOrder(root.getLeft());
        printPreOrder(root.getRight());
    }

    /*
        Time Complexity :
            O(n) : Linear to the number of nodes

        Space Complexity :
            O(n) : Tree is left /right skewed
            O(log n) : Best case for a Balanced Binary Tree

     */
    public int nodeCount() {
        if (root == null) {
            System.out.println("Binary Tree is empty");
            return -1;
        }

        return nodeCount(root);
    }

    private int nodeCount(BinaryNode node) {

        if (node == null) {
            return 0;
        }
        int leftCount = nodeCount(node.getLeft());
        int rightCount = nodeCount(node.getRight());

        return leftCount + rightCount + 1;
    }

    /*
        Time complexity : O(n) : Iterate all nodes to calculate sum

        Space complexity :
                O(n)    : Worst case when the tree is skewed
                O(log n): Best case for a balanced binary tree
     */
    public int sumOfAllNodes() {
        if (root == null) {
            System.out.println("Binary Tree is empty");
            return -1;
        }

        return sumOfAllNodes(root);
    }
    private int sumOfAllNodes(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        int leftTreeSum = sumOfAllNodes(node.getLeft());
        int rightTreeSum = sumOfAllNodes(node.getRight());

        return leftTreeSum + rightTreeSum + node.getKey();
    }


    /*
        Time Complexity :
                          O(n)  : Every node is processed exactly once.

        Space Complexity :
                Auxiliary space : O(h) : Extra space required for recursion stack
                                Worst case : O(n) : If the Binary tree is skewed
                                Best case : O(log n) : Tree is balanced
   */
    public int getTreeHeight() {
        if (root == null) {
            System.out.println("Tree is empty ...");
            return -1;
        }

        return getTreeHeight(root);
    }

    private int getTreeHeight(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        int leftTreeHeight = getTreeHeight(node.getLeft());
        int rightTreeHeight = getTreeHeight(node.getRight());
        return Math.max(leftTreeHeight + 1, rightTreeHeight + 1);
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }
}
