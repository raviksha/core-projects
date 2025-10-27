package org.self.yahoo.leetcode75.traversal.preorder;

import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*


        4
       / \
      2   6
     / \ / \
    1  3 5  7

    Pre Order : 4, 2, 1, 3, 6, 5, 7
    Post Order:  1, 3, 2, 5, 7, 6, 4
    InOrder:  1, 2, 3, 4, 5, 6, 7


 */



public class Medium {

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

    private static int testPathSumIIIV1(TreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int count = countPathFromNode(node, targetSum);
        count += testPathSumIIIV1(node.left, targetSum);
        count += testPathSumIIIV1(node.right, targetSum);
        return count;
    }

    private static int countPathFromNode(TreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.val == targetSum) {
            count += 1;
        }

        count += countPathFromNode(node.left, targetSum - node.val);
        count += countPathFromNode(node.right, targetSum - node.val);
        return count;
    }

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

        //  Leet code 437. Path Sum III
        root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);

        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        root.right.right = new TreeNode(11);

        int targetSum = 8;
        /*
            Approach: Naive DFS: Brute force approach

            Time complexity:
                        Best case:O(log n) For a balanced binary tree
                        Worst case:O(n ^ 2) Skewed binary tree when the iteration transforms into a Linked List

            Space complexity: O(h): Height of the tree
                                    For a skewed binary tree the height = n = number of nodes
         */
        int count = testPathSumIIIV1(root, targetSum);
        System.out.println("testPathSumIIIV1: " + count);
        /*
            Approach: Using a Prefix sum

            Time complexity: Single DFS traversal of the Binary tree: O(n)
                             All the HashMap operations: O(1)
                             Concluding t/c: O(n)

            Space complexity: h = height of the tree
                        Recursion stack: O(h)
                        PrefixMap: At most will contain one element per recursion level: O(h)
                        While traversing back, running total from the last recursion level is removed from the map

         */
        count = testPathSumIIIV2(root, targetSum);
        System.out.println("testPathSumIIIV2: " + count);
    }

    private static int testPathSumIIIV2(TreeNode node, int targetSum) {
        // Using a Prefix sum approach
        Map<Long, Integer> prefixMap = new HashMap<>();
        long runningSum = 0L;
        return testPathSumIIIV2(node, prefixMap, targetSum, runningSum);
    }

    private static int testPathSumIIIV2(TreeNode node, Map<Long, Integer> prefixMap, int targetSum, long runningSum) {
        if (node == null) {
            return 0;
        }
        runningSum = runningSum + node.val;
        int count = prefixMap.getOrDefault(runningSum - targetSum, 0);

        prefixMap.put(runningSum, prefixMap.getOrDefault(runningSum, 0) + 1);

        count += testPathSumIIIV2(node.left, prefixMap, targetSum, runningSum);
        count += testPathSumIIIV2(node.right, prefixMap, targetSum, runningSum);

        int updatedCount = prefixMap.get(runningSum) - 1;
        if (updatedCount == 0) {
            prefixMap.remove(runningSum);
        } else {
            prefixMap.put(runningSum, updatedCount);
        }
        return count;
    }


}


//
//class Solution {
//    public int pathSum(TreeNode root, int targetSum) {
//        // if (root == null) return 0;
//
//        Map<Long, Integer> freqMap = new HashMap<>();
//        freqMap.put(0L, 1);
//        return pathSum(root, targetSum, freqMap, 0L);
//
//
//    }
//
//    private int pathSum(TreeNode node, int targetSum, Map<Long, Integer> freqMap, long runningSum) {
//        if (node == null) {
//            return 0;
//        }
//
//        runningSum = runningSum + node.val;
//
//        int count = freqMap.getOrDefault(runningSum - targetSum, 0);
//
//        freqMap.put(runningSum, freqMap.getOrDefault(runningSum, 0) + 1);
//
//
//
//
//        int result = count +
//                pathSum(node.left, targetSum, freqMap, runningSum) +
//                pathSum(node.right, targetSum, freqMap, runningSum);
//
//
//        int updatedCount = freqMap.get(runningSum) - 1;
//        if (updatedCount == 0) {
//            freqMap.remove(runningSum);
//        } else {
//            freqMap.put(runningSum, updatedCount);
//        }
//
//        return result;
//    }
//
//
//
//
//
//}