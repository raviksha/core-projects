package org.self.yahoo.leetcode.treetraversal;

import org.self.yahoo.leetcode.binarytrees.BinaryTree;
import org.self.yahoo.leetcode.binarytrees.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Medium {

    private static List<List<Integer>> testBinaryTreeLevelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            List<Integer> listL = new ArrayList<>();

            for (int i = 0; i < queueSize; i++) {
                TreeNode currNode = queue.poll();
                listL.add(currNode.val);
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            if (!listL.isEmpty()) {
                result.add(listL);
            }

        }

        return result;
    }

    private static List<Integer> testBinaryTreeRightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int qS = queue.size();

            for (int i = 0; i < qS; i++) {
                TreeNode currNode = queue.poll();

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }

                if (i == (qS - 1)) {
                    result.add(currNode.val);
                }
            }
        }
        return result;
    }


    private static Node testPopulatingNextRightPointersV1(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int qSize = queue.size();
            Node prevNode = null;

            for (int i = 0; i < qSize; i++) {
                Node currNode = queue.poll();

                if (prevNode != null) {
                    prevNode.next = currNode;
                }
                prevNode = currNode;
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }

        return root;
    }

    private static Node testPopulatingNextRightPointersV2(Node root) {
        if (root == null) return null;

        Node current = root;

        while (current != null) {
            Node dummy = new Node(0); // Dummy node to start the level
            Node tail = dummy;

            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                current = current.next; // Move to the next node in the current level
            }

            // Move to the next level
            current = dummy.next;
        }

        return root;
    }

    private static int testPathSumIIIV1(TreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int rootTargetCount = getTargetCount(node, targetSum);
        int leftTargetCount = testPathSumIIIV1(node.left, targetSum);
        int rightTargetCount = testPathSumIIIV1(node.right, targetSum);

        return rootTargetCount + leftTargetCount + rightTargetCount;
    }

    private static int getTargetCount(TreeNode node, int targetSum) {
        if (node == null) return 0;

        int count = 0;

        // Check if the current node's value is equal to targetSum
        if (node.val == targetSum) count++;

        // Continue checking for sum with the left child and right child
        count += getTargetCount(node.left, targetSum - node.val);
        count += getTargetCount(node.right, targetSum - node.val);

        return count;
    }


    private static int testPathSumIIIV2(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> freqMap = new HashMap<>();
        freqMap.put(0L, 1);
        long runningSum = 0;
        return testPathSumIIIV2(root, targetSum, runningSum, freqMap);
    }

    private static int testPathSumIIIV2(TreeNode node, int targetSum, long runningSum, Map<Long, Integer> freqMap) {
        if (node == null) {
            return 0;
        }

        runningSum = runningSum + node.val;
        int count = freqMap.getOrDefault(runningSum - targetSum, 0);

        freqMap.put(runningSum, freqMap.getOrDefault(runningSum, 0) + 1);

        count = count + testPathSumIIIV2(node.left, targetSum, runningSum, freqMap);
        count = count + testPathSumIIIV2(node.right, targetSum, runningSum, freqMap);

        freqMap.put(runningSum, freqMap.get(runningSum) -1);
        return count;
    }

    private static TreeNode testConstructBinaryTreePITraversal(int[] preOrder, int[] inOrder) {

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) { // O(n)
            indexMap.put(inOrder[i], i);
        }

        int rootIndex = 0;
        int leftIndex = 0;
        int rightIndex = inOrder.length - 1;

        return testConstructBinaryTreePITraversal(preOrder, indexMap, rootIndex, leftIndex, rightIndex);
    }

    private static TreeNode testConstructBinaryTreePITraversal(int[] preOrder, Map<Integer, Integer> indexMap, int rootIndex, int leftIndex, int rightIndex) {

        int rootVal = preOrder[rootIndex];
        TreeNode root = new TreeNode(rootVal);

        int mid = indexMap.get(rootVal);

        if (mid > leftIndex) {
            root.left = testConstructBinaryTreePITraversal(preOrder, indexMap, rootIndex + 1, leftIndex, mid - 1);
        }

        if (mid < rightIndex) {
            /*
                    rootIndex + 1 + (mid - leftIndex)
                    Explanation:
                        rootIndex + 1 → skips the current root.
                        (mid - leftIndex) → is the number of nodes in the left subtree (from inorder).
                        = start of the right subtree in preorder.
                   mid + 1 is the right position to begin the inorder index for the start of the right subtree
                   rootIndex = calculated new root position for the start of the right preorder traversal
             */
            root.right = testConstructBinaryTreePITraversal(preOrder, indexMap, rootIndex + 1 + (mid - leftIndex), mid + 1, rightIndex);
        }

        return root;
    }

    private static boolean testValidateBinarySearchTree(TreeNode root) {
        List<Integer> treeL = new ArrayList<>();
        populateBST(root, treeL);
        int prev = treeL.get(0);

        for (int i = 1; i < treeL.size(); i++) {
            int currVal = treeL.get(i);

            if (prev >= currVal) {
                return false;
            }
            prev = currVal;
        }

        return true;
    }

    private static void populateBST(TreeNode node, List<Integer> treeL) {
        if (node == null) {
            return;
        }
        populateBST(node.left, treeL);
        treeL.add(node.val);
        populateBST(node.right, treeL);
    }

    private static int testKthSmallestElementInBSTV1(TreeNode root, int k) {
        List<Integer> treeList = new ArrayList<>();
        populateBST(root, treeList);
        System.out.println("treeList: " + treeList);
        return treeList.get(k -1);
    }


    static int largestK = -1;
    static int count = 0;
    private static void testKthSmallestElementInBSTV2(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        testKthSmallestElementInBSTV2(node.left, k);
        count++;
        if (count == k) {
            largestK = node.val;
        }
        testKthSmallestElementInBSTV2(node.right, k);
    }

    private static void testBinarySearchTreeIteratorV1(TreeNode root) {

        BSTIterator bSTIterator = new BSTIterator(root);
        System.out.println("testBinarySearchTreeIteratorV1......");
        System.out.println(bSTIterator.next() == 3);   // return 3
        System.out.println(bSTIterator.next() == 7);   // return 7
        System.out.println(bSTIterator.hasNext() + " true"); // return True
        System.out.println(bSTIterator.next() == 9);    // return 9
        System.out.println(bSTIterator.hasNext() +  " true"); // return True
        System.out.println(bSTIterator.next() == 15);    // return 15
        System.out.println(bSTIterator.hasNext() + " true"); // return True
        System.out.println(bSTIterator.next() == 20);    // return 20
        System.out.println(bSTIterator.hasNext() + " false"); // return False
    }

    private static void testBinarySearchTreeIteratorV2(TreeNode root) {
        BSTIteratorV2 bSTIterator = new BSTIteratorV2(root);
        System.out.println("testBinarySearchTreeIteratorV2......");
        System.out.println(bSTIterator.next() == 3);   // return 3
        System.out.println(bSTIterator.next() == 7);   // return 7
        System.out.println(bSTIterator.hasNext() + " true"); // return True
        System.out.println(bSTIterator.next() == 9);    // return 9
        System.out.println(bSTIterator.hasNext() +  " true"); // return True
        System.out.println(bSTIterator.next() == 15);    // return 15
        System.out.println(bSTIterator.hasNext() + " true"); // return True
        System.out.println(bSTIterator.next() == 20);    // return 20
        System.out.println(bSTIterator.hasNext() + " false"); // return False
    }


    public static void main(String[] args) {
        System.out.println("Medium:  + Tree Traversal - Level Order");

        // Leet code 102. Binary Tree Level Order Traversal
        // [3,9,20,null,null,15,7]
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(3);
        TreeNode root = binaryTree.getRoot();
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Print BFS testBinaryTreeLevelOrderTraversal: ");
        BinaryTree.printBFS(root);

        /*
            Approach : Using a queue for Level order traversing

            Time complexity: O(n): Each node in the BST tree is traversed once
                             Queue add() and poll() : O(1) constant time
                             Final : O(n)

            Space complexity: O(n)
                              Queue: At worst case a queue can store a max of n elements where n = w = width of the tree
                              Result list contains one list per level, max of n nodes
                              Total time complexity: O(n)
         */
        var result = testBinaryTreeLevelOrderTraversal(root);
        System.out.println("testBinaryTreeLevelOrderTraversal: " + result);

        // Leet code 199. Binary Tree Right Side View
        BinaryTree.initRoot();
        BinaryTree binaryTreeR = new BinaryTree();
        binaryTreeR.put(1);
        var rootR = binaryTreeR.getRoot();
        rootR.left = new TreeNode(2);
        rootR.right = new TreeNode(3);

        rootR.left.left = new TreeNode(4);
        rootR.left.left.left = new TreeNode(5);
        System.out.println("Print BFS testBinaryTreeRightSideView: ");
        BinaryTree.printBFS(rootR);

        /*
            Approach: Using a Queue for BFS

            Time complexity: O(n): Loops over all the n elements of the BST

            Space complexity: O(n): Worst case when the Tree is skewed
                              O(h): Avg case: Height of the tree
         */

        var resultR = testBinaryTreeRightSideView(rootR);
        System.out.println("testBinaryTreeRightSideView: " + resultR);

        // Leet code 117. Populating Next Right Pointers in Each Node II
        // 1,2,3,4,5,null,7
        Node rootN = new Node();
        rootN.left = new Node(1);
        rootN.left = new Node(2);
        rootN.right = new Node(3);

        rootN.left.left = new Node(4);
        rootN.left.left.right = new Node(5);

        rootN.right.right = new Node(7);

        /*
            Time complexity: O(n): Loops through all the nodes of the BST once.

            Space complexity: O(n)
                              O(n /2): Queue will at max store n/2 nodes at the last level of a BST.
                                       Max size of the queue : O(n)
                              O(n): Max size of the list containing all nodes of the BST
                              Concluding space complexity: O(n)
         */
        var resultN = testPopulatingNextRightPointersV1(rootN);
        System.out.println("testPopulatingNextRightPointersV1: " + (resultN.next == null));

        rootN = new Node();
        rootN.left = new Node(1);
        rootN.left = new Node(2);
        rootN.right = new Node(3);

        rootN.left.left = new Node(4);
        rootN.left.left.right = new Node(5);

        rootN.right.right = new Node(7);

        resultN = testPopulatingNextRightPointersV2(rootN);
        System.out.println("testPopulatingNextRightPointersV2: " + (resultN.next == null));

        // Leet code 437. Path Sum III
        BinaryTree.initRoot();
        binaryTreeR = new BinaryTree();
        binaryTreeR.put(10);
        rootR = binaryTreeR.getRoot();
        rootR.left = new TreeNode(5);
        rootR.right = new TreeNode(-3);

        rootR.left.left = new TreeNode(3);
        rootR.left.left.left = new TreeNode(3);
        rootR.left.left.right = new TreeNode(-2);

        rootR.left.right = new TreeNode(2);
        rootR.left.right.left = new TreeNode(1);

        rootR.right.right = new TreeNode(-3);
        rootR.right.right.right = new TreeNode(11);
        int targetSum = 8;

        /*
            Using Brute force approach using two functions
            The implementation uses a Pre Order traversal technique.
            1. Call the nested traversal function() for each node in the BST
            2. Skims through the left tree
            3. Skims through the right tree

            Time complexity: O(n ^ 2)
                             O(n): testPathSumIIIV1() is called for each of the n nodes of the tree
                             For each node testPathSumIIIV1() calls getTargetCount() which is turn traverses the entire tree
                             starting from the passed node as the root node. Best case it will traverse O(H) H = height of the tree
                             Worst case is the tree is skewed the getTargetCount() will traverse O(n) times
                             Total : O(n){testPathSumIIIV1()} * O(n){getTargetCount} = O(n ^ 2)

            Space complexity: O(n ^ 2)
                              O(n) : Recursion stack for all testPathSumIIIV1(). Calls for all elements to its left and right
                                     Best case: O(H) H= height of the tree, for a balanced binary tree
                                     Worst case: O(n): Skewed binary tree
                               O(n): Recursion calls from the root node in the getTargetCount() method
                                     Best case: O(H): Height of a balanced binary tree
                                     Worst case: O(n): For a skewed binary tree
         */
        int targetCount = testPathSumIIIV1(rootR, targetSum);
        System.out.println("testPathSumIIIV1: " + targetCount);
        /*
               Using PreFix sum approach

               Time complexity: O(n): Unlike testPathSumIIIV1(), in traversed each node of the BST only once

               Space complexity: O(H): Extra space to store the running sum which in avg case = Height of the tree
                                       Worst case: O(n): If the tree is skewed
                                 O(H): O(log n) => O(n) = Best Vs worst case
         */
        targetCount = testPathSumIIIV2(rootR, targetSum);
        System.out.println("testPathSumIIIV2: " + targetCount);

        // Leet code 105. Construct Binary Tree from Preorder and Inorder Traversal

        /*
            Two different binary trees can never have the same Pre or InOrder traversal
         */
        int [] preOrder = {3,9,20,15,7};
        int [] inOrder = {9,3,15,20,7};
        TreeNode rootT = testConstructBinaryTreePITraversal(preOrder, inOrder);

        /*
            Using the approach of using a Index map and recursion

            Time complexity: O(n)
                             Building the Index map: O(n)
                             Construction the binary tree using recursion: O(n): For adding all n nodes in the Binary tree
                             Final : O(n)

            Space complexity: O(n)
                              Building the index map: O(n)
                              Recursion stack depth: O(n)    : Worst case for skewed Binary tree
                                                     O(log n): Avg case for balanced BST
         */
        System.out.println("testConstructBinaryTreePITraversal: " + (rootT.val == 3));

        // Leet code 98. Validate Binary Search Tree
        /*
            Binary Tree: Each node will have 2 child nodes, values of which does not matter in accordance to value of the root node
            Binary search Tree: All the value of the left of the root node will have values lesser than root while nodes on the right will have
                                values greater than the root.

           Binary search tree ==> Binary Tree
           Binary tree !==> Binary search tree at all times.

           In Order traversal technique ensures that the elements of the tree are in ascending order i.e leftNode => root => rightNode
           and thereby ensuring that the tree is a Binary search tree

           Time complexity: O(n): Iterates once over all the n elements of the Binary search tree

           Space complexity: O(n): Extra space to store the values of all n nodes in a List
         */
        BinaryTree.initRoot();
        binaryTreeR = new BinaryTree();
        binaryTreeR.put(2);
        root = binaryTree.getRoot();
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        boolean isValidBST = testValidateBinarySearchTree(root);
        System.out.println("isValidBST: " + isValidBST);

        // Leet code 230. Kth Smallest Element in a BST
        BinaryTree.initRoot();
        binaryTreeR = new BinaryTree();
        binaryTreeR.put(3);
        root = binaryTree.getRoot();
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(4);
        int k = 1;

        /*
            Using the InOrder traversal approach

            Time complexity: O(n): Traverses through each node of the tree

            Space complexity: O(n)
                              O(h): Recursion stack = Height of the tree
                              O(n): Storing all n elements on the List
         */
        int kL = testKthSmallestElementInBSTV1(root, k);
        System.out.println("testKthSmallestElementInBSTV1: " + kL);

        BinaryTree.initRoot();
        binaryTreeR = new BinaryTree();
        binaryTreeR.put(3);
        root = binaryTree.getRoot();
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);

        root.left.right = new TreeNode(4);
        k = 1;

        /*
            Using recursion for InOrder traversal

            Time complexity: O(log n): Balanced binary tree
                             O(n): Worst case, skewed binary tree

            Space complexity: O(log n): Recursion stack
                              O(n): n level depth recursion stack when the tree is skewed
         */
        testKthSmallestElementInBSTV2(root, k);
        System.out.println("testKthSmallestElementInBSTV2: " + largestK);

        // Leet code 173. Binary Search Tree Iterator
        BinaryTree.initRoot();
        binaryTreeR = new BinaryTree();
        binaryTreeR.put(7);
        root = binaryTree.getRoot();
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);

        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        /*
            Uses the approach where the elements of the BST are added to a List in InOrder traversal
            This ensures that all the elements are in increasing order with index 0 as smallest and index n having the largest

            Time complexity: O(1): next() and hasNext() operate at the time complexity of O(1)

            Space complexity: O(n): Extra space required to store the n elements of thr binary tree
         */
        testBinarySearchTreeIteratorV1(root);

        /*
            Uses the approach of using a stack which at any given time stores only the left nodes from the BST root node
            Works on the design of In order traversal where the tree values are traversed from left to right in increasing order of values
            It starts by adding all the left nodes beginning  from root while top element of the stack will always be the smallest node in the traversed BST
            While traversing it checks if a right node exists then it recursively adds all the left nodes from the right subtree

            Time complexity: O(1) Avg case for next() and hasNext()

            Space complexity: O(H): At any time the stack contains the left nodes from the given BST root.
         */
        testBinarySearchTreeIteratorV2(root);

    }

}

class BSTIteratorV2 {
    Stack<TreeNode> stack = new Stack<>();

    public BSTIteratorV2(TreeNode root) {
        loadLeftTreeL(root);
    }

    public int next() {
        TreeNode currNode = stack.pop();

        if (currNode.right != null) {
            loadLeftTreeL(currNode.right);
        }
        return currNode.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void loadLeftTreeL(TreeNode node) {
        if (node == null) {
            return;
        }
        stack.push(node);
        loadLeftTreeL(node.left);
    }
}




class BSTIterator {
    List<Integer> treeL = new ArrayList<>();
    int index = -1;
    public BSTIterator(TreeNode root) {
        loadTreeL(root);
    }

    public int next() {
        return treeL.get(++index);
    }

    public boolean hasNext() {
        return index + 1 < treeL.size();
    }

    private void loadTreeL(TreeNode node) {
        if (node == null) {
            return;
        }
        loadTreeL(node.left);
        treeL.add(node.val);
        loadTreeL(node.right);
    }
}




class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};