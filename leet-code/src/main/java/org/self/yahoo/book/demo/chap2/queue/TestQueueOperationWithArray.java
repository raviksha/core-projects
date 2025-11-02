package org.self.yahoo.book.demo.chap2.queue;

public class TestQueueOperationWithArray {
    public static void main(String[] args) {
        QueueWithArray queueWithArray = new QueueWithArray(5);
        System.out.println(queueWithArray);

        // Enqueue operation : Adding element on the tail end

        queueWithArray.enQueue(5);
        queueWithArray.enQueue(6);
        queueWithArray.enQueue(7);
        queueWithArray.enQueue(8);
        queueWithArray.enQueue(9);
        queueWithArray.enQueue(10); // Queue overflow....

        System.out.println(queueWithArray); // Head -> [5, 6, 7, 8, 9] <-- Tail

        // Dequeue Operation
        int itemPopped = queueWithArray.deQueue();
        System.out.println("itemPopped: " + itemPopped);

        queueWithArray.enQueue(10); // Queue overflow...
        System.out.println(queueWithArray);

        itemPopped = queueWithArray.deQueue();
        System.out.println("itemPopped: " + itemPopped);
        itemPopped = queueWithArray.deQueue();
        System.out.println("itemPopped: " + itemPopped);
        itemPopped = queueWithArray.deQueue();
        System.out.println("itemPopped: " + itemPopped);
        itemPopped = queueWithArray.deQueue();
        System.out.println("itemPopped: " + itemPopped);

        itemPopped = queueWithArray.deQueue();        // Queue overflow.....
        System.out.println("itemPopped: " + itemPopped);

        System.out.println(queueWithArray);
    }
}
