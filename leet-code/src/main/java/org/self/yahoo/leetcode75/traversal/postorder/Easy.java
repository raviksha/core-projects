package org.self.yahoo.leetcode75.traversal.postorder;

import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Easy {
    /*
         Post order traversal is used in systems like
                      => package dependency resolution
                      => Garbage collection reference counting
     */

    public static void main(String[] args) {
        System.out.println("Post order traversal......Easy...");
        // Leet code 145. Binary Tree Postorder Traversal
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        /*
            Time complexity: O(n) Traverses each node of the Binary tree once during post order traversal

            Space complexity: O(n)
                         Result list: O(n): Result list storing n elements of the tree in post order
                         Recursion stack: O(n): Skewed binary tree
                                          O(log n): Balances binary tree
         */
        List<Integer> result = testBTPostOrderTraversal(root);
        System.out.println("testBTPostOrderTraversal: " + result);
    }

    private static List<Integer> testBTPostOrderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return testBTPostOrderTraversal(root, list);
    }

    private static List<Integer> testBTPostOrderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return list;
        }
        testBTPostOrderTraversal(node.left, list);
        testBTPostOrderTraversal(node.right, list);
        list.add(node.val);

        return list;
    }
}