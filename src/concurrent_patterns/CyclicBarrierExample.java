package concurrent_patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        class Friend implements Callable<String> {

            private CyclicBarrier cyclicBarrier;

            public Friend(CyclicBarrier barrier) {
                this.cyclicBarrier = barrier;
            }

            @Override
            public String call() throws Exception {

                Random random = new Random();
                Thread.sleep((random.nextInt(20)*100 + 100));
                System.out.println("I just arrived, waiting for the others...");

                cyclicBarrier.await();

                System.out.println("Lets go to the cinema!!");
                return "okay!";
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> System.out.println("Barrier opening"));
        List< Future<String>> futures = new ArrayList<>();

        try {
            for (int i=0;i<4;i++) {
                Friend friend = new Friend(cyclicBarrier);
                futures.add(executorService.submit(friend));
            }

            futures.forEach(
                    future -> {
                        try {
                            future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
            );
        }
        finally {
            executorService.shutdown();
        }
    }
}
