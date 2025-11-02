package org.self.yahoo.leetcode.linkedList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Easy {

    private static ListNode testIntersectionListsV2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> hashSet = new HashSet<>();

        ListNode currA = headA;
        ListNode currB = headB;

        while (currA != null) {
            hashSet.add(currA);
            currA = currA.next;
        }

        while (currB != null) {
            if (hashSet.contains(currB)) {
                return currB;
            }
            currB = currB.next;
        }

        return null; // Reaches here when no intersection found
    }

    private static ListNode testIntersectionListsV1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int sizeA = 0;
        int sizeB = 0;

        ListNode currA = headA;
        ListNode currB = headB;

        ListNode beginA = headA;
        ListNode beginB = headB;

        while(currA != null) {
            sizeA++;
            currA = currA.next;
        }

        while(currB != null) {
            sizeB++;
            currB = currB.next;
        }
        System.out.println("sizeA: >> " + sizeA);
        System.out.println("sizeB: >> " + sizeB);

        if (sizeB > sizeA) {
            while (sizeA != sizeB) {
                beginB = beginB.next;
                sizeB--;
            }
        } else {
            while (sizeB != sizeA) {
                beginA = beginA.next;
                sizeA--;
            }
        }
        System.out.println("Begin size: " + sizeA + " : " + sizeB);
        System.out.println("Begin val: " + beginA.val + " : " + beginB.val);

        while (beginA != null && beginB != null) {
            int valA = beginA.val;
            int valB = beginB.val;

            System.out.println("***  " + valA + " : " + valB);

            if (valA == valB && beginA == beginB) {
                System.out.println("Intersection found  at : " + valA);
                return beginA;
            }

            beginA = beginA.next;
            beginB = beginB.next;
        }
        return null; // Reaches here when no intersection found
    }

    private static ListNode testMiddleOfLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(slow.next != null && fast.next != null && fast.next.next != null) { // O(n)
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast.next != null) {
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode testMergeTwoSortedListsV1(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode mergedHead = new ListNode(Integer.MIN_VALUE);
        ListNode newMergedHead = mergedHead;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                ListNode tmpNode = new ListNode(list1.val);
                newMergedHead.next = tmpNode;
                list1 = list1.next;
            } else {
                ListNode tmpNode = new ListNode(list2.val);
                newMergedHead.next = tmpNode;
                list2 = list2.next;
            }
            newMergedHead = newMergedHead.next;
        }

        if (list1 != null) {
            newMergedHead.next = list1;
        }

        if (list2 != null) {
            newMergedHead.next = list2;
        }

        return mergedHead.next;
    }


    public static void main(String[] args) {
        System.out.println("Linked list Easy....");

        System.out.println("*********************  Intersection of Two Linked Lists ****************");
        // Leet code 160. Intersection of Two Linked Lists

        ListNode intersectionNode = new ListNode(8);
        intersectionNode.next = new ListNode(4);
        intersectionNode.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersectionNode;

        ListNode headb = new ListNode(5);
        headb.next = new ListNode(6);
        headb.next.next = new ListNode(1);
        headb.next.next.next = intersectionNode;

        /*
               Time complexity: O(m + n) : Iterates over the 2 link lists of length m + n => O(m + n)

               Space complexity: O(1) : No extra space required
         */
        ListNode resp = testIntersectionListsV1(headA, headb);
        System.out.println("testIntersectionLists V1: " + resp.val);

        /*
            Time complexity: O(n + m): Iterates over the 2 link lists of length m + n => O(m + n)

            Space complexity: O(n) : Stores n elements from one of the Linked list on the Hash Set
         */

        resp = testIntersectionListsV2(headA, headb);
        System.out.println("testIntersectionLists V2: " + resp.val);

        // Leet code 876. Middle of the Linked List

        headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);
        headA.next.next.next.next.next = new ListNode(6);

        /*
            Time complexity: O(n) : Does a single pass over the link list using 2 pointers

            Space complexity: O(1) : No extra space used
         */

        ListNode middleNode = testMiddleOfLinkedList(headA);
        ListNode.printList(middleNode);
        System.out.println("middleOfLinkedList: " + middleNode.val);

        // Leet code 21. Merge Two Sorted Lists
        headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(4);

        headb = new ListNode(1);
        headb.next = new ListNode(3);
        headb.next.next = new ListNode(4);

        /*
            Time complexity: O(m + n): Loops over both the sorted list once => m + n

            Space complexity: O(m + n): Total of m + n new nodes created during iteration
         */
        ListNode mergedHead = testMergeTwoSortedListsV1(headA, headb);
        System.out.println("testMergeTwoSortedListsV1: ");
        ListNode.printList(mergedHead);

        headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(4);

        headb = new ListNode(1);
        headb.next = new ListNode(3);
        headb.next.next = new ListNode(4);

        /*
            Time complexity: O(m + n): Loops over both the sorted list once => m + n

            Space complexity: O(m + n): Total of m + n temp List Node objects created for setting up the new List
                                        This also includes the extra space required for the recursive method call stack
         */

        mergedHead = testMergeTwoSortedListsV2(headA, headb);
        System.out.println("testMergeTwoSortedListsV2: ");
        ListNode.printList(mergedHead);
    }

    private static ListNode mergeTwoLists(ListNode list1, ListNode list2, ListNode newMergedHead) {
        if (list1 == null &&  list2 == null) {
            return newMergedHead;
        }

        if (list1 == null) {
            newMergedHead.next = list2; // Append the rest of list2
            return newMergedHead;
        } else if (list2 == null) {
            newMergedHead.next = list1; // Append the rest of list1
            return newMergedHead;
        }

        if (list1.val <= list2.val) {
            ListNode tmpNode = new ListNode(list1.val);
            newMergedHead.next = tmpNode;
            list1 = list1.next;
            newMergedHead = newMergedHead.next;
        } else {
            ListNode tmpNode = new ListNode(list2.val);
            newMergedHead.next = tmpNode;
            list2 = list2.next;
            newMergedHead = newMergedHead.next;
        }
        return mergeTwoLists(list1, list2, newMergedHead);
    }

    private static ListNode testMergeTwoSortedListsV2(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) {
            return null;
        }

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode mergedHead = new ListNode(Integer.MIN_VALUE);
        ListNode newMergedHead = mergedHead;

        newMergedHead = mergeTwoLists(list1, list2, newMergedHead);

        return mergedHead.next;
    }


}
