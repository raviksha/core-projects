package org.self.yahoo.book.demo.chap3.hashtable.worksheet;

import java.util.LinkedList;
import java.util.Objects;

public class ChainedHashTable implements HashTable {
    private HashProvider hashProvider;
    private LinkedList<Pair> [] array;
    int capacity;

    public ChainedHashTable(int capacity, HashProvider hashProvider) {
        this.capacity = capacity;
        array = new LinkedList[capacity];
        this.hashProvider = hashProvider;

        for (int i = 0; i < capacity; i++) {
            array[i] = new LinkedList();
        }
    }


    @Override
    public void put(String key, String value) {
        int hashValue = hashProvider.hashKey(key, capacity);
        LinkedList<Pair> currentLInkedList = array[hashValue];
        currentLInkedList.addFirst(new Pair(key, value));
    }

    @Override
    public Pair get(String key) {
        Pair result = null;
        int hashValue = hashProvider.hashKey(key, capacity);
        LinkedList<Pair> currentLInkedList = array[hashValue];

        for (Pair item : currentLInkedList) {
            if (Objects.equals(item.getKey(), key)) {
                return item;
            }
        }
        return result;
    }

    @Override
    public void remove(String key) {

        int hashValue = hashProvider.hashKey(key, capacity);
        LinkedList<Pair> currentLInkedList = array[hashValue];

        for (Pair item : currentLInkedList) {
            if (Objects.equals(item.getKey(), key)) {
                currentLInkedList.remove(item);
            }
        }
    }
}
