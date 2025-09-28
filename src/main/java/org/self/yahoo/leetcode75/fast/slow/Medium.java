package org.self.yahoo.leetcode75.fast.slow;

import org.self.yahoo.leetcode.linkedList.ListNode;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Fast and slow pointer .... Medium");

        // Leet code 142. Linked List Cycle II
        // [3,2,0,-4]
        ListNode head = new ListNode(3);
        ListNode child1 = new ListNode(2);
        head.next = child1;
        child1.next = new ListNode(0);
        child1.next.next = new ListNode(-4);
        child1.next.next.next = child1;

        /*
            Time complexity: O(n + n) Worst case will traverse the link list 2 times
                             Concluded: O(n)

            Space complexity: O(1): No extra storage/memory used.
         */
        ListNode result = testLinkedListCycle(head);
        System.out.println("testLinkedListCycle: " + result.val);

    }

    private static ListNode testLinkedListCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                fast = head;

                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}


//public class Solution {
//    public ListNode detectCycle(ListNode head) {
//
//        if (head == null || head.next == null) {
//            return null;
//        }
//        ListNode slowPointer = head;
//        ListNode fastPointer = head;
//
//        while (fastPointer.next != null && fastPointer.next.next != null) {
//            slowPointer = slowPointer.next;
//            fastPointer = fastPointer.next.next;
//
//            if (fastPointer == slowPointer) {
//                fastPointer = head;
//
//                while(fastPointer != slowPointer) {
//                    fastPointer = fastPointer.next;
//                    slowPointer = slowPointer.next;
//                }
//                return slowPointer;
//            }
//        }
//
//        return null;
//    }
//}
