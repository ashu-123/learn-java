package async_programming;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class RunTasksAsynchronouslyUsingCompletableFuture {

    record Quotation(String server, int num) {
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Random random = new Random();

        Supplier<Quotation> fetchQuoteA = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Quotation("Server A", random.nextInt(40, 60));
        };

        Supplier<Quotation> fetchQuoteB = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Quotation("Server B", random.nextInt(30, 70));
        };

        Supplier<Quotation> fetchQuoteC = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Quotation("Server C", random.nextInt(40, 80));
        };

        var quotes = List.of(fetchQuoteA, fetchQuoteB, fetchQuoteC);

        List<Quotation> quotations = new ArrayList<>();
        Instant begin =  Instant.now();

            List<CompletableFuture<Quotation>> futures = new ArrayList<>();
            for (var quotation : quotes) {
                CompletableFuture<Quotation> future = CompletableFuture.supplyAsync(quotation);
                futures.add(future);
            }


            for (CompletableFuture<Quotation> future : futures) {
                Quotation quotation = future.join();
                quotations.add(quotation);
            }

        Quotation bestQuote = quotations.stream()
                .min(Comparator.comparing(Quotation::num))
                .orElseThrow();

        Instant end = Instant.now();
        Duration duration = Duration.between(begin, end);
        System.out.println("Best quotation [ASYNC USING EXECUTOR SERVICE] = " + bestQuote + " (" + duration.toMillis() + "ms)");

//      Best quotation [ASYNC USING EXECUTOR SERVICE] = Quotation[server=Server A, num=50] (119ms)
    }

}
