import concurrency.ComputeFileSizeConcurrently;
import concurrency.ComputeFileSizeSequential;
import concurrency.NaiveComputationFileSizeConcurrently;
import sealed_classes.FirstSealed;
import sealed_classes.SealedClass2;
import sealed_classes.SealedClass1;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Base {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        long start = System.nanoTime();
//        long total = new ComputeFileSizeSequential().getTotalFileSizeInDir(new File("C:\\Users\\ashutosh\\IdeaProjects"));
//        long end = System.nanoTime();
//        System.out.println("Total size:" + total);
//        System.out.println("Total time taken to compute file size sequentially:" + (end-start)/1.0e9);

//        long start = System.nanoTime();
//        long total = new NaiveComputationFileSizeConcurrently().computeFileSizeConcurrently(new File("C:\\Users\\ashutosh\\IdeaProjects"));
//        long end = System.nanoTime();
//        System.out.println("Total size:" + total);
//        System.out.println("Total time taken to compute file size concurrently:" + (end-start)/1.0e9);

        long start = System.nanoTime();
        long total = new ComputeFileSizeConcurrently().getTotalSize(new File("C:\\Users\\ashutosh\\IdeaProjects"));
        long end = System.nanoTime();
        System.out.println("Total size:" + total);
        System.out.println("Total time taken to compute file size concurrently:" + (end-start)/1.0e9);
    }

    private static void greet(FirstSealed message) {
        switch (message) {
            case SealedClass1 sealedClass1 -> System.out.println("Sealed class 1");
            case SealedClass2 sealedClass2 -> System.out.println("Sealed class 2");
            case default -> System.out.println("default");
        }
    }
}
