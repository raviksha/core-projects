package org.self.yahoo.leetcode75.traversal.inorder;

import com.sun.source.tree.Tree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    private static List<Integer> testBinaryTreeInOrderTraversalV1(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        return inorderTraversal(root, result);

    }


    private static List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null) {
            return list;
        }

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
        return list;
    }


    private static List<Integer> testBinaryTreeInOrderTraversalV2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;


        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);

            current = current.right;

        }

        return result;
    }

    private static boolean testValidateSearchTreeV1(TreeNode node, Stack<Integer> stack) {
        if (node == null) {
            return true;
        }
        if (!testValidateSearchTreeV1(node.left, stack)) {
            return false;
        }
        int val = node.val;
        int prevVal = (stack.isEmpty()) ? Integer.MIN_VALUE : stack.peek();

        if (val <= prevVal) {
            return false;
        }
        stack.push(val);
        return testValidateSearchTreeV1(node.right, stack);
    }

    private static boolean testValidateSearchTreeV1(TreeNode root) {
        Stack<Integer> stack = new Stack<>();
        return testValidateSearchTreeV1(root, stack);
    }

    private static boolean testValidateSearchTreeV2(TreeNode root) {
        List<Integer> bTreeList = new ArrayList<>();
        testValidateSearchTreeV2(root, bTreeList);
        int prev = Integer.MIN_VALUE; // This condition will fail when the tree first node value is  Integer.MIN_VALUE itself : -2147483648
        for (int item : bTreeList) {
            if (prev >= item) {
                return false;
            }
            prev = item;
        }
        return true;
    }

    private static boolean testValidateSearchTreeV21(TreeNode root) {
        List<Integer> bTreeList = new ArrayList<>();
        testValidateSearchTreeV2(root, bTreeList);
        int prev = bTreeList.get(0);
        for (int i = 1; i < bTreeList.size(); i++) {
            int item = bTreeList.get(i);
            if (prev > item) {
                return false;
            }
            prev = item;
        }
        return true;
    }

    private static void testValidateSearchTreeV2(TreeNode node, List<Integer> integerList) {
        if (node == null) {
            return;
        }
        testValidateSearchTreeV2(node.left, integerList);
        integerList.add(node.val);
        testValidateSearchTreeV2(node.right, integerList);
    }


    public static void main(String[] args) {
        System.out.println("In order traversal ... Medium");
        // Leet code 94. Binary Tree Inorder Traversal
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);

        /*
            Time complexity: O(n): Traverses through each node of the Binary tree

            Space complexity: O(n)
                        result list: O(n): Extra space required to store all the tree nodes via Inorder traversal
                        Recursion stack depth: O(log n): Balanced binary tree
                                         O(n): Skewed binary tree

         */

        List<Integer> result = testBinaryTreeInOrderTraversalV1(root);
        System.out.println("testBinaryTreeInOrderTraversalV1: " + result);

        /*
            Time complexity: O(n) Traverses through each node of the Binary tree

            Space complexity: O(n)
                             result list: O(n): Extra space required to store all the tree nodes via Inorder traversal
                             Extra memory for Stack : O(log n): Balanced binary tree
                             O(n): Skewed binary tree

         */

        result = testBinaryTreeInOrderTraversalV2(root);
        System.out.println("testBinaryTreeInOrderTraversalV2: " + result);

        // Leet code 98. Validate Binary Search Tree
        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        /*
            Time complexity: O(n): Traverses each node of the tree only once

            Space complexity: O(log n ) : Recursion stack depth for a balanced binary tree
                              O(n): Recursion stack depth for a skewed binary tree
         */
        boolean isValidBST = testValidateSearchTreeV1(root);
        System.out.println("testValidateSearchTreeV1: " + isValidBST);

        // Fails for : -2147483648 as the first root node value
        isValidBST = testValidateSearchTreeV2(root);
        System.out.println("testValidateSearchTreeV2: " + isValidBST);

        isValidBST = testValidateSearchTreeV21(root);
        System.out.println("testValidateSearchTreeV21: " + isValidBST);

        // Leet code 230. Kth Smallest Element in a BST
        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(2);
        int k = 1;

        /*
            Time complexity: O(n) Does an in order traversal of the whole Binary tree nodes

            Space complexity:
                            Recursion stack depth,
                            O(log n): When tree is balanced
                            O(n): Skewed binary tree
         */
        int kthElement = testKthSmallestElement(root, k);
        System.out.println("testKthSmallestElement: " + kthElement);

    }
    static int count = 0;
    static int result = -1;
    private static int testKthSmallestElement(TreeNode node, int k) {
        if (node == null) {
            return -1;
        }
        testKthSmallestElement(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
        }

        testKthSmallestElement(node.right, k);
        return result;

    }


}

//
//class Solution {
//    int count = 0;
//    int result = 0;
//
//    public int kthSmallest(TreeNode root, int k) {
//
//        kthSmallestElementInBSTV2(root, k);
//        return result;
//
//    }
//
//    private void kthSmallestElementInBSTV2(TreeNode node, int k) {
//        if (node == null) {
//            return;
//        }
//        kthSmallestElementInBSTV2(node.left, k);
//        count++;
//        if (count == k) {
//            result = node.val;
//        }
//        kthSmallestElementInBSTV2(node.right, k);
//    }
//
//}