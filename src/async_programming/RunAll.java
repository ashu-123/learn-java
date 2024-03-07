package async_programming;

public class RunAll {
    public static void main(String[] args) {
        RunTasksSynchronously.run();
        RunTasksAsynchronouslyInExecutorService.run();
        RunTasksAsynchronouslyUsingCompletableFuture.run();

//        Best quotation [SYNC] = Quotation[server=Server A, num=44] (312ms)
//        Best quotation [ASYNC USING EXECUTOR SERVICE] = Quotation[server=Server B, num=51] (105ms)
//        Best quotation [ASYNC USING EXECUTOR SERVICE] = Quotation[server=Server B, num=41] (106ms)
    }
}
