package org.self.yahoo.leetcode.treetraversal;

import org.self.yahoo.leetcode.binarytrees.BinaryTree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Medium {

    private static List<List<Integer>> testBinaryTreeLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            List<Integer> listL = new ArrayList<>();

            for (int i = 0; i < queueSize; i++) {
                TreeNode currNode = queue.poll();
                listL.add(currNode.val);
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            if (!listL.isEmpty()) {
                result.add(listL);
            }

        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Medium:  + Tree Traversal - Level Order");

        // Leet code 102. Binary Tree Level Order Traversal
        // [3,9,20,null,null,15,7]
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(3);
        TreeNode root = binaryTree.getRoot();
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Print BFS testBinaryTreeLevelOrderTraversal: ");
        BinaryTree.printBFS(root);

        /*
            Approach : Using a queue for Level order traversing

            Time complexity: O(n): Each node in the BST tree is traversed once
                             Queue add() and poll() : O(1) constant time
                             Final : O(n)

            Space complexity: O(n)
                              Queue: At worst case a queue can store a max of n elements where n = w = width of the tree
                              Result list contains one list per level, max of n nodes
                              Total time complexity: O(n)
         */
        var result = testBinaryTreeLevelOrderTraversal(root);
        System.out.println("testBinaryTreeLevelOrderTraversal: " + result);

        // Leet code 199. Binary Tree Right Side View
        BinaryTree.initRoot();
        BinaryTree binaryTreeR = new BinaryTree();
        binaryTreeR.put(1);
        var rootR = binaryTreeR.getRoot();
        rootR.left = new TreeNode(2);
        rootR.right = new TreeNode(3);

        rootR.left.left = new TreeNode(4);
        rootR.left.left.left = new TreeNode(5);
        System.out.println("Print BFS testBinaryTreeRightSideView: ");
        BinaryTree.printBFS(rootR);

        /*
            Approach: Using a Queue for BFS

            Time complexity: O(n): Loops over all the n elements of the BST

            Space complexity: O(n): Worst case when the Tree is skewed
                              O(h): Avg case: Height of the tree
         */

        var resultR = testBinaryTreeRightSideView(rootR);
        System.out.println("testBinaryTreeRightSideView: " + resultR);

    }

    private static List<Integer> testBinaryTreeRightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int qS = queue.size();

            for (int i = 0; i < qS; i++) {
                TreeNode currNode = queue.poll();

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }

                if (i == (qS - 1)) {
                    result.add(currNode.val);
                }
            }
        }
        return result;
    }


}
