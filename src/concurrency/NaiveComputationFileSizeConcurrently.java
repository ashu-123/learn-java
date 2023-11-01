package concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class NaiveComputationFileSizeConcurrently {

    private long getTotalFileSizeInDir(ExecutorService executorService, File file) throws ExecutionException, InterruptedException, TimeoutException {
        long total = 0;
        if (file.isFile()) return file.length();
        File[] children = file.listFiles();
        if (children != null) {
            final List<Future<Long>> partialTotalFutures = new ArrayList<>();
            for (File child : children) {
                partialTotalFutures.add(executorService.submit(() -> getTotalFileSizeInDir(executorService, child)));
            }
            for (Future<Long> partialFuture : partialTotalFutures) {
                total += partialFuture.get(100, TimeUnit.SECONDS);
            }
        }
        return total;
    }

    public long computeFileSizeConcurrently(File file) throws ExecutionException, InterruptedException, TimeoutException {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        try {
            return getTotalFileSizeInDir(executorService, file);
        }
        finally {
            executorService.shutdown();
        }
    }
}
