package org.self.yahoo.book.demo.chap3.hashtable.worksheet;

public class RemainderHashing implements HashProvider{
    @Override
    public int hashKey(String key, int tableSize) {
        return key.hashCode() % tableSize ;
    }


    public static void main(String[] args) {
        RemainderHashing remainderHashing = new RemainderHashing();
        System.out.println(remainderHashing.hashKey("337481990", 1000));
        System.out.println(remainderHashing.hashKey("116241990", 1000));
        System.out.println(remainderHashing.hashKey("983611990", 1000));
        System.out.println(remainderHashing.hashKey("201031990", 1000));
    }
}
