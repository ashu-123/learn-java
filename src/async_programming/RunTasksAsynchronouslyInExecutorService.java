package async_programming;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class RunTasksAsynchronouslyInExecutorService {

    record Quotation(String server, int num) {
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Random random = new Random();

        Callable<Quotation> fetchQuoteA = () -> {
            Thread.sleep(random.nextInt(80, 120));
            return new Quotation("Server A", random.nextInt(40, 60));
        };

        Callable<Quotation> fetchQuoteB = () -> {
            Thread.sleep(random.nextInt(80, 120));
            return new Quotation("Server B", random.nextInt(30, 70));
        };

        Callable<Quotation> fetchQuoteC = () -> {
            Thread.sleep(random.nextInt(80, 120));
            return new Quotation("Server C", random.nextInt(40, 80));
        };

        var quotes = List.of(fetchQuoteA, fetchQuoteB, fetchQuoteC);

        List<Quotation> quotations = new ArrayList<>();
        Instant begin = null;

        try (ExecutorService executorService = Executors.newFixedThreadPool(4);) {
            begin = Instant.now();

            List<Future<Quotation>> futures = new ArrayList<>();
            for (var quotation : quotes) {
                Future<Quotation> future = executorService.submit(quotation);
                futures.add(future);
            }


            for (Future<Quotation> future : futures) {
                Quotation quotation = future.get();
                quotations.add(quotation);
            }

        } catch (InterruptedException | CancellationException | ExecutionException e) {
            e.printStackTrace();
        }


        Quotation bestQuote = quotations.stream()
                .min(Comparator.comparing(Quotation::num))
                .orElseThrow();

        Instant end = Instant.now();
        Duration duration = Duration.between(begin, end);
        System.out.println("Best quotation [ASYNC USING EXECUTOR SERVICE] = " + bestQuote + " (" + duration.toMillis() + "ms)");

//      Best quotation [ASYNC USING EXECUTOR SERVICE] = Quotation[server=Server A, num=46] (114ms)
    }
}
