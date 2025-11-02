package org.self.yahoo.book.demo.chap3.binaryTree.worksheet2;

import org.self.yahoo.book.demo.chap2.queue.DoubleLinkedListNode;
import org.self.yahoo.book.demo.chap3.binaryTree.worksheet.BinaryTree;
import org.self.yahoo.book.demo.chap3.binaryTree.worksheet.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

public class TreeInterviewQuestions {

    /*
        Time complexity : O(n)

        Space complexity :
            Auxiliary Space : O(h) : Extra space coz of recursion = height of the tree
            O(n) : Worst case if the tree is skewed
            O(log n) : Balanced binary tree

     */

    private static void testChildrenSumParent(int[] arr) {
        BinaryT binaryTree = initializeTree(arr);
        int sumOfNodes = binaryTree.sumOfAllNodes();
        System.out.println("sumOfNodes: " + sumOfNodes);
        System.out.println(sumOfNodes == (60 + 10 + 50 + 20 + 5));

    }

    private static BinaryT initializeTree(int[] arr) {
        BinaryT binaryTree = new BinaryT();
        for (int i = 0; i < arr.length; i++) {
            binaryTree.put(arr[i]);
        }
        return binaryTree;
    }

    /*
        Time Complexity :
                          O(n)  : Every node is processed exactly once.

        Space Complexity :
                Auxiliary space : O(h) : Extra space required for recursion stack
                Worst case : O(n) : If the Binary tree is skewed
                Beast case : O(log n) : Tree is balanced
     */
    private static void testTreeHeight(int[] arr) {
        BinaryT binaryTree = initializeTree(arr);
        int treeHeight = binaryTree.getTreeHeight();
        System.out.println("treeHeight: " + treeHeight);
    }


    /*
           Uses DFS approach

           Time Complexity:
                            O(n) : Compare each node
           Space Complexity:
                             O(n)    : Worst case for skewed trees
                             O(log n): Balanced binary trees

     */
    private static boolean testDetermineIfIdenticalV1(int[] arr, int[] tmp) {
        BinaryT binaryTree1 = initializeTree(arr);
        BinaryT binaryTree2 = initializeTree(tmp);

        binaryTree1.printBFS();
        binaryTree2.printBFS();

        if (binaryTree1 == null || binaryTree2 == null) {
            return true;
        }

        if (binaryTree1.getTreeHeight() != binaryTree2.getTreeHeight()) {
            return false;
        }
        return testDetermineIfIdenticalV1(binaryTree1.getRoot(), binaryTree2.getRoot());
    }

    private static boolean testDetermineIfIdenticalV1(BinaryNode node1, BinaryNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.getKey() != node2.getKey()) {
            return false;
        }

        if (node1.getLeft() != null && node2.getLeft() != null) {
            int valLeft1 = node1.getLeft().getKey();
            int valLeft2 = node2.getLeft().getKey();

            if (valLeft1 == valLeft2) {
                return testDetermineIfIdenticalV1(node1.getLeft(), node2.getLeft());
            } else {
                return false;
            }
        }

        if (node1.getRight() != null && node2.getRight() != null) {
            int valRight1 = node1.getRight().getKey();
            int valRight2 = node2.getRight().getKey();

            if (valRight1 == valRight2) {
                return testDetermineIfIdenticalV1(node1.getRight(), node2.getRight());
            } else {
                return false;
            }
        }
        return true;
    }

    /*
            Time complexity  :
                            O(n) : Compare and check each node in the Binary tree

            SPace complexity :
                            O(N) : Binary Tree is balances
                            O(1) : Queue will hold only 1 item if the Binary tree is skewed
     */
    private static boolean testDetermineIfIdenticalV2(int[] arr, int[] tmp) {
        BinaryT binaryTree1 = initializeTree(arr);
        BinaryT binaryTree2 = initializeTree(tmp);

        if (binaryTree1 == null && binaryTree2 == null) {
            return true;
        }
        if (binaryTree1 == null || binaryTree2 == null) {
            return false;
        }

        Queue<BinaryNode> queue1 = new LinkedList<>();
        Queue<BinaryNode> queue2 = new LinkedList<>();

        BinaryNode root1 = binaryTree1.getRoot();
        BinaryNode root2 = binaryTree2.getRoot();

        if (root1.getKey() != root2.getKey()) {
            return false;
        } else {
            queue1.add(root1);
            queue2.add(root2);
            while (!queue1.isEmpty() && !queue2.isEmpty()) {

                BinaryNode currentNode1 = queue1.poll();
                BinaryNode currentNode2 = queue2.poll();

                if (currentNode1.getKey() != currentNode2.getKey()) {
                    return false;
                }

                if (currentNode1.getLeft() != null && currentNode2.getLeft() != null) {
                    queue1.add(currentNode1.getLeft());
                    queue2.add(currentNode2.getLeft());
                }

                /*
                    	(n1.left != null) != (n2.left != null) evaluates to:
	•	                     true when one of them is null and the other is not.
	•	                     false when both are null or both are not null.

	                    Tree 1          Tree 2
                           1              1
                          / \            / \
                         2   3          2   3

                         n1.left = 2 and n2.left = 2 → No issue.

                        n1.left = null
                        n2.left = null

                        This condition fails because it wrongly detects differences even when both left children are null.


                 */

                else if ((currentNode1.getLeft() == null) != (currentNode2.getLeft() == null)) {
                    return false;
                }
//                else if (currentNode1.getLeft() == null || currentNode2.getLeft() == null) {
//                    return false;
//                }

                if (currentNode1.getRight() != null && currentNode2.getRight() != null) {
                    queue1.add(currentNode1.getRight());
                    queue2.add(currentNode2.getRight());
                } else if ((currentNode1.getRight() == null) != (currentNode2.getRight() == null)) {
                    return false;
                }
//                else if (currentNode1.getRight() == null || currentNode2.getRight() == null) {
//                    return false;
//                }
            }
        }
        return queue1.isEmpty() && queue2.isEmpty();
    }


    /*
        Using recursion
        Time complexity :  O(n) : Iterates over all n nodes
        Space complexity : O(n) : n nodes are created for all elements of the array

    */
    private static BinaryNode testSortedArrayToBalancedBSTV1(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Empty input Array");
            return null;
        }
        Arrays.sort(arr); // Uses Merge sort with a time complexity of O(n log n)
        return testSortedArrayToBalancedBSTV1(arr, 0, arr.length - 1);
    }

    private static BinaryNode testSortedArrayToBalancedBSTV1(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BinaryNode rootNode;
        rootNode = new BinaryNode(arr[mid]);

        rootNode.left = testSortedArrayToBalancedBSTV1(arr, start, mid - 1);
        rootNode.right = testSortedArrayToBalancedBSTV1(arr, mid + 1, end);

        return rootNode;
    }

    /*
        Using Queues

        Time complexity: O(n): Iterates over n nodes

        Space complexity: O(n) N nodes created for each element
     */
    private static BinaryNode testSortedArrayToBalancedBSTV2(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Empty input Array");
            return null;
        }
        Arrays.sort(arr);

        Queue<BinaryNode> queue = new LinkedList<>();
        int mid = arr.length / 2;

        BinaryNode rootNode = new BinaryNode(arr[mid]);
        queue.add(rootNode);

        for (int i = mid - 1; i >= 0; i--) {
            BinaryNode tmpNode = new BinaryNode(arr[i]);
            queue.add(tmpNode);
        }

        for (int i = mid + 1; i < arr.length; i++) {
            BinaryNode tmpNode = new BinaryNode(arr[i]);
            queue.add(tmpNode);
        }

        System.out.println(queue);

        BinaryT binaryTree = new BinaryT();

        while (!queue.isEmpty()) {
            binaryTree.put(queue.poll().getKey());
        }
        return binaryTree.getRoot();
    }

    /*
        Diameter of a binary tree is the length of the longest path between any two nodes in the tree.
        The path may or may not pass through the root.

        Using DFS, we begin by calculating the height and diameter from the bottom node and move up the chain

        Time Complexity : O(n^2) : The diameter function calls height() for each node in the tree.
                                   height() in turn traverses all nodes of the tree.
                                   So for each node in the diameter() we perform O(n) class in the height()
                                   Hence the final complexity is : O(n^2)

        Space complexity :
                         Even with nested calls between diameter() and height() there is no extra space usage except the recursion stack frames
                         O(n) : Worst case if the Binary tree is skewed
                         O(h) : Height of the tree

     */
    private static int testDiameterOfATree(int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        BinaryT binaryTree = initializeTree(arr);

        return testDiameterOfATree(binaryTree.getRoot());
    }

    private static int testDiameterOfATree(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        /*
            Height calculation is done to assume that the diameter passes through the root
            If the diameter does not pass through the root then we need to process the diameter of the left sub tree and right subtree
         */
        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());


        /*
            Calculates the diameter when the maximum does not pass through the root
         */
        int leftDiameter = testDiameterOfATree(node.getLeft());
        int rightDiameter = testDiameterOfATree(node.getRight());

        /*
            The last recursive call to Math.max() in the diameter() method does indeed calculate the maximum value from all the previous recursive calls by comparing the calculated diameters of the left and right subtrees and the sum of the heights of the left and right subtrees at the current node.
            This ensures that the diameter is computed correctly for the entire tree.
         */
        return Math.max((leftHeight + rightHeight), Math.max(leftDiameter, rightDiameter));

    }

    private static int getTreeHeight(BinaryNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /*
        Time complexity:
                        O(n)     : Traverses for each node to compute its horizontal distance from root node
                        O(log n) : To keep TreeMap insertions which internally sorts the keys

        Space complexity:
                        O(n) : Auxiliary space required to create n NodeInfo objects + Queue + TreeMap
     */
    private static void testVerticalTraversalOfBST(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Tree is empty..");
            return;
        }
        BinaryT tree = initializeTree(arr);

        testVerticalTraversalOfBST(tree.getRoot());
    }

    private static class NodeInfo {
        int hd;
        BinaryNode node;

        NodeInfo(BinaryNode node, int hd) {
            this.hd = hd;
            this.node = node;
        }
    }

    private static void testVerticalTraversalOfBST(BinaryNode node) {

        if (node == null) {
            return;
        }

        /*
                     10
                    /  \
                   7    15
                  / \   /  \
                 3   9 12  18

            Output : HD distance : Node key
              -2   [3]
              -1   [7]
               0   [10, 9, 12]  // Nodes at same horizontal level are printed in level order
               1   [15]
               2   [18]
         */

        Map<Integer, List<Integer>> vdMap = new TreeMap<>();
        Queue<NodeInfo> queue = new LinkedList<>();
        NodeInfo nodeInfo = new NodeInfo(node, 0);
        queue.add(nodeInfo);

        while (!queue.isEmpty()) {
            NodeInfo currentNodeInfo = queue.poll();
            BinaryNode currentNode = currentNodeInfo.node;
            int currentHd = currentNodeInfo.hd;

            vdMap.putIfAbsent(currentHd, new ArrayList<>());
            vdMap.get(currentHd).add(currentNode.getKey());

            if (currentNode.getLeft() != null) {
                NodeInfo temp = new NodeInfo(currentNode.getLeft(), currentHd - 1);
                queue.add(temp);
            }

            if (currentNode.getRight() != null) {
                NodeInfo temp = new NodeInfo(currentNode.getRight(), currentHd + 1);
                queue.add(temp);
            }

        }

        // Print Vertical Order
        System.out.println("Tree Vertical Order: ");
        for (var mapEntry : vdMap.entrySet()) {
            System.out.println(mapEntry.getKey() + ": " + mapEntry.getValue());
        }
    }

    private static int sum(BinaryNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = sum(node.getLeft());
        int rightSum = sum(node.getRight());
        return node.getKey() + leftSum + rightSum;
    }

     /*
        Time Complexity : O(n^2) : For each node sum calculation the control traverses through the entire tree to calculate the sum of the child nodes

        Space Complexity :
                      O(h)    : Stack frames required for recursions
                      O(n)    : Worst case if the tree is skewed
     */

    private static boolean testIfSumTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Tree is empty ...");
            return false;
        }
        BinaryT binaryTree = initializeTree(arr);
        return testIfSumTree(binaryTree.getRoot());
    }

    private static boolean testIfSumTree(BinaryNode root) {
        if (root == null) {
            return true;
        }
        // Below condition holds true for leaf nodes
        if (root.getLeft() == null && root.getRight() == null) {
            return true;
        }

        int leftSum = sum(root.getLeft()); // Returns the addition fo the all the nodes to the left of root
        int rightSum = sum(root.getRight()); // Returns the addition of all the nodes to the right of root
        int total = leftSum + rightSum;

        if (total == root.getKey()) { // If the sum of the root left tree matches with root right tree then check for nested left and right nodes
            if (testIfSumTree(root.getLeft()) && testIfSumTree(root.getRight())) {
                return true;
            }
        }
        return false;
    }

    /*
        Time complexity  : O(n) : One iteration for each node creation
        Space complexity :
                           O(n) : Node creation for each element
     */
    private static BinaryNode testSortedLinkedListToBST() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        int end = list.size() - 1;
        int start = 0;

        return testSortedLinkedListToBST(list, start, end);
    }

    private static BinaryNode testSortedLinkedListToBST(List<Integer> list, int start, int end) {

        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;

        BinaryNode root = new BinaryNode(list.get(mid));

        root.left = testSortedLinkedListToBST(list, start, mid - 1);
        root.right = testSortedLinkedListToBST(list, mid + 1, end);

        return root;

    }

    /*
        https://www.youtube.com/watch?v=FsxTX7-yhOw

        Time Complexity : O(n) : Each node is traversed once
        
        Space Complexity : O(h) : For Balanced binary tree in the input the recursions calls will be O(log n) = Height of the tree
     */

    static BinaryNode prev = null;
    static BinaryNode head = null;

    private static void testBinaryTreeToDoublyLinkList(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Empty Tree..");
            return;
        }
        BinaryT binaryTree = initializeTree(arr);

        testBinaryTreeToDoublyLinkList(binaryTree.getRoot());
        System.out.println();
        System.out.println("BinaryTreeToDoublyLinkList: ");
        printDLL(head);
    }

    private static void printDLL(BinaryNode head) {
        BinaryNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.getKey() + "<==>");
            currentNode = currentNode.getRight();
        }
    }

    /*
        A brief on how the In Order traversal works for a binary tree.
        1. All the nodes in the balanced tree will go through all the 3 steps below.
                => Go to left : Step 1
                => Compute the operation for the node : Step 2
                => Go to Right  : Step 3
                    => From the right node again the tree traversal follows Step 1, 2 & 3 for all the nested nodes
                    => Once done then the control goes back to the node from where the control was branched out
        2. Refer the link : https://www.youtube.com/watch?v=FsxTX7-yhOw

     */
    private static void testBinaryTreeToDoublyLinkList(BinaryNode node) {
        if (node == null) {
            return;
        }
        // Step: 1
        testBinaryTreeToDoublyLinkList(node.getLeft());

        // Step: 2
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;

        // Step: 3
        testBinaryTreeToDoublyLinkList(node.getRight());
    }

    /*
        Using Recursion
        Time Complexity:
                O(n) : Traversal happens once for each node in the Tree

        Space Complexity: Auxiliary space consumed coz of the recursion stack
                O(h) : Best case when the Binary Tree is balanced
                O(n) : Skewed Binary Tree
     */

    static class Counter {
        int count =0;
    }

    private static int testSingleValuedSubTreeV1() {

        BinaryT tree = new BinaryT();
        tree.setRoot(new BinaryNode(5));
        // Set Left
        tree.getRoot().left = new BinaryNode(4);
        tree.getRoot().getLeft().left = new BinaryNode(4);
        tree.getRoot().getLeft().right = new BinaryNode(4);

        // Set right
        tree.getRoot().setRight(new BinaryNode(5));
        tree.getRoot().getRight().setRight(new BinaryNode(5));
        Counter counter = new Counter();
        testSingleValuedSubTreeV1(tree.getRoot(), counter);
        return counter.count;
    }

    private static boolean testSingleValuedSubTreeV1(BinaryNode node, Counter counter) {
        if (node == null) {
            return true;
        }

        boolean leftValue = testSingleValuedSubTreeV1(node.getLeft(), counter);
        boolean rightValue = testSingleValuedSubTreeV1(node.getRight(), counter);

        if (leftValue == false  || rightValue == false) {
            return false;
        }

        if (node.getLeft() != null && node.getKey() != node.getLeft().getKey()) {
            return false;
        }

        if (node.getRight() != null && node.getKey() != node.getRight().getKey()) {
            return false;
        }
        /*
            Control reaching at this stage infers that:
                1. Left node key (value) = Calling parent node.getKey()
                2. Right node key (value) = Calling parent node.getKey()
                3. node.getLeft() and node.getRight() != null

        */
        counter.count++;
        return true;
    }

    /*
        Using Queue

        Time Complexity : O(n) : Control traverses through each node once

        Space Complexity : Space required for storing n Nodes in the Queue
                          O(w) : w = width of the tree
                          O(1) : Skewed Binary Tree
     */
    private static int testSingleValuedSubTreeV2() {
        BinaryT tree = new BinaryT();
        tree.setRoot(new BinaryNode(5));
        // Set Left
        tree.getRoot().left = new BinaryNode(4);
        tree.getRoot().getLeft().left = new BinaryNode(4);
        tree.getRoot().getLeft().right = new BinaryNode(4);

        // Set right
        tree.getRoot().setRight(new BinaryNode(5));
        tree.getRoot().getRight().setRight(new BinaryNode(5));
        Counter counter = new Counter();
        testSingleValuedSubTreeV2(tree.getRoot(), counter);
        return counter.count;
    }

    private static void testSingleValuedSubTreeV2(BinaryNode root, Counter counter) {
        if (root == null) {
            System.out.println("Tree is empty..");
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryNode currentNode = queue.poll();
            if (checkIfSingleValuedNode(currentNode, counter)) {
                counter.count++;
            }

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }

    }

    private static boolean checkIfSingleValuedNode(BinaryNode currentNode, Counter counter) {

        if (currentNode.getLeft() != null && currentNode.getLeft().getKey() != currentNode.getKey()) {
            return false;
        }

        if (currentNode.getRight() != null && currentNode.getRight().getKey() != currentNode.getKey()) {
            return false;
        }

        return true;
    }

    /*

        Time complexity : Control traverses against each element of the tree
                           O(n)

        Space complexity : Auxiliary space : Extra storage required to store the elements in the Queue
                           O(W) : Width of the queue
                           O(1) : Best case, for Skewed queues

     */
    private static void testLargestValueInEachLevelOfBSTV1() {
        BinaryT tree = new BinaryT();
        tree.setRoot(new BinaryNode(4));
        tree.getRoot().setLeft(new BinaryNode(9));
        tree.getRoot().setRight(new BinaryNode(2));
        tree.getRoot().getLeft().setLeft(new BinaryNode(3));
        tree.getRoot().getLeft().setRight(new BinaryNode(5));
        tree.getRoot().getRight().setRight(new BinaryNode(7));

        testLargestValueInEachLevelOfBST(tree.getRoot());
    }

    private static void testLargestValueInEachLevelOfBST(BinaryNode root) {
        if (root == null) {
            System.out.println("Tree is empty ....");
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        queue.add(root);

;       while (!queue.isEmpty()) {
            int size = queue.size();
            int maxValue = Integer.MIN_VALUE;
            System.out.println("***********************");
            /*
                Before the for loop begins the maxValue will always be computed against the element the queue is Pre loaded with
                before it begins the for loop.

                Does not take into account the elements added while the for loop is in progress

                        4
                       / \
                      9   2
                     / \   \
                    3   5   7

                ***********************
                    Values compared against:4
                ***********************
                    Values compared against:9
                    Values compared against:2
                ***********************
                    Values compared against:3
                    Values compared against:5
                    Values compared against:7
                ***********************


             */
            for (int i = 0; i < size; i++) {
                BinaryNode currentNode = queue.poll();
                System.out.println("Values compared against:" + currentNode.getKey());
                maxValue = Math.max(maxValue, currentNode.getKey());

                if (currentNode.getLeft() != null) {
                    queue.add(currentNode.getLeft());
                }

                if (currentNode.getRight() != null) {
                    queue.add(currentNode.getRight());
                }
            }
            System.out.println("***********************");
            System.out.println("Max value: " + maxValue);
            list.add(maxValue);
        }
        System.out.println("Largest Value each level V1: " + list);
    }

    /*
        Recursion : Using PreOrder Traversal

        Time complexity :
                        O(n) : Traversal compares each node in the tree

        Space complexity : Auxiliary space for recursion stack
                        O(H) : Height of the tree
                        O(n) : Tree is skewed
     */
    private static void testLargestValueInEachLevelOfBSTV2() {

        BinaryT tree = new BinaryT();
        tree.setRoot(new BinaryNode(4));
        tree.getRoot().setLeft(new BinaryNode(9));
        tree.getRoot().setRight(new BinaryNode(2));
        tree.getRoot().getLeft().setLeft(new BinaryNode(3));
        tree.getRoot().getLeft().setRight(new BinaryNode(5));
        tree.getRoot().getRight().setRight(new BinaryNode(7));

        Map<Integer, Integer> parityMap = new HashMap<>();
        int rootHeight = 0;
        testLargestValueInEachLevelOfBSTV2(tree.getRoot(), parityMap, rootHeight);

        System.out.println("Largest Value each level V2: " + parityMap); // {0=4, 1=9, 2=7}
    }

    private static void testLargestValueInEachLevelOfBSTV2(BinaryNode root, Map<Integer, Integer> parityMap, int rootHeight) {
        if (root == null) {
            return;
        }

        if (parityMap.size() == rootHeight) {
            parityMap.put(rootHeight, root.getKey());
        } else {
            parityMap.put(rootHeight, Math.max(parityMap.get(rootHeight), root.getKey()));
        }

        testLargestValueInEachLevelOfBSTV2(root.getLeft(), parityMap, rootHeight +1);
        testLargestValueInEachLevelOfBSTV2(root.getRight(), parityMap, rootHeight + 1);
    }

    static class RightPointerNode {
        int data;
        RightPointerNode left;
        RightPointerNode right;
        RightPointerNode nextRight;

        RightPointerNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }

    /*
            Using recursion : Pre Order Traversal

            Time Complexity : O(n) : Traversal though each node in the Binary Tree

            Space Complexity :
                              O(n) : Auxiliary space required for recursion stack and parity map creation
     */
    private static void testConnectNodesAtSameLevel() {

        /*
               10
              / \
             8   2
            /
           3
        */

        RightPointerNode root = new RightPointerNode(10);
        root.left = new RightPointerNode(8);
        root.right = new RightPointerNode(2);
        root.left.left = new RightPointerNode(3);

        Map<Integer, Queue<RightPointerNode>> parityMap = new HashMap<>();
        int treeHeight = 0;

        testConnectNodesAtSameLevel(root, parityMap, treeHeight);
        System.out.println();
        System.out.println("ConnectNodesAtSameLevel: " + parityMap);

        for (var mapEntry: parityMap.entrySet()) {
            var queue = mapEntry.getValue();
            var currentNode = queue.poll();
            System.out.print("# ");
            System.out.print(currentNode.data + " ");
            while (!queue.isEmpty()) {
                currentNode.nextRight = queue.poll();
                currentNode = currentNode.nextRight;
                System.out.print(currentNode.data + " ");
            }
        }
    }

    private static void testConnectNodesAtSameLevel(RightPointerNode node, Map<Integer, Queue<RightPointerNode>> parityMap, int treeHeight) {
        if (node == null) {
            return;
        }

        if (parityMap.size() == treeHeight) {
            Queue<RightPointerNode> queue = new LinkedList<>();
            queue.add(node);
            parityMap.put(treeHeight, queue);
        } else {
            Queue<RightPointerNode> stack = parityMap.get(treeHeight);
            stack.add(node);
        }

        testConnectNodesAtSameLevel(node.left, parityMap, treeHeight + 1);
        testConnectNodesAtSameLevel(node.right, parityMap, treeHeight + 1);
    }

    /*
        Time complexity:  O(n) : Control traverses through each node in the Binary tree

        Space complexity: Extra Auxiliary space required for:
                                   recursion stack
                                   Queue for storing n nodes for level order traversing
                         O(h) : Height of the tree
                         O(n) : Binary tree is skewed


                 1
                / \
               2   3
              / \
             4   5

             Mirror :

               1
              / \
             3   2
                / \
               5   4

     */
    private static void testMirrorTree() {
        // Height: 0
        BinaryNode root = new BinaryNode(1);
        // Height: 1
        root.setLeft(new BinaryNode(2));
        root.setRight(new BinaryNode(3));
        // Height: 2
        root.getLeft().setLeft(new BinaryNode(4));
        root.getLeft().setRight(new BinaryNode(5));

        testMirrorTree(root);
        System.out.println("Mirror Tree: ");
        printLevelOrder(root);
    }
    /*

            // Mirror Tree:
               1
              / \
             3   2
                / \
               5   4
     */
    private static void printLevelOrder(BinaryNode root) {
        if (root == null) {
            System.out.println("Empty Tree ...");
            return;
        }

        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        while (!queue.isEmpty()) {
            var currentNode = queue.poll();
            stringBuilder.append(currentNode.key).append(" ");

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
        stringBuilder.append(" ]");
        System.out.println(stringBuilder);
    }


    private static void testMirrorTree(BinaryNode node) {
        if (node == null) {
            return;
        }

        testMirrorTree(node.getLeft());
        testMirrorTree(node.getRight());

        BinaryNode leftNode = node.getLeft();
        BinaryNode rightNode = node.getRight();

        node.setLeft(rightNode);
        node.setRight(leftNode);
    }

    /*
        Using Pre Order Traversal

        Time Complexity : O(n^2)
                         For each node in the primary tree the control traverses through each node in the child tree
                         Equality check runs n times in the child node for each node traversal in the parent tree
                         Worst case would be when the in equality is present on the last leaf node of the tree comparison.

        Space Complexity :
                        Auxiliary space required for recursion call stack
     */
    private static boolean testCheckIfSubTree() {

        /*
                  26
                 /  \
                10   3
               / \    \
              4   6    3
               \
                30
         */
        BinaryNode root1 = new BinaryNode(26);
        // Level 1 : Left
        root1.setLeft(new BinaryNode(10));

        // Level 2 : Left
        root1.getLeft().setLeft(new BinaryNode(4));
        root1.getLeft().setRight(new BinaryNode(6));

        // Level 0 : Right
        root1.setRight(new BinaryNode(3));
        root1.getRight().setRight(new BinaryNode(3));

        // Level 3
        root1.getLeft().getLeft().setRight(new BinaryNode(30));


        /*
                  10
                 /  \
                4    6
                 \
                  30
         */

        BinaryNode root2 = new BinaryNode(10);
        // Level 1
        root2.setLeft(new BinaryNode(4));
        root2.setRight(new BinaryNode(6));

        // Level 2
        root2.getLeft().setRight(new BinaryNode(30));

        return testCheckIfSubTree(root1, root2);
    }

    private static boolean checkIfIdentical(BinaryNode mainRoot, BinaryNode childRoot) {

        // If both the trees are NULL then they are considered equal
        if (mainRoot == null && childRoot == null) {
            return true;
        }

        if (mainRoot != null && childRoot != null) {
            /*
                For each iteration the following 3 steps are executed :
                1. Check the node key at that level
                2. Recursive check to verify the equality of the left side of the tree
                3. Recursive check to verify the equality of the right side of the tree
             */
             var keyMatched = mainRoot.getKey() == childRoot.getKey();
             var leftSubTreeMatch = checkIfIdentical(mainRoot.getLeft(), childRoot.getLeft());
             var rightSubTreeMatch = checkIfIdentical(mainRoot.getRight(), childRoot.getRight());

             // Final result is the cumulative of the boolean return of the previous recursion calls.
             return keyMatched && leftSubTreeMatch && rightSubTreeMatch;
        }

        // Handles case when one of the tree is null and other isn't
        return false;
    }

    private static boolean testCheckIfSubTree(BinaryNode node1, BinaryNode node2) {
        if (node2 == null) {
            return true;
        }

        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        boolean isSubTreeIdentical = checkIfIdentical(node1, node2);

        if (isSubTreeIdentical) {
            return true;
        }
        /*
            If the main and sub tree are not found to be identical then we continue to check if the subtree
            is present on the left side or right side of the main tree
         */
        var leftMatch = testCheckIfSubTree(node1.getLeft(), node2);
        var rightMatch = testCheckIfSubTree(node1.getRight(), node2);
        return leftMatch || rightMatch;
    }

    /*
        Time complexity:
                       O(n) : Control traverses though each node of the tree

        Space complexity:
                        O(w) : Width of the tree
                        O(log n) : Balanced binary trees
                        O(1) : Best case when tree is skewed
     */
    private static void testEvenOddLevelDiff() {

        // Hardcoded input binary tree
        //       10
        //      /  \
        //     20   30
        //    /  \
        //  40    60

        BinaryNode rootNode = new BinaryNode(10);
        // Level 1
        rootNode.setLeft(new BinaryNode(20));
        rootNode.setRight(new BinaryNode(30));

        // Level 2
        rootNode.getLeft().setLeft(new BinaryNode(40));
        rootNode.getLeft().setRight(new BinaryNode(60));

        testEvenOddLevelDiff(rootNode);
    }

    private static void testEvenOddLevelDiff(BinaryNode node) {
        if (node == null) {
            System.out.println("Invalid input ...");
            System.exit(1);
        }
        /*
               10
              /  \
             20   30
            /  \
          40    60

         */
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);
        int oddSum = 0;
        int evenSum = 0;
        int level = 1;


        while (!queue.isEmpty()) {

            BinaryNode currentNode = queue.poll();

            if (currentNode == null) {
                level++;
            } else {
                // Loop for odd levels
                if (level % 2 != 0) {
                    oddSum += currentNode.getKey();
                }

                // Loop for even levels
                if (level % 2 == 0) {
                    evenSum += currentNode.getKey();
                }

                if (currentNode.getLeft() != null) {
                    queue.add(currentNode.getLeft());
                }

                if (node.getRight() != null) {
                    queue.add(currentNode.getRight());
                }
                queue.add(null);
            }


        }
        System.out.println("Odd sum: " + oddSum);
        System.out.println("Even sum: " + evenSum);

        System.out.println("Odd sum - Even sum: " + (oddSum - evenSum));
    }

    public static void main(String[] args) {

        int[] arr = null;

        /* ***************** Easy ***************** */

        // Children Sum Parent
        arr = new int[]{60, 10, 50, 20, 5};
        testChildrenSumParent(arr);

        // Determine if two trees are identical
        arr = new int[]{60, 10, 50, 20, 5};
        int[] tmp = new int[]{60, 10, 50, 20, 5};
        // Using DFS
        var resp = testDetermineIfIdenticalV1(arr, tmp);
        System.out.println("Tree Identical V1: " + resp);

        // Using Level Order
        arr = new int[]{60, 10, 50, 20, 6};
        tmp = new int[]{60, 10, 50, 20};
        resp = testDetermineIfIdenticalV2(arr, tmp);
        System.out.println("Tree Identical V2: " + resp);


        // Sorted Array to Balanced BST : Using Recursion
        arr = new int[]{60, 10, 50, 20, 6};
        BinaryNode rootNode = testSortedArrayToBalancedBSTV1(arr);
        System.out.println("Balanced Binary Tree V1: ");
        BinaryT.printPreOrder(rootNode);
        System.out.println();

        // Sorted Array to Balanced BST : Using Queues
        arr = new int[]{60, 10, 50, 20, 6};
        rootNode = testSortedArrayToBalancedBSTV2(arr);
        System.out.println("Balanced Binary Tree V2: ");
        BinaryT.printPreOrder(rootNode);
        System.out.println();


        // Diameter of tree
        arr = new int[]{5, 8, 6, 3, 7, 9};
        int diameter = testDiameterOfATree(arr);
        System.out.println("Tree diameter: " + diameter);


        // Maximum Depth/Height of Binary Tree
        arr = new int[]{45, 36, 67, 22, 41, 66, 75};
        testTreeHeight(arr);

        //Largest value in each level of binary tree
        testLargestValueInEachLevelOfBSTV1(); // Using Queue (Level Order)
        testLargestValueInEachLevelOfBSTV2(); // Using recursion (In Order)

        // Mirror tree
        testMirrorTree();


        /* ***************** Medium ***************** */

        // Vertical Traversal of a Binary Tree
        arr = new int[]{10, 7, 15, 3, 9, 12, 18};
        testVerticalTraversalOfBST(arr);

        // Sum Tree
        arr = new int[]{26, 10, 3, 4, 6, 3};
        boolean isSumTree = testIfSumTree(arr);
        System.out.println("IsSumTree: " + isSumTree);

        // Single Valued Subtree
        int result = testSingleValuedSubTreeV1(); // Using recursion
        System.out.println("SingleValuedSubTree V1: " + result);

        result = testSingleValuedSubTreeV2(); // Using Queue (BFS)
        System.out.println("SingleValuedSubTree V2: " + result);

        // Check if subtree
        boolean isSubTree = testCheckIfSubTree();
        System.out.println("Check if SubTree: " + isSubTree);

        // Odd Even Level Difference
        testEvenOddLevelDiff();

        /* ***************** Hard ******************* */

        // Sorted Linked List to BST
        rootNode = testSortedLinkedListToBST();
        System.out.println("Linked List BST Pre Order: ");
        BinaryT.printPreOrder(rootNode);

        // Binary Tree to Doubly Linked List
        arr = new int[]{5, 10, 15, 20, 25, 30};
        testBinaryTreeToDoublyLinkList(arr);

        // Connect nodes at same level
        testConnectNodesAtSameLevel();
    }



}
