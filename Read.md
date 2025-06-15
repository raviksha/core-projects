8 Leet code patterns : https://www.youtube.com/watch?v=xo7XrRVxH8Y
    1. Two Pointers 
        => Used to solve patterns when we need to iterate through a SORTED array
        eg: Two sum II : Input Array Sorted : 167: Leet code
        eg: Triplets which add up to ZERO in an sorted array 15: Leet code
    
    2.  Sliding Window
        => Used on Linear data structure like Array and Linked List
        => Used to find a contiguous subset of a elements matching a given solution
        eg : Find longest substring with K unique characters in a given String 
    
    3. Subset pattern
        => Used to find all possible arrangments from a given set
        => Repitition may oy may not be allowed depending on the problem
        => Approach very similar to BFS 
        eg : Permutation problem of 46: Leet code 
    
    4. Modified Binary Search
        => Binary search Core idea is to divide the search space by half, again and again
        eg: Search in rotated Array 33: Leet code problem
    
    5. Top K elements
        =>  Used on Liner data stucture i.e Arrays and LinkedList
        => Used to solve problenms like : Top ranking elements in an Array
        eg: Given an array of numbers, find the K largest elements in the array
            (Find the 2nd largest elements in an array)

    6. DFS (Depth First Search) of a Binary Tree
        => Used to Traverse a binary tree with left side and then right side using recursion
        eg: Max depth of a Binary Tree 104: Leet code
    
    7. Topological Sort:
        => Used to arrange elements in a specific order when they have dependencies on each other
        => Usefule for directed Acyclic graph
        => Used to write modules which are dependent on other modules
        => Helps in figure out the order in which the dependent modules should be written
        eg: Course schedule problem 207: Leet code
    
    8. BFS (Breadth First Search) of Binary Tree
        => Explores all the nodes at the same level in different brances first
        => Uses a queue data structure to acheive this
        eg: Binary Tree Level Order Traversal 102: Leet code
    

Types of Problems in Sliding window & Two pointers approach
    In a sliding window problem there is a window expansion and a window shrink
        => Typically the expansion happens on the right edge and shrinking happens on the left edge
    
    1. Constant window 
        eg: Find the max sum of k array elements in a integer array which could be sorted or unsorted

    2. Longest contigious SubArray | SubString where <condition>
        eg: Longest contigious sub array with sum <= k 
    
    3. Number of subarrays where <condition>
        eg: Number of sub arrays where sum = k
        => These problems will solved using type#2 from above 

    4. Finding the shortest or the minimum window where <condition>
        => First get the window which is valid
        => Keep shrinking it till its meets the condition to get the minimum window



#################   Quick reference notes ####################
. Java Hashmap 
    1. On avg a hashMap put() abd get () operation takes constant time of O(1)
    2. Pre Java 8 used a Linked List and post Java 8 it uses a Balanced tree
    3. In worst the time complexity of a hash map look up and addition takes about O(n) time (Pre Java 8)
       if all the hash codes result in a conflict and land in the same bucket, hence in the same linked list
    4. For Java 8 onwards, the time complexity of a hash map lookup and addition, in worst case takes about : O(log n) time 


https://github.com/AlgoMaster-io/leetcode-solutions/blob/main/java/

Arrays
    Easy: 
        Leet code 283: Move Zeroes 
        Leet code 169. Majority Element
        Leet code  26. Remove Duplicates from Sorted Array
        Leet code 121. Best Time to Buy and Sell Stock

    Medium:
        Leet code 189. Rotate Array
        Leet code 122. Best Time to Buy and Sell Stock II
        Leet code 2348. Number of Zero-Filled Sub arrays [**]
        Leet code 334. Increasing Triplet Subsequence [**]

String 
    Easy:
        Leet code 392. Is Subsequence
        Leet code 125. Valid Palindrome
        Leet code 14. Longest Common Prefix

    Medium:
        Leet code 6. Zigzag Conversion
        Leet code 151. Reverse Words in a String

HashTables
    Easy:
        Leet code 205. Isomorphic Strings
        Leet code 219. Contains Duplicate II

    Medium:
        Leet code 535 Encode and Decode TinyURL
        Leet code 128. Longest Consecutive Sequence
        Leet code 49. Group Anagrams [**]
        Leet code 767. Reorganize String
        Leet code 1525. Number of Good Ways to Split a String
        Leet code 792. Number of Matching Subsequences

Prefix Sum
    Easy:
        Leet code 303. Range Sum Query - Immutable
    
    Medium:
        Leet code 11. Container With Most Water
        Leet code 167. Two Sum II - Input Array Is Sorted [**]
        Leet code 15. 3Sum
        Leet code 560. Subarray Sum Equals K
        Leet code 525. Contiguous Array


Linked List
    Easy:
        Leet code 160. Intersection of Two Linked Lists
        Leet code 876. Middle of the Linked List
        Leet code 21. Merge Two Sorted Lists
    Medium:
        Leet code 82. Remove Duplicates from Sorted List II
        Leet code 24. Swap Nodes in Pairs
        Leet code 2. Add Two Numbers
        Leet code 142. Linked List Cycle II
        Leet code 61. Rotate List

LinkedList In-place Reversal
    Easy :
        Leet code 206. Reverse Linked List
    Medium :
        Leet code 707. Design Linked List
        Leet code 19. Remove Nth Node From End of List

Kadane Algo
    Medium :
        Leet code 53. Maximum Subarray
        Leet code 152. Maximum Product Subarray

Sliding Window - Fixed Size
    Medium :
        Leet code 438. Find All Anagrams in a String [**]
        Leet code 567. Permutation in String
        Leet code 2461. Maximum Sum of Distinct Subarrays With Length K

Stacks
    Easy:
        Leet code 20. Valid Parentheses
    Medium:
        Leet code 155. Min Stack
        Leet code 316. Remove Duplicate Letters
        Leet Code 739. Daily Temperatures
        Leet Code 496. Next Greater Element I

Recursion
    Easy:
    Medium:
        Leet Code 394. Decode String

Merge Sort:
    Easy:
    Medium:
        Leet code 148. Sort List

Quick Sort:
    Easy:
    Medium:
       Leet code 75. Sort Colors
       Leet code 215. Kth Largest Element in an Array

MinHeap:
    Easy:
    Medium: Leet code 692. Top K Frequent Words

BinarySearch:
    Easy:
    Medium:
        Leet code 34. Find First and Last Position of Element in Sorted Array
        Leet code 162. Find Peak Element
        Leet code 33. Search in Rotated Sorted Array
        Leet code 875. Koko Eating Bananas
        Leet code 74. Search a 2D Matrix


Back Tracking
    Easy:
    Medium:
        Leet code 22. Generate Parentheses
        Leet code 78. Subsets

Tree Traversal
    Easy:
        Leet code 144. Binary Tree Preorder Traversal
        Leet code 108. Convert Sorted Array to Binary Search Tree
        Leet code 94. Binary Tree Inorder Traversal
    Medium:
        Leet code 102. Binary Tree Level Order Traversal
        Leet code 199. Binary Tree Right Side View
        Leet code 117. Populating Next Right Pointers in Each Node II
        Leet code 437. Path Sum III ######## TO REVISIT
        Leet code 105. Construct Binary Tree from Preorder and Inorder Traversal
        Leet code 98. Validate Binary Search Tree
        Leet code 230. Kth Smallest Element in a BST
        Leet code 173. Binary Search Tree Iterator

BST: Ordered Set
    Medium:
        Leet code 729. My Calendar I
        Leet code 731. My Calendar II
        Leet code 2034. Stock Price Fluctuation

Trie:
    Medium:
        Leet code 211. Design Add and Search Words Data Structure
        Leet code 208. Implement Trie (Prefix Tree)
        Leet code 1268. Search Suggestions System

Sliding Window / Two pointers
    Easy:
        Leet code 88. Merge Sorted Array
    Medium:
        Leet code 1423. Maximum Points You Can Obtain from Cards
        Leet code 3. Longest Substring Without Repeating Characters [**] [Top5]
        Leet code 1004. Max Consecutive Ones III
        Leet code 395. Longest Substring with At Least K Repeating Characters
        Leet code 1358. Number of Substrings Containing All Three Characters
        Leet code 424. Longest Repeating Character Replacement
        Leet code 930. Binary Subarrays With Sum    [**]
        Leet code 1248. Count Number of Nice Subarrays
        Leet code 992. Subarrays with K Different Integers
        Leet code : 76. Minimum Window Substring [HARD]
        Leet code : 904. Fruit Into Baskets
        Leet code: 209. Minimum Size Subarray Sum
        Leet code: 974. Subarray Sums Divisible by K



Top 5
    Merge Intervals
    Longest Substring Without Repeating Characters
    Best Time to Buy and Sell Stock
    Valid Parentheses
    Two Sum II
