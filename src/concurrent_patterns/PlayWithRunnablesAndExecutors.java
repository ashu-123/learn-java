package concurrent_patterns;

public class PlayWithRunnablesAndExecutors {

    public static void main(String[] args) {
        Runnable runnableTask = () -> System.out.println("I am executing in thread " + Thread.currentThread().getName());

        for(int i=0;i<10;i++) {
            new Thread(runnableTask).start();
        }
    }
}
