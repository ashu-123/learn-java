package concurrent_patterns;

import com.sun.source.tree.WhileLoopTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheWithReadWriteLock {

    private Map<Long, String> cache = new HashMap<>();

    public String get(Long key) {
        return cache.get(key);
    }

    public String put(Long key, String value) {
        return cache.put(key, value);
    }

    public static void main(String[] args) {
        CacheWithReadWriteLock cacheWithReadWriteLock = new CacheWithReadWriteLock();

        class Producer implements Callable<String> {

            Random random = new Random();

            @Override
            public String call() throws Exception {

                while (true) {
                    long key = random.nextInt(1_000);
                    cacheWithReadWriteLock.put(key, Long.toString(key));
                    if(cacheWithReadWriteLock.get(key)==null) {
                        System.out.println("Key " + key + "has not been put in the map");
                    }
                }

            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            for (int i=0;i<4;i++) {
                executorService.submit(new Producer());
            }
        }
        finally {
            executorService.shutdown();
        }
    }
}
