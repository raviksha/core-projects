package org.self.yahoo.leetcode.binarysearch;

public class LowerUpperBound {

    private static boolean testBinarySearch(int[] arr, int k) {
        int low = 0;
        int high = arr.length -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == k) {
                return true;
            }

            if (k < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    // Lower bound: Find the smallest index where val >= k
    // Time complexity: O(log n)
    private static int testBinarySearchLowerBound(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Upper bound: Smallest index where val > k
    // Time complexity: O(log n)
    private static int testBinarySearchUpperBound(int[] arr, int k) {
        int low = 0;
        int high = arr.length -1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) /2;

            if (arr[mid] > k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Insert position: Index position in the array where the val >= k == lower-bound Index
    // Time complexity: O(log n)
    private static Object testInsertPosition(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Largest index in the array where val <= k
    // Time complexity: O(log n)
    private static int testFloor(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= k) {
                ans = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    // Smallest index where val > k
    // Time complexity: O(log n)
    private static int testCeil(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > k) {
                ans = arr[mid];
                high = mid -1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] arr = {34, 56, 78, 90, 100, 120, 134, 234};
        int k = 95;
        var result = testBinarySearch(arr, k);
        System.out.println("result: " + result);

        // Test Binary search lower bound => Smallest Index where the number >= val
        var lowerIndex = testBinarySearchLowerBound(arr, k);
        System.out.println("testBinarySearchLowerBound: " + lowerIndex);

        // Test Binary Search upper bound => Smallest Index where the number > val

        var upperIndex = testBinarySearchUpperBound(arr, k);
        System.out.println("testBinarySearchUpperBound: " + upperIndex);

        // Test search insert position
        //  Smallest index where n >= k : lower bound

        var insertPosition = testInsertPosition(arr, k);
        System.out.println("testInsertPosition: " + insertPosition);

        // Test floor and ceil
        // Floor: Largest index where val <= k
        // Ceil: Smallest index where val > k;

        var floorVal = testFloor(arr, k);
        System.out.println("testFloor: " + floorVal);

        // Test ceil val
        // Ceil val = Smallest index in the array where val > k: Upper bound
        var ceilVal = testCeil(arr, k);
        System.out.println("ceilVal: " + ceilVal);

    }




}
