package org.self.yahoo.leetcode75.traversal.preorder;

import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Binary Tree traversal ..... Pre Order ...  Medium");
        // Leet code 144. Binary Tree Preorder Traversal
        /*
            Pre Order Traversal: Pre Order tree traversal = Node => Left = Right
            Pre Order is used for use cases when a parent needs to be processed first before its child nodes
            Real world example: Directory listing

            Time complexity: O(n): Each nodes of the Binary Tree is visited only once

            Space complexity: O(n):
                                   Result list contains the node values of all the n nodes of the binary tree
                                   Recursion stack: Best case: O(log n): Balances Binary tree
                                                    Worst case: O(n): Skewed Binary tree
         */
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = testBinaryTreePreOrderTraversal(root);
        System.out.println("testBinaryTreePreOrderTraversal: " + result);
    }

    private static List<Integer> testBinaryTreePreOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return testBinaryTreePreOrderTraversal(root, result);
    }

    private static List<Integer> testBinaryTreePreOrderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return result;
        }
        result.add(node.val);
        testBinaryTreePreOrderTraversal(node.left, result);
        testBinaryTreePreOrderTraversal(node.right, result);
        return result;
    }
}



//class Solution {
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> treeL = new ArrayList<>();
//
//        if (root == null) {
//            return treeL;
//        }
//
//        return preorderTraversal(root, treeL);
//
//    }
//
//    private List<Integer> preorderTraversal(TreeNode root, List<Integer> list) {
//
//        if (root == null) {
//            return list;
//        }
//
//        list.add(root.val);
//        preorderTraversal(root.left, list);
//        preorderTraversal(root.right, list);
//        return list;
//    }
//}