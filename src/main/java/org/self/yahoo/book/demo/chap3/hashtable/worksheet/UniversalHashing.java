package org.self.yahoo.book.demo.chap3.hashtable.worksheet;

import java.math.BigInteger;

public class UniversalHashing implements  HashProvider {

    private final BigInteger i;
    private final BigInteger j;

    private final long p = 47055833459L;

    public UniversalHashing() {
        j = BigInteger.valueOf((long) (Math.random() * p));
        i = BigInteger.valueOf(1 + (long) (Math.random() * (p - 1L)));
    }

    @Override
    public int hashKey(String key, int tableSize) {
        var hashCode = i.multiply(BigInteger.valueOf(key.length())).add(j)
                .mod(BigInteger.valueOf(p))
                .mod(BigInteger.valueOf(tableSize))
                .intValue();
        return hashCode;
    }


    public static void main(String[] args) {
        UniversalHashing universalHashing = new UniversalHashing();
        System.out.println(universalHashing.hashKey("337481990", 1000));
        System.out.println(universalHashing.hashKey("116241990", 1000));
        System.out.println(universalHashing.hashKey("983611990", 1000));
        System.out.println(universalHashing.hashKey("201031990", 1000));
    }
}
