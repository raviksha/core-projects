package org.self.yahoo.leetcode.linkedList;

import java.util.HashMap;
import java.util.Map;

public class Medium2 {
    private static ListNode testRemoveDuplicateFromLinkList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode prevBack = dummy;
        ListNode back = head;
        ListNode front = head.next;

        while (front != null) { // O(n)
            if (back.val == front.val) {
                while (front != null && (back.val == front.val)) {
                    front = front.next;
                }
                prevBack.next = front;
                // reset node assignment
                back = prevBack.next;

                if (back != null) {
                    front = back.next;
                }
            } else {
                prevBack = back;
                back = back.next;
                front = front.next;
            }
        }
        return dummy.next;
    }

    private static ListNode testSwapNodesInPair(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        ListNode prevNode = dummyNode;
        ListNode currentNode = head;
        ListNode nextNode = head.next;

        while (currentNode != null && nextNode != null) { // O(n)
            prevNode.next = nextNode;
            currentNode.next = nextNode.next;
            nextNode.next = currentNode;


            prevNode = currentNode;
            currentNode = currentNode.next;
            if (currentNode != null) {
                nextNode = currentNode.next;
            }
        }
        return dummyNode.next;
    }

    private static ListNode testLinkListCycleV1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        Map<ListNode, Integer> freqMap = new HashMap<>();

        ListNode currNode = head;
        int ctr = 0;
        while (currNode != null) {
            freqMap.put(currNode, ctr);
            currNode = currNode.next;
            if (freqMap.containsKey(currNode)) {
                return currNode;
            }
            ctr++;
        }

        return null;
    }

    private static ListNode testLinkListCycleV2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (fastPointer == slowPointer) {
                fastPointer = head;

                while (fastPointer != slowPointer) {
                    fastPointer = fastPointer.next;
                    slowPointer = slowPointer.next;
                }
                return slowPointer;
            }
        }

        return null;
    }

    private static ListNode testAddTwoLinkLists(ListNode l1, ListNode l2) {
        ListNode mergedList = new ListNode(-1);
        int remainder = 0;

        ListNode currNode1 = l1;
        ListNode currNode2 = l2;
        ListNode newHead = mergedList;

        while (currNode1 != null && currNode2 != null) {
            int sum = currNode1.val + currNode2.val + remainder;
            int newVal = sum;
            if (sum >= 10) {
                newVal = sum % 10;
            }
            remainder = (sum - newVal) / 10;
            ListNode newNode = new ListNode(newVal);
            newHead.next = newNode;

            currNode1 = currNode1.next;
            currNode2 = currNode2.next;
            newHead = newHead.next;
        }

        while (currNode1 != null) {
            int sum = currNode1.val + remainder;
            int newVal = sum;
            if (sum >= 10) {
                newVal = sum % 10;
            }
            remainder = (sum - newVal) / 10;
            ListNode newNode = new ListNode(newVal);
            newHead.next = newNode;

            currNode1 = currNode1.next;
            newHead = newHead.next;
        }

        while (currNode2 != null) {
            int sum = currNode2.val + remainder;
            int newVal = sum;
            if (sum >= 10) {
                newVal = sum % 10;
            }
            remainder = (sum - newVal) / 10;
            ListNode newNode = new ListNode(newVal);
            newHead.next = newNode;

            currNode2 = currNode2.next;
            newHead = newHead.next;
        }

        if (remainder > 0) {
            ListNode newNode = new ListNode(remainder);
            newHead.next = newNode;
        }

        return mergedList.next;
    }

    private static ListNode testRotateListV1(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.next;
            length++;
        }
        k = k % length;
        head = rotateList(head, k);
        return head;
    }

    private static ListNode rotateList(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        ListNode prevNode = head;
        ListNode currentNode = head.next;

        while (currentNode.next != null) {
            prevNode = prevNode.next;
            currentNode = currentNode.next;
        }

        prevNode.next = null;
        currentNode.next = head;
        head = currentNode;
        return rotateList(head, k - 1);
    }

    private static ListNode testRotateListV2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode currentNode = head;
        while (currentNode != null) {
            currentNode = currentNode.next;
            length++;
        }
        k = k % length;
        ListNode tmpCurr = null;
        ListNode tmpNext = null;

        for (int i = 0; i < k; i++) {
            tmpCurr = head;
            tmpNext = tmpCurr.next;

            while (tmpNext.next != null) {
                tmpCurr = tmpCurr.next;
                tmpNext = tmpNext.next;
            }
            tmpCurr.next = null;
            tmpNext.next = head;
            head = tmpNext;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("Linked list ... Medium ...");

        // Leet code 82. Remove Duplicates from Sorted List II
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        /*
            Using two pointers approach
            Time complexity: O(n): Loops once over the link list using two pointers

            Space complexity: O(1): No extra compute space required
         */
        ListNode respHead = testRemoveDuplicateFromLinkList(head);
        ListNode.printList(respHead);

        // Leet code 24. Swap Nodes in Pairs
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        /*
            Using Two pointers approach
            Time complexity: O(n): Single pass over the Link list elements

            Space complexity: O(1): No extra space used

         */
        ListNode result = testSwapNodesInPair(head);
        ListNode.printList(result);

        // Leet code 142. Linked List Cycle II
        ListNode cycleNode = new ListNode(2);
        ListNode tailNode = new ListNode(-4);

        head = new ListNode(3);
        head.next = cycleNode;
        head.next.next = new ListNode(0);
        head.next.next.next = tailNode;
        tailNode.next = cycleNode;

        /*
            Brute force approach using HashMap
            Time complexity: O(n): Loops over the link list n times

            Space complexity: O(n): n NodeList elements stored in the Frequency map
         */
        ListNode cyclicNode = testLinkListCycleV1(head);
        System.out.println("testLinkListCycleV1: " + cyclicNode.val);

        /*
            Approach : Using Flyod algo of using a fast and small counter

            Time complexity: O(n): Both the small and fast pointers loops over the link list just once

            Space complexity: O(1) : No extra space used

         */
        cyclicNode = testLinkListCycleV2(head);
        System.out.println("testLinkListCycleV2: " + cyclicNode.val);


        // Leet code 2. Add Two Numbers
        head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        /*
            Time complexity: O(n): Iterates once over both the input link lists

            Space complexity: O(1): No extra space consumed
         */
        ListNode mergedNode = testAddTwoLinkLists(head, head2);
        System.out.print("testAddTwoLinkLists :");
        ListNode.printList(mergedNode);

        // Leet code 61. Rotate List
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;

        /*
            Using recursion
            Time complexity: O(n) Loops once over the Singly link list

            Space complexity: O(n) Extra space require for n recursion stacks
         */

        ListNode rotatedNode = testRotateListV1(head, k);
        System.out.print("testRotateList V1: ");
        ListNode.printList(rotatedNode);

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        k = 2;
        /*
            Using linear iteration instead of recursion.
            Performs better for large values of k as it avoids the extra space complexity associated with recursion stack

           Time complexity: O(n): Loops once over the link list

           Space complexity: O(1): No extra space required

         */
        rotatedNode = testRotateListV2(head, k);
        System.out.print("testRotateList V2: ");
        ListNode.printList(rotatedNode);
    }


}
