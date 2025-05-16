package org.self.yahoo.book.demo.chap3.hashtable.worksheet;


public class ChainedHashTableExample {

    public static void main(String[] args) {
        System.out.println("ChainedHashTableExample.....");

        RemainderHashing remainderHashing = new RemainderHashing();

        ChainedHashTable chainedHashTable = new ChainedHashTable(100, remainderHashing);
        chainedHashTable.put("hello", "world");
        chainedHashTable.put("world", "cup");
        chainedHashTable.put("vehicle", "car");

        System.out.println(chainedHashTable.get("hello").getValue());
        System.out.println(chainedHashTable.get("world").getValue());
        System.out.println(chainedHashTable.get("vehicle").getValue());
    }
}
