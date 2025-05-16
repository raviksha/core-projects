package org.self.yahoo.leetcode.linkedList;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
    public static void printList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        ListNode currNode = head;
        System.out.println();
        while (currNode != null) {
            System.out.print(currNode.val);
            currNode = currNode.next;
            if (currNode != null) {
                System.out.print(" => ");
            }
        }
        System.out.println();
    }
}
