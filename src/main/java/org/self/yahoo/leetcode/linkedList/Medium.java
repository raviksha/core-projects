package org.self.yahoo.leetcode.linkedList;

import org.w3c.dom.NodeList;

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }
}

class MyLinkedList {

    Node head;
    int size;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index > size -1 || index < 0) {
            return -1;
        }
        int indexCtr = 0;
        Node currentNode = head;
        while (indexCtr != index) {
            currentNode = currentNode.next;
            indexCtr++;
        }
        return currentNode.val;
    }

    public void addAtHead(int val) {
        Node newNode = new Node(val);
        size++;
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addAtTail(int val) {
        Node newNode = new Node(val);
        size++;
        if (head == null) {
            head = newNode;
            return;
        }

        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = newNode;
    }

    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }
        size++;
        if (index == 0) {
            Node newNode = new Node(val);
            newNode.next = head;
            head = newNode;
        } else {
            Node newNode = new Node(val);
            int indexCtr = 1;
            Node prevNode = head;
            Node nextNode = head.next;
            while (indexCtr < index) {
                prevNode = prevNode.next;
                nextNode = nextNode.next;
                indexCtr++;
            }
            prevNode.next = newNode;
            newNode.next = nextNode;

        }

    }

    public void deleteAtIndex(int index) {
        if (index > size -1 || index < 0) {
            return;
        }

        if (index == 0 && size > 0) {
            head = head.next;
            size--;
            // currNode.next = null;
            return;

        }
        size--;
        int indexCtr = 1;
        Node prevNode = head;
        Node currNode = head.next;

        while (indexCtr < index) {
            prevNode = prevNode.next;
            currNode = currNode.next;
            indexCtr++;
        }

        Node nextNode = currNode.next;
        currNode.next = null;
        prevNode.next = nextNode;

    }

    public void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node currNode = head;
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

class DoublyNode {
    int val;
    DoublyNode next;
    DoublyNode prev;

    DoublyNode(int val) {
        this.val = val;
    }
}

class MyDoubleLinkedList {

    DoublyNode head;
    DoublyNode tail;
    int size;

    public MyDoubleLinkedList() {

    }

    public int get(int index) {
        if (index > size -1 || index < 0) {
            return -1;
        }
        int indexCtr = 0;
        DoublyNode currNode = head;
        while (indexCtr < index) {
            currNode = currNode.next;
            indexCtr++;
        }
        return currNode.val;
    }

    public void addAtHead(int val) {
        DoublyNode newNode = new DoublyNode(val);
        size++;

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        head.prev = newNode;
        newNode.next = head;
        head = newNode;
    }

    public void addAtTail(int val) {
        DoublyNode newNode = new DoublyNode(val);
        size++;

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }

        DoublyNode newNode = new DoublyNode(val);

        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        if (index == 0) {
            size++;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        if (index == size) {
            addAtTail(val);
        } else {
            size++;
            int indexCtr = 1;
            DoublyNode prevNode = head;
            DoublyNode currentNode = head.next;

            while (indexCtr < index) {
                prevNode = prevNode.next;
                currentNode = currentNode.next;
                indexCtr++;
            }
            prevNode.next = newNode;
            newNode.prev = prevNode;

            currentNode.prev = newNode;
            newNode.next = currentNode;
        }

    }

    public void deleteAtIndex(int index) {
        if (index > size -1 || index < 0) {
            return;
        }

        if (index == 0 && size > 0) {
            head = head.next;
            size--;
            return;
        }

        if (size > 0 && index == size -1 ) {
            DoublyNode prevNode = tail.prev;
            prevNode.next = null;
            tail = prevNode;
            size--;
            return;
        }

        int indexCtr = 1;
        DoublyNode prevNode = head;
        DoublyNode currentNode = head.next;

        while (indexCtr < index) {
            prevNode = prevNode.next;
            currentNode = currentNode.next;
            indexCtr++;
        }
        DoublyNode nextNode = currentNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    public void printList(DoublyNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        DoublyNode currNode = head;
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

    public void printReverseList(DoublyNode tail) {
        if (tail == null) {
            System.out.println("null");
            return;
        }
        DoublyNode currNode = tail;
        System.out.println();
        while (currNode != null) {
            System.out.print(currNode.val);
            currNode = currNode.prev;
            if (currNode != null) {
                System.out.print(" => ");
            }
        }
        System.out.println();
    }
}



public class Medium {

    private static ListNode testRemoveNthFromEndV1(ListNode head, int n) {


        int size = 0;

        ListNode currentNode = head;

        while (currentNode != null) {    // TC: O(m)
            currentNode = currentNode.next;
            size++;
        }

        if (size == n) {
            head = head.next;
            return head;
        }

        int hops = size - n;

        if (hops > 0) {      // TC: O(n -1) : n - 1 number of hops to the node before nth
            currentNode = head;
            while (hops > 1) {
                currentNode = currentNode.next;
                hops--;
            }

            ListNode skipNode = currentNode.next;
            currentNode.next = skipNode == null ? null : skipNode.next;

        }
        return head;
    }


    private static void testDesignLinkedListV2() {
        MyDoubleLinkedList myDoubleLinkedList = new MyDoubleLinkedList();
//        myDoubleLinkedList.addAtHead(3);
//        myDoubleLinkedList.addAtHead(2);
//        myDoubleLinkedList.addAtHead(1);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 1 => 2 => 3
//
//        myDoubleLinkedList.addAtTail(4);
//        myDoubleLinkedList.addAtTail(5);
//
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 1 => 2 => 3 => 4 => 5
//
//        myDoubleLinkedList.addAtIndex(5, 99); // 1 => 2 => 3 => 4 => 5 => 99
//        myDoubleLinkedList.printList(myDoubleLinkedList.head);
//
//        myDoubleLinkedList.addAtIndex(0, 88);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 88 => 1 => 2 => 3 => 4 => 5 => 99
//        myDoubleLinkedList.printReverseList(myDoubleLinkedList.tail);
//
//        var result = myDoubleLinkedList.get(myDoubleLinkedList.size -1); // 99 => 5 => 4 => 3 => 2 => 1 => 88
//        System.out.println("result 2: " + result); // result 2: 99
//
//        myDoubleLinkedList.deleteAtIndex(myDoubleLinkedList.size -2);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head);   // 88 => 1 => 2 => 3 => 4 => 99
//        myDoubleLinkedList.printReverseList(myDoubleLinkedList.tail); // 99 => 4 => 3 => 2 => 1 => 88

        // ["MyLinkedList","addAtIndex","addAtIndex","addAtIndex","get"]
        // [[],              [0,10],     [0,20],      [1,30],[0]]
//        myDoubleLinkedList.addAtIndex(0, 10);
//        myDoubleLinkedList.addAtIndex(0, 20);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head);
//
//        myDoubleLinkedList.addAtIndex(1, 30);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head);

        // ["MyLinkedList","addAtHead","get","addAtIndex","addAtIndex","deleteAtIndex","addAtHead","addAtHead","deleteAtIndex","addAtIndex","addAtHead","deleteAtIndex"]
        //   [[],             [9] ,     [1],     [1,1],     [1,7],         [1],           [7],        [4],           [1],        [1,4],        [2],         [5]]
//        myDoubleLinkedList.addAtHead(9);
//        var result = myDoubleLinkedList.get(1);
//        System.out.println("result1: " + result);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 9
//
//        myDoubleLinkedList.addAtIndex(1, 1);
//        myDoubleLinkedList.addAtIndex(1, 7);
//
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 9 => 7 => 1
//
//        myDoubleLinkedList.deleteAtIndex(1);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 9 => 1
//
//        myDoubleLinkedList.addAtHead(7);
//        myDoubleLinkedList.addAtHead(4);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 4 => 7 => 9 => 1
//
//        myDoubleLinkedList.deleteAtIndex(1);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 4 => 9 => 1
//
//        myDoubleLinkedList.addAtIndex(1, 4);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 4 => 4 => 9 => 1
//        myDoubleLinkedList.addAtHead(2);
//        myDoubleLinkedList.printList(myDoubleLinkedList.head); // 2 => 4 => 4 => 9 => 1
//
//        myDoubleLinkedList.deleteAtIndex(5);

        // ["MyLinkedList","addAtHead","deleteAtIndex"]
        // [[],[1],[0]]
        myDoubleLinkedList.addAtHead(1);
        myDoubleLinkedList.printList(myDoubleLinkedList.head);
        myDoubleLinkedList.deleteAtIndex(0);
        myDoubleLinkedList.printList(myDoubleLinkedList.head);

    }

    private static void testDesignLinkedListV1() {

        // [[],                 [1],       [0]]
        // ["MyLinkedList","addAtHead","deleteAtIndex"]

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.printList(myLinkedList.head); // null

        myLinkedList.addAtHead(1);
        myLinkedList.printList(myLinkedList.head); // 1

        myLinkedList.deleteAtIndex(0);
        myLinkedList.printList(myLinkedList.head);

        // ["MyLinkedList","addAtIndex","addAtIndex","addAtIndex","get"]
        //        [[],         [0,10],      [0,20]      ,[1,30],    [0]]
//        myLinkedList = new MyLinkedList();
//        myLinkedList.addAtIndex(0, 10); // 10
//        myLinkedList.printList(myLinkedList.head);
//
//        myLinkedList.addAtIndex(0, 20); // 20 => 10
//        myLinkedList.printList(myLinkedList.head);
//
//        myLinkedList.addAtIndex(1, 30); // 20 => 30 => 10
//        myLinkedList.printList(myLinkedList.head);
//
//        var result = myLinkedList.get(0); // 20
//        System.out.println("result 0 : " + result);


        // [[],           [7],        [2],       [1],       [3,0],          [2],           [6],        [4],       [4],    [4],      [5,0],         [6]]

        //  ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]

//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(7);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(1);
//        myLinkedList.printList(myLinkedList.head); // 1 => 2 => 7
//
//        myLinkedList.addAtIndex(3, 0);
//        myLinkedList.printList(myLinkedList.head); // 1 => 2 => 7 => 0
//
//        myLinkedList.deleteAtIndex(2);
//        myLinkedList.printList(myLinkedList.head); // 1 => 2 => 0
//
//        myLinkedList.addAtHead(6);
//        myLinkedList.printList(myLinkedList.head); // 6 => 1 => 2 => 0
//        myLinkedList.addAtTail(4);
//        myLinkedList.printList(myLinkedList.head); // // 6 => 1 => 2 => 0 => 4
//
//        var result = myLinkedList.get(4);
//        System.out.println("result 4: " + result);
//
//        myLinkedList.addAtHead(4);
//        myLinkedList.printList(myLinkedList.head); // 4 => 6 => 1 => 2 => 0 => 4
//
//        myLinkedList.addAtIndex(5, 0); // 4 => 6 => 1 => 2 => 0 => 0 => 4
//        myLinkedList.printList(myLinkedList.head);
//
//        myLinkedList.addAtHead(6); // 6 => 4 => 6 => 1 => 2 => 0 => 0 => 4
//        myLinkedList.printList(myLinkedList.head);


        // [[],[1],[3],[1,2],[1],[1],[1]]

//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtTail(3);
//        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
//        myLinkedList.printList(myLinkedList.head);
//
//        myLinkedList.get(1);              // return 2
//        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
//        myLinkedList.get(1);              // return 3
    }


    public static void main(String[] args) {
        System.out.println("Linked list: Medium .......");
        // Leet code 707. Design Linked List
        /*
            Single Link list

            Time complexity: Link list doesn't provide random access to it elements. Traversing is all sequential
                             O(n): get/add/delete  operations takes linear time, except for operation on the head

            Space complexity: O(n): Linear space to store references, including the data for each node
                              Note : Space complexity for the operation itself is O(1)
         */
        //testDesignLinkedListV1();

        /*
            Doubly Link List

            Time complexity: O(n) :All operations like get/add/delete takes O(n) time, except for operations near on head & tail


            Space complexity: O(n) : Extra memory to store both the prev and next node pointer.
                                     Space for the prev counter can be ignored and be concluded to O(n)
                                     Note : Space complexity for the operation itself is O(1)

         */
        //testDesignLinkedListV2();

        // Leet code 19. Remove Nth Node From End of List
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        int n = 2;

        /*
            Time complexity: O(m + n)
                                O(m): One complete traversal of the whole linked list
                                O(n -1): n - 1 hops to the node before the the nth node ot be deleted

            Space complexity: O(1): No extra space used
         */

        ListNode resp = testRemoveNthFromEndV1(head, n);
        System.out.println("V1 resp: ");
        ListNode.printList(resp);


        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        n = 2;

        /*
            Time complexity: O(n):  Entire list is traversed only one
            Space complexity: O(1): No extra space used
         */

        resp = testRemoveNthFromEndV2(head, n);
        System.out.println("V2 resp: ");
        ListNode.printList(resp);
    }

    private static ListNode testRemoveNthFromEndV2(ListNode head, int n) {

        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;

        ListNode slowNode = dummyHead;
        ListNode fastNode = dummyHead;

        int counter = 0;

        while (counter < n) {
            fastNode = fastNode.next;
            counter++;
        }

        while (fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next;

        }

        slowNode.next = slowNode.next.next;
        return dummyHead.next;
    }


}

