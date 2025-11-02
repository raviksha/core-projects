package org.self.yahoo.book.demo.chap6.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeNumber {

    private static void findPrimeNumbersV1(int n) {
        Set<Integer> hashSet = new HashSet<>();
        List<Integer> primeSet = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            for (int j = 2; j <= i; j++) {
                int mod = i % j;
                if (mod == 0 && i != j) {
                    hashSet.add(i);
                }

                if (mod == 0 && i == j) {
                    if (!hashSet.contains(i)) {
                        primeSet.add(i);
                        hashSet.add(i);
                    }
                }

            }
        }
        System.out.print("Prime numbers below V1: " + n + "  ");
        System.out.println(primeSet);
    }


    private static void findPrimeNumbersV2(int n) {
        boolean [] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= n ; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j+= i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        System.out.print("Prime numbers below V2: " + n + "  ");
        System.out.println(primes);
    }

    public static void main(String[] args) {
        // Find all prime numbers between 0 to N
        System.out.println("PrimeNumber....");
        int n = 10;

        /*
            Prime Number is a number > 1 divisible only by 1 and itself
            Time Complexity : O(n ^ 2): For each N, Control iterates through nested loop at least 2 times
            Space Complexity: O(n): Avg case stores all numbers from 0 to  N

         */
        findPrimeNumbersV1(n);

        /*
            Uses Sieve of Eratosthenes Algo. This improves the overall time complexity compared to the implementation
            where it loops to check the divisibility of the number until itself.

            Efficient marking: Instead of checking each number individually, we mark multiples of primes in bulk;
            Avoids unnecessary computations as we skip numbers already known to be non prime.

            Time complexity : Runs in O(n log log n) time, which is much faster then O(n ^ 2)


         */
        findPrimeNumbersV2(n)
;}


}
