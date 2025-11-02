package org.self.yahoo.book.demo.chap2.queue;

public class TestQueueOperations {

    public static void main(String[] args) {
        Queue queue = new Queue();
        // Enqueue : Adds items to the tail
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println(queue); // [ 1, 2, 3, 4, 5 ]
        // Head -> [ 1, 2, 3, 4, 5 ] <- Tail

        // Dequeue : Removed items from the head
        int itemRemoved = queue.dequeue();
        System.out.println("itemRemoved: " + itemRemoved);
        itemRemoved = queue.dequeue();
        System.out.println("itemRemoved: " + itemRemoved);
        // Head -> [ 3, 4, 5 ] <- tail
        System.out.println(queue);
    }
}
