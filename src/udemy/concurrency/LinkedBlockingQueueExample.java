package udemy.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ProduceThread implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;
    private int counter = 1;

    ProduceThread(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.put(counter);
                System.out.println("Producer updating the queue with value: " + counter);
                counter++;
                Thread.sleep(2000);
            }
        }
        catch (InterruptedException e) {

        }
    }
}

class ConsumeThread implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public ConsumeThread(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int val = blockingQueue.take();
                System.out.println("Consumer consuming value: " + val + " from the queue");
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {

        }
    }
}
public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        ProduceThread produceThread = new ProduceThread(blockingQueue);
        ConsumeThread consumeThread = new ConsumeThread(blockingQueue);

        new Thread(produceThread).start();
        new Thread(consumeThread).start();
    }

}
