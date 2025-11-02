package org.self.yahoo.book.demo.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBookChapter1 {
    public static void main(String[] args) {
        System.out.println("Hello TestBookExample1...");
        convertNumbersToOctal("17");
        findIntersectionElementsInArrays();
    }

    private static void findIntersectionElementsInArrays() {
        var base = new int[]{2, 3, 4, 5, 6};
        var child = new int[]{7, 3, 4, 5, 6};
        List<Integer> result = new ArrayList<>();

        // Approach 1 : Time complexity : O(n^2)
//        for (int x: base) {
//            for (int y : child) {
//                if (x == y) {
//                    result.add(x);
//                }
//            }
//        }
//        System.out.println("Overlapping element approach one: " + result);

        // Approach 2: TIme complexity : O(log n)

        Arrays.sort(base);
        Arrays.sort(child);
        result = new ArrayList<>();
        System.out.println("Sorted Array base: " + Arrays.toString(base));
        System.out.println("Sorted Array child: " + Arrays.toString(child));

        for (int x: base) {
            for (int y: child) {
                int end = child.length;
                int start = 0;
                while (start <= end) {
                    int mid = (end + start)/2;
                    // int mid = (end - start)/2 + start
                    System.out.println("Start : " + start);
                    System.out.println("End: " + end);
                    System.out.println("Mid : " + mid);
                    if (x == y) {
                        System.out.println("x===y");
                        result.add(x);
                        break;
                    } else if (x > y) {
                        System.out.println("x > y");
                        end = mid -1;
                    } else {
                        System.out.println("x < y");
                        start = mid +1;
                    }
                }
            }
        }
        System.out.println("Overlapping element : Approach two: " + result);
    }

    private static void convertNumbersToOctal(String octNum) {
        int coversion = 1;
        int decimal = 0;
        System.out.println("Length of Input String : " + octNum.length());
        for (int i = octNum.length(); i > 0; i--) {
            //var digit = octNum.charAt(i -1) - '0';
            var digit = Integer.parseInt(octNum.charAt(i -1) + "");
            decimal += digit * coversion;
            coversion *= 8;
        }
        // Validation
        var expected = Integer.parseInt("17", 8);
        System.out.println("Expected : " + expected);
        System.out.println("Actual : " + decimal);
        System.out.println(decimal == expected);
    }
}
