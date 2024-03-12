package concurrent_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerWithLocks {

    public static void main(String[] args) {

        List<Integer> buffer = new ArrayList<>();

        Lock lock = new ReentrantLock();

        Condition isEmpty = lock.newCondition();
        Condition isFull = lock.newCondition();


        class Consumer implements Callable<String> {

            @Override
            public String call() throws InterruptedException, TimeoutException {
                int count = 0;
                while (count++ < 50) {
                    try {
                        lock.lock();
                        while (buffer.isEmpty()) {
                            if (!isEmpty.await(10, TimeUnit.MILLISECONDS)) {// park the consumer thread when no elements to consume from the buffer {
                                throw new TimeoutException("Consumer timed out");
                            }
                        }
                        buffer.remove(buffer.size() - 1);
                        isFull.signalAll(); //signal the producer when an element is removed from the buffer, to produce elements in it
                    } finally {
                        lock.unlock();
                    }
                }
                return "Consumed " + (count - 1);

            }
        }

        class Producer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                while (count++ < 50) {
                    try {
                        lock.lock();
                        int i = 10/0;
                        while (buffer.size() == 50) {
                            isFull.await(); // park the executing thread when no space to add elements
                        }

                        buffer.add(1);
                        isEmpty.signalAll(); // signal to the consumer to resume consuming elements from the buffer

                    } finally {
                        lock.unlock();

                    }

                }
                return "Produced " + (count - 1);
            }
        }

        List<Producer> producers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Producer producer = new Producer();
            producers.add(producer);
        }

        List<Consumer> consumers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Consumer consumer = new Consumer();
            consumers.add(consumer);
        }

        System.out.println("Producers and Consumers have been launched");

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        producersAndConsumers.addAll(producers);
        producersAndConsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);

            futures.forEach(
                    future -> {
                        try {
                            System.out.println(future.get());
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
            );
        } catch (InterruptedException interruptedException) {

        } finally {
            executorService.shutdown();
        }

    }
}
