package org.self.yahoo.book.demo.chap3.binaryTree.worksheet2;

public class TestBinaryTrees {
    public static void main(String[] args) {
        BinaryT binaryTree = new BinaryT();

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

        int arr [] = {5, 6, 1, 4, 3, 7, 2};
        binaryTree = new BinaryT();
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
        binaryTree.printInOrder(); // InOrder    : [ 22 36 41 45 66 67 75 ]



    }
}
