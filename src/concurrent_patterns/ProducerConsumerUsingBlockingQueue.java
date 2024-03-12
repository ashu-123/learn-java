package concurrent_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ProducerConsumerUsingBlockingQueue {

    public static void main(String[] args) {

        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(50);

        class Consumer implements Callable<String> {

            @Override
            public String call() throws InterruptedException, TimeoutException {
                int count = 0;
                while (count++ < 50) {
                    buffer.take();
                }
                return "Consumed " + (count - 1);

            }
        }

        class Producer implements Callable<String> {

            @Override
            public String call() throws InterruptedException {
                int count = 0;
                while (count++ < 50) {
                    buffer.put(count);

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
