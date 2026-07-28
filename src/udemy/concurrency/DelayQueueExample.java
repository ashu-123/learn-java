package udemy.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {

    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> delayedWorkers = new DelayQueue<>();
        try {
            delayedWorkers.put(new DelayedWorker("FIRST MESSAGE", 2_000L));
            delayedWorkers.put(new DelayedWorker("SECOND MESSAGE", 10_000L));
            delayedWorkers.put(new DelayedWorker("THIRD MESSAGE", 4_000L));

            while(!delayedWorkers.isEmpty()) {
                DelayedWorker expiredWorker = delayedWorkers.poll();
                System.out.println(expiredWorker.getMessage());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}

class DelayedWorker implements Delayed {

    private final String message;
    private final Long duration;

    public DelayedWorker(String message, Long duration) {
        this.message = message;
        this.duration = System.currentTimeMillis() + duration;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedWorker worker = (DelayedWorker)o;
        return (int)(this.duration - worker.duration);
    }

    public String getMessage() {
        return message;
    }

    public Long getDuration() {
        return duration;
    }
}
