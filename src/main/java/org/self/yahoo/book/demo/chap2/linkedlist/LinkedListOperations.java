package org.self.yahoo.book.demo.chap2.linkedlist;

public class LinkedListOperations {


    private static boolean isLinkedListCyclic(LinkedList list) {
        Node head = list.getHead();
        if (head == null || head.getNext() == null) {
            return false;
        }
        Node turtle = head;
        Node hare = head;

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            hare = head.getNext().getNext();
            turtle = turtle.getNext();

            if (turtle.getValue() == hare.getValue()) {
                return true;
            }
        }
        return false;
    }

    private static Node getMiddleNode(Node headNode) {
        Node turtle = headNode;
        Node hare = headNode;

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            hare = hare.getNext().getNext();
            turtle = turtle.getNext();
        }
        return turtle;

    }

    private static boolean isLinkListPalindrome(LinkedList list) {
        Node headNode = list.getHead();
        if (headNode == null || headNode.getNext() == null) {
            return true;
        }
        Node middleNode = getMiddleNode(headNode);
        System.out.println("Palindrome: Middle node value: " + middleNode.getValue());
        Node secondHalf = list.reverseLinkListRecursively(middleNode);
        System.out.println("Palindrome: Partial list after reverse after midpoint: " + list);
        Node firstHalf = headNode;

        System.out.println(">>>>>>>>>");
        list.printListV2(firstHalf);
        list.printListV2(secondHalf);
        System.out.println(">>>>>>>>>>");

        while (secondHalf.getNext() != null) {
            if (secondHalf.getValue() != firstHalf.getValue()) {
                return false;
            }
            secondHalf = secondHalf.getNext();
            firstHalf = firstHalf.getNext();
        }

        return true;

    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        // Add Front
        linkedList.addFront(3);
        linkedList.addFront(2);
        linkedList.addFront(1);
        linkedList.addFront(new Node(0, null));
        System.out.println("Add front: " + linkedList);

        // Find item
        Node resultNode = linkedList.find(1);
        System.out.println("Find Item: " + (resultNode.getValue() == 1));
        resultNode = linkedList.find(12);
        System.out.println("Find Item: " + (resultNode == null));

        // Add after
        resultNode = linkedList.find(1);
        linkedList.addAfter(resultNode, 111);
        System.out.println("Add After: " + linkedList);

        // Add Last
        linkedList.addLast(99);
        linkedList.addLast(new Node(999, null));
        System.out.println("Add last: " + linkedList);

        // Size
        System.out.println("Size: " + linkedList.getSize());

        // Reverse iterate : recursive

        System.out.println("Begin reverse recursive ...");
        var newHead = linkedList.reverseLinkListRecursively(linkedList.getHead());
        linkedList.setHead(newHead);
        System.out.println("Reverse print using recursion: " + linkedList);


        // Reverse Iterate
        System.out.println("Begin reverse Iterate....");
        linkedList.reverseIterate();
        System.out.println("Reverse Iterate: " + linkedList);


        // Delete Last
        linkedList.deleteLast();
        System.out.println("Delete last: " + linkedList);

        // Delete Front
        linkedList.deleteFront();
        System.out.println("Delete front: " + linkedList);
        linkedList.deleteFront();
        System.out.println("Delete front: " + linkedList);
        linkedList.deleteFront();
        System.out.println("Delete front: " + linkedList);
        linkedList.deleteFront();
        System.out.println("Delete front: " + linkedList);


        // Test isLinkListCyclic
        linkedList = new LinkedList();
        linkedList.addFront(1);
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(1);
        var result = isLinkedListCyclic(linkedList);
        System.out.println("Cyclic check: " + result);


        // Test isLinkList Palindrome
        // 1,2,3,2,1 : True
        // 1,2,2,1 : True
        linkedList = new LinkedList();
        linkedList.addFront(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(2);
        linkedList.addLast(1);
        result = isLinkListPalindrome(linkedList);
        System.out.println("Palindrome check: " + result);


    }
}
