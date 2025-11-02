package org.self.yahoo.book.demo.chap2.queue.worksheet;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;

    Node previous;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.previous = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}

public class LRUCache {

    int capacity;
    Map<Integer, Node> cacheMap;
    Node head;
    Node tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.previous = this.head;
    }


    private void deLInkNode(Node currentNode) {

    }

    private void insertAtHead(Node currentNode) {


    }

    int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }

        /*
            If item exists in the map then :
              1. Look up the node
              2. De link the node from the current chain
              3. Add the node the beginning head
         */

        // Step 1 : Look up the node from the cache map

        Node resultNode = cacheMap.get(key);

        // Step 2 : De link the node

        Node newPrev = resultNode.previous;
        Node newNext = resultNode.next;
        newPrev.next = newNext;
        newNext.previous = newPrev;

        // Step 3: Attach to the head node
        Node currentNext = head.next;
        resultNode.next = currentNext;
        head.next = resultNode;
        currentNext.previous = resultNode;

        return resultNode.value;
    }

    public void put(int key, int value) {
        Node newNode = new Node(key, value);

        // Handle first time insert
        if (cacheMap.isEmpty()) {
            Node currentNext = head.next;
            newNode.next = currentNext;
            head.next = newNode;
            currentNext.previous = newNode;
        } else if (cacheMap.size() == capacity) { // Handle when the cache is full.
            // Step 1: Remove last node from tail
            Node currentLast = tail.previous;
            Node newLast = currentLast.previous;
            newLast.next = tail;
            tail.previous = newLast;
            cacheMap.remove(currentLast.key);

            // Step 2: Add new node to head
            Node currentNext = head.next;
            newNode.next = currentNext;
            head.next = newNode;
            currentNext.previous = newNode;
        } else {

            // Add new node to head
            Node currentNext = head.next;
            newNode.next = currentNext;
            head.next = newNode;
            currentNext.previous = newNode;
        }
        cacheMap.put(key, newNode);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Node currentNode = head.next;
        while (currentNode != tail) {
            stringBuilder.append(currentNode.value);
            currentNode = currentNode.next;

            if (currentNode != tail) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
    /*
        https://www.geeksforgeeks.org/lru-cache-implementation/
        Time Complexity :
            put() operation: O(1) i.e. time required to insert or update new key-value pair is constant
            get() operation: O(1) i.e. time required to get the value of a key is constant

        Space Complexity : O(n) : Space required to store the n elements of the cache
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        System.out.println(cache);
        System.out.println(cache.cacheMap);

        cache.put(2, 2);
        System.out.println(cache);
        System.out.println(cache.cacheMap);

        System.out.println(cache.get(1));

        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }
}
