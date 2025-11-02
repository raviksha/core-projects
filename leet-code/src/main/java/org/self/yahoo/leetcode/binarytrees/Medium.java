package org.self.yahoo.leetcode.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class Medium {

    private static void printBFS(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        System.out.println("Print level order....");
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(rootNode);
        System.out.println();

        while (!treeNodeQueue.isEmpty()) {
            TreeNode currentNode = treeNodeQueue.poll();
            System.out.print(currentNode.val + " ");

            if (currentNode.left != null) {
                treeNodeQueue.add(currentNode.left);
            }

            if (currentNode.right != null) {
                treeNodeQueue.add(currentNode.right);
            }
        }
        System.out.println();

    }

    private static TreeNode testConvertSortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = testConvertSortedListToBST(head);
        root.right = testConvertSortedListToBST(slow.next);

        return root;
    }

    public static void main(String[] args) {
        System.out.println("Binary Tree .. Medium ....");

        // Leet code 109. Convert Sorted List to Binary Search Tree
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);

        /*
            Using recursion approach

            Time complexity: O(n log n)
                             List traversal to find the middle node : O(n)
                             For each traversal the list is divided into 2 halves: O(log n) level of recursions
                             Conclusion: Total time complexity: O(n log n)
            Space complexity: O(log n) : Recursion stack for a list of n elements
         */
        TreeNode rootNode = testConvertSortedListToBST(head);
        System.out.println("testConvertSortedListToBST: ");
        printBFS(rootNode);

        // Leet code 109.1 Convert Sorted Array to Binary Search Tree
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};

        int low = 0;
        int high = nums.length - 1;

        /*
            Using a recursion approach

            Time complexity: O(n) : Each element is visited only once

            Space complexity: O(log n): Recursion stack.
                              The space complexity is not O(n log n), as compared to testConvertSortedListToBST()
                              coz here we are avoiding the extra traversal on over n elements each time while computing the
                              middle of the Sorted link list
         */
        rootNode = testConvertSortedArrayToBST(nums, low, high);
        System.out.println("testConvertSortedArrayToBST: ");
        printBFS(rootNode);

    }

    private static TreeNode testConvertSortedArrayToBST(int[] nums, int low, int high) {

        if (low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode rootNode = new TreeNode(nums[mid]);
        rootNode.left = testConvertSortedArrayToBST(nums, low, mid - 1);
        rootNode.right = testConvertSortedArrayToBST(nums, mid + 1, high);

        return rootNode;
    }
}
