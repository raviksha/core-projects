package org.self.yahoo.leetcode75.traversal.levelorder;

import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

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
    private static List<List<Integer>> testBinaryTreeLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ctr = 0;
        int size = queue.size();

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();

            while (ctr < size) {
                TreeNode treeNode = queue.poll();

                if (treeNode != null) {
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }

                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }
                ctr++;
            }
            result.add(list);
            ctr = 0;
            size = queue.size();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Binary Tree Level Order traversal...... Medium...");
        // Leet code 102. Binary Tree Level Order Traversal
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(20);
        root.left = new TreeNode(9);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        /*
            Time complexity: O(n)
                             Traverses each node of the Binary Tree only once

            Space complexity:O(n) Result list stores a max of n nodes from the Binary tree

                   s/c is NOT  O(h * n) it should just be O(n) since height h doesn’t multiply with n.)
                                h: height of tree
                                n: nodes at each level
         */
        List<List<Integer>> result = testBinaryTreeLevelOrderTraversal(root);
        System.out.println("testBinaryTreeLevelOrderTraversal: ");
        for (List<Integer> item : result) {
            System.out.println(item);
        }

        // Leet code 199. Binary Tree Right Side View
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        /*
            Time complexity: O(n) Traverses each node of the Binary Tree only once

            Space complexity:
                            Max number of nodes the Queue will hold in itself during thr Binary Tree traversal
                                                    Vs
                            Max number of elements in the result List, one per level

                            Worst case: O(n): When all the nodes are present at a single: Queue
                            Best case: O(h) Height of the tree h = max number of right view nodes: Result List
         */
        List<Integer> resp = testBTRightSideView(root);
        System.out.println("testBTRightSideView: " + resp);
    }

    private static List<Integer> testBTRightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int ctr = 0;
        int size = nodeQueue.size();;

        while (!nodeQueue.isEmpty()) {
            TreeNode tmpNode = null;
            while (ctr < size) {
                tmpNode = nodeQueue.poll();

                if (tmpNode.left != null) {
                    nodeQueue.add(tmpNode.left);
                }

                if (tmpNode.right != null) {
                    nodeQueue.add(tmpNode.right);
                }
                ctr++;
            }
            ctr = 0;
            size = nodeQueue.size();
            result.add(tmpNode.val);
        }
        return result;
    }


}

//class Solution {
//    public List<Integer> rightSideView(TreeNode root) {
//
//        List<Integer> result = new ArrayList<>();
//
//        if (root == null) {
//            return result;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            int queueS = queue.size();
//            for (int i = 0; i < queueS; i++) {
//                TreeNode currNode = queue.poll();
//
//
//                if (currNode.left != null) {
//                    queue.add(currNode.left);
//                }
//
//                if (currNode.right != null) {
//                    queue.add(currNode.right);
//                }
//
//                if (i == (queueS - 1)) {
//                    result.add(currNode.val);
//                }
//
//
//            }
//        }
//
//        return result;
//
//    }
//}