package org.self.yahoo.leetcode.mergesort;

import org.self.yahoo.leetcode.linkedList.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Medium {

    private static ListNode testSortListV1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead1 = new ListNode(0);
        ListNode currNode = head;
        ListNode newHead2 = newHead1;

        Comparator<ListNode> comparator = Comparator.comparingInt((ListNode node) -> node.val);
        List<ListNode> nodeList = new ArrayList<>();

        while (currNode != null) {
            nodeList.add(currNode);
            currNode = currNode.next;
        }
        nodeList.sort(comparator); // O(n log n)

        for (ListNode node : nodeList) { // O(n)
            newHead2.next = node;
            newHead2 = newHead2.next;
            node.next = null;
        }

        return newHead1.next;

    }

    public static void main(String[] args) {
        System.out.println("Medium merge sort ...");

        // Leet code 148. Sort List
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(0);

        /*
            Using a Link list with a custom comparator approach: Brute Force

            Time complexity: O(n log n)
                           Traversing the Link list takes O(n) time
                           Sorting the LinkedList with custom comparator : O(n log n)
                           Adding elements in the Link List : O(1)
                           Concluded to : O(n log n)


            Space complexity: O(n)
                              Extra liner space to store Linked list nodes in a Array List
                              Merge sort also takes more space of O(n) while merging two sorted sub arrays
                              Concluded: O(n)

         */
        ListNode sortedHead = testSortListV1(head);
        ListNode.printList(sortedHead);

        head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(0);

        sortedHead = testSortListV2(head);
        ListNode.printList(sortedHead);

    }

    private static ListNode testSortListV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode midNode = getMidNode(head);
        System.out.println("midNode: " + midNode.val);
        ListNode leftHead = head;
        ListNode rightHead = midNode.next;
        midNode.next = null;

        leftHead = testSortListV2(leftHead);
        rightHead = testSortListV2(rightHead);

        ListNode mergedHead = new ListNode(Integer.MIN_VALUE);
        ListNode newMergedHead = mergedHead;
        mergeSortedList(leftHead, rightHead, newMergedHead);
        return mergedHead.next;
    }

    private static ListNode mergeSortedList(ListNode list1, ListNode list2, ListNode newMergedHead) {

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
        return mergeSortedList(list1, list2, newMergedHead);
    }


    private static ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


}
