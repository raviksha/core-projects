package org.self.yahoo.book.demo.chap2.queue.worksheet;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Deque {

    int[] arr;

    int head;
    int capacity;
    int size;

    public Deque(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
        head = 0;
        init();
    }

    private void init() {
        Arrays.fill(arr, -1);
    }


    public void insertFront(int item) {
        // Empty queue

        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }
        head = (head - 1 + capacity) % capacity;
        arr[head] = item;
        size++;
        System.out.println(Arrays.toString(arr));
    }

    public void insertRear(int item) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }
        var rear = (head + size) % capacity;
        arr[rear] = item;
        size++;
        System.out.println(Arrays.toString(arr));
    }

    public int deleteFront() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int res = arr[head];
        head = (head + 1) % capacity;  // Move front forward circularly
        size--;
        System.out.println(Arrays.toString(arr));
        return res;
    }

    public int deleteLast() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }

        int rear = (head + size - 1) % capacity;  // Compute rear index
        int resp = arr[rear];
        size--;
        System.out.println(Arrays.toString(arr));
        return resp;
    }

    public int frontElement() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[head];
    }

    public int rearElement() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        int rear = (head + size - 1) % capacity;  // Compute rear index
        return arr[rear];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size ==0;
    }

    public int getSize() {
        return size;
    }

    // Display the deque
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty!");
            return;
        }
        System.out.print("Deque: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(head + i) % capacity] + " ");
        }
        System.out.println();
    }


}



public class DeQueueUsingCircularArray {

    public static void main(String[] args) {
        // Create deque with capacity 4
        Deque deque = new Deque(5);
        // front = (front - 1 + capacity) % capacity;
        deque.insertFront(50); // [-1, -1, -1, -1, 50]

        // int rear = (front + size) % capacity;  // Compute rear index
        deque.insertRear(10);  // [10, -1, -1, -1, 50]

        //  int rear = (front + size) % capacity;  // Compute rear index
        deque.insertRear(20); // [10, 20, -1, -1, 50]


        // front = (front - 1 + capacity) % capacity;
        deque.insertFront(5); // [10, 20, -1, 5, 50]


        // front = (front - 1 + capacity) % capacity;
        deque.insertFront(3);//  [10, 20, 3, 5, 50]
        //deque.insertRear(25);

        System.out.println("Value of head counter: " + deque.head); // Value of head counter: 2
        deque.display(); // Deque: 3 5 50 10 20


        Queue queue = new PriorityQueue();

    }


    /*
        Insert Front:
         front = (front - 1 + capacity) % capacity;  // Move front backward circularly

         Eg :
         capacity = 5;
         front = 0; // Current front index (beginning of array)

         front = (0 - 1 + 5) % 5;
               = (-1 + 5) % 5;
               = 4 % 5;
               = 4;
     */

    /*
       Insert Rear
       int rear = (front + size) % capacity;  // Compute rear index
     */


    // Delete Front
    // front = (front + 1) % capacity;  // Move front forward circularly

    // Delete Rear
    //  int rear = (front + size - 1) % capacity;  // Compute rear index
}
