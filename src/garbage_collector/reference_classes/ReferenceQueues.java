package garbage_collector.reference_classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReferenceQueues {

    public static void main(String[] args) throws IOException {
        People p = new People();
        final ReferenceQueue<People> referenceQueue = new ReferenceQueue<People>();
        PeopleCleaner cleaner = new PeopleCleaner();
        PeopleWeakReference weakReference = new PeopleWeakReference(p, cleaner, referenceQueue );

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    PeopleWeakReference wr = (PeopleWeakReference) referenceQueue.remove();
                    wr.clean();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        p = null;
        System.gc();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Press any key to continue");
        br.readLine();
        executorService.shutdown();

    }
}

final class People {

}

class PeopleCleaner {

    public void clean() {
        System.out.println("Cleaned");
    }
}

class PeopleWeakReference extends WeakReference<People> {

    PeopleCleaner cleaner;
    public PeopleWeakReference(People referent, PeopleCleaner cleaner, ReferenceQueue<? super People> q) {
        super(referent, q);
        this.cleaner = cleaner;
    }

    public void clean(){
        cleaner.clean();
    }
}