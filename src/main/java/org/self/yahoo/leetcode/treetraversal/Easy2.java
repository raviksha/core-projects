package org.self.yahoo.leetcode.treetraversal;

import jdk.nashorn.api.tree.Tree;
import org.self.yahoo.leetcode.binarytrees.BinaryTree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Easy2 {

    private static List<Integer> testBinaryTreePostOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return list;
        }

        testBinaryTreePostOrderTraversal(node.left, list);
        testBinaryTreePostOrderTraversal(node.right, list);
        list.add(node.val);
        return list;
    }

    private static TreeNode testInvertBinaryTree(TreeNode node) {
        if (node == null) {
            return node;
        }

        TreeNode left = testInvertBinaryTree(node.left);
        TreeNode right = testInvertBinaryTree(node.right);

        node.left = right;
        node.right = left;

        return node;
    }

    public static void main(String[] args) {
        System.out.println("Easy2 Tree traversal post order ... ");

        // Leet code 145. Binary Tree Postorder Traversal
        BinaryTree.initRoot();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(1);
        TreeNode root = binaryTree.getRoot();
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = new ArrayList<>();

        /*
            Time complexity: O(n) : Traverses through each node of the tree from the root

            Space complexity: O(n)
                              Extra space to space to store n elements of the tree: O(n)
                              Recursion stack: O(h) h = height of the tree, worst case: O(n): skewed tree
                              Conclusive: O(n)
         */
        testBinaryTreePostOrderTraversal(root, result);
        System.out.println("testBinaryTreePostOrderTraversal: " + result);

        // Leet code 226. Invert Binary Tree
        BinaryTree.initRoot();
        binaryTree = new BinaryTree();
        binaryTree.put(2);
        root = binaryTree.getRoot();
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(1);

        /*
            Time complexity: O(n): Traverses through each node of the BTree from root

            Space complexity: O(h): Extra compute for the recursion stack depth, worst case: O(n) for skewed tree
                                    Best case: O(log n): when the tree is balanced
         */
        var invertedRoot = testInvertBinaryTree(root);
        System.out.println("testInvertBinaryTree: " + (invertedRoot.left.val == 3));

        // Leet code 543. Diameter of Binary Tree
        BinaryTree.initRoot();
        binaryTree = new BinaryTree();
        binaryTree.put(1);
        root = binaryTree.getRoot();
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);

        root.right.left.left = new TreeNode(4);
        root.right.left.right = new TreeNode(5);

        /*
            Time complexity: O(n): Traverses through each node of the BTree

            Space complexity: O(h): h = height of the Btree. Worst case O(n) when the tree is skewed
                              Best case: O(log n): when the tree is balanced
         */
        var treeDiameter = testDiameterOfBTree(root);
        System.out.println("testDiameterOfBTree: " + treeDiameter);
    }

    static int maxGlobalTreeDiameter = 0;
    private static int testDiameterOfBTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getTreeDiameter(root);
        return maxGlobalTreeDiameter;
    }

    private static int getTreeDiameter(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int leftHeight = getTreeDiameter(node.left);
        int rightHeight = getTreeDiameter(node.right);
        maxGlobalTreeDiameter = Math.max(maxGlobalTreeDiameter, leftHeight + rightHeight);
        return 1 + Math.max(leftHeight , rightHeight);
    }


}
