package org.self.yahoo.book.demo.chap3.hashtable.worksheet.openddress;

import org.self.yahoo.book.demo.chap3.hashtable.worksheet.HashProvider;
import org.self.yahoo.book.demo.chap3.hashtable.worksheet.HashTable;

public class OpenAddrHashTable implements HashTable {

    int tableSize;
    OpenPair [] strArr;
    HashProvider hashProvider;

    public OpenAddrHashTable(int capacity, HashProvider hashProvider) {
        this.tableSize = capacity;
        this.hashProvider = hashProvider;
        this.strArr = new OpenPair[capacity];
    }

    @Override
    public void put(String key, String value) {
        int hashCode = hashProvider.hashKey(key, tableSize);
        int i =0;
        int tableSize = this.tableSize;

        while (i < this.tableSize &&
                strArr[(hashCode + i) % tableSize] != null &&
                !strArr[(hashCode + i) % tableSize].isDeleted()) {
            i++;
        }
        if (i < tableSize)  {
            strArr[(hashCode + i) % tableSize] = new OpenPair(key, value);
        }

    }

    private int searchPosition(String key) {
        int s = this.tableSize;
        int hashValue = hashProvider.hashKey(key, s);
        int i = 0;
        while (i < s &&
                strArr[(hashValue + i) % s] != null &&
                !strArr[(hashValue + i) % s].getKey().equals(key)) {
            i++;
        }

        return (hashValue + i) % s;
    }

    @Override
    public OpenPair get(String key) {

        OpenPair resp = strArr[searchPosition(key)];

        if (resp != null && !resp.isDeleted && resp.getKey().equals(key)) {
            return resp;
        }
        return null;
    }

    @Override
    public void remove(String key) {
        OpenPair resp = strArr[searchPosition(key)];

        if (resp != null && !resp.isDeleted && resp.getKey().equals(key)) {
            resp.setDeleted(true);
        }
    }
}
