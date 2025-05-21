package org.self.yahoo.leetcode.binarytrees;


import org.self.yahoo.book.demo.chap3.binaryTree.worksheet2.BinaryNode;
import org.self.yahoo.book.demo.chap3.binaryTree.worksheet2.BinaryT;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    TreeNode root;

    public boolean get(int key) {
        if (root == null) {
            return false;
        }
        return get(key, root);

    }

    public TreeNode getRoot() {
        return root;
    }

    /*
        Time complexity: O(log n) n is the height of the tree
                         O(n): Worst case when the tree is skewed

        Space complexity: O(log n): Recursion stack
                          O(n): Worst case when the tree is skewed

     */
    private boolean get(int key, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (key == node.val) {
            return true;
        }
        if (key < node.val) {
            return get(key, node.left);
        } else {
            return get(key, node.right);
        }

    }

    public void put(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return;
        }
        put(data, root);
    }

    /*
        Time complexity: O(log n) n is the height of the tree
                         O(n): Worst case when the tree is skewed

        Space complexity: O(log n): Recursion stack
                          O(n): Worst case when the tree is skewed
 */
    private void put(int data, TreeNode node) {
        if (data == node.val) {
            System.out.println("Data already exists...");
            return;
        }

        if (data < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(data);
            } else {
                put(data, node.left);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(data);
            } else {
                put(data, node.right);
            }
        }
    }


    public int getMinKey() {
        if (root == null) {
            return -1;
        }
        return getMinKey(root);
    }

    /*
        Time complexity: O(log n) n is the height of the tree
                         O(n): Worst case when the tree is skewed

        Space complexity: O(log n): Recursion stack
                          O(n): Worst case when the tree is skewed
   */
    private int getMinKey(TreeNode node) {
        if (node.left == null) {
            return node.val;
        }
        return getMinKey(node.left);
    }



    public int getMaxKey() {
        if (root == null) {
            return -1;
        }
        return getMaxKey(root);

    }

    /*
        Time complexity: O(log n) n is the height of the tree
                 O(n): Worst case when the tree is skewed

        Space complexity: O(log n): Recursion stack
                          O(n): Worst case when the tree is skewed
     */

    private int getMaxKey(TreeNode node) {
        if (node.right == null) {
            return node.val;
        }
        return getMaxKey(node.right);
    }

    /*
        Level Order Traversal : Print order with Breadth First i.e printing all elements in each tree height


        Time complexity: O(n): Traverses and prints each element of the tree

        Space complexity: O(w): Width of the tree. w elements stored in the Queue

     */
    public void printBFS() {
        if (root == null) {
            System.out.println("Empty BST..");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.println();
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            System.out.print(currentNode.val + " ");

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        System.out.println();
    }

    /*
        Inorder : Left => Root => Right
     */
        /*
        Time complexity: O(n)

        Space complexity: O(1)
     */
    public void printInOrder() {

        if (root == null) {
            System.out.println("Empty BST...");
            return;
        }

        printInOrder(root);
        System.out.println();

    }
    /*
        Time complexity: O(n): Iterates each elements of the tree once

        Space complexity: O(h): Height df the tree
 */
    private void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }


        /*
        Time complexity: O(n): Iterates each elements of the tree once

        Space complexity: O(h): Height df the tree
     */

    public static void printPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }


    public int nodeCount() {
        if (root == null) {
            return 0;
        }
        return nodeCount(root);
    }

    /*
        Time complexity: O(n): Iterates each elements of the tree once

        Space complexity: O(h): Height df the tree
   */

    private int nodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftCount = nodeCount(node.left);
        int rightCount = nodeCount(node.right);
        return leftCount + rightCount + 1;
    }


    /*
        Time complexity: O(n): Iterates each elements of the tree once

        Space complexity: O(h): Height df the tree
                          O(n): Worst case when the tree is skewed
     */


    public int sumOfAllNodes() {
        if (root == null) {
            return 0;
        }
        return sumOfAllNodes(root);
    }

    /*
    Time complexity: O(n): Iterates each elements of the tree once

    Space complexity: O(h): Height df the tree
                      O(n): Worst case when the tree is skewed
 */
    private int sumOfAllNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSum = sumOfAllNodes(node.left);
        int rightSUm = sumOfAllNodes(node.right);
        return leftSum + rightSUm + node.val;

    }


    /*
        Time complexity: O(n): Iterates each elements of the tree once

        Space complexity: O(h): Height df the tree
                          O(n): Worst case when the tree is skewed
     */
    public int getTreeHeight() {
        if (root == null) {
            return 0;
        }
        return getTreeHeight(root);
    }

    private int getTreeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getTreeHeight(node.left);
        int rightHeight = getTreeHeight(node.right);
        return Math.max(leftHeight + 1, rightHeight + 1);
    }

    public void setRoot(TreeNode root) {
        this.root = root;

    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(45);
        binaryTree.put(36);
        binaryTree.put(67);
        binaryTree.put(22);
        binaryTree.put(41);
        binaryTree.put(75);
        binaryTree.put(66);

                /*
                45
              /    \
             36     67
            / \     / \
           22  41  66  75

         */


        binaryTree.printBFS();     // LevelOrder : [ 45 36 67 22 41 66 75  ]
        binaryTree.printInOrder(); // InOrder    : [ 22 36 41 45 66 67 75 ]

        // Get Min
        int minElement = binaryTree.getMinKey();
        System.out.println("minElement: " + minElement);

        // Get Max
        int maxElement = binaryTree.getMaxKey();
        System.out.println("maxElement: " + maxElement);

        // Check if value exists
        boolean isPresent = binaryTree.get(66);
        System.out.println("isPresent: " + isPresent);

        // Node count
        int numberOfNodes = binaryTree.nodeCount();
        System.out.println("numberOfNodes: " + numberOfNodes);

        // Sum of all Nodes
        int sumOfNodes = binaryTree.sumOfAllNodes();
        System.out.println("sumOfNodes: " + sumOfNodes);
        System.out.println(sumOfNodes == (22 + 36 + 41 + 45 + 66 + 67 + 75));

        // Get Tree height

        int treeHeight = binaryTree.getTreeHeight();
        System.out.println("treeHeight: " + treeHeight);


        // Build a BST Tree from Array

        int [] arr = {5, 6, 1, 4, 3, 7, 2};
        binaryTree = new BinaryTree();
        for (int i = 0; i < arr.length; i++) {
            binaryTree.put(arr[i]);
        }

        /*
                5
              /    \
             1      6
            / \     / \
               4       7
              / \
             3
            / \
           2

         */
        binaryTree.printBFS();     // LevelOrder : [5 1 6 4 7 3 2  ]
        binaryTree.printInOrder(); // InOrder    : [ 1 2 3 4 5 6 7  ]
    }


}
