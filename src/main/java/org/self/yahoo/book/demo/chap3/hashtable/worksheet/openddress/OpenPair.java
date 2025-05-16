package org.self.yahoo.book.demo.chap3.hashtable.worksheet.openddress;

import org.self.yahoo.book.demo.chap3.hashtable.worksheet.Pair;

public class OpenPair extends Pair {

    String key;
    String value;
    boolean isDeleted;

    public OpenPair(String key, String value) {
        super(key, value);
        this.key = key;
        this.value = value;
        isDeleted = false;

    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

