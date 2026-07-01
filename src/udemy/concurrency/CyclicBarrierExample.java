package udemy.concurrency;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class CyclicBarrierWorker implements Runnable {

    private final int id;

    private final CyclicBarrier cyclicBarrier;

    CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println("Task executing with id: " + this.id);
        try {
            Thread.sleep(2000);
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task finished with id: " + this.id);
    }
}

public class CyclicBarrierExample {

    public static void main(String[] args) {

        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("All tasks finished successfully"));
            IntStream.range(0, 5)
                    .forEach(id -> executorService.execute(new CyclicBarrierWorker(id+1, cyclicBarrier)));
        }

        System.out.println("All tasks finished in main");

    }
}
