package org.self.yahoo.book.demo.chap3.hashtable.worksheet.openddress;

import org.self.yahoo.book.demo.chap3.hashtable.worksheet.RemainderHashing;

public class OpenAddressingExample {
    public static void main(String[] args) {
        OpenAddrHashTable openAddrHashTable = new OpenAddrHashTable(10, new RemainderHashing());

        openAddrHashTable.put("hello", "world");
        openAddrHashTable.put("vehicle", "car");

        System.out.println(openAddrHashTable.get("hello"));
        System.out.println(openAddrHashTable.get("world"));

    }
}
