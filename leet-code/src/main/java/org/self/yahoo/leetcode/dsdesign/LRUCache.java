package org.self.yahoo.leetcode.dsdesign;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCacheV1 {

    Map<Integer, Node> cache;
    int capacity = 0;
    Node head;
    Node tail;

    public LRUCacheV1(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node currNode = cache.get(key);
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
        attachToHead(head, currNode);
        return currNode.value;
    }

    public void put(int key, int value) {
        System.out.println("Cache put entry: " + cache + " key: " + key);

        if (cache.containsKey(key)) {
            Node oldNode = cache.get(key);
            oldNode.prev.next = oldNode.next;
            oldNode.next.prev = oldNode.prev;
            cache.remove(key);
        }


        Node currNode = new Node(key, value);

        if (cache.size() < capacity) {
            attachToHead(head, currNode);
        } else {
            removeFromTail(tail);
            attachToHead(head, currNode);
        }
        cache.put(key, currNode);

    }

    private void attachToHead(Node head, Node currNode) {
        Node nextNode = head.next;
        head.next = currNode;
        currNode.prev = head;

        nextNode.prev = currNode;
        currNode.next = nextNode;
    }

    private void removeFromTail(Node tail) {
        Node lruNode = tail.prev;
        lruNode.prev.next = tail;
        tail.prev = lruNode.prev;
        cache.remove(lruNode.key);
    }
}


public class LRUCache {

    Map<Integer, Node> cache;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        Node currNode = cache.get(key);
        removeNode(currNode);
        addToHead(currNode);
        return currNode.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node currNode = cache.get(key);
            removeNode(currNode);
            cache.remove(key); // unnecessary if you overwrite below, but ok
        } else if (cache.size() == capacity) {
            removeFromTail();
        }

        addToHead(key, value); // internally does cache.put(key, newNode)
    }

    private void removeFromTail() {
        Node lruNode = this.tail.prev;
        lruNode.prev.next = tail;
        tail.prev = lruNode.prev;
        cache.remove(lruNode.key);
    }

    private void addToHead(Node node) {
        addToHead(node.key, node.value);
    }
    private void addToHead(int key, int value) {
        Node newHead = new Node(key, value);

        Node currHead = this.head.next;


        currHead.prev = newHead;
        newHead.next = currHead;



        newHead.prev = this.head;
        this.head.next = newHead;

        cache.put(key, newHead);
    }

    private void removeNode(Node currNode) {
        currNode.prev.next = currNode.next;
        currNode.next.prev = currNode.prev;
    }

    /*
        Uses the approach of using a Map for cache and a double link list to track the LRU pointer.

        Time complexity:
                    get(): O(1)
                    put(): O(1)

        Space complexity: O(n) — includes both the HashMap and the doubly linked list used to store up to n key-value pairs.
        Each node in the list stores a key, value, and two pointers.
     */
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1) == 1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2) == -1);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1) == -1);    // return -1 (not found)
        System.out.println(lRUCache.get(3) == 3);    // return 3
        System.out.println(lRUCache.get(4) == 4);    // return 4
    }
}
