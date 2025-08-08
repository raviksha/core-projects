package org.self.yahoo.leetcode.mathGeometry;

public class Medium {

    private static boolean testPalindromeNUmber(int num) {
        int rev = 0;
        int n = num;

        while (n > 0) {
            int digit = n % 10;

            rev = (rev * 10) + digit;

            n = n / 10;
        }

        return num == rev;
    }

    public static void main(String[] args) {
        // Leet code 9. Palindrome Number
        int num = 121;

        /*
            Time complexity: O(n log10(n)): While loop iterates once for each digit of the number

            Space complexity: O(1)
         */
        boolean isPalindrome = testPalindromeNUmber(num);
        System.out.println("testPalindromeNUmber: " + isPalindrome);

        // Leet code 7. Reverse Integer

        num = 456;
        /*
            Time complexity: O(log 10(n)): While loops iterates once for each digit of the number

            Space complexity: O(1)
         */
        int result = testReverseInteger(num);
        System.out.println("testReverseInteger: " + result);
    }

    private static int testReverseInteger(int num) {
        int rev = 0;

        while (num != 0) {
            int digit = num % 10;

            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            rev = (rev * 10) + digit;

            num = num / 10;
        }

        return rev;
    }


}
