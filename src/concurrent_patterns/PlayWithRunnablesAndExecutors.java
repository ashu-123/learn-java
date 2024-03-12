package concurrent_patterns;

import java.util.concurrent.*;

public class PlayWithRunnablesAndExecutors {

    public static void main(String[] args) throws Exception {
        Runnable runnableTask = () -> System.out.println("I am executing in thread " + Thread.currentThread().getName());

        Callable<String> callableTask = () -> {
            throw new IllegalStateException();
//            return "I am executing in thread " + Thread.currentThread().getName();
        };

        for(int i=0;i<10;i++) {
            new Thread(runnableTask).start();
        }

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        for (int i=0;i<10;i++)  executorService.execute(runnableTask);


        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            for (int i=0;i<10;i++)  executorService.execute(runnableTask);
        }
        catch (Exception exception) { }

        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i=0;i<10;i++) {
                Future<String> future = executorService.submit(callableTask);
                System.out.println("I get:- " + future.get(100, TimeUnit.MILLISECONDS));
            }
        }
        catch (Exception exception) {
            System.out.println("got exception:- " + exception);
        }
    }
}
