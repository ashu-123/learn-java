package async_programming;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class RunTasksSynchronously {

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

        Instant begin = Instant.now();

        var quotes = List.of(fetchQuoteA, fetchQuoteB, fetchQuoteC);
        Quotation bestQuote = quotes.stream()
                .map(RunTasksSynchronously::fetchQuotation)
                .min(Comparator.comparing(Quotation::num))
                .orElseThrow();

        Instant end = Instant.now();
        Duration duration = Duration.between(begin, end);
        System.out.println("Best quotation [SYNC] = " + bestQuote + " (" + duration.toMillis() + "ms)");

//      OUTPUT:-  Best quotation [SYNC] = Quotation[server=Server B, num=36] (302ms)
    }

    private static Quotation fetchQuotation(Callable<Quotation> task) {
        try {
            return task.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
