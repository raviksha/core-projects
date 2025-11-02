package org.self.yahoo.book.demo.chap2.linkedlist;

public class LLInterviewQuestions {
    private static Node fetchMiddleNodeOpt1(Node headNode) {
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }
        Node turtle = headNode;
        Node hare = headNode;

        while (hare.getNext() != null && hare.getNext().getNext() != null) {
            hare = hare.getNext().getNext();
            turtle = turtle.getNext();
        }
        return turtle;
    }

    //Print the Middle of a given linked list
    private static Node fetchMiddleNodeOpt2(LinkedList list) {
        var headNode = list.getHead();
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }

        int size = list.getSize();
        System.out.println("Size V2: " + size);
        int midIndex = size / 2;

        while (midIndex > 0) {
            headNode = headNode.getNext();
            midIndex--;
        }
        return headNode;
    }

    private static void testPrintMiddleOfLinkedList() {
        var linkedList = fillLinkedList();

        System.out.println("Head Node: " + linkedList.getHead().getValue());

        var middleNode = fetchMiddleNodeOpt1(linkedList.getHead());
        System.out.println("Middle node of the list V1: " + middleNode.getValue());

        middleNode = fetchMiddleNodeOpt2(linkedList);
        System.out.println("Middle node of the list V2: " + middleNode.getValue());

        System.out.println("Full Linked list: " + linkedList);

    }

    private static void testConvertSingleLinkListIntoCircular() {
        var linkedList = fillLinkedList();

        var currentNode = linkedList.getHead();
        var headNode = linkedList.getHead();

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(headNode);
        linkedList.printList(headNode);
    }

    private static void testReverseASublistOfLinkList(int left, int right) {
        /*
            Given a linked list and positions left and right. We need to reverse the linked list from position left to right.
         */
        var linkedList = fillLinkedList();
        var headNode = linkedList.getHead();

        // Dummy node to simplify edge cases (e.g., reversing from head)
        Node dummy = new Node(0, null);
        dummy.setNext(headNode);
        Node prev = dummy;

        // Step 1: Move `prev` to the node just before `left`
        for (int i = 1; i < left; i++) {
            prev = prev.getNext();
        }

        // Step 2: Reverse the sublist from left to right

        Node currentNode = prev.getNext();
        Node next = null;
        Node previous = null;

        for (int i = left; i <= right; i++) {
            next = currentNode.getNext();
            currentNode.setNext(previous);

            previous = currentNode;
            currentNode = next;
        }

        /*
            After the above for loop iteration the pointing of the nodes are follows :
            0 ---> 1  2 <--- 3  <--- 4   5

            currentNode : pointing to 5
            previous    : pointing to 4
         */

        /* Remaining pending mapping is :
             : 2 ----> 5
             : 1 ----> 4
         Currently
          :   prev is pointing to 1
          :   prev.getNext() is mapped to : 2
        */

        prev.getNext().setNext(currentNode); // This points the 2 --> 5
        prev.setNext(previous);              // This points the 1 --> 4

        /*
            New mapping is :
            0 ---> 1 ---> 4
            2 <--- 3  <--- 4
            2 ---> 5
         */

        linkedList.printListV2(linkedList.getHead());
    }

    private static LinkedList fillLinkedList() {
        var linkedList = new LinkedList();
        linkedList.addFront(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        return linkedList;
    }

    private static void printList(Node head) {
        if (head == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node currNode = head;
        while (currNode != null) {
            sb.append(currNode.getValue());
            currNode = currNode.getNext();
            if (currNode != null) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        System.out.println(sb);
    }

    private static Node reverseLinkList(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return head;
        }
        // [3, 4, 5]
        Node prevNode = head;
        Node currentNode = head.getNext();
        Node nextNode = null;
        Node newHead = null;

        while (currentNode != null) {
            nextNode = currentNode.getNext();
            currentNode.setNext(prevNode);

            prevNode = currentNode;
            currentNode = nextNode;
        }
        head.setNext(null);
        newHead = prevNode;
        return newHead;
    }

    private static void rearrangeAGivenLinkedListInPlace() {
        //Rearrange a given linked list in-place.
        /*
            Time complexity : O(n)
            Space complexity : O(1)
        */

        // Build Nodes

        Node head = new Node(1, null);
        head.setNext(new Node(2, null));
        head.getNext().setNext(new Node(3, null));
        head.getNext().getNext().setNext(new Node(4, null));
        head.getNext().getNext().getNext().setNext(new Node(5, null));

        if (head == null) {
            System.out.println("List is empty...");
            return;
        }

        System.out.println("Input list: ");
        printList(head);

        Node middleNode = fetchMiddleNodeOpt1(head);
        System.out.println("***************");
        System.out.println("head: " + head.getValue());
        System.out.println("middleNode: " + middleNode.getValue());
        System.out.println("***************");

        /*
            Needs to skip one node to break the link between first half and second half so that final output
            does not include the value 3` twice eg : [ 1, 5, 2, 4, 3, 3]

            [ 1, 2, 3 ]
            [ 5, 4, 3 ]

            After skipping the node, the linked list is split like :
            [ 1, 2, 3 ]
            [ 5, 4 ]

            Final result :  [ 1, 5, 2, 4, 3]
         */

        Node tmpNode = middleNode;
        middleNode =  middleNode.getNext();
        tmpNode.setNext(null);
        Node midStartPoint = reverseLinkList(middleNode);

        /*
            At this point the LInk list is split into 2 halves :
            First half :  1, 2, 3
            Second half : 5, 4, 3
         */
        System.out.println("***************");
        printList(head);
        printList(midStartPoint);
        System.out.println("***************");

        // Merge the 2 lists together with one value from each

        Node dummy = new Node(-1, null);
        Node newList = dummy;

        while (head != null || midStartPoint != null) {
            if (head != null) {
                newList.setNext(new Node(head.getValue(), null));
                head = head.getNext();
                newList = newList.getNext();
            }
            if (midStartPoint != null) {
                newList.setNext(new Node(midStartPoint.getValue(), null));
                midStartPoint = midStartPoint.getNext();
                newList = newList.getNext();
            }
        }

        System.out.println("Response: ");
        printList(dummy.getNext());

    }

    public static void main(String[] args) {
        // 1. Print the Middle of a given linked list
        //testPrintMiddleOfLinkedList();

        // 2. Convert singly linked list into circular linked list
        // testConvertSingleLinkListIntoCircular();

        // 3. Reverse a sublist of linked list
        //testReverseASublistOfLinkList(2, 4); // Complexity: Hard

        // 4. Rearrange a given linked list in-place.
        rearrangeAGivenLinkedListInPlace();  // Complexity: Hard
    }




}
