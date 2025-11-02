package org.self.yahoo.book.demo.chap2.stack.worksheet;

import org.apache.commons.lang3.StringUtils;
import org.self.yahoo.book.demo.chap2.queue.Queue;
import org.self.yahoo.book.demo.chap2.stack.StringStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class NodeToImplement {
    int data;
    NodeToImplement next;
}

class StackToImplement {

}

class StackUsingQueue {
    Queue queue;
    int size;

    public StackUsingQueue() {
        this.queue = new Queue();
    }

    public void push(int item) {
        size++;
        queue.enqueue(item);
        for (int i = 1; i < queue.getSize(); i++) {
            int tmpItem = queue.dequeue();
            queue.enqueue(tmpItem);
        }
        System.out.println("Queue size: " + queue.getSize());
        System.out.println("Final queue:" + queue);
        System.out.println("...");
    }

    public int pop() {
        if (queue.getSize() == 0) {
            return -1;
        }
        size--;
        var popItem = queue.dequeue();
        return popItem;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

class StackUsingTwoQueues {
    Queue primary;
    Queue secondary;
    int size;

    public StackUsingTwoQueues() {
        primary = new Queue();
        secondary = new Queue();
    }

    public int pop() {
        return primary.dequeue();
    }

    public void push(int item) {

        size++;
        secondary.enqueue(item);
        int size = primary.getSize();
        while (size > 0) {
            int tmpItem = primary.dequeue();
            secondary.enqueue(tmpItem);
            size--;
        }
        primary = secondary;
        secondary = new Queue();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return primary.toString();
    }
}

class StackWithMinimum {
    int minVal;
    org.self.yahoo.book.demo.chap2.stack.Stack stack;

    public StackWithMinimum() {
        stack = new org.self.yahoo.book.demo.chap2.stack.Stack();
    }

    public void push(int item) {
        if (stack.isEmpty()) {
            stack.push(item);
            minVal = item;
            return;
        }
        if (item >= minVal) {
            stack.push(item);
        } else { // if item <  minVal
            /*
                / Mathematical formula to store the second last min value along with the hashed current value
                 2 * newValue - minVal
                 Makes it easier the retrieve the second last minimum value when the current minimum is popped
             */
            int minHash = 2 * item - minVal;
            minVal = item;
            stack.push(minHash);
        }
    }

    public int pop() {

        if (stack.isEmpty()) {
            return -1;
        }
        int curItem = stack.pop();
        if (curItem >= minVal) {
            return curItem;
        } else {
            /*
                / Mathematical formula to retrieve the second last minimum value before the current one.
                 2 * minVal - modifiedValue : Retrieve old minimum on pop
             */
            minVal = 2 * minVal - curItem;
            return minVal;
        }
    }

    public int peek() {
        if (stack.isEmpty()) {
            return -1;
        }
        int currItem = stack.peek();
        if (currItem < minVal) { // If currItem < minVal means minVal stores value of t.
            return minVal;
        } else {
            return currItem;
        }
    }

    public int getMinVal() {
        return minVal;
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}


class BrowserHistory {
    String homePage;
    org.self.yahoo.book.demo.chap2.stack.StringStack forwardStack = new org.self.yahoo.book.demo.chap2.stack.StringStack();
    org.self.yahoo.book.demo.chap2.stack.StringStack backStack = new org.self.yahoo.book.demo.chap2.stack.StringStack();

    public BrowserHistory(String homePage) {
        if (homePage != null) {
            backStack.push(homePage);
        }
    }

    /*
        While visiting a new URL: Clear the forwardStack because visiting a new page invalidates forward history.
        Pop all elements from forwardStack.
        Push the new url into the backStack as it is now the current page.
     */
    public void visit(String url) {
        if (url != null) {
            while (!forwardStack.isEmpty()) {
                forwardStack.pop();
            }

            backStack.push(url);
        }
    }

    /*
        To move backward: Run a while loop for steps number of times.
        Stop early if backStack has only one element, as we can’t go back further.
        In each step: Push the top element of backStack into forwardStack.
        Pop the top element from backStack.
        After moving, return the topmost element of backStack, which is now the current page.
     */
    public String back(int steps) {
        if (steps > 0) {
            while (steps > 0) {
                if (backStack.getSize() > 0) {
                    forwardStack.push(backStack.pop());
                }
                steps--;
            }
            return backStack.peek();
        }
        return null;
    }

    /*
        To move forward: Run a while loop for steps number of times.
        Stop early if forwardStack is empty.
        In each step: Push the top element of forwardStack into backStack.
        Pop the top element from forwardStack.
        After moving, return the topmost element of backStack, which is now the current page.
     */
    public String forward(int steps) {
        if (steps > 0) {
            while (steps > 0) {
                if (forwardStack.peek() != null) {
                    backStack.push(forwardStack.pop());
                }
                steps--;
            }
            return backStack.peek(); // Topmost element of backStack is now the current page
        }

        return null;

    }
}

class Node {
    String url;
    Node prev;
    Node next;

    public Node(String url) {
        this.url = url;
        prev = null;
        next = null;
    }
}

class BrowserHistoryDoubleLL {
    Node rootNode = null;

    /*
        Initialize the browser with the given homepage by creating a new node and setting it as the starting page.
        Set the current pointer (curr) to point to the homepage node.
     */
    public BrowserHistoryDoubleLL(String homePage) {
        if (homePage != null) {
            rootNode = new Node(homePage);
        }
    }

    /*
        When visiting a new URL, create a new node for the URL.
        Set the previous pointer of the new node to the current node.
        Set the next pointer of the current node to the new node.
        Move the curr pointer to the new node.
     */
    public void visit(String url) {
        if (url != null) {
            org.self.yahoo.book.demo.chap2.stack.worksheet.Node newNode = new Node(url);
            newNode.prev = rootNode;
            rootNode.next = newNode;
            rootNode = newNode;
        }
    }

    /*
        To move backward, iterate the pointer steps times.
        In each step, move the pointer to the previous node if it’s not null.
        After moving, update the curr pointer to the new node.
        Return the URL stored in the curr node.
     */
    public String back(int steps) {
        if (steps > 0) {
            Node currentNode = rootNode;
            while (steps > 0 && currentNode.prev != null) {
                currentNode = currentNode.prev;
                steps--;
            }
            rootNode = currentNode;
            return currentNode.url;
        }
        return null;
    }

    /*
        To move forward, iterate the pointer steps times.
        In each step, move the pointer to the next node if it’s not null.
        After moving, update the curr pointer to the new node.
        Return the URL stored in the curr node.
     */
    public String forward(int steps) {
        if (steps > 0) {
            Node currentNode = rootNode;
            while (steps > 0 && currentNode.next != null) {
                currentNode = currentNode.next;
                steps--;
            }
            rootNode = currentNode;
            return rootNode.url;
        }

        return null;

    }
}


class TwoStacksUsingSingleArray {

    int capacity;
    int stack1Head;
    int stack2Head;
    int [] arr;

    public TwoStacksUsingSingleArray (int capacity) {
        this.capacity = capacity;
        stack1Head = -1;
        stack2Head = capacity;
        arr = new int[capacity];
    }

    private boolean isExceedingCurrCapacity() {
        int currCapacity = (stack1Head + (capacity - stack2Head)) + 1;
        return currCapacity > capacity;
    }

    public void push1(int item) {
        if ((stack1Head >= stack2Head)) {
            System.out.println("Stack overflow.....");
            return;
        }
        if (isExceedingCurrCapacity()) {
            System.out.println("Stack overflow.....");
            return;
        }
        stack1Head++;
        arr[stack1Head] = item;
    }


    public void push2(int item) {
        if ((stack1Head >= stack2Head)) {
            System.out.println("Stack overflow.....");
            return;
        }
        if (isExceedingCurrCapacity()) {
            System.out.println("Stack overflow.....");
            return;
        }
        stack2Head--;
        arr[stack2Head] = item;
    }

    public int pop1() {
        if (stack1Head == -1) {
            System.out.println("Stack Underflow .....");
            return -1;
        }
        int currItem = arr[stack1Head];
        stack1Head--;
        return currItem;
    }

    public int pop2() {
        if (stack2Head == capacity) {
            System.out.println(" Stack Underflow.... ");
            return -1;
        }
        int currItem = arr[stack2Head];
        stack2Head++;
        return currItem;
    }
}


public class StackInterviewQuestions {
    private static List<String> openingBrackets = new ArrayList<>();

    private static void initializeOpeningBrackets() {
        openingBrackets = Arrays.asList("[", "{", "(");
    }

    /*
        Time Complexity: O(n)
        Space Complexity : O(n) :
               It cannot be O(1) since we have to use a Stack data struct to sore value of the new split String
     */
    private static boolean testParenthesisCheckerV1() {
        var expression = " [ { ( ) } ]";
        boolean isBalanced = true;
        initializeOpeningBrackets();

        var chrArray = StringUtils.split(expression, " ");
        System.out.println("Input char [] " + Arrays.toString(chrArray));

        StringStack stack = new StringStack();

        for (int i = 0; i < chrArray.length; i++) {
            if (openingBrackets.contains(chrArray[i])) {
                stack.push(chrArray[i]);
            } else {
                var curItem = stack.pop().trim();
                var currArrItem = chrArray[i];
                switch (curItem) {
                    case "(":
                        isBalanced = currArrItem.equals(")");
                        break;
                    case "[":
                        isBalanced = currArrItem.equals("]");
                        break;
                    case "{":
                        isBalanced = currArrItem.equals("}");
                        break;
                }
            }
        }
        return isBalanced;
    }
    /*
        Approach using single queue
        Time complexity : O(n)
        Space complexity : O(n) : Since 1 extra Queue is used
     */

    private static void testImplementStackUsingQueuesV1() {
        Queue queue = new Queue();
        StackUsingQueue stackUsingQueue = new StackUsingQueue();
        stackUsingQueue.push(1);
        stackUsingQueue.push(2);
        stackUsingQueue.push(3);
        stackUsingQueue.push(4);
        stackUsingQueue.push(5);
        System.out.println("Input Stack V1: " + stackUsingQueue); // Input Stack: Head => [ 5, 4, 3, 2, 1 ] <= Tail
        stackUsingQueue.pop();
        stackUsingQueue.pop();
        System.out.println("Final Stack V1: " + stackUsingQueue); // Head => [3, 2, 1] <= Tail
    }

    /*
        Approach using 2 queues
        Time complexity : O(n)
        Space complexity : O(n) Since 2 extra Queues are used
     */
    private static void testImplementStackUsingQueuesV2() {
        StackUsingTwoQueues stackUsingTwoQueues = new StackUsingTwoQueues();
        stackUsingTwoQueues.push(1);
        stackUsingTwoQueues.push(2);
        stackUsingTwoQueues.push(3);
        stackUsingTwoQueues.push(4);
        stackUsingTwoQueues.push(5);
        System.out.println("Input Stack V2: " + stackUsingTwoQueues); // Head => [ 5, 4, 3, 2, 1 ] <= Tail

        stackUsingTwoQueues.pop();
        stackUsingTwoQueues.pop();
        System.out.println("Final Stack V1: " + stackUsingTwoQueues); // Head => [3, 2, 1] <= Tail
    }

    /*
        Time Complexity : O(n)
        Space Complexity : O(n) : Space required for Stack

         Time complexity for Adding and Removing elements :
            Add/Remove : O(1)

         Space complexity : O(n) : Storing n elements


     */
    private static void testReverseAStringUsingStackV1() {
        String input = "YAHOO";
        StringStack stringStack = new StringStack();

        for (int i = 0; i < input.length(); i++) {
            stringStack.push(Character.toString(input.charAt(i)));
        }
        System.out.println("Input Stack V1: " + stringStack); //[ O, O, H, A, Y ]

        StringBuilder stringBuilder = new StringBuilder();

        var currItem = stringStack.pop();

        while (currItem != null) {
            stringBuilder.append(currItem);
            currItem = stringStack.pop();
        }
        System.out.println("Reversed String: " + stringBuilder); //OOHAY
    }

    /*
        Using Java internal Stack API

                Time Complexity : O(n)
        Space Complexity : O(n) : Space required for Stack

         Time complexity for Adding and Removing elements :
            Add/Remove : O(1)

         Space complexity : O(n) : Storing n elements
     */
    private static void testReverseAStringUsingStackV2() {
        String input = "YAHOO";

        java.util.Stack<Character> javaStack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            javaStack.push(input.charAt(i));
        }

        System.out.println("Input Stack V2: " + javaStack);

        StringBuilder stringBuilder = new StringBuilder();
        while (!javaStack.isEmpty()) {
            stringBuilder.append(javaStack.pop());
        }

        System.out.println("Reversed String V2: " + stringBuilder);
    }

    /*
        A stack permutation is a permutation of an input sequence that can be obtained using a stack (with push and pop operations only).
        Time complexity : O(n)
        Auxiliary Space complexity : O(n) : Stack used to store the elements

     */
    private static boolean testGivenStackPermutation(int[] input, int[] output) {
        boolean isStackPermutation = false;

        int j = 0; // Pointer for the input2 array

        Stack<Integer> stack = new Stack<>();

        for (int k : input) {
            stack.push(k);

            // Iterate through the input2 array till the current stack element == to the element of input2[j]
            while (!stack.isEmpty() && stack.peek() == output[j]) {
                stack.pop();
                j++;
            }
        }
        isStackPermutation = j == output.length;
        return isStackPermutation;
    }

    /*
        https://www.geeksforgeeks.org/next-greater-element/

        Time Complexity : O(n) : Linear
        Space Complexity : O(n)

     */

    private static void testNextGreaterElementInGivenArray() {
        int[] input = {50, 40, 30, 10};     // Output  [-1, -1, -1, -1]
        //int [] input = {1, 3, 2, 4};       // Output  [3, 4, 4, -1]
        //int [] input = {6, 8, 0, 1, 3};  // Output : [8, -1, 1, 3, -1]
        int[] output = new int[input.length];

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();

        for (int i = input.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= input[i]) {
                stack.pop(); // Pop all the elements which are smaller or equal to current element in input[i]
            }
            if (!stack.isEmpty()) {
                int largerElement = stack.peek();
                output[i] = largerElement;
            } else {
                output[i] = -1;
            }
            stack.push(input[i]);
        }

        System.out.println("Next bigger element on the right for : " + Arrays.toString(input));
        System.out.println("                                       " + Arrays.toString(output));
    }

    /*
        https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
        Time complexity : O(n^2) : Worst case when the input stack is already sorted in descending order
        Space complexity : O(n)
     */

    private static void testSortAStackUsingTemporaryStack() {

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(98);
        stack.push(92);
        stack.push(23);

        System.out.println("Input Stack: " + stack); // [ 23, 92, 98, 31, 3, 34 ]

        org.self.yahoo.book.demo.chap2.stack.Stack tempStack = new org.self.yahoo.book.demo.chap2.stack.Stack();

        while (!stack.isEmpty()) {
            int temp = stack.pop();
            if (tempStack.isEmpty()) {
                tempStack.push(temp);
            } else if (tempStack.peek() < temp) {
                tempStack.push(temp);
            } else {
                while (!tempStack.isEmpty() && (tempStack.peek() > temp)) {
                    int stkItem = tempStack.pop();
                    stack.push(stkItem);
                }
                tempStack.push(temp);
            }
        }
        System.out.println("Sorted Stack: " + tempStack); // [ 98, 92, 34, 31, 23, 3 ]
    }

    /*
        Time complexity getMin() =>  O(1) : Constant time
        Auxiliary Space complexity : O(1) : Extra space
     */

    private static void testDesignAStackWithGetMin() {

        StackWithMinimum stackWithMinimum = new StackWithMinimum();
        stackWithMinimum.push(3);
        stackWithMinimum.push(5);
        System.out.println("Stack content: " + stackWithMinimum); // Head => [ 5, 3 ]
        System.out.println("Mim Value: " + stackWithMinimum.getMinVal()); // 3

        stackWithMinimum.push(2);
        stackWithMinimum.push(1);
        System.out.println("Stack content: " + stackWithMinimum);   //  Head => [ 0, 1, 5, 3 ]
        System.out.println("Mim Value: " + stackWithMinimum.getMinVal());  // 1

        stackWithMinimum.pop();
        System.out.println("Stack content: " + stackWithMinimum);   // Head => [ 1, 5, 3 ]
        System.out.println("Mim Value: " + stackWithMinimum.getMinVal());  // 2

        stackWithMinimum.pop();
        System.out.println("Stack content: " + stackWithMinimum);   //  Head => [ 5, 3 ]
        System.out.println("Min Value: " + stackWithMinimum.getMinVal());  // 2

        System.out.println("Stack peek: " + stackWithMinimum.peek());

    }

    /*
        https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/

        Time complexity : O(n)
        Space complexity : O(n)
     */
    private static void testNearestSmallerElement() {
        int[] input = {1, 6, 4, 10, 2, 5};
        int[] output = new int[input.length];

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();

        // Iterate the array from right to left
        for (int i = 0; i < input.length; i++) {

            if (stack.isEmpty()) {
                stack.push(input[i]);
                output[i] = -1;
            } else {
                while (!stack.isEmpty() && stack.peek() >= input[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    output[i] = stack.peek();
                } else {
                    output[i] = -1;
                }
                stack.push(input[i]);
            }

        }

        System.out.println("Nearest smaller element on the left for : " + Arrays.toString(input));
        System.out.println("                                          " + Arrays.toString(output));

    }

    /*
        https://www.geeksforgeeks.org/design-custom-browser-history-based-on-given-operations/

        Using 2 stacks

        Time complexity : O(n)
        Space complexity : O(n)


     */
    private static void testDesignBrowserHistoryV1() {
        String homePage = "gfg.org";
        BrowserHistory browserHistory = new BrowserHistory(homePage);

        String url = "google.com";
        browserHistory.visit(url);

        url = "facebook.com";
        browserHistory.visit(url);

        url = "youtube.com";
        browserHistory.visit(url);

        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.back(1));
        System.out.println(browserHistory.forward(1));
        browserHistory.visit("linkedin.com");
        System.out.println(browserHistory.forward(2));
        System.out.println(browserHistory.back(2));
        System.out.println(browserHistory.back(7));
          /*
            facebook.com
            google.com
            facebook.com
            linkedin.com
            google.com
            gfg.org
         */
    }

    /*
        Using Doubly Linked List
         https://www.geeksforgeeks.org/design-custom-browser-history-based-on-given-operations/

         Time Complexity : O(n)
         Space Complexity : O(n)

     */
    private static void testDesignBrowserHistoryV2() {
        String homepage = "gfg.org";
        BrowserHistoryDoubleLL browserHistoryDoubleLL = new BrowserHistoryDoubleLL(homepage);
        String url = "google.com";
        browserHistoryDoubleLL.visit(url);

        url = "facebook.com";
        browserHistoryDoubleLL.visit(url);

        url = "youtube.com";
        browserHistoryDoubleLL.visit(url);

        System.out.println(browserHistoryDoubleLL.back(1));
        System.out.println(browserHistoryDoubleLL.back(1));
        System.out.println(browserHistoryDoubleLL.forward(1));

        browserHistoryDoubleLL.visit("linkedin.com");

        System.out.println(browserHistoryDoubleLL.forward(2));
        System.out.println(browserHistoryDoubleLL.back(2));
        System.out.println(browserHistoryDoubleLL.back(7));

        /*
            facebook.com
            google.com
            facebook.com
            linkedin.com
            google.com
            gfg.org
        */
    }

    /*
        Using recursion
     */

    private static void deleteMiddleElement(org.self.yahoo.book.demo.chap2.stack.Stack stack, int size, int currentSize) {
        if (stack.isEmpty() || (currentSize == ((size / 2) + 1))) {
            stack.pop();
            return;
        }
        int item = stack.pop();
        deleteMiddleElement(stack, size, stack.getSize());
        stack.push(item);
    }

    /*
        Using Recursion

        Time Complexity : O(n)
        Space Complexity : O(n)
     */
    private static void testDeleteMiddleElementOfAStack() {
        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        System.out.println("Input Stack: " + stack);  // [ 7, 6, 5, 4, 3, 2, 1 ]
        deleteMiddleElement(stack, stack.getSize(), stack.getSize());
        System.out.println("Result Stack: " + stack); // [ 7, 6, 5, 3, 2, 1 ]
    }

    /*
           Time Complexity   : O(n)
           Space Complexity  : O(n) : Extra space coz of Stack object
     */
    private static void testReverseArrayUsingStack() {
        int[] input = {1, 2, 3, 4, 5};

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();

        System.out.println("Input Array: " + Arrays.toString(input));

        for (int j : input) {
            stack.push(j);
        }
        System.out.println("Input Stack: " + stack);  // Head => [ 5, 4, 3, 2, 1 ] <= Tail

        for (int i = 0; i < input.length; i++) {
            input[i] = stack.pop();
        }
        System.out.println("Reversed Array: " + Arrays.toString(input));
    }

    private static void insertAtBottom(org.self.yahoo.book.demo.chap2.stack.Stack stack, int item) {
        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }
        int currentItem = stack.pop();
        insertAtBottom(stack, item);
        /*
            Elements are received in the order : 1, 2, 3 where 3 is the default Head => [3, 2, 1] but
            insertAtBottom calls pops out
                1. the existing elements
                2. Insert the current elements
                3. Inserts the elements back in reverse order

            Trail run: 1
                1. Stack is empty and 1 is added to the bottom of stack Head => [2]

            Trial run: 2
               1. Existing number 1 is popped out
               2. Current item 2 is inserted
               3. Previous number 1 is added on top   Head => [1, 2]

            Trial run: 3
               1. Existing numbers are popped out in sequence : Head => [1, 2] with the last element popped out as 2
               2. Current item 3 is inserted followed by 2 and then 1 Head = [1, 2, 3]
         */
        stack.push(currentItem);
    }

    private static void recursiveReverse(org.self.yahoo.book.demo.chap2.stack.Stack stack) {

        if (stack.isEmpty()) {
            return;
        }
        // Initial stack order : Head => [ 5, 4, 3, 2, 1 ] <= Tail
        int currItem = stack.pop(); // Elements are popped in the order 5, 4, 3, 2, 1
        System.out.println("Item popped in order: " + currItem);
        recursiveReverse(stack);  // Recursive call to recursiveReverse() will pop elements in sequence : 5, 4, 3, 2, 1

        /*
            Call to insertAtBottom() places each element at the bottom of stack.
            Stack is popped empty from the above recursive calls to  recursiveReverse();
            insertAtBottom() is called in the sequence : 1, 2, 3, 4, 5
         */
        insertAtBottom(stack, currItem);

    }

    /*
           Time complexity : O(n^2)
           Space complexity : O(n)
     */
    private static void testReverseAStackUsingRecursion() {

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("Input Stack: " + stack);
        recursiveReverse(stack);
        System.out.println("Reverse Stack: " + stack);
    }

    private static void testImplementTwoStacksInAnArray() {
        TwoStacksUsingSingleArray twoStacksUsingSingleArray = new TwoStacksUsingSingleArray(10);
        twoStacksUsingSingleArray.push1(5);
        twoStacksUsingSingleArray.push2(10);
        twoStacksUsingSingleArray.push2(15);
        twoStacksUsingSingleArray.push1(11);
        twoStacksUsingSingleArray.push2(7);

        System.out.println("First popped element Stack1: " + twoStacksUsingSingleArray.pop1());

        twoStacksUsingSingleArray.push2(40);

        System.out.println("First popped element Stack2: " + twoStacksUsingSingleArray.pop2());
        System.out.println("Second popped element Stack2: " + twoStacksUsingSingleArray.pop2());

    }

    private static void sortUsingRecursion(org.self.yahoo.book.demo.chap2.stack.Stack stack, org.self.yahoo.book.demo.chap2.stack.Stack tmpStack) {
        if (stack.isEmpty()) {
            return;
        }

        int item = stack.pop();

        if (tmpStack.isEmpty()) {
            tmpStack.push(item);
        } else if (tmpStack.peek() < item) {
            while (!tmpStack.isEmpty() && tmpStack.peek() < item) {
                int tmpItem = tmpStack.pop();
                stack.push(tmpItem);
            }
            tmpStack.push(item);
        } else {
            tmpStack.push(item);
        }
        sortUsingRecursion(stack, tmpStack);
    }

    /*
        Time Complexity : O(n^2) : Coz the tmp stack iterates n times for each element of the main stack (Worst case)
        Auxiliary Space complexity O(n) : Use of an extra tmp stack to store sorted numbers
     */
    private static void testSortAStackUsingRecursion() {

        org.self.yahoo.book.demo.chap2.stack.Stack stack = new org.self.yahoo.book.demo.chap2.stack.Stack();
        stack.push(12);
        stack.push(21);
        stack.push(1);
        stack.push(124);
        stack.push(9);
        System.out.println("Input stack: " + stack); // [ 9, 124, 1, 21, 12 ]
        org.self.yahoo.book.demo.chap2.stack.Stack tmpStack = new org.self.yahoo.book.demo.chap2.stack.Stack();
        sortUsingRecursion(stack, tmpStack);

        System.out.println("Sorted stack: " + tmpStack); // [ 1, 9, 12, 21, 124 ]
    }

    public static void main(String[] args) {


        /******* Complexity: Easy ******/

        // Parenthesis Checker
//        var isBalanced = testParenthesisCheckerV1();
//        System.out.println("isBalanced: " + isBalanced);


        // Reverse a String using Stack   Complexity: Easy
        //testReverseAStringUsingStackV1();
        //testReverseAStringUsingStackV2();

        // Delete middle element of a stack
        // testDeleteMiddleElementOfAStack();

        // Reverse an array using Stack
        //testReverseArrayUsingStack();

        /******  Complexity: Medium *******/

        // Implement Stack using Queues
        //testImplementStackUsingQueuesV1();
        //testImplementStackUsingQueuesV2();

        // Next Greater Element (NGE) for every element in given Array
        //testNextGreaterElementInGivenArray();

        // Sort a stack using a temporary stack
        //testSortAStackUsingTemporaryStack();

        // Nearest Smaller Element
        //testNearestSmallerElement();

        // Reverse a stack using recursion
       // testReverseAStackUsingRecursion();


        /****** Complexity : Hard ******/

        // Check if the given permutation is valid stack permutation or not
//        int [] input = {1, 2, 3};
//        int [] output = {2, 1, 3};
//        var isStackPermutation = testGivenStackPermutation(input, output);
//        System.out.println("isStackPermutation: " + isStackPermutation);

//        input = new int[] {1, 2, 3};
//        output = new int[] {3, 1, 2};
//        isStackPermutation = testGivenStackPermutation(input, output);
//        System.out.println("isStackPermutation: " + isStackPermutation);

        // Design a stack that supports getMin() in O(1) time and O(1) extra space
        //testDesignAStackWithGetMin();

        // Design Browser History
        // testDesignBrowserHistoryV1();
        // testDesignBrowserHistoryV2();

        // Implement two Stacks in an Array
        //testImplementTwoStacksInAnArray();
        //How to efficiently implement k stacks in a single array TODO://

        // How to Sort a Stack using Recursion
        testSortAStackUsingRecursion();
    }
}