package org.self.yahoo.leetcode.treetraversal;

import org.self.yahoo.leetcode.binarytrees.BinaryTree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Easy {

    private static List<Integer> testTreeTraversalPreOrder(TreeNode root, List<Integer> treeL) {

        if (root == null) {
            return treeL;
        }

        treeL.add(root.val);
        testTreeTraversalPreOrder(root.left, treeL);
        testTreeTraversalPreOrder(root.right, treeL);
        return treeL;
    }

    private static TreeNode testCovertSortedArrayToBST(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        int low = 0;
        int high = nums.length - 1;
        return testCovertSortedArrayToBST(nums, low, high);
    }

    private static TreeNode testCovertSortedArrayToBST(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = testCovertSortedArrayToBST(nums, low, mid -1);
        root.right = testCovertSortedArrayToBST(nums, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {
        System.out.println("Tree Traversal: Easy ...");
        // Leet code 144. Binary Tree Preorder Traversal
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(1);
        binaryTree.put(2);
        binaryTree.put(3);
        System.out.println("printBFS..");
        binaryTree.printBFS(binaryTree.getRoot());

        List<Integer> treeL = new ArrayList<>();

        /*
            Time complexity: O(n) Traverses through each node of the BTree

            Space complexity: O(n)
                             O(log n) For balanced BT
                             O(n) Worst case when the tree could be skewed
                             O(n) List containing n values for all n nodes
                             Dominating space complexity: O(n)
         */
        var preOrderL = testTreeTraversalPreOrder(binaryTree.getRoot(), treeL);
        System.out.println("testTreeTraversalPreOrder: " + preOrderL);

        // Leet code 108. Convert Sorted Array to Binary Search Tree

        int [] nums = new int[] {-10,-3,0,5,9};

        /*
            Time complexity: O(n) Loops over all the elements of the nums[]

            Space complexity: O(n)
                               O(n) n instances of TreeNode BST objects.
                                       When analyzing auxiliary space complexity, we typically focus on the temporary space used during the execution of the algorithm,
                                       not the space for the final output structure itself.

                               O(log n): Recursion call stack
                               NOTE : Worst case for skewed binary tree is not possible here as the BST generated is balanced
                               Conclusion: O(log n): Extra auxiliary space used
         */
        TreeNode root = testCovertSortedArrayToBST(nums);
        System.out.println("In Order: testCovertSortedArrayToBST: ");
        BinaryTree.printBFS(root);

        // Leet code 94. Binary Tree Inorder Traversal
        BinaryTree.initRoot();
        binaryTree = new BinaryTree();
        binaryTree.put(1);
        root = binaryTree.getRoot();
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = new ArrayList<>();
        /*
            Time complexity: O(n): Traverses each element of the BST
                             list.add(): O(1) constant time
                             Total: O(n)

            Space complexity: O(n)
                              O(n): n elements stored in the result list
                              O(h): Recursion stack for tree height h, worst case O(n) for skewed tree
                              Total: O(n)
         */
        testInOrderTraverse(binaryTree.getRoot(), result);
        System.out.println("testInOrderTraverse: " + result);

    }

    private static List<Integer> testInOrderTraverse(TreeNode root, List<Integer> list) {
        if (root == null) {
            return list;
        }

        testInOrderTraverse(root.left, list);
        list.add(root.val);
        testInOrderTraverse(root.right, list);

        return list;
    }


}
