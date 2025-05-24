package org.self.yahoo.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SlidingWindowANDTwoPointers {

    private static int testMaxConsecutiveAvg(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int sum = 0;


        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        int startIndex = 0;
        int endIndex = k;

        while (endIndex < nums.length) {
            sum -= nums[startIndex];
            startIndex++;

            sum += nums[endIndex];
            endIndex++;

            maxSum = Math.max(sum, maxSum);
        }
        return maxSum / k;
    }

    private static String testMaxSubStringNonRepeatingChars(String input) {
        if (input == null) {
            return input;
        }
        // abcabcbb
        int startIndex = 0;
        int endIndex = startIndex + 1;
        String subString;
        String longestString = "";

        while (endIndex < input.length()) {
            char left = input.charAt(startIndex);
            char right = input.charAt(endIndex);

            if (left != right) {
                endIndex++;
            } else {
                subString = input.substring(startIndex, endIndex);
                longestString = subString.length() > longestString.length() ? subString : longestString;
                startIndex++;
                endIndex++;
            }
        }
        return longestString;
    }


    private static int testSubArraySumV1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int counter = 0;

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            sum += key;

            if (map.containsKey(sum - key)) {
                counter += map.get(sum - key);
            }

            map.put(key, map.getOrDefault(sum, 0) + 1);

        }
        return counter;
    }


    private static int testMaxFourCardsV1(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int leftSum = 0;
        int rightSum = 0;
        int totalSum = 0;
        int sum = 0;
        int leftCtr = 0;
        int size = nums.length - 1;

        for (int i = 0; i <= k; i++) {
            leftCtr = 0;
            sum = 0;
            leftSum = 0;
            rightSum = 0;
            size = nums.length - 1;

            int split = k - i;
            System.out.println("******************");
            System.out.println("Split: " + split);

            while (leftCtr < split) {
                leftSum += nums[leftCtr];
                leftCtr++;
            }
            System.out.println("Left sum: " + leftSum);

            int rightCtr = split;

            while (rightCtr < k) {
                rightSum += nums[size--];
                rightCtr++;
            }
            System.out.println("Right sum: " + rightSum);

            sum = leftSum + rightSum;
            System.out.println("Sum: " + sum);

            totalSum = Math.max(sum, totalSum);
            System.out.println("******************");
        }

        return totalSum;
    }


    private static int testMaxFourCardsV2(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }

        // Grab the total of the first k elements from the beginning
        int leftSum = 0;
        int totalSum = 0;
        for (int i = 0; i < k; i++) {
            leftSum += nums[i];
        }
        totalSum = leftSum;
        System.out.println("Total leftSum: " + leftSum);
        int rightSum = 0;
        int rightIndex = nums.length - 1;
        for (int i = k - 1; i >= 0; i--) {
            leftSum = leftSum - nums[i];
            rightSum = rightSum + nums[rightIndex];
            rightIndex--;
            int sum = leftSum + rightSum;
            totalSum = Math.max(totalSum, sum);
        }
        return totalSum;
    }


    private static int testLengthOfLongestSubStringV1(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] strArr = s.toCharArray();
        int maxLength = 0;

        for (int i = 0; i < strArr.length; i++) {
            Set<Character> visited = new HashSet<>();
            for (int j = i; j < strArr.length; j++) {
                if (visited.contains(strArr[j])) {
                    break;
                }
                int length = j - i;
                maxLength = Math.max(maxLength, length + 1);
                visited.add(strArr[j]);
            }
        }


        return maxLength;
    }

    private static int testLengthOfLongestSubStringV2(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] strArr = s.toCharArray();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < strArr.length) {
            char rightChar = strArr[right];
            if (map.containsKey(rightChar)) {
                int currentIndex = map.get(rightChar);
                if (currentIndex >= left) {
                    left = currentIndex + 1;
                }
            }
            int length = right - left;
            maxLength = Math.max(length + 1, maxLength);
            map.put(rightChar, right);
            right++;
        }
        return maxLength;
    }

    private static int testMaxConsecutiveOnesV1(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int ctr = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) {
                    ctr++;
                }
                if (ctr <= k) {
                    int length = j - i + 1;
                    maxCount = Math.max(length, maxCount);
                }
                if (ctr > k) {
                    break;
                }
            }
        }
        return maxCount;
    }


    private static int testMaxConsecutiveOnesV2(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int maxCount = 0;
        int left = 0;
        int right = 0;

        int zeroCtr = 0;
        while (right < nums.length) {

            int rightVal = nums[right];


            if (rightVal == 0) {
                zeroCtr++;
            }

            while (zeroCtr > k) {
                int leftVal = nums[left];
                if (leftVal == 0) {
                    zeroCtr--;
                }
                left++;
            }

            if (zeroCtr <= k) {
                int length = right - left;
                maxCount = Math.max(length + 1, maxCount);
            }
            right++;
        }
        return maxCount;
    }


    private static int testMaxConsecutiveOnesV3(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        int maxCount = 0;
        int left = 0;
        int right = 0;

        int zeroCtr = 0;
        while (right < nums.length) {

            int rightVal = nums[right];


            if (rightVal == 0) {
                zeroCtr++;
            }

            if (zeroCtr > k) {
                int leftVal = nums[left];
                if (leftVal == 0) {
                    zeroCtr--;
                }
                left++;
            }

            if (zeroCtr <= k) {
                int length = right - left;
                maxCount = Math.max(length + 1, maxCount);
            }
            right++;
        }
        return maxCount;
    }

    private static int testPickMaxFruitsV1(int[] fruits) {
        Set<Integer> hashSet = new HashSet<>();
        int maxLength = 0;

        for (int i = 0; i < fruits.length; i++) {
            hashSet = new HashSet<>();
            for (int j = i; j < fruits.length; j++) {
                hashSet.add(fruits[j]);
                if (hashSet.size() <= 2) {
                    int length = j - i;
                    maxLength = Math.max(maxLength, length + 1);
                } else {
                    break;
                }
            }
        }

        return maxLength;
    }


    private static int testPickMaxFruitsV2(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int maxCount = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> hashMap = new HashMap<>();

        while (right < nums.length) {
            int leftVal = nums[left];
            int rightVal = nums[right];
            hashMap.put(rightVal, hashMap.getOrDefault(rightVal, 0) + 1);
            if (hashMap.size() > 2) {
                int currLeft = leftVal;
                while (hashMap.size() > 2 && hashMap.get(currLeft) != null) {
                    int count = hashMap.get(currLeft);
                    if (count == 1) {
                        hashMap.remove(currLeft);
                    } else {
                        hashMap.put(currLeft, hashMap.get(currLeft) - 1);
                    }
                    currLeft = nums[++left];
                }
            }
            int length = right - left;
            maxCount = Math.max(length + 1, maxCount);
            right++;
        }
        return maxCount;
    }


    private static int testLongestSubStringLeastKRptCharsV1(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int maxLength = 0;
        char[] arr = s.toCharArray();
        Map<Character, Integer> map;

        boolean patternFound = true;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            map = new HashMap();
            for (int j = i; j < arr.length; j++) {
                char key = arr[j];

                map.put(key, map.getOrDefault(key, 0) + 1);
                sum = 0;
                patternFound = true;


                for (Map.Entry<Character, Integer> entry : map.entrySet()) {

                    char tmpKey = entry.getKey();
                    int tmpValue = entry.getValue();
                    System.out.println("################## j:" + j);
                    System.out.println("Key : " + tmpKey);
                    System.out.println("Value : " + tmpValue);

                    if (tmpValue < k) {
                        patternFound = false;
                        break;
                    } else {
                        sum += tmpValue;
                    }
                }
                if (patternFound) {
                    //maxLength += sum;
                    maxLength = Math.max(maxLength, sum);
                }
                System.out.println(map);
                System.out.println("patternFound: " + patternFound);
                System.out.println("maxLength: " + maxLength);
                System.out.println("##################");

            }

        }
        return maxLength;
    }

    private static int testLongestSubStringLeastKRptCharsV2(String s, int k) {
        int freq[] = new int[26];
        char[] str = s.toCharArray();
        for (char c : str) {
            int index = c - 'a';
            freq[index]++;
        }

        boolean valid = true;

        int start = 0;
        int maxLen = 0;
        for (int end = 0; end < s.length(); end++) {
            // TC -> O(N2)
            if (freq[str[end] - 'a'] > 0 && freq[str[end] - 'a'] < k) {
                String subString = s.substring(start, end);
                maxLen = Math.max(maxLen, testLongestSubStringLeastKRptCharsV2(subString, k));
                start = end + 1;
                valid = false;
            }
        }

        if (valid) {
            return s.length();
        } else {
            return Math.max(maxLen, testLongestSubStringLeastKRptCharsV2(s.substring(start), k));
        }
    }


    private static int testSubStringsWithAllThreeCharsV1(String s) {
        int maxTotal = 0;
        char[] arr = s.toCharArray();

        Set<Character> hashSet;

        for (int i = 0; i < arr.length - 2; i++) {
            hashSet = new HashSet<>();
            for (int j = i; j < arr.length; j++) {
                char curChar = arr[j];
                hashSet.add(curChar);

                if (hashSet.size() == 3) {
                    maxTotal++;
                }
            }

        }
        return maxTotal;
    }

    private static int testSubStringsWithAllThreeCharsV2(String s) {
        int maxLength = 0;
        char[] arr = s.toCharArray();

        Map<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            char currChar = arr[i];
            hashMap.put(currChar, i);

            if (hashMap.size() == 3) {
                int lValue = Integer.MAX_VALUE;
                char key = Character.MIN_VALUE;
                for (Entry<Character, Integer> mapEntry : hashMap.entrySet()) {
                    key = mapEntry.getKey();
                    int tmpValue = mapEntry.getValue();
                    lValue = Math.min(tmpValue, lValue);
                }
                System.out.println("Lowest key: " + key);
                System.out.println("Lowest value: " + lValue);
                maxLength += lValue + 1;
            }

        }
        return maxLength;
    }

    private static int testLongestRepeatingCharacterReplacementV1(String s, int k) {

        int maxLength = 0;
        char[] arr = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {  // TC : O(1) : Linear time
            map.put(arr[i], i);
        }
        System.out.println("HashMap: " + map);

        for (int i = 0; i < arr.length; i++) {      // TC : O(n)
            for (Map.Entry<Character, Integer> entry : map.entrySet()) { // TC: O(26) => O(1)
                int replCount = 0;
                int sum = 0;
                char primary = entry.getKey();
                System.out.println("Primary: " + primary);

                for (int j = i; j < arr.length; j++) {  // TC : O(n)

                    if (arr[j] != primary && replCount >= k) {
                        break;
                    }

                    if (arr[j] != primary) {
                        replCount++;
                    }

                    sum = j - i;
                    maxLength = Math.max(sum + 1, maxLength);
                }
            }
        }
        return maxLength;
    }

    private static int testLongestRepeatingCharacterReplacementV2(String s, int k) {
        // AABABBA
        int maxLength = 0;
        char[] arr = s.toCharArray();

        Map<Character, Integer> map;

        for (int i = 0; i < arr.length; i++) {   // TC : O(n)

            int maxFrequency = 0;
            int sum = 0;
            int changes = 0;
            map = new HashMap<>();

            for (int j = i; j < arr.length; j++) { // TC : O(n)
                char currentChar = arr[j];
                map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);

                int frequency = map.get(currentChar);
                maxFrequency = Math.max(frequency, maxFrequency);
                sum = j - i + 1;

                /*
                    Sum = Length of the string
                    Frequency : Highest # of occurrences of any given character in the string compared to others
                    Changes = Length - highest frequency = Diff representing the max characters that can be replaced
                              If the changes is <= k, then we can continue to traverse the string char [] else break;
                 */
                changes = sum - maxFrequency;

                if (changes <= k) {
                    maxLength = Math.max(sum, maxLength);
                } else {
                    break;
                }

            }

        }
        return maxLength;
    }

    private static int testLongestRepeatingCharacterReplacementV3(String s, int k) {
        int maxLength = 0;
        int maxFrequency = 0;
        int left = 0;
        int right = 0;
        char[] arr = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        while (right < arr.length) {  // TC O(n)
            char currRightChar = arr[right];
            map.put(currRightChar, map.getOrDefault(currRightChar, 0) + 1);

            int frequency = map.get(currRightChar);
            maxFrequency = Math.max(frequency, maxFrequency);

            // Shrink the window
            if ((right - left + 1) - maxFrequency > k) {
                char leftChar = arr[left];
                map.put(leftChar, map.get(leftChar) - 1);
                left = left + 1;
            }
            int length = right - left + 1;
            maxLength = Math.max(length, maxLength);
            right++;
        }
        return maxLength;
    }


    private static int testBinarySubArrayWithSumV1(int[] nums, int goal) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == goal) {
                    maxLength += 1;
                }
            }
        }
        return maxLength;
    }

    private static int testBinarySubArrayWithSumV2(int[] nums, int goal) {
        int maxLength = 0;
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1); // Handles the case when the current num[i] elment = goal

        for (int curValue : nums) {
            sum += curValue;
            if (map.containsKey(sum - goal)) {
                maxLength += map.get(sum - goal);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return maxLength;
    }


    private static int testNiceSubArraysV2(int[] nums, int k) {
        // Prefix sum using HashMap
        int maxLength = 0;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int sum = 0;
        prefixMap.put(0, 1); // Handles use case when the nums[0] itself == k
        for (int i = 0; i < nums.length; i++) {
            int curVal = nums[i];
            sum += curVal % 2;
            int key = sum - k;
            if (prefixMap.containsKey(key)) {
                maxLength += prefixMap.get(key);
            }
            prefixMap.put(sum, prefixMap.getOrDefault(sum, 0) + 1);
        }
        System.out.println(prefixMap);
        return maxLength;
    }

    private static int testNiceSubArraysV1(int[] nums, int k) {
        // Brute force
        int maxLength = 0;
        for (int i=0; i< nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                int currVal = nums[j];
                if (currVal % 2 != 0) {
                    sum += 1;
                }

                if (sum == k) {
                    maxLength++;
                }
            }
        }
        return maxLength;
    }

    private static int testSubArrayWithKDiffIntV1(int[] nums, int k) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                int curVal = nums[j];
                set.add(curVal);

                if (set.size() == k) {
                    maxLength++;
                }

                if (!set.contains(curVal) && set.size() ==k) {
                    break;
                }
            }
        }

        return maxLength;
    }

    private static int testSubArrayWithKDiffIntV2(int[] nums, int k) {
        int maxK = slidingWindowAtMost(nums, k);
        int minK = slidingWindowAtMost(nums, k -1);

        return maxK - minK;
    }

    private static int slidingWindowAtMost(int[] nums, int k) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        while (right < nums.length) {
            int curElement = nums[right];
            freqMap.put(curElement, freqMap.getOrDefault(curElement, 0) + 1);

            while(freqMap.size() > k) {
                int leftKey = freqMap.get(nums[left]);
                if (leftKey == 1) {
                    freqMap.remove(nums[left]);
                } else {
                    freqMap.put(nums[left], --leftKey);
                }
                left++;
            }
            // formulae = maxLength += window size
            // window size = right - left + 1;
            maxLength += right - left + 1;
            right++;
        }
        return maxLength;
    }

    private static String testMinimumWindowSubStringV3(String s, String t) {
        // s = d d a a a b b c a
        // t = a b c
        char [] tArr = t.toCharArray();
        char [] sArr = s.toCharArray();

        int left = 0;
        int right = 0;

        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;

        int count = 0;

        String result = "";

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : tArr) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        while (right < sArr.length) {   // TC O(n)
            char currChar = sArr[right];
            int currFreq = tMap.getOrDefault(currChar, 0);

            if (currFreq > 0) {
                count ++;
            }
            tMap.put(currChar, tMap.getOrDefault(currChar, 0) -1);


            while (count == t.length()) {  // TC O(max n iterations in the whole process)

                if (minLength > (right - left) + 1) {
                    minLength = (right - left) + 1;
                    startIndex = left;
                }

                char currLeftChar = sArr[left];
                tMap.put(currLeftChar, tMap.get(currLeftChar) + 1);
                int currLefFreq = tMap.get(currLeftChar);
                left++;

                if (currLefFreq > 0) {
                    count = count - 1;
                }
            }
            right++;
        }

        result = startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLength);
        return result;

    }

    private static String testMinimumWindowSubStringV2(String s, String t) {
        char [] tArr = t.toCharArray();
        char [] sArr = s.toCharArray();

        int left = 0;
        int right = 0;

        String minSubString = "";


        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : tArr) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> visited = new HashMap<>();
        for (char c : tArr) {
            visited.put(c, visited.getOrDefault(c, 0) + 1);
        }

        while (right < s.length()) {
            char currChar = sArr[right];

            if (visited.containsKey(currChar)) {
                int count = visited.get(currChar);
                count = count - 1;

                if (count == 0) {
                    visited.remove(currChar);
                } else {
                    visited.put(currChar, count);
                }
            }

            if (visited.isEmpty()) {
                String tmp = s.substring(left, right + 1);

                if (minSubString.isEmpty()) {
                    minSubString = tmp;
                } else {
                    minSubString = tmp.length() <= minSubString.length() ? tmp : minSubString;
                }

                visited = new HashMap<>();

                for (char c : tArr) {
                    visited.put(c, visited.getOrDefault(c, 0) + 1);
                }
                boolean isInitialized = false;

                if (left + 1 < sArr.length) {
                    while (!isInitialized && left + 1< sArr.length) {
                        left++;
                        char tmpChar = sArr[left];

                        if (tMap.containsKey(tmpChar)) {
                            isInitialized = true;
                        }
                    }
                    right = left - 1;
                }

            }
            right++;

        }
        return minSubString;

    }

    private static String testMinimumWindowSubStringV1(String s, String t) {
        char [] sArr = s.toCharArray();
        char [] tArr = t.toCharArray();


        Map<Character,Integer> tMap = new HashMap<>();
        String minSubString = "";

        for (int i = 0; i < sArr.length; i++) {  // TC O(n)
            int beginIndex = -1;
            for (char t1 : tArr) {  // TC O(m)
                tMap.put(t1, tMap.getOrDefault(t1, 0) + 1);
            }

            for (int j = i; j < sArr.length; j++) { // TC O(n)
                char currChar = sArr[j];
                if (tMap.containsKey(currChar)) {
                    int count = tMap.get(currChar);
                    count = count - 1;

                    if (count == 0) {
                        tMap.remove(currChar);
                    } else {
                        tMap.put(currChar, count);
                    }
                    if (beginIndex == -1) {
                        beginIndex = j;
                    }
                }
                if (tMap.isEmpty()) {
                    String tmp = s.substring(beginIndex, j+1);

                    if (minSubString.equals("")) {
                        minSubString = tmp;
                    } else {
                        if (tmp.length() <= minSubString.length()) {
                            minSubString = tmp;
                        }
                    }
                    break;
                }
            }
        }
        return minSubString;
    }

    private static int testMinimumSizeSubArraySum(int[] nums, int target) {
        int minLength = Integer.MAX_VALUE;
        int length = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length) {
            int currVal = nums[right];

            sum += currVal;

            while (sum >= target) { // Shrink the window
                length = (right - left) + 1;
                minLength = Math.min(minLength, length);
                int tmpVal = nums[left];
                sum -= tmpVal;
                left++;
            }
            right++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    private static int testSubArrayDivisibleByK(int[] nums, int k) {
        int maxCount = 0;
        int sum = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        freqMap.put(0, 1); // Accounts for the element itself meeting the condition

        for (int curVal : nums) {
            sum += curVal;

            int reminder = sum % k;

            if (reminder < 0) { // Critical part of the problem
               reminder += k;
            }

            if (freqMap.containsKey(reminder)) {
                maxCount += freqMap.get(reminder);
            }

            freqMap.put(reminder, freqMap.getOrDefault(reminder, 0) + 1);
        }
        return maxCount;
    }

    private static void testMergeTwoSortedArrayV1(int[] nums1, int m, int[] nums2, int n) {
        int [] tmp = new int[m + n];
        int counter = 0;

        int i = 0;
        int j = 0;

        while (i < m && j < n) {

            if (nums1[i] <= nums2[j]) {
                tmp[counter] = nums1[i];
                i++;
                counter++;
            } else {
                tmp[counter] = nums2[j];
                j++;
                counter++;
            }
        }

        while (i < m) {
            tmp[counter] = nums1[i];
            i++;
            counter++;
        }

        while (j < n) {
            tmp[counter] = nums2[j];
            j++;
            counter++;
        }

        System.arraycopy(tmp, 0, nums1, 0, tmp.length);
    }


    public static void main(String[] args) {
        System.out.println("SlidingWindowANDTwoPointers");

        System.out.println("*************** Sliding window problems Two pointer approach problems ****************************");

        /*
            Properties of Sliding window problems :
                1. Typically working with contiguous sub arrays
                2. Maintain a window of fixed size
                3. Avoid recalculating everything. Add new element and remove old one
                4. Uses liner time
         */
        int[] nums = {1, 12, -5, -6, 50, 3};
        int k = 4;
        // Example: Find the max sum of any subarray of size k
        int maxAvg = testMaxConsecutiveAvg(nums, k);
        System.out.println("maxAvg : " + maxAvg);

        // Example: Longest substring with at most 2 distinct characters
        // Given a string s, find the length of the longest substring without repeating characters

        String input = "abcabcbb";
        var result = testMaxSubStringNonRepeatingChars(input);
        System.out.println("testMaxSubStringNonRepeatingChars: " + result);

        // 560. Subarray Sum Equals K
        nums = new int[]{4, 2, 1, 6, 3, 1, 2};
        k = 12;
        int count = testSubArraySumV1(nums, k);
        System.out.println("testSubArraySum V1: " + count);

        System.out.println("*******************************************");
        nums = new int[]{6, 2, 3, 4, 7, 2, 1, 7, 1};
        k = 4;

        // Leet code 1423. Maximum Points You Can Obtain from Cards

        /*
            Brute force approach
            Time complexity: O(k ^ 2) : For each pass in the outer loop of k elements, it nests an inner while loop which again iterates k times
                                        O(k) * O(k) = O(k ^ 2)

            Space complexity: O(1) : No extra space used
         */

        int maxSum = testMaxFourCardsV1(nums, k);
        System.out.println("testMaxFourCards: V1 " + maxSum);

        /*
            Sliding window problem

            Time complexity: O(k) : Single pass over the array for total k elements from wither side

            Space complexity: O(1) : No extra space used
         */
        nums = new int[]{96, 90, 41, 82, 39, 74, 64, 50, 30};
        k = 8;
        maxSum = testMaxFourCardsV2(nums, k);
        System.out.println("testMaxFourCards: V2 " + maxSum);


        // Leet code 3. Longest Substring Without Repeating Characters
        //String s = "au";
        //String s = "abcabcbb";
        String s = "pwwkew";

        /*
            Brute force approach

            Time complexity: O(n ^ 2): For each character of the string in the outer loop the inner loop runs for the length of the string
                                        O(n) * O(n) = O(n ^ 2)

            Space complexity: O(256): Extra hashSet used to store the max ASCII 256 characters could be present in a string
         */
        int strLength = testLengthOfLongestSubStringV1(s);
        System.out.println("strLength V1: " + strLength);

        /*
                Two pointer approach

                Time complexity: O(n) : Does a single pass over the char []

                Space complexity: O(256) : Stores the map of last visited and the index the char was seen
         */

        strLength = testLengthOfLongestSubStringV2(s);
        System.out.println("strLength V2: " + strLength);


        // Leet code 1004. Max Consecutive Ones III

        //   nums = new int[] {1,1,1,0,0,0,1,1,1,1,0};
        //   k = 2;

        //  nums = new int[] {1,1,1,0,0,0,1,1,1,1,0};
        // k = 0;


        //  nums = new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        //  k = 3;

        // 0,0,0,1
        nums = new int[]{0, 0, 0, 1};
        k = 4;
        /*
            Brute force approach
            Time complexity:  O(n ^ 2) ; Nested loop which runs n times for each n of the outer loop

            Space complexity: O(1) : No extra space used
         */
        int maxConsOnes = testMaxConsecutiveOnesV1(nums, k);
        System.out.println("maxConsOnes V1: " + maxConsOnes);

        /*
            Sliding window using two pointers
            Time complexity: O(n + n) : Outer while loop runs n times and the inner loop at most runs n times in the whole ietration cycle.
                                        Inner loop does not run n times for each run of the outer while loop (pointer r)

            Space complexity: O(1) : No extra space required.
         */
        maxConsOnes = testMaxConsecutiveOnesV2(nums, k);
        System.out.println("maxConsOnes V2: " + maxConsOnes);

        /*
            Sliding window using 2 pointers

            Time complexity: O(n) : Does a single pass over the n elements on the nums[]
                                    Performs better than V2 option coz :
                                        => when the zeroCtr > k, the left pointer does not iterate all the way till it finds a zero and increment it
                                           to its new position
                                        => the left pointer just moves by 1 position and if the element at the position is a ZERO then it decreases the zerCtr by 1
                                        => the maxCount position is only updates when the zeroCtr is <= k

            Space complexity: O(1) : No extra space used
         */
        maxConsOnes = testMaxConsecutiveOnesV3(nums, k);
        System.out.println("maxConsOnes V3: " + maxConsOnes);

        // Leet code : 904. Fruit Into Baskets
        // nums = new int[] {3,3,3,1,2,1,1,2,3,3,4};
        //  nums = new int[] {5,2,5,2,0,3};
        nums = new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3};

        /*
            Brute force approach
            Time complexity: O(n ^ 2) : Inner loop of nums[] run n times for each iteration of the outer loop
                                      : Set add operations are of log n time, but it runs only for max 2 elements which can be ignored

            Space complexity: O(1): No extra space required.
                                    Set is used to store 2 elements at max, the size of which can be ignored.
         */
        int longestSubArr = testPickMaxFruitsV1(nums);
        System.out.println("longestSubArr V1:" + longestSubArr);

        /*
            // Sliding window, two pointer approach
            Time complexity: O(n + n); Single pass iteration of the nums[]
                                   Add operation on the HashMap takes O(log n) time but its extremely small and can be ignored
                                   The inner while loop for left pointer run for max n times in totality.
                                   Hence the total time complexity is : O(n + n)

            Space complexity: O(3) : At max only e elements are stored in the HashMap at any given time;
         */

        longestSubArr = testPickMaxFruitsV2(nums);
        System.out.println("longestSubArr V2:" + longestSubArr);

        // Leet code 395. Longest Substring with At Least K Repeating Characters

        //s = "ababbc";
        //k = 2;
        s = "bbaaacbd";
        k = 3;
        // s = "ababacb";
        // k = 3;

        /*
            Time complexity: O(n ^ 2): Inner loop iterates n times for each n elements of the outer array.
                                       Map get operation works on O(1) constant time. Total time complexity can be concluded to O(n ^ 2)

            Space complexity: O(26) : HashMap string max 26 keys. Can be ignored and concluded to : O(1)
        */
        int longRepeatChar = testLongestSubStringLeastKRptCharsV1(s, k);
        System.out.println("longRepeatChar V1: " + longRepeatChar);

        // TODO : Revisit the implementation
        longRepeatChar = testLongestSubStringLeastKRptCharsV2(s, k);
        System.out.println("longRepeatChar V2: " + longRepeatChar);

        // Leet code 1358. Number of Substrings Containing All Three Characters

        s = "abcabc";

        /*
            Brute force approach

            Time complexity: O(n ^ 2) : Inner loop iterates n times for each element of the out loop in the String char array

            Space complexity: O(3): Max 3 characters in the hash set at any given time. Can be concluded to O(1)
         */
        int numOfSubStrings = testSubStringsWithAllThreeCharsV1(s);
        System.out.println("numOfSubStrings: V1: " + numOfSubStrings);

        /*
            Partial Brute force approach

            Time complexity: O(n) : Single pass over the String char array. Map is traversed 3 times for checking the minimum the time for which can be excluded

            Space complexity: O(1) : No extra space required except the HashMap storing max 3 elements. Fractional memory used anc can be ignored
         */
        numOfSubStrings = testSubStringsWithAllThreeCharsV2(s);
        System.out.println("numOfSubStrings: V2: " + numOfSubStrings);

        // Leet code 424. Longest Repeating Character Replacement

        //    s = "AABABBA";
        //   k = 2;

        //  s= "ABAA";
        //  k = 0;

        /*
            Brute force approach
            Time complexity: O(n ^ 2):  Inner loop run n times for each iteration of the outer loop of char[] elements
                                        Iteration over the map here, but at max the complexity is O(26), for each unique char, hence can be ignored
                                        Adding elements to the map takes linear time and is not part of the nested loop

            Space complexity: O(26) : Negligible space for adding at max 26 characters
         */
        int longestStrLength = testLongestRepeatingCharacterReplacementV1(s, k);
        System.out.println("longestStrLength : V1 " + longestStrLength);

        /*
            Brute force approach
            Time complexity : O(n ^ 2) : Inner loop run n times for each iteration of the outer loop of char[] elements

            Space complexity : O(26) : Negligible space for adding at max 26 characters
         */
        longestStrLength = testLongestRepeatingCharacterReplacementV2(s, k);
        System.out.println("longestStrLength : V2 " + longestStrLength);

        /*
            Sliding window, 2 pointer approach

            Time complexity: O(n) : Outer loop runs n times and there is no  inner loop
                                        Hence concludes to : O(n)
            Space complexity: O(26) : Map contains a max of 26 keys. Fractional storage cost and can be ignored to conclude : O(1)
         */
        longestStrLength = testLongestRepeatingCharacterReplacementV3(s, k);
        System.out.println("longestStrLength : V3 " + longestStrLength);

        // Leet code 930. Binary Subarrays With Sum
        nums = new int[]{1, 0, 1, 0, 1};
        int goal = 2;

        /*
            Brute force approach
            Time complexity: O(n ^2) : Nested loops, where the inner runs n times for each iteration of outer nums[]

            Space complexity: O(1) : No extra space required
         */
        int goalCount = testBinarySubArrayWithSumV1(nums, goal);
        System.out.println("goalCount V1: " + goalCount);

        /*
             Uses Pre fix sum sliding window technique

             Time complexity: O(n) : Linear time as the control loops over the nums[] once

             Space complexity: O(n) : Map stores a sum of n pre fix elements.
                                      All Map operation take a constant time of O(1) on avg so can be ignored

         */

        goalCount = testBinarySubArrayWithSumV2(nums, goal);
        System.out.println("goalCount V2: " + goalCount);

        // Leet code 1248. Count Number of Nice Subarrays
        nums = new int[] {1,1,2,1,1};
        k = 3;

        /*
            Brute force approach

            Time complexity: O(n ^ 2): Two loops run in a nested within each other for nums [] having n elements : O(n ^2)

            Space complexity: O(1): No extra space required

         */
        int niceCount = testNiceSubArraysV1(nums, k);
        System.out.println("niceCount: V1" + niceCount);

        /*
            Prefix sum approach

            Time complexity: O(n): Does a single pass over the n elements of the array. Assuming Map put/get operation adding O(1) to the time complexity, which can be ignored

            Space complexity: O(n): Map used to store the sum for each ith level of the nums []
         */
        niceCount = testNiceSubArraysV2(nums, k);
        System.out.println("niceCount: V2" + niceCount);

        // Leet code 992. Subarrays with K Different Integers
        nums = new int[] {1,2,1,2,3};
        k = 2;
        /*
            Brute force approach

            Time complexity: O(n ^ 2): Nested loops runs (n * n) times to accomplish the brute force approach

            Space complexity: O(n) : Worst case to store n unique element from the nums []
         */
        int kDiffInts = testSubArrayWithKDiffIntV1(nums, k);
        System.out.println("kDiffInts : V1: " + kDiffInts);


        /*
            Sliding window

            Approach here is to use a sliding window and keep increasing the right counter till the frequencyMap does not have more than k elements
            Once the frequencyMap size is breached, it's time to shrink the window till the size == k
            At any time when the frequencyMap has exact k elements the max elements can be calculated using the formula

            maxLength = maxLength + window size
            windowSize = right - left + 1;


            Time complexity: O(n + n)
                              Right pointer is moving a max of n times using the outer while loop
                              Inner while loop for the left counter at most a total of n times for the whole execution
                              Map get/put operation is assumed to take O(1) constant time can be ignored

            Space complexity: O(n)
                                Map storing the frequency of all it unique n elements of the nums []
         */

        kDiffInts = testSubArrayWithKDiffIntV2(nums, k);
        System.out.println("kDiffInts : V2: " + kDiffInts);


        // Leet code : 76. Minimum Window Substring

        s = "ADOBECODEBANC";
        String t = "ABC";

       s = "ddaaabbca";
       t = "abc";
        /*
            Brute force approach

            Time complexity: O(n ^ 2  + m) : Nested loops for looping through the string s char [] + a nested loop of m char[] for string t

            Space complexity: O(m) : Extra storage required to store the frequency of m elements of string t char []
         */
        String minStr = testMinimumWindowSubStringV1(s, t);
        System.out.println("minStr: V1: " + minStr);

        /*
            Two pointer approach

            Time complexity: ??

            Space complexity: ??
         */

        minStr = testMinimumWindowSubStringV2(s, t);
        System.out.println("minStr: V2: " + minStr);

        /*
            Two pointer, sliding window approach
            Time complexity: O(2n + m) : Right pointer loops n times and the left pointer iterates a max of n times => O(n + n)
                             O(m) : Loop to initialize the frequency map for m elements of string t char []

            Space complexity: O(m) : Extra space required to store the frequency of each m elements of string t char []
         */
        minStr = testMinimumWindowSubStringV3(s, t);
        System.out.println("minStr: V3: " + minStr);

        // Leet code: 209. Minimum Size Subarray Sum
        nums = new int[] {2,3,1,2,4,3};
        k = 7;

        /*
            Time complexity: O(n): Outer while loop runs n times and the inner left pointer moves at most n times in the whole computation
                                    Ignoring the inner n iteration overall the complexity is concluded to be O(n)

            Space complexity: O(1): No extra compute space required
         */
        int minSubArrSize = testMinimumSizeSubArraySum(nums, k);
        System.out.println("minSubArrSize: " + minSubArrSize);

        // Leet code: 974. Subarray Sums Divisible by K
        nums = new int[] {4,5,0,-2,-3,1};
        k = 5;

        /*
            Time complexity: O(n): Does a single pass over the nums []

            Space complexity: O(n): Stores the frequency map of the sum computed at each iteration
         */
        int subArrSums = testSubArrayDivisibleByK(nums, k);
        System.out.println("subArrSums: " + subArrSums);

        // Leet code 88. Merge Sorted Array
        int [] nums1 = new int[] {1,2,3,0,0,0};
        int [] nums2 = new int[] {2,5,6};
        int m = 3;
        int n = 3;
        /*
            Approach: Use the approach of using Merge sort when merging two sorted arrays
            Time complexity: O(m + n): m and n are the length of the nums1[] and nums2[]. Each element is traversed once
            Space complexity:O(m + n): Extra tmp[] array used extra space to store m + n elements of nums1[] an nums2[]
         */
        testMergeTwoSortedArrayV1(nums1, m, nums2, n);
        System.out.println("testMergeTwoSortedArray V1: " + Arrays.toString(nums1));

        nums1 = new int[] {1,2,3,0,0,0};
        nums2 = new int[] {2,5,6};
        m = 3;
        n = 3;

        /*
            Using in place update of num1[]. No extra space required

            Time complexity: O(m + n) m and n are the length of the nums1[] and nums2[]. Each element is traversed once

            Space complexity: O(1): Constant space. Uses inplace replacement of nums1[]
         */
        testMergeTwoSortedArrayV2(nums1, m, nums2, n);
        System.out.println("testMergeTwoSortedArrayV2: " + Arrays.toString(nums1));



    }

    private static void testMergeTwoSortedArrayV2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n -1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }


}
