package org.self.yahoo.book.demo.chap2.queue;

import java.util.Arrays;

public class QueueWithArray {
    int headPtr = 0 ;
    int tailPtr = 0 ;
    int [] queueArray;
    boolean isFull = false;

    /*
        Queue using static arrays is tricky coz :
            => Items are added to the tail coz of FIFO
            => Items are removed from the head
     */
    public QueueWithArray(int capacity) {
        queueArray = new int[capacity];
        initializeArrayWithDefault();
    }

    private void initializeArrayWithDefault() {
        Arrays.fill(queueArray, -1);
    }

    public void enQueue(int i) {
        if (!isFull) {
            queueArray[tailPtr] = i;
            tailPtr = (tailPtr + 1) % queueArray.length; // Makes it into a circular buffer
            isFull = (tailPtr == headPtr);
        } else {
            System.out.println("Queue overflow....");
        }
    }

    public int deQueue() {
        if (headPtr < queueArray.length) {
            int delItem = queueArray[headPtr];
            queueArray[headPtr] = -1;
            headPtr = (headPtr + 1) % queueArray.length;  // Makes it into a circular buffer
            isFull = false;
            return delItem;
        } else {
            System.out.println("Queue underflow.....");
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.toString(queueArray);
    }
}
