package org.self.yahoo.leetcode.hashTables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class Codec {
    final String BASE_URL = "http://tinyurl.com";
    Map<Integer, String> codecMap = new HashMap<>();
    int counter = 0;

    public String encode(String longURL) {
        if (longURL == null || longURL.isBlank()) {
            return longURL;
        }
        codecMap.put(counter, longURL);
        return BASE_URL + counter++;
    }

    public String decode(String shortURL) {
        if (shortURL == null || shortURL.isBlank()) {
            return shortURL;
        }
        int id = Integer.parseInt(shortURL.replace(BASE_URL, ""));
        System.out.println("Map id: " + id);
        System.out.println("Map contents: " + codecMap);
        return codecMap.get(id);
    }
}

class CodecV2 {
    final String BASE_URL = "http://tinyurl.com";
    Map<String, String> codecMap = new HashMap<>();
    private final String base62Encoding = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int KEY_LENGTH = 6;

    public String encode(String longUrl) {
        if (longUrl == null && longUrl.isBlank()) {
            return longUrl;
        }
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        do {
            for (int i = 0; i < KEY_LENGTH; i++) {
                key.append(base62Encoding.charAt(random.nextInt(base62Encoding.length())));
            }
        } while (codecMap.containsKey(key.toString()));

        codecMap.put(key.toString(), longUrl);
        return BASE_URL + key.toString();
    }

    public String decode(String shortUrl) {
        if (shortUrl == null && shortUrl.isBlank()) {
            return shortUrl;
        }
        String key = shortUrl.replace(BASE_URL, "");
        return codecMap.get(key);
    }

}


public class Medium {
    private static void testEncodeDecodeV1() {
        Codec codec = new Codec();
        String sampleUrl1 = "www.yahoo.com";

        var shortUrl = codec.encode(sampleUrl1);
        System.out.println("shortUrl V1: " + shortUrl);
        var longUrl = codec.decode(shortUrl);
        System.out.println("longUrl V1: " + longUrl);
        System.out.println(longUrl.equals(sampleUrl1));
    }

    private static void testEncodeDecodeV2() {
        CodecV2 codecV2 = new CodecV2();
        String sampleUrl1 = "www.google.com";

        var shortUrl = codecV2.encode(sampleUrl1);
        System.out.println("shortUrl V2: " + shortUrl);
        var longUrl = codecV2.decode(shortUrl);
        System.out.println("longUrl V2: " + longUrl);
        System.out.println(longUrl.equals(sampleUrl1));
    }


    private static int testConsecutiveSequenceV1(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            treeSet.add(nums[i]);
        }
        int prev = Integer.MIN_VALUE;
        int seqCount = 0;
        int globalSeqCount = 0;

        for (int num : treeSet) {
            if (prev + 1 == num) { // Match found which are in sequence
                seqCount++;
            } else { // Sequence is broken here
                if (seqCount > globalSeqCount) {
                    globalSeqCount = seqCount;
                }
                seqCount = 0;
            }
            prev = num;
        }
        if (seqCount > globalSeqCount) {
            globalSeqCount = seqCount;
        }
        return globalSeqCount + 1;
    }

    private static int testConsecutiveSequenceV2(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        Map<Integer, Boolean> hashMap = new HashMap<>();

        // Initialize all elements lookup value to false;
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], false);
        }
        int globalSeqCount = 0;
        int seqCount = 0;

        for (int num : nums) {
            seqCount = 1;

            // Look for number with increments of 1
            int tmpNum = num + 1;
            while (hashMap.containsKey(tmpNum) && !hashMap.get(tmpNum)) {
                hashMap.put(tmpNum, true);
                seqCount++;
                tmpNum++;
            }

            // Look for number with decrements of 1

            tmpNum = num - 1;
            while (hashMap.containsKey(tmpNum) && !hashMap.get(tmpNum)) {
                hashMap.put(tmpNum, true);
                seqCount++;
                tmpNum--;
            }

            globalSeqCount = Math.max(globalSeqCount, seqCount);
        }
        return globalSeqCount;
    }


    private static Object testGroupAnagramsV1(String[] strs) {

        List<List<String>> list = new ArrayList<>();
        Map<String, Boolean> hashMap = new HashMap<>();

        Arrays.sort(strs);
        System.out.println("Sorted Array: " + Arrays.toString(strs));

        // Initialize map with all visited flag as : FALSE
        for (int i = 0; i < strs.length; i++) {
            hashMap.put(strs[i], false);
        }

        // Loops through the str [] element and looks for anagrams;
        for (int i = 0; i < strs.length; i++) {
            String currElement = strs[i];

            List<String> annagramList = new ArrayList<>();

            if (!hashMap.get(currElement)) { // Element has not been traversed
                hashMap.put(currElement, true);
                annagramList.add(currElement);

                for (int j = i + 1; j < strs.length; j++) {
                    String tmpElement = strs[j];
                    if (tmpElement.length() == currElement.length()) {
                        boolean isMatch = true;

                        Map<Character, Integer> charMap = new HashMap<>();
                        Map<Character, Integer> parentCharMap = new HashMap<>();

                        for (int x = 0; x < tmpElement.length(); x++) {
                            char c = tmpElement.charAt(x);
                            charMap.put(c, charMap.getOrDefault(c, 0) + 1);

                            char c1 = currElement.charAt(x);
                            parentCharMap.put(c1, parentCharMap.getOrDefault(c1, 0) + 1);
                        }

                        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
                            char key = entry.getKey();
                            int value = entry.getValue();

                            int value2 = parentCharMap.getOrDefault(key, 0);

                            if (value != value2) {
                                isMatch = false;
                            }
                        }

                        // If anagram match found then update the list entry with the new element
                        if (isMatch) {
                            hashMap.put(tmpElement, true);
                            annagramList.add(tmpElement);
                        }
                    }

                }

            }
            if (!annagramList.isEmpty()) {
                list.add(annagramList);
            }
        }
        return list;
    }

    private static List<List<String>> testGroupAnagramsV2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List> hashMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String element : strs) {
            char[] charArr = element.toCharArray();
            Arrays.sort(charArr);
            var sortedString = new String(charArr);

            var list = hashMap.getOrDefault(sortedString, new ArrayList<String>());
            list.add(element);
            hashMap.put(sortedString, list);
        }

        for (var entry : hashMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }


    private static String testReorganiseString(String s) {
        char[] arr = s.toCharArray();

        Map<Character, Integer> freqMap = new HashMap<>();
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt((Pair p) -> p.freq).reversed());


        for (char item : arr) {
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
            int curFeq = freqMap.get(item);

            if (curFeq > (arr.length + 1) / 2) {
                return "";
            }
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char item = entry.getKey();
            int freq = entry.getValue();
            Pair pair = new Pair(freq, item);
            queue.add(pair);

        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            Pair first = queue.poll();
            Pair second = queue.poll();

            if (first != null) {
                stringBuilder.append(first.c);
                int freq = first.freq;
                freq = freq - 1;
                if (freq != 0) {
                    Pair p = new Pair(freq, first.c);
                    queue.add(p);
                }

            }

            if (second != null) {
                stringBuilder.append(second.c);
                int freq = second.freq;
                freq = freq - 1;
                if (freq != 0) {
                    Pair p = new Pair(freq, second.c);
                    queue.add(p);
                }
            }
        }
        return stringBuilder.toString();
    }




    private static int testNumberOfGoodWaysToSplitString(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int goodSplits = 0;
        Map<Character, Integer> leftFreqMap = new HashMap<>(); // Stores only frequency of Unique elements : O(26)
        Map<Character, Integer> rightFreqMap = new HashMap<>();

        int left = 0;
        int right = s.length();


        char currChar = s.charAt(left);
        leftFreqMap.put(currChar, leftFreqMap.getOrDefault(currChar, 0) + 1);
        left++;


        while (right > left) { // O(n)
            currChar = s.charAt(right -1);
            rightFreqMap.put(currChar, rightFreqMap.getOrDefault(currChar, 0) + 1); // O(1)
            right--;
        }



        if (leftFreqMap.size() == rightFreqMap.size()) {
            goodSplits++;
        }

        left--;

        while(left < s.length() && right < s.length()) { // O(n)
            currChar = s.charAt(right);
            leftFreqMap.put(currChar, leftFreqMap.getOrDefault(currChar, 0) + 1); // O(1)

            int freq = rightFreqMap.get(currChar);
            freq = freq - 1;
            if (freq == 0) {
                rightFreqMap.remove(currChar); // O(1)
            } else {
                rightFreqMap.put(currChar, freq); // O(1)
            }


            if (leftFreqMap.size() == rightFreqMap.size()) {
                goodSplits++;
            }
            left++;
            right++;
        }
        return goodSplits;
    }

    private static int testNumberOfMatchingSubsequencesV1(String s, String[] words) {
        Map<Character, List<Integer>> freqMap = new HashMap<>();
        char [] strArr = s.toCharArray();

        int matchCount = 0;

        for (int i = 0; i < strArr.length; i++) {  // O(n)
            char c = strArr[i];
            List<Integer> list = freqMap.getOrDefault(c, new ArrayList<>()); // O(1)
            list.add(i); // O(n)
            freqMap.put(c, list); // O(n)
        }

        System.out.println("Frequency Map: " + freqMap);

        for(String temp : words) { // O(k)

            char[] arr = temp.toCharArray();
            int lastIndex = -1;
            int ctr = 0;

            for (int j = 0; j < arr.length; j++) { // O(m)
                char currChar = arr[j];

                if (freqMap.containsKey(currChar)) {
                    var list = freqMap.get(currChar);

                    for (int index: list) { // O(n) worst case
                        if (index > lastIndex) {
                            lastIndex = index;
                            ctr++;
                            break;
                        }
                    }
                }
            }

            if (ctr == arr.length) {
                matchCount+= 1;
            }

        }
        return matchCount;
    }



    public static void main(String[] args) {
        System.out.println("Hash tables : Medium....");
        System.out.println("********************* Encode and Decode TinyURL *******************");
        // 535. Leet code Encode and Decode TinyURL

        /*
                Time complexity: O(1) : Read and Write to a HashMap takes O(1) time

               Space complexity: O(n) : Space required to stored the n url mappings in the Codec map
        */
        testEncodeDecodeV1();

        /*
            Time complexity : O(1) : Constant time for both encode() and decode()

            Space complexity: O(n) Space required to stored the n url mappings in the Codec map
         */
        testEncodeDecodeV2();

        System.out.println("********************* Longest Consecutive Sequence *******************");
        // Leet code 128. Longest Consecutive Sequence
        int[] nums = {100, 4, 200, 1, 3, 2};
        /*
            Time complexity: O(n + n) :
                                Passes over the loop 2 times, fill the map + iterate over the map : O(n + n) => O(n)
                                Adding items to TreeMap : O(n log n) + O(n) => O(n log n)

            Space complexity: O(n) : Treemap to store sorted n values
         */
        int numConsSeq = testConsecutiveSequenceV1(nums);
        System.out.println("numConsSeq V1: " + numConsSeq);

        /*
              Time complexity: O(n) :
                         Two passes on n elements. Loads the Map with n elements + Iterating the nums array of n elements O(n + n) = O(n)


              Space complexity: O(n) : Map to store n elements
         */
        numConsSeq = testConsecutiveSequenceV2(nums);
        System.out.println("numConsSeq V2: " + numConsSeq);

        System.out.println("********************* Group Anagrams *******************");
        // Leet code 49. Group Anagrams
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        /*
                Brute force approach
                Time complexity: O(n + m)^ 2 : Loops over each strs element and for each elements loops over the entire array again looking for anagrams
                                               n = n number of elements
                                               m = String of max length : m iteration looking for character count match

                Space complexity: O(n + m) : Map to store n elements  + ArrayList to store m anagrams strings
         */
        var list = testGroupAnagramsV1(strs);
        System.out.println("testGroupAnagrams V1: " + list);

        /*
            Time complexity: O(n * m log m): Sorting complexity : m log m * n elements in the str array  => O(n * m log m)
                            Sorting is done n times with a time complexity of O(m log m) for each n str element

            Space complexity: O(n + m ) : Extra space to store the anagram mapping and the list separately in a map and a result list
         */

        list = testGroupAnagramsV2(strs);
        System.out.println("testGroupAnagrams V2: " + list);

        //  Leet code 767. Reorganize String
        String s = "aab";

        /*
            Time complexity: O(n) : Loops over all the n characters and loads the hashMap
                             O(log k) : Time taken for storing elements in the Priority Queue and a total of k unique elements => O(k log k)
                             Overall time complexity is O(n log k) : k the number of unique elements, which at max will be 26.
                             O(log k) : basically becomes a constant, concluding the time complexity to be : O(n)

            Space complexity: O(k + k): k unique elements frequency stored in the Map + k elements stored in the priority queue.
                              O(n): Stores the final n characters in the result string
                              Final concludes to : O(n + k) => O(n) in practice
         */
        String resultStr = testReorganiseString(s);
        System.out.println("resultStr: " + resultStr);

        // Leet code 1525. Number of Good Ways to Split a String
        s = "aacaba";

        /*
            Two pointer, Frequency map approach
            Time complexity: O(n + n): Iterates over the string twice, one in each direction

            Space complexity: O(26) Two frequency maps storing the frequency of unique elements. Worst case would be O(n)
                              Can be concluded to constant space of : O(1)
         */
        int result = testNumberOfGoodWaysToSplitString(s);
        System.out.println("goodWaysToSplitString: " + result);

        // Leet code 792. Number of Matching Subsequences
        s = "dsahjpjauf";
        String [] words = {"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        /*
            Time complexity: O(n + m * k * n)
                              O(n) = n iteration to populate the frequency map
                              n = length of the sample string s
                              m = # of string in the words []
                              k = avg length of each string
                              If all the elements of the sample string contains the same char, then the for (int index: list) will also
                              loop n times in worst case

            Space complexity: O(n) => Stores the frequency map for each character for the sample string
         */
        int maxCount = testNumberOfMatchingSubsequencesV1(s, words);
        System.out.println("matchingSubsequences count V1: " + maxCount);

        /*
            Time complexity: O(n + m * k log n)
                              n = length of the sample string s
                              m = # of string in the words []
                              k = avg length of each string
                              log n : time taken for the binary search on the frequency index array

            Space complexity: O(n) => Stores the frequency map for each character for the sample string
         */

        maxCount = testNumberOfMatchingSubsequencesV2(s, words);
        System.out.println("matchingSubsequences count V2: " + maxCount);

    }

    private static int testNumberOfMatchingSubsequencesV2(String s, String[] words) {
        Map<Character, List<Integer>> freqMap = new HashMap<>();
        char [] strArr = s.toCharArray();

        int matchCount = 0;

        for (int i = 0; i < strArr.length; i++) {  // O(n)
            char c = strArr[i];
            List<Integer> list = freqMap.getOrDefault(c, new ArrayList<>()); // O(1)
            list.add(i); // O(n)
            freqMap.put(c, list); // O(n)
        }

        for(String temp : words) { // O(k)

            char[] arr = temp.toCharArray();
            int lastIndex = -1;
            int ctr = 0;

            for (int j = 0; j < arr.length; j++) { // O(m)
                char currChar = arr[j];

                if (freqMap.containsKey(currChar)) {
                    var list = freqMap.get(currChar);
                    int index = binarySearchIndex(list, lastIndex + 1); // O(log n)


                    index = list.get(index);

                    if (index < list.size())
                    {
                        index = list.get(index);
                    }


                    if (index > lastIndex) {
                       // System.out.println("Index from BS: " + index);
                        lastIndex = index;
                        ctr++;
                    }
                }
            }

            if (ctr == arr.length) {
                matchCount+= 1;
            }

        }
        return matchCount;
    }

    private static int binarySearchIndex(List<Integer> list, int target) {
        int low = 0;
        int high = list.size();


        while (low < high) {
            int mid = low + (high - low) / 2;

            int currVal = list.get(mid);

            if (currVal < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


}
class Pair {
    int freq;
    char c;

    Pair(int freq, char c) {
        this.freq = freq;
        this.c = c;
    }

}