package concurrent_patterns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayWithRunnablesAndExecutors {

    public static void main(String[] args) {
        Runnable runnableTask = () -> System.out.println("I am executing in thread " + Thread.currentThread().getName());

        for(int i=0;i<10;i++) {
            new Thread(runnableTask).start();
        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++)  executorService.execute(runnableTask);


//        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
//            for (int i=0;i<10;i++)  executorService.execute(runnableTask);
//        }
//        catch (Exception exception) { }
    }
}
