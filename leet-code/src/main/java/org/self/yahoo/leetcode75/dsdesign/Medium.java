package org.self.yahoo.leetcode75.dsdesign;

import java.util.HashMap;
import java.util.Map;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Data structure design.....  LRU cache");

        // Leet code 146. LRU Cache
        /*
            Time complexity: O(1) for get() and put() operations
                             All HashMap lookups put(), get() and containsKey(): O(1)

            Space complexity: O(n)
                              Linked List: O(n)
                              LRU Map: O(n)
                              Final s/c: O(n)
         */
        testDesignLRUCache();
    }

    private static void testDesignLRUCache() {
        LRUCache lruCache = new LRUCache(2);
        System.out.println("lruCache.get(2) " + (lruCache.get(2) == -1));
        lruCache.put(2, 6);
        System.out.println("lruCache.get(1) " + (lruCache.get(1) == -1));
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        System.out.println("lruCache.get(1) " + (lruCache.get(1) == 2));
        System.out.println("lruCache.get(2) " + (lruCache.get(2) == 6));
    }
}

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


class LRUCache {

    Node head;
    Node tail;
    int capacity;

    Map<Integer, Node> nodeMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        if (!nodeMap.containsKey(key)) {
            return -1;
        }

        Node tmpNode = nodeMap.get(key);
        deLinkNode(tmpNode);
        addToHead(tmpNode);
        return tmpNode.value;
    }

    public void put(int key, int value) {

        if (nodeMap.size() == capacity && !nodeMap.containsKey(key)) {
            Node tmpNode = tail.prev;
            nodeMap.remove(tmpNode.key);

            Node prevNode = tmpNode.prev;
            prevNode.next = tail;
            tail.prev = prevNode;
        }

        if (nodeMap.containsKey(key)) {
            Node tmpNode = nodeMap.get(key);
            deLinkNode(tmpNode);

        }
        Node newNode = new Node(key, value);
        addToHead(newNode);
        nodeMap.put(key, newNode);

    }

    private void addToHead(Node newNode) {
        Node nextNode = head.next;

        newNode.prev = head;
        head.next = newNode;


        newNode.next = nextNode;
        nextNode.prev = newNode;

    }

    private void deLinkNode(Node tmpNode) {
        Node prevNode = tmpNode.prev;
        Node nextNode = tmpNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
