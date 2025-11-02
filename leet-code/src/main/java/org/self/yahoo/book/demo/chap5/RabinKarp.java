package org.self.yahoo.book.demo.chap5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    private long generateHash(String P, int length) {
        if (P == null) {
            System.out.println("Invalid pattern ..");
            return -1;
        }
        System.out.println("Hash requested for : " + P);
        int patternLength = length;
        long hashCode = 0L;
        for (int i = 0 ; i < length; i++) {
            int asciiCode = P.charAt(i);
            System.out.println(P.charAt(i) + ": " + asciiCode);
            BigInteger base = BigInteger.TEN.pow(--patternLength);
            hashCode = hashCode + (long) asciiCode * base.intValue();
        }
        return hashCode;
    }

    /*
        Time complexity  :
                        Avg and Best case : O(n) : When there are no hash collisions
                        	•	When there are no hash collisions, the algorithm only computes hashes and performs at most O(n) comparisons.
	                        •	The rolling hash update takes constant time O(1) per character.
	                        •	Therefore, in the best and average case, the time complexity is O(n).


                        Worst case : O(mn) in the worst case (In situations when the hash computation is done for each iteration)
                        •	Hash collisions can cause the algorithm to perform direct character comparisons instead of relying on hash values.
                        •	This leads to a worst-case scenario where each window requires an O(m) string comparison.
                        •	If the hash function is poor (i.e., many collisions occur), the algorithm degenerates into brute-force string matching, taking O(nm) time.


        Space complexity :
                         O(1) : No extra space required during computation
        https://www.youtube.com/watch?v=qQ8vS2btsxI
     */
    private List<Integer> match(String P, String T) {
        if (P == null || T == null) {
            return null;
        }

        int patternLength = P.length();
        int textLength = T.length();

        if (patternLength > textLength) {
            System.out.println("Invalid input ..");
            System.exit(-1);
        }
        long patternHashCode = generateHash(P, P.length());

        long textHashCode = generateHash(T, P.length());

        boolean matchFound = true;
        int shift = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < textLength - patternLength + 1; i+= shift) {
            int j = 0;
            matchFound = true;
            StringBuilder tempString = new StringBuilder();

            while (j < patternLength) {
                tempString.append(T.charAt( i + j));
                j++;
            }
            System.out.println("String extracted from T: " + tempString);

            if (textHashCode != patternHashCode) {
                matchFound = false;
            }

            //  Once the hashcode matches, the equality of the strings is verified
            if (matchFound && tempString.toString().equals(P)) {
                list.add(i);
            }
            shift = 1;



            /*
                Below method re computes the rolling hash of the string
                The formulae used is :
                       0. For each character of the pattern matching string of length 3, each position is multiplied by a base 10 i.e
                           Index 0 : 10 ^ 2 = 100 * ascii values of char
                           Index 1 : 10 ^ 1 =  10 * ascii values of char
                           Index 2 : 10 ^ 0 =   1   * ascii values of char

                       1. To subtract the computed hash of the first character of the Pattern string of length m by :
                            BigInteger removeBaseHash = BigInteger.TEN.pow(patternLength - 1);
                            long toDeleteHash = (long) removeBaseHash.intValue() * asciiCode;
                       2. Multiply the remaining hash value by * 10. This moves the second last character of the pattern string to the first position
                          i.e 10 ^ 2
                       3. Add the new character in the pattern matching from the Text string by moving one position to the right
             */

            if (i < T.length() - P.length()) {
                BigInteger removeBaseHash = BigInteger.TEN.pow(patternLength - 1);
                int asciiCode = T.charAt(i);
                long toDeleteHash = (long) removeBaseHash.intValue() * asciiCode;

                char nextChar = T.charAt(j + i);
                textHashCode = ((textHashCode - toDeleteHash) * 10) + nextChar;
            }


        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("RabinKarp....");
        RabinKarp rabinKarp = new RabinKarp();
        String T = "ABABABABAC";
        String P = "ABA";
        List<Integer> matchList = rabinKarp.match(P, T);

        for (int match : matchList) {
            System.out.println("Pattern match found at position: " + match);
        }
    }


}
