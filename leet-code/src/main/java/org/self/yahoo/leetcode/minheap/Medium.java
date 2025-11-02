package org.self.yahoo.leetcode.minheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Min heap: Medium.....");
        // Medium: Leet code 692. Top K Frequent Words
        String [] strArr = new String[] {"i","love","leetcode","i","love","coding"};
        int k = 2;

        /*
            Using a Max heap with Priority queue

            Time complexity: O(n log n)
                             Adding and iterating elements in the freq Map: O(n)
                             Adding elements to Priority queue: each operation takes O(log n) time * n = O(n log n), where n is the number of items in the PQ
                             Removing k elements from the Priority Queue: Each element takes O(log n) * k = O(k log n), where n is the number of items in the PQ
                             Total : O(n log n) + O(k log n). Since k is small, the dominating time complexity is : O(n log n)

            Space complexity: O(n)
                              Freq Map: Stores max O(n) unique elements
                              PQ      : Stores max O(n) unique elements
                              Result list : O(k) elements
                              Dominating space complexity is : O(n)
         */
        List<String> topFreqList = testTopKFreqWords(strArr, k);
        System.out.println("testTopKFreqWords: " + topFreqList);
    }

    private static List<String> testTopKFreqWords(String[] strArr, int k) {
        Map<String, Integer> freqMap = new HashMap<>();

        var comparator = Comparator.comparingInt((Pair p) -> p.freq).reversed().thenComparing((Pair p) -> p.word);

        // Priority queue is min heap by default

        PriorityQueue<Pair> queue = new PriorityQueue<>(comparator);
        List<String> result = new ArrayList<>();

        for (String word: strArr) { // O(n)
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("freqMap: " + freqMap);

        for (Map.Entry<String, Integer> entry: freqMap.entrySet()) { // O(n)
            String word = entry.getKey();
            int freq = entry.getValue();
            Pair p = new Pair(freq, word);
            queue.add(p); // O(n log n)
        }

        System.out.println("queue: " + queue);

        while (k > 0) {
            Pair temp = queue.poll(); // O(k log n)
            result.add(temp.word);
            k--;
        }
        return result;
    }
}

class Pair {
    int freq;
    String word;

    Pair(int freq, String word) {
        this.freq = freq;
        this.word = word;
    }
}
