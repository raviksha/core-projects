package org.self.yahoo.book.demo.chap3.binaryTree.worksheet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SimpleBinaryTree implements BinaryTree {

    BinaryTreeNode rootNode;

    @Override
    public String get(int key) {
        if (rootNode == null) {
            return null;
        } else {
            return get(key, rootNode);
        }
    }

    private String get(int key, BinaryTreeNode node) {
        int currKey = node.getKey();

        if (currKey == node.getKey()) {
            return node.getValue();
        } else if (currKey < key) {
            return get(key, node.getLeft());
        } else {
            return get(key, node.getRight());
        }
    }

    @Override
    public void put(int key, String value) {
        if (rootNode == null) {
            rootNode = new BinaryTreeNode(key, value);
        } else {
            put(key, value, rootNode);
        }
    }

    private void put(int key, String value, BinaryTreeNode rootNode) {
        int currKey = rootNode.getKey();
        if (key == currKey) {
            rootNode.setKey(key);
            rootNode.setValue(value);
        } else if (key < currKey) {
            if (rootNode.getLeft() != null) {
                put(key, value, rootNode.getLeft());
            } else {
                rootNode.setLeft(new BinaryTreeNode(key, value));
            }

        } else {
            if (rootNode.getRight() != null) {
                put(key, value, rootNode.getRight());
            } else {
                rootNode.setRight(new BinaryTreeNode(key, value));
            }

        }
    }

    public int getMinKey() {
        if (rootNode == null) {
            System.out.println("Tree is empty..");
            System.exit(-1);
        } else {
            return getMinKey(rootNode);
        }
        return -1;
    }

    private int getMinKey(BinaryTreeNode node) {

        while (node.getLeft() != null) {
            getMinKey(node.getLeft());
        }
        return node.getKey();
    }

    public int getMaxKey() {
        if (rootNode == null) {
            System.out.println("Tree is empty..");
            System.exit(-1);
        } else {
            return getMaxKey(rootNode);
        }
        return -1;
    }

    private int getMaxKey(BinaryTreeNode node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node.getKey();
    }

    /*
        Breadth-First Search (BFS) traversal, also known as Level Order Traversal, for a Binary Tree.

                   10
                  /  \
                 5    15
                / \   / \
               2   7 12  20

               Output :
               10 5 15 2 7 12 20
     */

    public void printBfs() {
        if (rootNode == null) {
            return;
        }
        System.out.print("Level Order...");
        Queue<BinaryTreeNode> binaryTreeNodeQueue = new LinkedList<>();
        binaryTreeNodeQueue.add(rootNode);
        System.out.println();
        while (!binaryTreeNodeQueue.isEmpty()) {
            BinaryTreeNode currentNode = binaryTreeNodeQueue.poll();  // Removes element from Queue
            System.out.print(currentNode.getKey() + " ");

            if (currentNode.getLeft() != null) {
                binaryTreeNodeQueue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                binaryTreeNodeQueue.add(currentNode.getRight());
            }
        }
        System.out.println();

    }



    /*
               10
              /  \
             5    15
            / \   / \
           2   7 12  20

     */

    /*
        INORDER : Left => Root => Right
     */
    private void printInOrder() {

        if (rootNode == null) {
            System.out.println("Tree is empty..");
            return;
        }
        System.out.println();
        System.out.println("In Order...");
        printInOrder(rootNode);
        System.out.println();
    }

    private void printInOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.getLeft());
        System.out.print(node.getKey() + " ");
        printInOrder(node.getRight());
    }

    /*
        PREORDER : Root => Left => Right

               45
              /  \
             36    67
            / \    / \
           22  41    75
     */
    private void printPreOrder() {
        if (rootNode == null) {
            System.out.println("Tree is empty...");
            return;
        }
        System.out.println();
        System.out.println("Pre Order...");
        printPreOrder(rootNode);
        System.out.println();
    }

    private void printPreOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getKey() + " ");
        printPreOrder(node.getLeft());
        printInOrder(node.getRight());
    }

    /*
         POSTORDER : left => Right => Root
     */
    private void printPostOrder() {

        if (rootNode == null) {
            System.out.println("Tree is empty ...");
        }
        System.out.println();
        System.out.println("Post Order ...");
        printPostOrder(rootNode);
        System.out.println();

    }

    private void printPostOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.getLeft());
        printPostOrder(node.getRight());
        System.out.print(node.getKey() + " ");
    }

    /*
        Output : 45 36 22 41 67 75
        Push its right child first (so left child is processed first).

        Time Complexity :  O(n)
        Space Complexity : O(n) : Worst case : Skewed tree
                           O(h) : Best case :  Height of the tree
     */
    private void dfsUsingStack() {
        if (rootNode == null) {
            System.out.println("Tree is empty..");
            return;
        }
        System.out.println("DFS using stack ...");
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(rootNode);

        while (!stack.isEmpty()) {
            BinaryTreeNode currentNode = stack.pop();
            System.out.print(currentNode.getKey() + " ");

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }


        }
    }


    public static void main(String[] args) {
        SimpleBinaryTree binaryTree = new SimpleBinaryTree();

        /* ********** Breadth First Algo **************** */

        /*
               45
              /  \
             36    67
            / \    / \
           22  41    75

         */
        binaryTree.put(45, "Isabel");
        binaryTree.put(36, "John");
        binaryTree.put(67, "Ruth");
        binaryTree.put(22, "Sarah");
        binaryTree.put(41, "Peter");
        binaryTree.put(75, "Tom");

        /*
            [ 45 36 67 22 41 75 ]
            LEVEL_ORDER : BFS
         */
        binaryTree = new SimpleBinaryTree();
        binaryTree.put(60, "Isabel");
        binaryTree.put(10, "John");
        binaryTree.put(50, "Ruth");
        binaryTree.put(20, "Sarah");
        binaryTree.put(5, "Peter");
        binaryTree.printBfs();


        /* ********** Depth First Algo **************** */

        /*
               45
              /  \
             36    67
            / \    / \
           22  41    75

         */

        /*
            POSTORDER : left => Right => Root
            22 41 36 75 67 45
         */
        binaryTree.printPostOrder();

        /*
             PREORDER : Root => Left => Right
            45 36 22 41 67 75
         */
        binaryTree.printPreOrder();

        /*
            INORDER : Left => Root => Right
            22 36 41 45 67 75
         */
        binaryTree.printInOrder();

        /*

         */
        binaryTree.dfsUsingStack();


    }

    // TODO : Implement DFS non recursive using Stack
    // TODO: Construct a Binary Tree from an Array
    // TODO : Construct a Binary Tree from the Pre Order | Post Order | In Order


//    public void leftRotate(BinaryTreeNode nodeX, BinaryTreeNode parent) {
//        BinaryTreeNode nodeY = nodeX.getRight();
//        if (nodeY == null) return; // No right child, cannot rotate
//
//        nodeX.setRight(nodeY.getLeft());
//        nodeY.setLeft(nodeX);
//
//        if (parent == null) {
//            rootNode = nodeY; // Update root if rotating the root node
//        } else if (parent.getLeft() == nodeX) {
//            parent.setLeft(nodeY);
//        } else {
//            parent.setRight(nodeY);
//        }
//    }
//
//    public void rightRotate(BinaryTreeNode nodeX, BinaryTreeNode parent) {
//        BinaryTreeNode nodeY = nodeX.getLeft();
//        if (nodeY == null) return; // No left child, cannot rotate
//
//        nodeX.setLeft(nodeY.getRight();
//        nodeY.setRight(nodeX);
//
//        if (parent == null) {
//            rootNode = nodeY; // Update root if rotating the root node
//        } else if (parent.getRight() == nodeX) {
//            parent.setRight(nodeY);
//        } else {
//            parent.setLeft(nodeY);
//        }
//    }


}
