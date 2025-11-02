package org.self.yahoo.leetcode75.tries;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Tries ..... Medium..");

        // Leet code 208. Implement Trie (Prefix Tree)
        /*
            Time complexity: O(n)
                             Array access takes O(1) constant time
                             Traversing through n elements for insert(), search() and startsWith() => O(n)

            Space complexity: O(26 * n)
                               n: Number of char in each word
                               Each char will have a Trie Map of max 26 chars
                               Final s/c: O(n * 26)

                               For efficient s/c, Using an Array is a better approach than using a HashMap coz:
                                  => Avoids the overhead of boxing and unboxing
                                  => Object creation overhead including Map.Entry<>
                                  => Over head for computing hashCode() and equals()
                                  => Follow bucket chain node objects during conflicts
         */
        testTriePreFixTree();
    }

    private static void testTriePreFixTree() {
        //  [[],  ["apple"],["apple"],["app"],  ["app"],    ["app"],["app"]]
        // ["Trie","insert","search",  "search","startsWith","insert","search"]
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("trie.search(\"apple\"): " + trie.search("apple"));
        System.out.println("trie.search(\"app\"): " + trie.search("app"));
        System.out.println("trie.startsWith(\"app\"): " + trie.startsWith("app"));

        trie.insert("app");
        System.out.println("trie.search(\"app\"): " + trie.search("app"));
    }
}


class TrieNode {
    char val;
    boolean isComplete = false;
    TrieNode [] trieMap = new TrieNode[26];

    TrieNode(char c) {
        this.val = c;
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode('*');
    }

    public void insert(String word) {
        TrieNode currentNode = root;

        for (char currChar : word.toCharArray()) {
            int index = currChar - 'a';

            TrieNode tmp = currentNode.trieMap[index];
            if (tmp == null) {
                tmp = new TrieNode(currChar);
            }
            currentNode.trieMap[index] = tmp;
            currentNode = tmp;
        }
        currentNode.isComplete = true;
    }

    public boolean search(String word) {
        TrieNode currentNode = root;
        return traverseTrie(word, currentNode, false);
    }

    private boolean traverseTrie(String word, TrieNode currentNode, boolean isPrefixSearch) {

        for (char currChar : word.toCharArray()) {
            int index = currChar - 'a';
            currentNode = currentNode.trieMap[index];

            if (currentNode == null || currentNode.val != currChar) {
                return false;
            }
        }

        if (isPrefixSearch) {
            return true;
        }
        return currentNode.isComplete;
    }

    public boolean startsWith(String prefix) {
        TrieNode currentNode = root;
        return traverseTrie(prefix, currentNode, true);
    }
}
