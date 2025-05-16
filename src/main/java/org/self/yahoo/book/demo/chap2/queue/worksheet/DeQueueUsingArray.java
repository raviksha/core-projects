package org.self.yahoo.book.demo.chap2.queue.worksheet;


class ArrayQueue {

    private int size = -1;
    private int capacity = -1;
    private int head = -1;
    private int tail = -1;
    private int [] arr ;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        size = 0;
    }

    public void insertFront(int item) {
        if (head == 0) {
            System.out.println("Queue is full..");
            return;
        }
        if (isEmpty()) {
            head = 0;
            tail = 0;

        } else {
            // Moves pointer 1 place ahead to absorb the new item
            head--;
        }
        arr[head] = item;
        System.out.println("Inserting : " + item + " at front");
        size++;
    }

    public void insertRear(int item) {

        if (tail == capacity -1) {
            System.out.println("Queue is full..");
            return;
        }
        if (isEmpty()) {
            head = 0;
            tail = 0;

        } else {
            tail++;
        }
        arr[tail] = item;
        System.out.println("Inserting : " + item + " at rear");
        size++;
    }


    public int deleteFront() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        // Contains only one element
        int currentItem = arr[head];
        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            head++;
        }
        size--;
        return currentItem;
    }

    public int deleteRear() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int currentItem = arr[tail];

        if (head == tail) {
            head = -1;
            tail = -1;
        } else {
            tail--;
        }
        size--;
        return currentItem;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");

        for (int i = head; i <= tail; i++) {
            stringBuilder.append(arr[i]).append(" ");
        }

        stringBuilder.append(" ]");
        System.out.println(stringBuilder);
    }


}


public class DeQueueUsingArray {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.insertFront(10);
        arrayQueue.insertRear(20);
        arrayQueue.display();
        System.out.println("Size : " + arrayQueue.getSize());

        arrayQueue.deleteFront();
        arrayQueue.insertFront(40);
        arrayQueue.display();
        System.out.println("Size : " + arrayQueue.getSize());

        arrayQueue.insertRear(50);
        arrayQueue.insertRear(60);
        arrayQueue.insertRear(70);
        arrayQueue.display();
        System.out.println("Size : " + arrayQueue.getSize());

        arrayQueue.deleteFront();
        arrayQueue.insertFront(50);
        arrayQueue.display();
        System.out.println("Size : " + arrayQueue.getSize());
    }
}
