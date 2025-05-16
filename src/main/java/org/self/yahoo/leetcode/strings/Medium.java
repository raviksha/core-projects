package org.self.yahoo.leetcode.strings;

public class Medium {

    private static String testZigzagConversionV1(String str, int numRows) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (numRows == 1) {
            return str;
        }
        // Array of StringBuilder = 2D array of storage container
        StringBuilder [] rows = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int count = 0;
        boolean goingDown = true;

        for (int i = 0; i < str.length(); i++) {
            var strBuilder = rows[count];
            strBuilder.append(str.charAt(i));

            if (count == numRows -1) {
                goingDown = false;
            } else if (count == 0) {
                goingDown = true;
            }

            if (goingDown) {
                count++;
            } else {
                count--;
            }

        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder builder : rows) {
            result.append(builder);
        }

        return result.toString();
    }


    private static String testReverseWordV1(String str) {
        if (str == null) {
            return "";
        }

        String [] strArray = str.split(" ");

        if (strArray.length == 1) {
            return str.trim();
        }

        int length = strArray.length;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = length -1; i >= 0; i--) {
            String currentItem = strArray[i];

            if (!currentItem.isBlank()) {
                stringBuilder.append(currentItem);

                if (i != 0) {
                    stringBuilder.append(" ");
                }
            }
        }

        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("Medium ...");
        System.out.println("******************************************************");
        // Leet code 6. Zigzag Conversion
        String str = "PAYPALISHIRING";
        int numRows = 3;
        /*
            Time complexity: O(m + n) : Loops once through the entire string of N characters + M iterations for the number of rows
                                n = Length of the string
                                m = number of rows

            Space complexity: O(n) : New StringBuilder to store the string in a different order
         */

        var zzoutput = testZigzagConversionV1(str, numRows);
        System.out.println("zzoutput V1:" + zzoutput.isBlank());

        System.out.println("******************************************************");

        // Leet code 151. Reverse Words in a String

        /*
                Time complexity: O(n) :     Worst case when all the string characters are separated by space
                                            Number of elements of thr str array = num of chars in the string after trimmed spaces


                Space complexity: O(n) : Extra String Builder to store the new string in reverse order
         */
        str = "  hello world  ";
        var reversedStr = testReverseWordV1(str);
        System.out.println("reversedStr V1: " + reversedStr);
        System.out.println(reversedStr.equals("world hello"));

    }
}
