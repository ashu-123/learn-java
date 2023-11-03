package concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComputeFileSizeConcurrently {

    private FileSizeAndSubdirectories getTotalFileAndSubdirectories(ExecutorService service, File file) {
        long total = 0;
        final List<File> subDirectories = new ArrayList<>();
        if(file.isDirectory()) {
            final File[] children = file.listFiles();
            for(File child : children) {
                if(child.isFile()) total+=child.length();
                else subDirectories.add(child);
            }
        }
        return new FileSizeAndSubdirectories(total, subDirectories);
    }

    public long getTotalSize(File file) throws ExecutionException, InterruptedException, TimeoutException {
        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<File> directories = new ArrayList<>();
        long total = 0;
        directories.add(file);
        try {
            while (!directories.isEmpty()) {
                final List<Future<FileSizeAndSubdirectories>> partialResults = new ArrayList<>();
                for (File directory : directories) {
                    partialResults.add(executorService.submit(() -> getTotalFileAndSubdirectories(executorService, directory)));
                }
                directories.clear();
                for (Future<FileSizeAndSubdirectories> partialResult : partialResults) {
                    var fileSizeAndSubdirectories = partialResult.get(100, TimeUnit.SECONDS);
                    total += fileSizeAndSubdirectories.size;
                    directories.addAll(fileSizeAndSubdirectories.subdirectories);
                }
            }
            return total;
        }
        finally {
            executorService.shutdown();
        }
    }
}
