package org.self.yahoo.leetcode75.matrix2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium {

    private static List<Integer> testSpiralMatrix(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // Right
            for (int i = left; i <= right; i++) {
                int item = matrix[top][i];
                result.add(item);
            }

            // Down
            top++;

            for (int i = top; i <= bottom; i++) {
                int item = matrix[i][right];
                result.add(item);
            }

            // Left
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    int item = matrix[bottom][i];
                    result.add(item);
                }

                bottom--;
            }

            // Top

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    int item = matrix[i][left];
                    result.add(item);
                }
                left++;
            }


        }
        return result;
    }

    private static int[][] testRotateIMageV1(int[][] matrix) {
        int rowEnd = matrix.length;
        int colEnd = matrix[0].length;

        int [] [ ] result = new int[rowEnd][colEnd];
        int colIndex = colEnd - 1;
        for (int i = 0; i < matrix.length; i++) {
            int [] arr = matrix[i];
            int rowIndex = 0;

            for (int k : arr) {
                result[rowIndex][colIndex] = k;
                rowIndex++;
            }
            colIndex--;
        }

        for (int i = 0; i < result.length; i++) {
            matrix[i] = result[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("2 D Matrix .... Medium");
        // Leet code 54. Spiral Matrix
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        /*
            For 2D array the total element traversal takes O(m * n) compute time
            Time complexity: O(m * n) Each element of the matrix[][] is traversed only once

            Space complexity: O(m * n) Extra storage to store the matrix[][] is spiral order
         */

        List<Integer> result = testSpiralMatrix(matrix);
        System.out.println("testSpiralMatrix: " + result);

        // Leet code 48. Rotate Image

        /*
            Time complexity: O(m * n) Traversing through each element of matrix[][]

            Space complexity: O(m * n) Auxiliary space required to store rotated matrix [] [] elements
         */
        int [] [] rotatedImage = testRotateIMageV1(matrix);
        System.out.println("testRotateIMageV1: ......");
        for (int i = 0; i < rotatedImage.length; i++) {
            System.out.println(Arrays.toString(rotatedImage[i]));
        }

        /*
            Approach: Uses in place matrix swap

            Time complexity: O(m * n) Traversing each element of the matrix [][]

            Space complexity: O(1) No extra auxiliary space required. Uses in place sorting
         */
        matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotatedImage = testRotateIMageV2(matrix);
        System.out.println("testRotateIMageV2: ......");
        for (int i = 0; i < rotatedImage.length; i++) {
            System.out.println(Arrays.toString(rotatedImage[i]));
        }
    }

    private static int[][] testRotateIMageV2(int[][] matrix) {
        /*
            2 step approach
                1. Transpose the matrix => Swap rows with columns and columns with rows
                2. Reverse each row
         */

        // Step 1: Transpose the matrix

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            // j begins from j + 1 coz the elements before i + 1 are already swapped in the previous iteration
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // Step 2 :
        // Reverse each row of matrix []

        for (int i = 0; i < n; i++) {
            int [] arr = matrix[i];
            reverseArray(arr);
        }


        return matrix;

    }

    private static void reverseArray(int [] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;

            left++;
            right--;
        }
    }


}
