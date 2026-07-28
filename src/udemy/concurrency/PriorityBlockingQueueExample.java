package udemy.concurrency;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();

        priorityBlockingQueue.add(20);
        priorityBlockingQueue.add(200);
        priorityBlockingQueue.add(10);
        priorityBlockingQueue.add(120);
        priorityBlockingQueue.add(2);

        try {
            while (!priorityBlockingQueue.isEmpty()) {
                int val = priorityBlockingQueue.take();
                System.out.println(val);
            }
        }
        catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
