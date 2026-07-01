package udemy.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class Worker implements Runnable {

    private final int id;
    private final CountDownLatch countDownLatch;

    Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }


    @Override
    public void run() {
        System.out.println("Task created with id: " + this.id);
        try {
            Thread.sleep(2000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class CountDownLatchExample {

    public static void main(String[] args) {
        int numOfTasks = 5;
        CountDownLatch countDownLatch = new CountDownLatch(numOfTasks);

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            IntStream.range(0, numOfTasks)
                    .forEach(id -> executorService.execute(new Worker(id, countDownLatch)));
            countDownLatch.await();
            System.out.println("All tasks completed..");
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
