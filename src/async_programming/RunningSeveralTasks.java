package async_programming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

public class RunningSeveralTasks {

    private record Quotation(String server, int amount) {
    }

    private record Weather(String server, String weather) {
    }

    private record TravelPage(Quotation quotation, Weather weather) {
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {

        ExecutorService quotationExecutor = Executors.newFixedThreadPool(4);
        ExecutorService weatherExecutor = Executors.newFixedThreadPool(4);
        ExecutorService minExecutor = Executors.newFixedThreadPool(1);

        var weatherTasks = getAllWeatherTasks();
        var quotationTasks = getAllQuotationsTasks();

        List<CompletableFuture<Weather>> weatherCFs = new ArrayList<>();
        for (Supplier<Weather> weatherTask : weatherTasks) {
            CompletableFuture<Weather> weatherCompletableFuture = CompletableFuture.supplyAsync(weatherTask, weatherExecutor);
            weatherCFs.add(weatherCompletableFuture);
        }

        CompletableFuture<Weather> anyWeather = CompletableFuture.anyOf(weatherCFs.toArray(CompletableFuture[]::new))
                .thenApply(o -> (Weather) o);

        List<CompletableFuture<Quotation>> quotationCFs = new ArrayList<>();
        for (Supplier<Quotation> quotationTask : quotationTasks) {
            CompletableFuture<Quotation> quotationCompletableFuture = CompletableFuture.supplyAsync(quotationTask, quotationExecutor);
            quotationCFs.add(quotationCompletableFuture);
        }

        CompletableFuture<Void> allOfQuotation = CompletableFuture.allOf(quotationCFs.toArray(CompletableFuture[]::new));

        CompletableFuture<Quotation> bestQuotationCf = allOfQuotation.thenApplyAsync(
                v -> {
                    System.out.println("AllOf then apply " + Thread.currentThread());
                    return quotationCFs.stream()
                            .map(CompletableFuture::join)
                            .min(Comparator.comparing(Quotation::amount))
                            .orElseThrow();
                },
                minExecutor
        );
//        DO NOT DO THIS
//        TravelPage travelPage = new TravelPage(bestQuotationCf.join(), anyWeather.join());
//        System.out.println(travelPage);

        CompletableFuture<TravelPage> travelPageCompletableFuture = bestQuotationCf.thenCombine(anyWeather, TravelPage::new);
        travelPageCompletableFuture.thenAccept(System.out::println).join();

        CompletableFuture<TravelPage> travelPageCompletableFuture1 = bestQuotationCf.thenCompose(quotation -> anyWeather.thenApply(weather -> new TravelPage(quotation, weather)));
        travelPageCompletableFuture1.thenAccept(System.out::println).join();
    }

    public static List<Supplier<Weather>> getAllWeatherTasks() {

        Random random = new Random();
        Supplier<Weather> fetchWeatherA = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server A", "Sunny");
        };

        Supplier<Weather> fetchWeatherB = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server B", "Cloudy");
        };

        Supplier<Weather> fetchWeatherC = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server C", "Cold");
        };

        return List.of(fetchWeatherA, fetchWeatherB, fetchWeatherC);
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
