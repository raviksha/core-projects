package org.self.yahoo.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium {

    private static void testRotateImage(int[][] matrix) {
        int rowEnd = matrix.length - 1;
        int colEnd = matrix[0].length - 1;

        int [] [] result = new int[rowEnd + 1][colEnd + 1];

        int rowIndex = 0;
        for (int[] currArr : matrix) {
            for (int i : currArr) {
                result[rowIndex][colEnd] = i;
                rowIndex++;
            }
            colEnd--;
            rowIndex = 0;
        }
        // System.arraycopy performs a native memory copy for primitive types and is faster than manual loops in most cases
        System.arraycopy(result, 0, matrix, 0, result.length); // t/c O(n)
    }

    private static List<Integer> testSpiralMatrix(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;

        int colBegin = 0;
        int colEnd = matrix[0].length -1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            // Traverse right ->
            if (rowBegin <= rowEnd && colBegin <= colEnd) {
                for (int i = colBegin; i <= colEnd; i++) {
                    list.add(matrix[rowBegin][i]);
                }
            }


            rowBegin++;

            // Traverse down
            if (rowBegin <= rowEnd && colBegin <= colEnd) {
                for (int i = rowBegin; i <= rowEnd; i++) {
                    list.add(matrix[i][colEnd]);
                }
            }

            colEnd--;

            // Traverse left <-
            if (rowBegin <= rowEnd && colBegin <= colEnd) {
                for (int i = colEnd; i >= colBegin ; i--) {
                    list.add(matrix[rowEnd][i]);
                }
            }


            rowEnd--;

            // Traverse up
            if (rowBegin <= rowEnd && colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    list.add(matrix[i][colBegin]);
                }
            }

            colBegin++;
        }

        return list;
    }


    public static void main(String [] args) {
        System.out.println("2D Matrix ... Medium");

        // Leet code 54. Spiral Matrix
        int [] [] matrix = new int [] [] {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20},{21,22,23,24}};
        /*
            Time complexity: O(m * n)
                             Each element of the matrix [][] is visited only once. Total of m * n elements

            Space complexity: O(m * n)
                              Extra compute space required create a List from each element in spiral order.
                              Total elements in spiral order: O(m * n)

         */
        List<Integer> spiralList = testSpiralMatrix(matrix);


        System.out.println("testSpiralMatrix: " + spiralList);

        // Leet code 48. Rotate Image
        // [[1,2,3],[4,5,6],[7,8,9]]
        matrix = new int [] [] {{1,2,3},{4, 5,6},{7,8,9}};

        /*
            Time complexity: O(n * m)
                             n = length of the 2D array matrix[][]
                             m = elements in an array at each index

            Space complexity: O(n * m): Extra compute space required to store temp result [][]
                                        result [][] is of the same space as the matrix [][]
         */
        testRotateImage(matrix);
        for (int[] arr : matrix) {
            System.out.println("testSpiralMatrix: " + Arrays.toString(arr));
        }

        // Leet code 73. Set Matrix Zeroes
        // [[1,1,1],[1,0,1],[1,1,1]]
        matrix = new int[][] {{1,1,1},{1,0,1},{1,1,1}};

        /*
            Time complexity: O(m * n)
                             Loops through each element of the matrix [] [] : n * m

            Space complexity: O(n)
                              Extra compute space to store the mapping of zeros for each row and its zero val based columns
         */
        testSetMatrixZeros(matrix);
        for (int[] arr : matrix) {
            System.out.println("testSetMatrixZeros: " + Arrays.toString(arr));
        }

    }

    private static void testSetMatrixZeros(int[][] matrix) {

        Map<Integer, List<Integer>> zerosMap = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            int [] curArr = matrix[i];
            for (int j = 0; j < curArr.length; j++) {
                if (curArr[j] == 0) {
                    List<Integer> colList;
                    colList = zerosMap.getOrDefault(i, new ArrayList<Integer>());
                    colList.add(j);
                    zerosMap.put(i, colList);
                }
            }
        }

        setMatrixZeros(matrix, zerosMap);
    }

    private static void setMatrixZeros(int[][] matrix, Map<Integer, List<Integer>> zerosMap) {

        for (Map.Entry<Integer, List<Integer>> entry: zerosMap.entrySet()) {
            int row = entry.getKey();
            int [] curRow = matrix[row];
            Arrays.fill(curRow, 0); // t/c O(n)

            for (int col : entry.getValue()) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][col] = 0;
                }
            }
        }
    }


}
