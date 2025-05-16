package org.self.yahoo.book.demo.chap2.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LLInterviewQuestions2 {

    private static Node fillSortedLinkedList() {
        Node head = new Node(5, null);
        head.setNext(new Node(10, null));
        head.getNext().setNext(new Node(15, null));
        head.getNext().getNext().setNext(new Node(40, null));
        return head;
    }

    private static Node fillSortedLinkedList2() {
        Node head = new Node(2, null);
        head.setNext(new Node(3, null));
        head.getNext().setNext(new Node(20, null));
        return head;
    }

    private static Node fillLinkedList() {
        Node head = new Node(5, null);
        head.setNext(new Node(7, null));
        head.getNext().setNext(new Node(6, null));
        head.getNext().setNext(new Node(8, null));
        head.getNext().getNext().setNext(new Node(2, null));
        return head;
    }

    private static int size(Node head) {
        int size = 0;
        if (head == null) {
            return size;
        }

        var currentNode = head;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.getNext();
        }
        System.out.println("Size: " + size);
        return size;
    }


    private static void printList(Node head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (head == null) {
            sb.append(" ]");
            System.out.println(sb);
            return;
        }
        var currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.getValue());
            currentNode = currentNode.getNext();
            if (currentNode == null) {
                sb.append(" ]");
            } else {
                sb.append(", ");
            }
        }
        System.out.println(sb);
    }

    // Merge two sorted linked lists : using Collection.sort()
    private static void testMergeTwoSortedLinkedListsV1() {

        /*
            Time complexity : O(n)
            Space complexity for merge : O(1) as the sorting algo space computation does not depend on input side.
            Space Complexity of a Linked List Itself : O(n)
         */

        // Merge two sorted linked lists
        Node headNodeList1 = fillSortedLinkedList();
        printList(headNodeList1);
        size(headNodeList1);


        Node headNodeList2 = fillSortedLinkedList2();
        printList(headNodeList2);
        size(headNodeList2);

        var list1CurrNode = headNodeList1;
        var list2CrrNode = headNodeList2;

        List<Integer> finalList = new ArrayList<>();

        while (list1CurrNode != null) {
            finalList.add(list1CurrNode.getValue());
            list1CurrNode = list1CurrNode.getNext();
        }

        while (list2CrrNode != null) {
            finalList.add(list2CrrNode.getValue());
            list2CrrNode = list2CrrNode.getNext();
        }

        Collections.sort(finalList);
        System.out.println("final list: " + finalList);

        /*
            Creation of dummy node is required bcoz
            the finalHeadNode keeps iterating till the end and losses the pointer to the head
         */


        Node dummy = new Node(-1, null);
        Node finalHeadNode = dummy;

        var iterator = finalList.listIterator();
        while (iterator.hasNext()) {
            finalHeadNode.setNext(new Node(iterator.next(), null));
            finalHeadNode = finalHeadNode.getNext();
        }
        System.out.println("Printing sorted list V1...");
        printList(dummy.getNext());

    }

    private static void testMergeTwoSortedLinkedListsV2() {
        /*
            Time complexity : O(n)
            Space complexity for merge : O(1) as the sorting algo space computation does not depend on input side.
            Space Complexity of a Linked List Itself : O(n)
         */
        Node list1Head = fillSortedLinkedList();
        Node list2Head = fillSortedLinkedList2();

        Node dummy = new Node(-1, null);
        Node finalList = dummy;

        while (list1Head != null && list2Head != null) {
            if (list1Head.getValue() < list2Head.getValue()) {
                finalList.setNext(new Node(list1Head.getValue(), null));
                list1Head = list1Head.getNext();
            } else {
                finalList.setNext(new Node(list2Head.getValue(), null));
                list2Head = list2Head.getNext();
            }
            finalList = finalList.getNext(); // Imp step to not miss iterating the pointer of the final list
        }

        if (list1Head != null) {
            finalList.setNext(list1Head);
        } else {
            finalList.setNext(list2Head);
        }
        System.out.println("Printing sorted list V2...");
        printList(dummy.getNext());

    }

    private static Node getMiddleNodeV1(Node headNode) {
        if (headNode == null) {
            return headNode;
        }
        var turtle = headNode;
        var hare = headNode;

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();
        }
        return turtle;
    }

    private static Node mergeSort(Node headNode) {
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }
        // Step 1: Split the list into two halves
        Node middleNode = getMiddleNodeV1(headNode);
        Node secondHalfHead = middleNode.getNext();
        middleNode.setNext(null);

        // Step 2: Recursively sort both halves
        Node left = mergeSort(headNode);
        Node right = mergeSort(secondHalfHead);

        // Step 3: Merge the sorted halves
        return mergeNodes(left, right);
    }

    private static Node mergeNodes(Node leftHead, Node rightHead) {
        Node dummy = new Node(-1, null);
        Node sortedHead = dummy;

        while (leftHead != null && rightHead != null) {
            if (leftHead.getValue() < rightHead.getValue()) {
                sortedHead.setNext(leftHead);
                leftHead = leftHead.getNext();
            } else {
                sortedHead.setNext(rightHead);
                rightHead = rightHead.getNext();
            }
            sortedHead = sortedHead.getNext();
        }

        if (leftHead != null) {
            sortedHead.setNext(leftHead);
        } else {
            sortedHead.setNext(rightHead);
        }
        return dummy.getNext();
    }

    private static void testSortALinkList() {
        // Sort a Linked List
        /*
	        Time Complexity: O(n log n) (average), O(n²) (worst case)
	        Space Complexity: O(log n) (due to recursion)
         */
        Node headNode = fillLinkedList();

        // Works on the concept of Merge sort and selecting the mid-node as a pivot
        Node sortedHead = mergeSort(headNode);
        printList(sortedHead);

    }

    private static void removeDuplicateElementsFromSortedList() {
        /*
            Time Complexity : O(n)
            Space complexity : O(1)
         */

        // Build List
        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(3, null));
        head.getNext().getNext().getNext().setNext(new Node(3, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(3, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().getNext().getNext().getNext().getNext().setNext(new Node(6, null));

        System.out.println();
        System.out.println("Input List: ");
        printList(head);
        // [ 1, 2, 3, 3, 3, 3, 4, 5, 5, 6 ]

        Node prevNode = head;
        Node currentNode = head.getNext();

        while (currentNode != null) {
            var prevValue = prevNode.getValue();
            var currValue = currentNode.getValue();
            System.out.println(prevValue + " : " + currValue);

            if (prevValue == currValue) {
                var newNode = currentNode.getNext();
                prevNode.setNext(newNode);
                currentNode.setNext(null); // Ensures GC for the Node object dangling in between
                currentNode = newNode;
            } else {
                prevNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }
        System.out.println();
        System.out.println("Result List: ");
        printList(head);
    }

    private static Node getPrevToMiddleNode(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node turtle = head;
        Node hare = head;
        Node prevNode = null;

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            prevNode = turtle;
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();
        }
        return prevNode;
    }

    private static void deleteMiddleOfLinkList() {

        // Build link list

        /*
            Time Complexity : O(n)
            Space complexity : O(1)
         */

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7, null));
        System.out.print("Input List: ");
        printList(head);

        Node prevToMiddleNode = getPrevToMiddleNode(head);
        System.out.println("Middle Node: " + prevToMiddleNode.getValue());
        Node middleNode = prevToMiddleNode.getNext();
        Node nextToMiddleNode = middleNode.getNext();
        middleNode.setNext(null);
        prevToMiddleNode.setNext(nextToMiddleNode);

        System.out.println();
        System.out.print("Result List: ");
        printList(head);
    }

    private static void deleteLastOccuranceOfItemInLinkList() {
        /*
            Time Complexity: O(n)
            Space complexity : O(1)
         */

        // Build Link list
        Node head = new Node(7, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(5, null));
        head.getNext().getNext().setNext(new Node(8, null));
        head.getNext().getNext().getNext().setNext(new Node(8, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(2, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7, null));

        System.out.print("Input List :");
        printList(head);
        System.out.println();

        int searchItem = 2;

        Node toDeleteNode = null;
        Node lastPrevNode = null;
        Node lastNextNode = null;
        Node prevNode = head;
        Node currentNode = head.getNext();

        while (currentNode != null) {
            if (currentNode.getValue() == searchItem) {
                toDeleteNode = currentNode;
                lastPrevNode = prevNode;
                lastNextNode = currentNode.getNext();
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        System.out.println("lastPrevNode: " + lastPrevNode.getValue());
        System.out.println("lastNextNode: " + lastNextNode.getValue());
        if (toDeleteNode != null) {
            lastPrevNode.getNext().setNext(null);
            lastPrevNode.setNext(lastNextNode);
        }
        System.out.println();
        System.out.print("Result list: ");
        printList(head);

    }


    private static void removeDuplicateElementsFromUnSortedList() {
        /*
            Time Complexity: O(n)
            Space Complexity : O(1)
         */

        // Build Link list
        Node head = new Node(7, null);
        head.setNext(new Node(8, null));
        head.getNext().setNext(new Node(5, null));
        head.getNext().getNext().setNext(new Node(8, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(2, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7, null));

        System.out.println();
        System.out.println("Input list: ");
        printList(head);

        HashSet<Integer> seen = new HashSet<>();
        Node prevNode = head;
        Node currentNode = head;
        Node newNode = null;
        while (currentNode != null) {
            if (seen.contains(prevNode.getValue())) {
                newNode = currentNode.getNext(); // 2
                prevNode.setNext(newNode);

                prevNode = currentNode;
                currentNode = newNode.getNext();
            } else {
                seen.add(prevNode.getValue());
                prevNode = currentNode;
                currentNode = currentNode.getNext();

            }
        }

        System.out.println("Final List: ");
        printList(head);
    }


    private static void nthNodeFromTheEndOfLinkedList() {
        // Nth node from end of linked list // Complexity : Medium
        /*
               Time Complexity : O(n)
               Space complexity : O(1)
        */

        // Build the LInk List

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(new Node(7, null));

        System.out.println("Input List:");
        printList(head);

        if (head == null) {
            System.out.println("Empty list...");
            return;
        }

        int nthNodeToDeleteFromEnd = 2;
        int listSize = size(head);
        int iterationPoint = listSize - nthNodeToDeleteFromEnd - 1;

        // [1, 2, 3, 4, 5, 6, 7]

        if (listSize > 0 && nthNodeToDeleteFromEnd > 0 && iterationPoint > 0) {

            Node prevNode = head;
            Node currentNode = head.getNext();

            while (iterationPoint > 0) {
                prevNode = prevNode.getNext();
                currentNode = currentNode.getNext();
                iterationPoint--;
            }
            System.out.println("Nth node to be deleted: " + currentNode.getValue());
            prevNode.setNext(currentNode.getNext());
            currentNode.setNext(null);

            System.out.println();
            System.out.println("Result List: ");
            printList(head);

        }


    }

    private static Node reverseLinkList(Node head) {
        if (head == null) {
            return head;
        }
        // [3, 2, 1]
        Node prevNode = head;
        Node currentNode = head.getNext();
        Node nextNode;
        Node newHead = null;

        while (currentNode != null) {
            System.out.println("PrevNode: " + prevNode.getValue());
            System.out.println("CurrentNode: " + currentNode.getValue());

            nextNode = currentNode.getNext();
            currentNode.setNext(prevNode);

            prevNode = currentNode;
            currentNode = nextNode;
        }
        head.setNext(null);

        System.out.println("Response list after reverse: ");
        /*
            After reverse the prevNode object becomes the HEAD of the new reversed list
            currentNode object is NULL and should not be referenced
         */
        newHead = prevNode;
        printList(newHead);
        return newHead;

    }


    private static boolean checkIfSinglyLinkListIsPalindrome() {
        //Function to check if a singly linked list is palindrome

        /*
            Time Complexity : O(n)
            Space Complexity : O(1)
        */

        // Build the LInk List

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(2, null));
        head.getNext().getNext().getNext().setNext(new Node(1, null));

        if (head == null) {
            System.out.println("List is empty..");
            return false;
        }
        // [1, 2, 3, 2, 1]

        System.out.println(">>> INIT >>>");
        printList(head);
        System.out.println(">>>>>>");

        Node middleNode = getMiddleNodeV1(head);
        System.out.println("Middle Node: " + middleNode.getValue());

        Node middleHeadNode = reverseLinkList(middleNode);

        while (middleHeadNode != null && head != null) {
            if (head.getValue() != middleHeadNode.getValue()) {
                return false;
            }
            middleHeadNode = middleHeadNode.getNext();
            head = head.getNext();
        }
        return true;
    }

    private static int printAndSizeOfCircularLinkListValues(Node head) {
        if (head == null) {
            return 0;
        }
        int nodeCount = 0;
        Node turtle = head;
        Node hare = head;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            stringBuilder.append(turtle.getValue());
            nodeCount++;
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();

            if (turtle.getValue() == hare.getValue()) {
                stringBuilder.append(" ]");
                System.out.println(stringBuilder);
                return nodeCount;
            } else {
                stringBuilder.append(", ");
            }
        }
        return 0;
    }
    private static void testSplitACircularLinkedListIntoTwoHalvesV2() {

        // Build a circular Link list

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(head);

        // Print circular list
        int size = printAndSizeOfCircularLinkListValues(head);
        System.out.println("Size fo the circular link list: " + size);

        // [1, 2, 3, 4, 5, 6] -> [1]


        int midIndex = size / 2;
        Node dummy = new Node(-1, null);
        Node newList = dummy;

        Node currentNode = head;

        // Advance the current node till mid position

        // TODO: For odd number the while loop should check  while (midIndex > 0)
        while (midIndex > 1) {
            currentNode = currentNode.getNext();
            midIndex--;
            size--;
        }

        System.out.println("Mid Node: " + currentNode.getValue()); // Mid Node: 3

        // Begin the new node from the node after the mid node : [4]
        newList.setNext(currentNode.getNext());
        size--;

        // Set the next pointer of [3] => null so that the loop breaks here
        currentNode.setNext(null);

        System.out.println("Begin new list: " + newList.getNext().getValue()); // Begin new list: 4
        while (size > 0) {
            newList = newList.getNext();
            size--;
        }
        newList.setNext(null);

        System.out.println("End new list: " + newList.getValue()); // End new list: 5

        printList(head);
        printList(dummy.getNext());

    }
    private static void testSplitACircularLinkedListIntoTwoHalvesV1() {
        // Split a Circular Linked List into two halves
        /*

         */

        // Build a circular Link list

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));
        head.getNext().getNext().getNext().getNext().setNext(new Node(6, null));
        head.getNext().getNext().getNext().getNext().getNext().setNext(head);

        // Print circular list
        int size = printAndSizeOfCircularLinkListValues(head);
        System.out.println("Size fo the circular link list: " + size);

        // [1, 2, 3, 4, 5, 6] -> [1]

        if (head == null) {
            System.out.println("Link list is empty");
            return;
        }

        Node hare = head;
        Node turtle = head;

        // For odd nodes, hare.next is head and
        // for even nodes, hare.next.next is head
        while (hare.getNext() != head && hare.getNext().getNext() != head) {
            turtle = turtle.getNext();
            hare = hare.getNext().getNext();
        }

        /*
            For odd number of entries the hare object at the end will always point to the last node
            But for even number of entries we need to loop one more time to point to the last entry
         */
        if (hare.getNext() != head) {
            hare = hare.getNext();
        }

        System.out.println("Turtle current value: " + turtle.getValue());
        System.out.println("Hare current value: " + hare.getValue());
        // [1, 2, 3, 4, 5]

//        Turtle current value: 3
//        Hare current value: 6

        Node firstHalfHead = head;
        Node secondtHalfHead = turtle.getNext();
        turtle.setNext(firstHalfHead);

        System.out.println("First half of the split circular linked list");
        printAndSizeOfCircularLinkListValues(firstHalfHead); // [ 1, 2, 3 ]



        hare.setNext(secondtHalfHead);
        System.out.println("Second half of the split circular linked list");
        printAndSizeOfCircularLinkListValues(secondtHalfHead); //[ 4, 5, 6 ]

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Continuation of LLInterviewQuestions.... ");

        // ********** Easy Complexity ****************

        // Delete last occurrence of an item from linked list
        //deleteLastOccuranceOfItemInLinkList(); // Complexity: Easy

        // Delete middle of linked list
        //deleteMiddleOfLinkList();  // Complexity : Easy

        // Remove duplicate elements from sorted linked list
        // removeDuplicateElementsFromSortedList();  // Complexity  Easy


        // Nth node from end of linked list // Complexity: Easy
        //nthNodeFromTheEndOfLinkedList();

        // ********** Medium Complexity ****************

        // Merge two sorted linked lists : using Collection.sort() :  Complexity: Medium
        // testMergeTwoSortedLinkedListsV1(); // Using Java Collection.sort(); :  Complexity: Medium

        // testMergeTwoSortedLinkedListsV2(); // Using Merge sort technique :  Complexity: Medium

        //testSortALinkList(); // Complexity:Medium (Using Merge Sort)

        // Remove duplicate elements from Un-sorted Linked list
        //removeDuplicateElementsFromUnSortedList();  // Complexity: Medium

        //Function to check if a singly linked list is palindrome  // Complexity: Medium
        // var resp = checkIfSinglyLinkListIsPalindrome();
        // System.out.println("checkIfSinglyLinkListIsPalindrome: " + resp);

        // Split a Circular Linked List into two halves // Complexity: Medium
        testSplitACircularLinkedListIntoTwoHalvesV1();
        testSplitACircularLinkedListIntoTwoHalvesV2();

    }

}
