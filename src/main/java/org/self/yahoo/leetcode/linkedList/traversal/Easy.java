package org.self.yahoo.leetcode.linkedList.traversal;


import org.w3c.dom.NodeList;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

     public void printList(ListNode headNode) {
          ListNode currentNode = headNode;
          while ((currentNode != null)) {
              System.out.print(currentNode.val + "=>");
              currentNode = currentNode.next;
          }
         System.out.println();
     }

 }


public class Easy {

    private static ListNode testReverseLinkListV1(ListNode headNode) {

        ListNode prevNode = null;
        ListNode currentNode = headNode;

        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = prevNode;

            prevNode = currentNode;
            currentNode = nextNode;
        }
        return prevNode;
    }

    private static ListNode testReverseLinkListV2(ListNode headNode) {

        if (headNode == null || headNode.next == null) {
            return headNode;
        }
        ListNode newHead = testReverseLinkListV2(headNode.next);
        headNode.next.next = headNode;
        headNode.next = null;
        return newHead;
    }

    private static  ListNode initilizeNodeList() {
        ListNode headNode = new ListNode(1);
        headNode.next = new ListNode(2);
        headNode.next.next = new ListNode(3);
        headNode.next.next.next = new ListNode(4);
        headNode.next.next.next.next = new ListNode(5);
        return headNode;
    }

    public static void main(String[] args) {
        System.out.println("Linked List Traversal Easy ....");
        System.out.println("*****************   Reverse Linked List ***************");
        // Leet code 206. Reverse Linked List
        ListNode headNode = initilizeNodeList();
        System.out.println("Input List: ");

        headNode.printList(headNode);

        /*
            Iterative solution

            Time complexity: O(n): Iterates through the loop for all n nodes in the Linked List
            Space complexity: O(1): No extra space required
         */
        ListNode reverseHeadNode = testReverseLinkListV1(headNode);
        System.out.println("Reversed List V1: ");
        headNode.printList(reverseHeadNode);


        headNode= initilizeNodeList();
        /*
            Time complexity: O(n) : Iterates through all the n nodes on a Link List

            Space complexity: O(n): Extra space required for the recursion stack
         */
        reverseHeadNode = testReverseLinkListV2(headNode);
        System.out.println("Reversed List V2: ");
        headNode.printList(reverseHeadNode);
    }
}
