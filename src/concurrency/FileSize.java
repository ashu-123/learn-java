package concurrency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FileSize {

    private static final ForkJoinPool forkJoinPool = new ForkJoinPool();

    private static class FileSizeFinder extends RecursiveTask<Long> {

        final File file;

        private FileSizeFinder(File file) {
            this.file = file;
        }

        @Override
        protected Long compute() {
            long size = 0;
            if (file.isFile()) return file.length();
            File[] children = file.listFiles();
            if(children!=null) {
                List<RecursiveTask<Long>> tasks = new ArrayList<>();
                for(File child : children) {
                    if(child.isFile()) size+=child.length();
                    else {
                        tasks.add(new FileSizeFinder(child));
                    }
                }
                for(RecursiveTask<Long> task : invokeAll(tasks)) {
                    size+=task.join();
                }
            }
            return size;
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        long total = forkJoinPool.invoke(new FileSizeFinder(new File("C:\\Users\\ashutosh\\IdeaProjects")));
        long end = System.nanoTime();
        System.out.println("Total size:" + total);
        System.out.println("Total time taken to compute file size concurrently:" + (end-start)/1.0e9);
    }
}
