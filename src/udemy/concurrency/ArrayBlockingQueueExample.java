package udemy.concurrency;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerThread implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;
    private int counter = 1;

    ProducerThread(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.put(counter);
                System.out.println("Producer updating the queue with value: " + counter);
                counter++;
//                Thread.sleep(2000);
            }
        }
        catch (InterruptedException e) {

        }
    }
}

class ConsumerThread implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public ConsumerThread(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int val = blockingQueue.take();
                System.out.println("Consumer consuming value: " + val + " from the queue");
                Thread.sleep(3000);
            }
        }
        catch (InterruptedException e) {

        }
    }
}


public class ArrayBlockingQueueExample {

    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        ProducerThread producerThread = new ProducerThread(blockingQueue);
        ConsumerThread consumerThread = new ConsumerThread(blockingQueue);
        new Thread(producerThread).start();
        new Thread(consumerThread).start();
    }
}
