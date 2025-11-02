package org.self.yahoo.leetcode.binarysearch;

import java.util.Arrays;

public class Medium {

    private static int [] testElementPosition(int[] arr, int target) {
        int leftIndex = leftBound(arr, target);
        int rightIndex = rightBound(arr, target);
        return new int [] {leftIndex, rightIndex};
    }

    private static int rightBound(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid;
                left = mid + 1;
            }

            if (nums[mid] > target) {
                right = right -1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }
    private static int testFindPeakElementV2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length -1;
        }

        int low = 1;
        int high = nums.length - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid -1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid -1]){
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return 0;
    }


    private static int testFindPeakElementV3(int[] nums) {
        int low = 0;
        int high = nums.length -1 ;
        int size = nums.length;

        if (nums.length == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[size -1] > nums[size -2]) {
            return size -1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int leftBound(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) /2;

            if (nums[mid] == target) {
                index = mid;
                right = mid -1;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }

    private static int testFindPeakElementV1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i< nums.length - 1; i++) {
            if (i == 0) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            } else {
                int prev = nums [i - 1];
                int current = nums[i];
                int next = nums[i + 1];

                if ((current > prev) && (current > next)) {
                    return i;
                }
            }

        }
        return nums.length - 1;
    }

    private static int testRotatedSortedArrayV1(int[] nums, int target) {
        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            } else {
                return -1;
            }
        }

        if (nums.length == 2) {
            if (nums[0] == target) {
                return 0;
            } else if (nums[1] == target) {
                return 1;
            }
            return -1;
        }

        int n = nums.length - 1;
        int pivot = n;
        for (int i = 0; i <= n-1; i++) { // O(n)
            if (nums[i + 1] < nums[i]) {
                pivot = i;
                break;
            }
        }
        System.out.println("Pivot V1: " + pivot);
        int targetIndex = binarySearch(nums, 0, pivot, target); // O(log n)

        if (targetIndex == -1 && n - pivot + 1 > 0) {
            targetIndex = binarySearch(nums, pivot + 1, n, target); // O(log n)
        }
        return targetIndex;
    }

    private static int testRotatedSortedArrayV2(int[] nums, int target) {
        int n = nums.length -1;
        int low = 0;
        int high = nums.length - 1;
        int pivot = findPivotPosition(nums, low, high); // O(log n)
        System.out.println("Pivot V2: " + pivot);
        if (pivot -1 >= 0 && nums[pivot -1] > nums[pivot]) {
            pivot = pivot -1;
        }

        int targetIndex =  binarySearch(nums, low, high, target); // O(log n)
        if (targetIndex == -1) {
            low = pivot + 1;
            high = n;
            targetIndex =  binarySearch(nums, low, high, target); // O(log n)
        }
        return targetIndex;
    }

    private static int findPivotPosition(int [] nums, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private static int binarySearch(int [] nums, int low, int high, int target) {

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }

        return -1;
    }

    private static int testRotatedSortedArrayV3(int[] nums, int target) {
        int n = nums.length -1;
        int low = 0;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if left part is sorted
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                // Left part is sorted
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid -1;
                } else {
                    low = mid + 1;
                }

            } else {
                // right part is sorted => nums[mid] > nums[high]
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid -1;
                }
            }
        }
        return -1;

    }


    private static int testKokoEatingBananas(int[] bananaP, int hours) {
        int low = 1;
        int high = 0;

        for (int i = 0; i < bananaP.length; i++) {
            high = Math.max(high, bananaP[i]);
        }
        System.out.println("High: " + high);
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalH = calculateEatRate(bananaP, mid);

            if (totalH <= hours) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int calculateEatRate(int[] bananaP, int eatR) {
        int totalH = 0;
        for (int i = 0; i < bananaP.length; i++) {
            totalH += (int) Math.ceil((double) bananaP[i] / (double) eatR);
        }
        return totalH;
    }

    public static void main(String[] args) {
        System.out.println("Binary search Medium.....");
        // Leet code 34. Find First and Last Position of Element in Sorted Array
        int [] arr = new int[] {5,7,7,8,8,10};
        int target = 8;
        /*
            Using two binary search approach

            Time complexity : O(log n): Time complexity for binary search

            Space complexity: O(1): No extra space required
         */
        var resp = testElementPosition(arr, target);
        System.out.println("testElementPosition: " + Arrays.toString(resp));

        // Leet code 162. Find Peak Element
        //int [] nums = {1,5,1,2,1};
         int [] nums = {1,2,3,1};
        //int [] nums = {1};
        /*

         */
        int peakElement = testFindPeakElementV1(nums);
        System.out.println("testFindPeakElementV1: " + peakElement);

        peakElement = testFindPeakElementV2(nums);
        System.out.println("testFindPeakElementV2: " + peakElement);

        peakElement = testFindPeakElementV3(nums);
        System.out.println("testFindPeakElementV3: " + peakElement);

        // Leet code 33. Search in Rotated Sorted Array
     //   nums = new int[] {3,5,1};
     //   target = 1;

      //  nums = new int[] {4, 5, 6, 7, 0, 1, 2};
  //      target = 0;

//        nums = new int[] {5, 1, 3};
//        target = 5;

//        nums = new int[] {8,9,2,3,4};
//        target = 9;

        nums = new int[] {3, 1};
        target = 1;

        /*
            Approach a mix of linear & binary search

            Time complexity: O(n) : Worst case to find the rotation pivot index
                             O(Log n): Find the element in the left half of pivot index
                             O(log n): Find the element in the right half of the pivot index
                             Dominating TC : (n)
             Space complexity: O(1)
         */
        int targetIndex = testRotatedSortedArrayV1(nums, target);
        System.out.println("testRotatedSortedArrayV1: " + targetIndex);

        /*
            Using only binary search but runs the search twice. Optimized further using : testRotatedSortedArrayV3()
            Time complexity: O(log n)
                             O(log n) time taken for pivot search, binary search of left and right halves
                             Conclusion : O(log n)

            Space complexity: O(1) : Constant space used
         */

        targetIndex = testRotatedSortedArrayV2(nums, target);
        System.out.println("testRotatedSortedArrayV2: " + targetIndex);

        /*
            Uses only 1 iteration of binary search

            Time complexity: O(log n) : Performs a single iteration of Binary Search

            Space complexity: O(1) : Constant space
         */

        targetIndex = testRotatedSortedArrayV3(nums, target);
        System.out.println("testRotatedSortedArrayV3: " + targetIndex);

        // Leet code 875. Koko Eating Bananas
//        int [] bananaPiles = new int[] {30,11,23,4,20};
//        int hours = 5;

        int [] bananaPiles = new int[] {3,6,7,11};
        int hours = 18;

        /*
            Using Binary search

            Time complexity: O(n log m)
                            O(n) :To calculate the max of the bananaPiles array
                            O(log m): Binary search for m elements, where m = max element of the  bananaPiles []
                            Total time complexity = O(n log m)

            Space complexity: O(1) Constant space.
         */
        int eatingR = testKokoEatingBananas(bananaPiles, hours);
        System.out.println("testKokoEatingBananas: " + eatingR);

        // Leet code 74. Search a 2D Matrix
        int [] [] matrix = new int [] [] {
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,60}
        };
        target = 3;
        /*
            Using binary search Divide and Conquer

            Time complexity: O(m + log n) vS O(log(m * n)),
                               O(m): Where m is the length of the matrix
                               O(log n): n is the number of elements in 2D matrix[m]
                               Total = O(m) + O(log n) => O(m + log n)

            Space complexity: O(1) : Constant space. No extra computing space used
         */
        boolean result = testSearchA2DMatrix(matrix, target);
        System.out.println("testSearchA2DMatrix: " + result);

    }

    private static boolean testSearchA2DMatrix(int[][] matrix, int target) {
        int [] rangeA = new int [matrix.length];

        boolean rangeMatrixL = false;
        for (int i = 0; i < matrix.length; i++) { // O(m)

            if (matrix[i].length == 1) {
                if (matrix[i][0] == target) {
                    return true;
                }
            } else {
                rangeA[i] = matrix[i][0];
                rangeMatrixL = true;
            }
        }

        if (rangeMatrixL) {

            int low = 0;
            int high = rangeA.length -1;

            while (low <= high) {                  // O(log m)
                int mid = low + (high - low) / 2;

                if (rangeA[mid] == target) {
                    return true;
                }

                if (rangeA[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            if (low > 0) {
                int [] searchA = matrix[low -1];
                high = searchA.length -1;
                if (target > searchA[high]) {
                    return false;
                }
                return  binarySearch(searchA, target); // O(log n)
            }

        }
        return false;
    }

    private static boolean binarySearch(int [] arr, int target) {
        int low = 0;
        int high = arr.length -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return true;
            }

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return false;

    }
}
