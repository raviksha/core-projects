package org.self.yahoo.book.demo.chap3.hashtable.worksheet;

public class MultiplicationHashing implements HashProvider {

    double multiplier;

    public MultiplicationHashing(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int hashKey(String key, int tableSize) {
        return (int) (tableSize * (multiplier * key.length() % 1));
    }

    public static void main(String[] args) {
        MultiplicationHashing multiplicationHashing = new MultiplicationHashing((Math.sqrt(5) - 1) / 2);
        System.out.println(multiplicationHashing.hashKey("337481990", 1000));
        System.out.println(multiplicationHashing.hashKey("116241990", 1000));
        System.out.println(multiplicationHashing.hashKey("983611990", 1000));
        System.out.println(multiplicationHashing.hashKey("201031990", 1000));
    }
}
