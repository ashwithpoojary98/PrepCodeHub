package main.java.io.github.ashwithpoojary98.basic;


import java.util.PriorityQueue;
public class PriorityQueueTest {
    
    public static void main(String[] args){
        PriorityQueue<String> queue=new PriorityQueue();
        queue.add("1");
         queue.add("3");
        queue.add("2");
    
        queue.add("4");
        System.out.println(queue);

    }
}
