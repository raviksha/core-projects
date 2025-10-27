package org.self.yahoo.leetcode75.traversal.postorder;

import com.sun.source.tree.Tree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Post order traversal .... Medium....");

        /*
            In postorder traversal the root is printed at the last.
            Useful for dependency resolution, File system and Garbage collection
         */

        // Leet code 236. Lowest Common Ancestor of a Binary Tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);

        /*
            Approach: Using a Post Order traversal / DFS method

            Types of DFS traversal techniques:
                        => Post Order
                        => Pre Order
                        => In Order

            Time complexity: O(n): Traverses each node only once

            Space complexity:
                        Recursion stack
                            Skewed Tree : O(n)
                            Balanced Tree: O(log n): Height of the tree
         */
        TreeNode result = testLCABtree(root, p, q);
        System.out.println("testLCABtree: " + result.val);
    }

    private static TreeNode testLCABtree(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }

        if (node.val == p.val || node.val == q.val) {
            return node;
        }
        TreeNode leftLCA = testLCABtree(node.left, p, q);
        TreeNode rightLCA = testLCABtree(node.right, p, q);

        if (leftLCA != null && rightLCA != null) {
            return node;
        }

        if (leftLCA != null) {
            return leftLCA;
        }

        return rightLCA;
    }
}
