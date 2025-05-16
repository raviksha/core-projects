package org.self.yahoo.book.demo.chap2.queue.worksheet;

import org.self.yahoo.book.demo.chap2.queue.DoubleLinkedListNode;
import org.self.yahoo.book.demo.chap2.stack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


class StackUsingTwoQueues {

    int size = 0;
    Queue primary;
    Queue temp;

    public StackUsingTwoQueues() {
        primary = new Queue();
        temp = new Queue();
    }

    public void push(int item) {

        size++;
        temp.enQueue(item);

        while (!primary.isEmpty()) {
            temp.enQueue(primary.deQueue());
        }
        primary = temp;
        temp = new Queue();
    }

    public int pop() {

        if (primary.isEmpty()) {
            return -1;
        }
        size--;
        return primary.deQueue();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        DoubleLinkedListNode currentNode = primary.head;
        while (currentNode != null) {
            stringBuilder.append(currentNode.getData());
            currentNode = currentNode.getNext();

            if (currentNode != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}

class Queue {
    DoubleLinkedListNode head;
    DoubleLinkedListNode tail;
    int size;

    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    public void enQueue(int item) {
        DoubleLinkedListNode node = new DoubleLinkedListNode(item, null, null);
        size++;
        if (head == null) {
            node.setNext(null);
            head = node;
            return;
        }
        if (tail == null) {
            node.setNext(null);
            tail = node;
            tail.setPrevious(head);
            head.setNext(tail);
            return;
        }
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
    }

    public int deQueue() {
        if (head == null) {
            return -1;
        }
        size--;

        if (tail == null) {
            int currentData = head.getData();
            head = null;
            return currentData;
        }
        int currentItem = head.getData();
        DoubleLinkedListNode nextNode = head.getNext();

        if (nextNode != null) {
            head.setNext(null);
            head = nextNode;
        } else {
            head = null;
            tail = null;
        }

        return currentItem;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getFirst() {
        if (head == null) {
            return -1;
        }

        return head.getData();
    }


    public int getLast() {

        if (tail == null && head == null) {
            return -1;
        }

        if (tail == null && head != null) {
            return head.getData();
        }
        return tail.getData();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");

        DoubleLinkedListNode node = head;
        while (node != null) {
            stringBuilder.append(node.getData());
            node = node.getNext();

            if (node != null) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}

class QueueUsingArray {

    int[] qArr = null;
    int capacity;
    int size;

    int head = 0;
    int tail = 0;

    boolean isFull = false;

    public QueueUsingArray(int capacity) {
        qArr = new int[capacity];
        head = -1;
        tail = -1;
        this.capacity = capacity;
        Arrays.fill(qArr, -1);
    }

    public int getSize() {
        return size;
    }

    public void enQueue(int item) {
        if (tail >= size) {
            System.out.println("Queue overflow ....");
            System.exit(-1);
        }
        size++;

        if (tail == -1 || head == -1) {
            head++;
            tail++;
            qArr[tail] = item;
            return;
        }
        tail++;
        qArr[tail] = item;
    }

    public int deQueue() {
        if (size == 0) {
            System.out.println("Queue is empty..");
            System.exit(-1);
        }
        size--;
        int currItem = qArr[tail];
        qArr[tail] = -1;
        tail--;
        return currItem;
    }

    @Override
    public String toString() {
        return Arrays.toString(qArr);
    }

    public boolean isFull() {
        System.out.println("*******  is Full *********");
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + capacity);
        return size == capacity;
    }
}

public class QueueInterviewQuestions {


    private static void swapKElementsBackToFront(Queue queue, int noOfFrontElementsToSwap) {
        if (noOfFrontElementsToSwap <= 0) {
            return;
        }
        noOfFrontElementsToSwap--;
        int item = queue.deQueue();
        queue.enQueue(item);
        swapKElementsBackToFront(queue, noOfFrontElementsToSwap);

    }

    private static void reverseQueueElements(Queue queue, int numberOfElements) {
        if (numberOfElements <= 0) {
            return;
        }
        numberOfElements--;
        int item = queue.deQueue();
        reverseQueueElements(queue, numberOfElements);
        /*
            At this point the contents of the Queue is :
             Head => [ 60, 70, 80, 90, 100, 50, 40, 30, 20, 10 ]  <= Tail
         */
        queue.enQueue(item);
    }

    /*
         Using recursion
         Time Complexity  :  O(n)
         Auxiliary Space Complexity :  O(n) : Due to the extra space required to store recursive calls
     */
    private static void testReverseFirstKElementsOfQueueV1() {
        Queue queue = new Queue();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);
        queue.enQueue(50);
        queue.enQueue(60);
        queue.enQueue(70);
        queue.enQueue(80);
        queue.enQueue(90);
        queue.enQueue(100);

        System.out.println("Input Queue: " + queue); // Head => [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ] <= Tail

        int elementsToReverseCtr = 5;
         /*
            At this point the contents of the Queue is :
             Head => [ 60, 70, 80, 90, 100, 50, 40, 30, 20, 10 ]  <= Tail
         */

        reverseQueueElements(queue, elementsToReverseCtr);

        /*
            Next steps is to move [60, 70, 80, 90, 100] to the end of the queue : [ 50, 40, 30, 20, 10, 60, 70, 80, 90, 100 ]
        */
        int noOfFrontElementsToSwap = queue.size - elementsToReverseCtr;
        swapKElementsBackToFront(queue, noOfFrontElementsToSwap);

        System.out.println("Result Queue: " + queue); // Head => [ 50, 40, 30, 20, 10, 60, 70, 80, 90, 100 ] <= Tail
    }

    private static void reverseQueueElementPointerMapping(Queue queue, int elementsToReverseCtr) {
        if (elementsToReverseCtr <= 0) {
            System.exit(1);
        }
        int origCtr = elementsToReverseCtr;
        Stack tmpStack = new Stack();

        while (elementsToReverseCtr > 0) {
            int item = queue.deQueue();
            tmpStack.push(item);
            elementsToReverseCtr--;
        }

        while (!tmpStack.isEmpty()) {
            int item = tmpStack.pop();
            queue.enQueue(item);
        }

        System.out.println("Interim queue: " + queue); // Head =>  [ 70, 80, 90, 100, 60, 50, 40, 30, 20, 10 ]  <= Tail

        /*
            At this time the first elementsToReverseCtr are flipped and pushed to the end of the queue [ 70, 80, 90, 100, 60, 50, 40, 30, 20, 10 ]
            Next steps is to dequeue the first {pendingItemsToFlip} elements and queue them to the end of the queue
         */
        int pendingItemsToFlip = queue.size - origCtr;

        while (pendingItemsToFlip > 0) {
            int item = queue.deQueue();
            queue.enQueue(item);
            pendingItemsToFlip--;
        }
        // Result queue :  [ 60, 50, 40, 30, 20, 10, 70, 80, 90, 100 ]
    }

    /*
        Using Doubly linked list
     */
    private static void testReverseFirstKElementsOfQueueV2() {
        Queue queue = new Queue();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);
        queue.enQueue(50);
        queue.enQueue(60);
        queue.enQueue(70);
        queue.enQueue(80);
        queue.enQueue(90);
        queue.enQueue(100);

        int elementsToReverseCtr = 5;

        System.out.println("Input Queue: " + queue); // [ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ]

        reverseQueueElementPointerMapping(queue, elementsToReverseCtr);

        System.out.println("Result Queue: " + queue.toString());
    }

    /*
        Time Complexity : O(1) : For both enQueue() and deQueue()
        Auxiliary Space : O(n) : Coz we need an extra array to store the queue elements
     */
    private static void testImplementQueueUsingArray() {
        QueueUsingArray queueUsingArray = new QueueUsingArray(5);
        queueUsingArray.enQueue(1);
        queueUsingArray.enQueue(2);
        queueUsingArray.enQueue(3);

        System.out.println("Queue size: " + queueUsingArray.getSize());
        System.out.println("queueUsingArray: " + queueUsingArray);


        int deQItem = queueUsingArray.deQueue();

        System.out.println("deQItem: " + deQItem);
        System.out.println("Queue size: " + queueUsingArray.getSize());
        System.out.println("queueUsingArray: " + queueUsingArray);

        queueUsingArray.deQueue();
        queueUsingArray.deQueue();

        System.out.println("Queue size: " + queueUsingArray.getSize());
        System.out.println("queueUsingArray: " + queueUsingArray);

        queueUsingArray.enQueue(1);
        queueUsingArray.enQueue(2);
        queueUsingArray.enQueue(3);
        queueUsingArray.enQueue(4);
        queueUsingArray.enQueue(5);

        System.out.println("Queue size: " + queueUsingArray.getSize());
        System.out.println("Queue: isFull: " + queueUsingArray.isFull());

        queueUsingArray.deQueue();
        queueUsingArray.deQueue();
        queueUsingArray.deQueue();

        System.out.println("Queue size: " + queueUsingArray.getSize());
        System.out.println("queueUsingArray: " + queueUsingArray);


    }

    private static void reverseQueue(Queue queue) {
        if (queue.isEmpty()) {
            return;
        }
        int currItem = queue.deQueue();
        reverseQueue(queue); // Drain the queue recursively .....

        /*
            At this point the queue is drained and the elements and dequeued in the order 5, 4, 3, 2, 1
            Now start filling the queue back in the reverse order : 1, 2, 3, 4, 5
        */
        queue.enQueue(currItem);
    }

    /*
        Time Complexity : O(n)
        Space Complexity : O(n) : Extra space required for the recursion call stack.
     */
    private static void testReverseAQueueUsingRecursion() {

        Queue queue = new Queue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);

        System.out.println("Input queue: " + queue);

        reverseQueue(queue);

        System.out.println("Reversed Queue: " + queue);
    }

    private static void testImplementLRUCacheUsingQueue() {
        // Check : LRUCache.java
    }

    /*
        https://www.geeksforgeeks.org/implementation-deque-using-circular-array/

     */
    private static void testDequeUsingArray() {
        // Check : DeQueueUsingArray
    }

    /*

     */
    private static void testPrintAllElementsInNewLine() {
    }

    /*
            Time Complexity : O(n^2)
            Space Complexity : O(n) : Extra space required to store the elements on the Stack
     */
    private static void testCheckIfQueueCanBeSortedUsingStack() {
        Queue queue = new Queue();
        queue.enQueue(11);
        queue.enQueue(2);
        queue.enQueue(33);
        queue.enQueue(44);
        queue.enQueue(5);

        System.out.println("Input Queue: " + queue); //  Head => [ 11, 2, 33, 44, 5 ] <= Tail

        Stack stack = new Stack();

        while (!queue.isEmpty()) {
            int currItem = queue.deQueue();

            // Iterate the Stack till the point when the current item > current stack element
            // Till then push the items which are smaller than current items from the stack back int the queue
            while (!stack.isEmpty() && currItem > stack.peek()) {
                queue.enQueue(stack.pop());
            }

            // At this point the currItem <= to the top element of the stack
            stack.push(currItem);
        }
        // Add the sorted elements back in the queue
        while (!stack.isEmpty()) {
            queue.enQueue(stack.pop());
        }
        System.out.println("Sorted queue: " + queue);

    }

    /*
        Time complexity :
            Push : O(n) : For each element the Queue needs to be all popped and loaded again.
                          The complexity is NOT O(n^2) coz the iteration is not exponential.
            Pop  : O(1)

        Space complexity : O(n) : Extra space required for extra temp queue
     */
    private static void testImplementStackUsingQueues() {

        StackUsingTwoQueues stackUsingTwoQueues = new StackUsingTwoQueues();
        stackUsingTwoQueues.push(1);
        stackUsingTwoQueues.push(2);
        stackUsingTwoQueues.push(3);
        stackUsingTwoQueues.push(4);
        stackUsingTwoQueues.push(5);

        System.out.println("StackUsingTwoQueues: " + stackUsingTwoQueues);//  Head => [5, 4, 3, 2, 1]

        // Item popped should be : 5 Head => [5, 4, 3, 2, 1]
        int currItem = stackUsingTwoQueues.pop();
        System.out.println(currItem == 5);

        // Item popped should be 4 : Head => [4, 3, 2, 1]
        currItem = stackUsingTwoQueues.pop();
        System.out.println(currItem == 4);

    }

    /*

        Using a Java HashSet and a ArrayList

        Input: s = “geeksforgeeks”
        Output: ‘f’
        Explanation: ‘f’ is the first character in the string which does not repeat.

        Time complexity : O(n^2) : Worst Case
            ArrayList:
                uniqElements.add(strChar): O(1) (appending to the list).
                uniqElements.contains(strChar): O(N) in worst case.
                uniqElements.remove(index): O(N) worst case (shifting elements).
            HashSet:
                hashSet.contains(strChar): O(1)
	            hashSet.add(strChar): O(1)

        Space complexity: O(n)
     */
    private static void testFindFirstNonRepeatingCharacterV1() {
        String sample = "geeksforgeeks";
       // String sample = "aabbccc";
        Set<Character> hashSet= new HashSet<>();
        List<Character> uniqElements= new ArrayList<>();

        for (int i = 0; i < sample.length(); i++) {
            var strChar = sample.charAt(i);
            if (!hashSet.contains(strChar)) {
                hashSet.add(strChar);
                uniqElements.add(strChar);
            } else if (uniqElements.contains(strChar)) {
                uniqElements.remove(uniqElements.indexOf(strChar));
            }
        }
        var result = (!uniqElements.isEmpty() && uniqElements.get(0) != -1) ? uniqElements.get(0) : "#";
        System.out.println("First Non repeating Unique element: " + result);
    }

    /*
        Using Java Hashset and Queue

     */

    private static void testFindFirstNonRepeatingCharacterV2() {

        String sample = "geeksforgeeks";
        char nonRepeatingChar = '#';

        Set<Character> characterSet = new HashSet<>();
        Queue queue = new Queue();
        Queue tmpQueue = new Queue();

        for (int i = 0; i < sample.length(); i++) {
            var element = sample.charAt(i);

            // Add elements to the HashSet and Queue if they do not exist
            if (!characterSet.contains(element)) {
                characterSet.add(element);
                queue.enQueue((int)element);
            } else {
                // If an elements exists then the queue needs to be cleaned up to  ensure that
                // it will always contain the non repeating char
                // Iterate the current queue and remove the current iteration {element} if it exists in the queue.
                // Head of the queue will always contain the first non-repeating character
                tmpQueue = new Queue();
                while (!queue.isEmpty()) {
                    if (element != (char) queue.getFirst()) {
                        tmpQueue.enQueue((char)queue.deQueue());
                    } else {
                        queue.deQueue();
                    }
                }
                queue = tmpQueue;
            }
        }

        if (!queue.isEmpty()) {
            nonRepeatingChar = (char) queue.deQueue();
        }
        System.out.println("First non repeating character: " + nonRepeatingChar);
    }



    public static void main(String[] args) {
        System.out.println("QueueInterviewQuestions.....");
        /* Complexity: Easy */

        // Reverse First k Elements of Queue
        //testReverseFirstKElementsOfQueueV1();  // Using recursion
        //testReverseFirstKElementsOfQueueV2();  // Using Doubly Linked List

        // Implement a Queue using an Array
        // testImplementQueueUsingArray();

        // Print all elements of a queue in a new line
        testPrintAllElementsInNewLine(); // TODO


        /* Complexity: Medium */

        // Reverse a queue using recursion
        testReverseAQueueUsingRecursion();

        // Implementation of Deque using circular array
        testDequeUsingArray(); // TODO

        // Check if a queue can be sorted into another queue using a stack
        testCheckIfQueueCanBeSortedUsingStack();

        // Implement Stack using Queues
        testImplementStackUsingQueues();

        /* Complexity: Hard */

        // Implement LRU Cache using Queue
       // testImplementLRUCacheUsingQueue();

        // Find the first non-repeating character from a stream of characters
        //testFindFirstNonRepeatingCharacterV1();
        testFindFirstNonRepeatingCharacterV2();

    }
}
