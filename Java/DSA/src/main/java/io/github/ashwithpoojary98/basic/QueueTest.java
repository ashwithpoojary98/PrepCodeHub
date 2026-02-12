package main.java.io.github.ashwithpoojary98.basic;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<String> linkedListQueue = new LinkedList<>();

        // insert the element
        linkedListQueue.offer("a");
        linkedListQueue.offer("b");
        linkedListQueue.offer("c");

        System.out.println(linkedListQueue);

        // Remove the top element and return
        System.out.println(linkedListQueue.poll());
        System.out.println(linkedListQueue);

        // Check the Top element
        System.out.println(linkedListQueue.peek());
        System.out.println(linkedListQueue);

        System.out.println("----------------------");
        System.out.println("Priority Queue");

        Queue<String> priorityQueue=new PriorityQueue<>();
        priorityQueue.offer("b");
        priorityQueue.offer("a");
        priorityQueue.offer("aaa");

        System.out.println(priorityQueue);

    

    }
}
