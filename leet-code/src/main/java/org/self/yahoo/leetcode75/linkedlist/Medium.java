package org.self.yahoo.leetcode75.linkedlist;

import org.self.yahoo.leetcode.linkedList.ListNode;

public class Medium {

    private static ListNode testRemoveNthNodeFromEndV1(ListNode head, int n) {
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;

        ListNode currNode = head;
        ListNode nextNode = null;
        int size = 0;

        while (currNode != null) {
            currNode = currNode.next;
            size++;
        }

        // If size == n, remove the first node
        if (size == n) {
            ListNode tmpNode = dummyHead.next;
            dummyHead.next = tmpNode.next;
            tmpNode.next = null;
            return dummyHead.next;
        }

        int hops = size - n;
        int ctr = 1;

        currNode = head;
        nextNode = currNode.next;

        while (ctr < hops) {
            currNode = nextNode;
            nextNode = nextNode.next;
            ctr++;
        }

        currNode.next = nextNode.next;
        nextNode.next = null;

        return head;
    }

    private static ListNode testRemoveNthNodeFromEndV2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;

        ListNode slowPointer = dummyHead;
        ListNode fastPointer = dummyHead;

        int size = 0;

        while (size < n) {
            fastPointer = fastPointer.next;
            size++;
        }

        while (fastPointer.next != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }
        slowPointer.next = slowPointer.next.next;


        return dummyHead.next;

    }

    private static ListNode testSwapNodeInPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;

        ListNode prevNode = dummyHead;
        ListNode currNode = head;

        while (currNode != null && currNode.next != null) {
            ListNode nextNode = currNode.next;
            ListNode tmpNode = nextNode.next;

            nextNode.next = currNode;
            currNode.next = tmpNode;
            prevNode.next = nextNode;

            prevNode = currNode;
            currNode = tmpNode;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println("Linked list ...Medium...");
        // Leet code 19. Remove Nth Node From End of List
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int n = 2;
        /*
            Time complexity: O(n): Single pass, 2 times, over the length of the Linked list
                                   O(n): Calculate the size
                                   O(n): List traversal
                                   Final t/c: O(n)

            Space complexity: O(1) No extra auxiliary space required
         */

        ListNode result = testRemoveNthNodeFromEndV1(head, n);
        ListNode.printList(result);

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        /*
            Approach: Using fast and slow pointer approach

            Time complexity: O(n)
                             More effective as it traverses the list only once as compared to the testRemoveNthNodeFromEndV1()

            Space complexity: O(1) No extra auxiliary space required
         */
        result = testRemoveNthNodeFromEndV2(head, n);
        ListNode.printList(result);

        // Leet code 24. Swap Nodes in Pairs
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        /*
            Time complexity: O(n) Single pass over the linked list

            Space complexity: O(1) No extra compute space required
         */
        result = testSwapNodeInPairs(head);
        ListNode.printList(result);

        // Leet code 2. Add Two Numbers
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        /*
            Time complexity: O(n + m) Visits each node from l1 and l2 at most once

            Space complexity: O(max(n, m)) Extra space required to store the sum l1 + l2

            => Auxiliary space = Extra temporary space or memory required other than the input and output storage
         */
        result = testAddTwoNumbers(l1, l2);
        ListNode.printList(result);
    }

    private static ListNode testAddTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);

        ListNode l1CurrNode = l1;
        ListNode l2CurrNode = l2;

        ListNode currHead = dummyHead;
        int carryOver = 0;

        while (l1CurrNode != null && l2CurrNode != null) {

            int l1Val = l1CurrNode.val;
            int l2Val = l2CurrNode.val;

            int sum = l1Val + l2Val + carryOver;
            carryOver = sum / 10;
            int val = sum % 10;
            currHead.next = new ListNode(val);


            l1CurrNode = l1CurrNode.next;
            l2CurrNode = l2CurrNode.next;
            currHead = currHead.next;
        }

        while (l1CurrNode != null) {

            int l1Val = l1CurrNode.val;
            int sum = l1Val + carryOver;

            carryOver = sum / 10;
            int val = sum % 10;
            currHead.next = new ListNode(val);

            l1CurrNode = l1CurrNode.next;
            currHead = currHead.next;
        }

        while (l2CurrNode != null) {

            int l2Val = l2CurrNode.val;
            int sum = l2Val + carryOver;

            carryOver = sum / 10;
            int val = sum % 10;
            currHead.next = new ListNode(val);

            l2CurrNode = l2CurrNode.next;
            currHead = currHead.next;
        }

        if (carryOver > 0) {
            currHead.next = new ListNode(carryOver);
        }

        return dummyHead.next;
    }
}



//class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//
//        ListNode mergedList = new ListNode(-1);
//        int remainder = 0;
//
//        ListNode currNode1 = l1;
//        ListNode currNode2 = l2;
//        ListNode newHead = mergedList;
//
//        while (currNode1 != null && currNode2 != null) {
//            int sum = currNode1.val + currNode2.val + remainder;
//            int newVal = sum;
//            if (sum >= 10) {
//                newVal = sum % 10;
//            }
//            remainder = (sum - newVal) / 10;
//            ListNode newNode = new ListNode(newVal);
//            newHead.next = newNode;
//
//            currNode1 = currNode1.next;
//            currNode2 = currNode2.next;
//            newHead = newHead.next;
//        }
//
//        while (currNode1 != null) {
//            int sum = currNode1.val + remainder;
//            int newVal = sum;
//            if (sum >= 10) {
//                newVal = sum % 10;
//            }
//            remainder = (sum - newVal) / 10;
//            ListNode newNode = new ListNode(newVal);
//            newHead.next = newNode;
//
//            currNode1 = currNode1.next;
//            newHead = newHead.next;
//        }
//
//        while (currNode2 != null) {
//            int sum = currNode2.val + remainder;
//            int newVal = sum;
//            if (sum >= 10) {
//                newVal = sum % 10;
//            }
//            remainder = (sum - newVal) / 10;
//            ListNode newNode = new ListNode(newVal);
//            newHead.next = newNode;
//
//            currNode2 = currNode2.next;
//            newHead = newHead.next;
//        }
//
//        if (remainder > 0) {
//            ListNode newNode = new ListNode(remainder);
//            newHead.next = newNode;
//        }
//
//        return mergedList.next;
//
//    }
//}