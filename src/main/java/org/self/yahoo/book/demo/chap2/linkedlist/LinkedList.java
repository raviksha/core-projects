package org.self.yahoo.book.demo.chap2.linkedlist;

import org.apache.commons.lang3.tuple.Pair;

public class LinkedList {
    private Node head;
    private int size = 0;

    public LinkedList() {
        head = null;
    }

    public void addFront(int item) {
        this.head = new Node(item, head);
        size++;
    }

    public void addLast(int item) {
        var newNode = new Node(item, null);
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        var currentNode = head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(newNode);
        size++;
    }

    public void deleteFront() {
        if (head == null) {
            System.out.println("Linked list is empty");
            return;
        }
        size--;
        Node firstNode = this.head;
        this.head = firstNode.getNext();
        firstNode.setNext(null);
    }

    public void addLast(Node node) {

        if (head == null) {
            this.head = node;
            return;
        }

        var currentNode = this.head;

        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(node);
        size++;
    }

    public void addFront(Node node) {
        if (this.head == null) {
            this.head = node;
            return;
        }
        node.setNext(head);
        this.head = node;
        size++;
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        size--;
        if (head.getNext() == null) {
            head = null;
            return;
        }

        var prevNode = head;
        var currentNode = prevNode.getNext();

        while (currentNode.getNext() != null) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        prevNode.setNext(null);
    }

    public void addAfter(Node aNode, int value) {
        Node newNode = new Node(value, aNode.getNext());
        aNode.setNext(newNode);
        size++;
    }

    public Node find(int item) {
        for (Node current = this.head; current != null; current = current.getNext()) {
            if (current.getValue() == item) {
                return current; // Return immediately when found
            }
        }
        return null; // Return null explicitly if not found
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return this.head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void reverseIterate() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        var prevNode = this.head;
        var currentNode = prevNode.getNext();
        var nextCurrentNode = currentNode.getNext();

        while (nextCurrentNode != null) {
            currentNode.setNext(prevNode);
            prevNode = currentNode;

            // Move pointers to next positions
            currentNode = nextCurrentNode;
            nextCurrentNode = nextCurrentNode.getNext();
        }
        currentNode.setNext(prevNode);
        this.head.setNext(null);
        this.head = currentNode;
    }

    public Node reverseLinkListRecursively(Node headNode) {
        if (headNode == null || headNode.getNext() == null) {
            return headNode;
        }
        Node newHead = reverseLinkListRecursively(headNode.getNext());
        //System.out.println("Recursive new head data: " + newHead.getValue());
        headNode.getNext().setNext(headNode);
        headNode.setNext(null);
        return newHead;
    }


    public String toString() {
        String value = "[";
        Node currentNode = this.head;
        while (currentNode != null) {
            value = value.concat(currentNode.getValue() + "");
            currentNode = currentNode.getNext();
            if (currentNode != null) {
                value = value.concat(", ");
            }
        }
        value = value.concat("]");
        return value;
    }

    public void printList(Node head) {
        Node curr = head;
        do {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        } while (curr != head);
        System.out.println();
    }

    // Function to print the linked list
    public void printListV2(Node head) {
        while (head != null) {
            System.out.print(head.getValue() + " ");
            head = head.getNext();
        }
        System.out.println();
    }
}
