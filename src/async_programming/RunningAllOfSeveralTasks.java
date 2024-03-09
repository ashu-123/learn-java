package async_programming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RunningAllOfSeveralTasks {

    record Quotation(String server, int amount) {  }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        var quotationTasks = getAllQuotationsTasks();

        List<CompletableFuture<Quotation>> completableFutures = new ArrayList<>();
        for (Supplier<Quotation> quotationTask : quotationTasks) {
            CompletableFuture<Quotation> future = CompletableFuture.supplyAsync(quotationTask);
            completableFutures.add(future);
        }

        CompletableFuture<Void> allOff = CompletableFuture.allOf(completableFutures.toArray(CompletableFuture[]::new));

        var bestQuotation = allOff.thenApply(v -> completableFutures.stream()
                .map(CompletableFuture::join)
                .min(Comparator.comparing(Quotation::amount))
                .orElseThrow())
                .join();

        System.out.println("best quotation = " + bestQuotation);
    }

    public static List<Supplier<Quotation>> getAllQuotationsTasks() {

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

        return List.of(fetchQuoteA, fetchQuoteB, fetchQuoteC);
    }
}
