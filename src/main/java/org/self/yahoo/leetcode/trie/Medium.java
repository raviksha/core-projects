package org.self.yahoo.leetcode.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> trieMap = new HashMap<>();
    boolean isComplete = false;

    TrieNode() {
        this.isComplete = false;
    }
}

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);

            var trieMap = currentNode.trieMap;
            currentNode = trieMap.getOrDefault(currChar, new TrieNode());
            trieMap.put(currChar, currentNode);
        }
        currentNode.isComplete = true;
    }

    public boolean search(String word) {
        TrieNode currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            var currentChar = word.charAt(i);
            var trieMap = currentNode.trieMap;

            if (!trieMap.containsKey(currentChar)) {
                return false;
            }
            currentNode = trieMap.get(currentChar);
        }
        return currentNode.isComplete;
    }

    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            var currentChar = prefix.charAt(i);
            var trieMap = currentNode.trieMap;

            if (!trieMap.containsKey(currentChar)) {
                return false;
            }
            currentNode = trieMap.get(currentChar);
        }
        /*
            Need to check for the currentNode.isComplete flag here coz a complete word can also be a valid prefix
         */
        return true;
    }
}

class TrieNodeV2 {
    TrieNodeV2 [] trieMap = new TrieNodeV2[26];
    boolean isComplete = false;

    TrieNodeV2() {
        this.isComplete = false;
    }
}

class TrieV2 {

    TrieNodeV2 root;

    public TrieV2() {
        root = new TrieNodeV2();
    }

    public void insert(String word) {
        TrieNodeV2 currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);

            var trieMap = currentNode.trieMap;
            currentNode = trieMap[currChar - 'a'];
            if (currentNode == null) {
                currentNode = new TrieNodeV2();
            }
            trieMap[currChar - 'a'] = currentNode;
        }
        currentNode.isComplete = true;
    }

    public boolean search(String word) {
        TrieNodeV2 currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            var currentChar = word.charAt(i);
            var trieMap = currentNode.trieMap;

            if (trieMap[currentChar - 'a'] == null) {
                return false;
            }
            currentNode = trieMap[currentChar - 'a'];
        }
        return currentNode.isComplete;
    }

    public boolean startsWith(String prefix) {
        TrieNodeV2 currentNode = root;

        for (int i = 0; i < prefix.length(); i++) {
            var currentChar = prefix.charAt(i);
            var trieMap = currentNode.trieMap;

            if (trieMap[currentChar - 'a'] == null) {
                return false;
            }
            currentNode = trieMap[currentChar - 'a'];
        }
        /*
            Need not to check for the currentNode.isComplete flag here coz a complete word can also be a valid prefix
         */
        return true;
    }

    public List<String> getSearchSuggestions(String prefix) {
        TrieNodeV2 currentNode = root;
        int k = 3;
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            var trieMap = currentNode.trieMap;
            currentNode = trieMap[currChar - 'a'];
            if (currentNode == null) {
                return resultList;
            }
        }
        return scanAllTriNodes(currentNode,  new StringBuilder(prefix), resultList, k);

    }

    private List<String> scanAllTriNodes(TrieNodeV2 currentNode, StringBuilder prefix, List<String> result, int k) {
        if (result.size() == k) {
            return result;
        }
        if (currentNode.isComplete) {
            result.add(prefix.toString());
        }
        var trieMap = currentNode.trieMap;
        for (int i = 0; i < trieMap.length; i++) {
            currentNode = trieMap[i];
            if (currentNode != null) {
                char currChar = (char) ('a' + i);
                prefix.append(currChar);
                scanAllTriNodes(currentNode, prefix, result, k);
                prefix.deleteCharAt(prefix.length() - 1); // backtrack
            }
        }
        Collections.sort(result);
        // Get top 3
        List<String> top3 = result.subList(0, Math.min(3, result.size()));
        return top3;
    }
}


public class Medium {

    private static void testAddAndSearchWord() {
        System.out.println("testAddAndSearchWord.....");
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad") + " : false"); // return False
        System.out.println(wordDictionary.search("bad") + " : true"); // return True
        System.out.println(wordDictionary.search(".ad") + " : true"); // return True
        System.out.println(wordDictionary.search("b..") + " : true"); // return True
    }

    private static void testImplementTrieV1() {
        System.out.println("testImplementTrieV1.....");
        // "insert","search","search","startsWith","startsWith","insert","search","startsWith","insert"   ,"search",        "startsWith"]
        //  ["ab"],  ["abc"],["ab"],   ["abc"],     ["ab"],      ["ab"],  ["abc"],  ["abc"],    ["abc"],  ["abc"]<E, true>, ["abc"]<E, true>]
        Trie trie = new Trie();
        trie.insert("ab");

        var isWordExists = trie.search("abc");
        System.out.println("trie.search: abc " + isWordExists);

        isWordExists = trie.search("ab");
        System.out.println("trie.search: ab " + isWordExists);

        var isStartWith = trie.startsWith("abc");
        System.out.println("trie.startsWith: abc " + isStartWith);

        isStartWith = trie.startsWith("ab");
        System.out.println("trie.startsWith: ab " + isStartWith);

        trie.insert("ab");

        isWordExists = trie.search("abc");
        System.out.println("trie.search: abc " + isWordExists);

        isStartWith = trie.startsWith("abc");
        System.out.println("trie.startsWith: abc " + isStartWith);

        trie.insert("abc");

        isWordExists = trie.search("abc");
        System.out.println("trie.search: abc " + isWordExists);

        isStartWith = trie.startsWith("abc");
        System.out.println("trie.startsWith: abc " + isStartWith);
    }

    private static void testImplementTrieV2() {
        System.out.println("testImplementTrieV2.....");
        TrieV2 trieV2 = new TrieV2();
        trieV2.insert("ab");

        var isWordExists = trieV2.search("abc");
        System.out.println("trieV2.search: abc " + isWordExists);

        isWordExists = trieV2.search("ab");
        System.out.println("trieV2.search: ab " + isWordExists);

        var isStartWith = trieV2.startsWith("abc");
        System.out.println("trieV2.startsWith: abc " + isStartWith);

        isStartWith = trieV2.startsWith("ab");
        System.out.println("trieV2.startsWith: ab " + isStartWith);

        trieV2.insert("ab");

        isWordExists = trieV2.search("abc");
        System.out.println("trieV2.search: abc " + isWordExists);

        isStartWith = trieV2.startsWith("abc");
        System.out.println("trieV2.startsWith: abc " + isStartWith);

        trieV2.insert("abc");

        isWordExists = trieV2.search("abc");
        System.out.println("trieV2.search: abc " + isWordExists);

        isStartWith = trieV2.startsWith("abc");
        System.out.println("trieV2.startsWith: abc " + isStartWith);
    }

    public static void main(String[] args) {
        System.out.println("Trie medium ...");
        // Leet code 208. Implement Trie (Prefix Tree)

        /*
            Trie data structure uses the approach of :
            1. Maintain a Tree like structure with the first char as a root
            2. Custom TrieNode class consists of a map to store the mapping of the next Trie Node containing any or all the 26 chars
            3. At any given level in the Trie data structure for any given time, there can be a max of 26 TrieNode mapping in the map

            Time complexity: O(n)
                             All the hashmap operation takes O(1) constant time * n chars in a word = O(n)
                             Applicable to insert() search() startsWith()

            Space complexity: O(n ^ m)
                              m = number of words in the Trie data structure
                              n = number of chars in each word

         */
        testImplementTrieV1();

        /*
            Uses the approach of using a Object[] instead of HashMap;
            This helps in reducing the space complexity and the TrieV2[] will take a fixed space 26 entries

            Time complexity: O(n)
                             Array access takes O(1) constant time
                             Looping over n elements while inserting(), searching() and startsWith() takes O(n) time

            Space complexity: O(n * 26)
                              n = number of words
                              Each node will have max 26 length char []
                 Using a char[] is better than using a HashMap coz :
                 1. Always 26 pointers per node
                 2. Predictable and minimal memory footprint
                 3. No extra structural overhead

                 HashMap has significant internal overhead
                 1. Stores a key
                 2. Stores a value TrieNode object
                 3. Hashcode for each key
                 4. Map.Entry wrapper object

                 NOTE: HashMap used 3 - 5 times more memory overhead compared to per key in an array

                 HashMap is more suitable when the input sample size is not fixed. Scales better than arrays in such scenarios
         */
        testImplementTrieV2();

        // Leet code 211. Design Add and Search Words Data Structure
        /*
            Approach is using a Trie data structure which internally uses a array of TrieNodeV2 , length 26, one for each lower case char

            Time complexity: O(n): Add word operation. Lops once through each character for the word
                            Search
                            Best case: No wildcards
                                    O(n): For searching word of length n in the Trie data structure
                             Wort case: ALl wild cards eg : ...
                             In worst case for wild card search it will have to traverse through the complete Trie tree, visiting all nodes
                             For each node it will so 26 recursive call to itself to find a matching path.

                             26 times = for each character
                             n character = O(26 ^ n) : Exponential t/c



            Space complexity: O(n * 26)
                              Add : Maintains a forward mapping array of 26 elements for each character from the root node: O(n * 26)
                              Search: O(n): Uses recursion, which is of max depth of n
                              Concluding t/c: O(n * 26)
         */

        testAddAndSearchWord();

        // Leet code 1268. Search Suggestions System

        /*
            Uses the approach of using a Trie data structure
            1. Iterates till the Trie node matching the length of the input prefix string
            2. Scans through each of the 26 child nodes for each node till the tail node is traversed
            3. While iterating, keeps track of complete words
            4. From the final list, sorts then and returns the top 3 strings in lexicographically

            Time complexity: O(p + k * l)
                             O(p): t/c to reach the prefix node
                             Traverse all words rooted at current node : O(w * l)
                             w = number of words limited to TOP k
                             l = avg length of the words
                             NOTE: The t/c is not O(26 ^ n) coz on this case we are not exploring all the branches from the root node.
                                   Traversal happens for only the path which exists in the Trie
                                   O(26ⁿ) happens when we are running a wild card search like : ...


            Space complexity: O(k * l)
                              Result list storing TOP k strings each with length l
                              Recursion: Stack space for DFS recursion is at most O(l) depth, negligible compared to original list
         */

        testSearchSuggestionSystemV1();
    }

    private static void testSearchSuggestionSystemV1() {
        TrieV2 trieV2 = new TrieV2();
        //["mobile","mouse","moneypot","monitor","mousepad"]
        trieV2.insert("mobile");
        trieV2.insert("mouse");
        trieV2.insert("moneypot");
        trieV2.insert("monitor");
        trieV2.insert("mousepad");


        List<List<String>> result = new ArrayList<>();
        String searchWord = "mouse";

        StringBuilder searchStr = new StringBuilder();

        for (int i = 0; i < searchWord.length(); i++) {
            searchStr.append(searchWord.charAt(i));
            var childList = trieV2.getSearchSuggestions(searchStr.toString());
            result.add(childList);
        }

        System.out.println("testSearchSuggestionSystemV1: final auto suggestion list: " + result);
    }


}



class WordDictionary {
    TrieNodeV2 root;

    WordDictionary() {
        root = new TrieNodeV2();
    }
    public void addWord(String word) {
        TrieNodeV2 currentNode = root;

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);

            if (currentNode.trieMap[currChar - 'a'] == null) {
                currentNode.trieMap[currChar - 'a'] = new TrieNodeV2();
            }
            currentNode = currentNode.trieMap[currChar - 'a'];
        }
        currentNode.isComplete = true;
    }

    public boolean search(String word) {
        return searchWord(root, 0, word);
    }

    private boolean searchWord(TrieNodeV2 currNode, int index, String word) {
        if (index == word.length()) {
            return currNode.isComplete;
        }

        char currChar = word.charAt(index);

        if (currChar == '.') {
            for (TrieNodeV2 child: currNode.trieMap) {
                if (child != null && searchWord(child, index + 1, word)) {
                    return true;
                }
            }

            return false;

        } else {
            currNode = currNode.trieMap[currChar - 'a'];
            if (currNode == null) {
                return false;
            }
            return searchWord(currNode, index + 1, word);
        }
    }
}