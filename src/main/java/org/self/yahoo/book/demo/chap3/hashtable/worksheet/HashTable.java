package org.self.yahoo.book.demo.chap3.hashtable.worksheet;

public interface HashTable {
    public void put(String key, String value);

    public Pair get(String key);

    public void remove(String key);
}
