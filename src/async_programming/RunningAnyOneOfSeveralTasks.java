package async_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RunningAnyOneOfSeveralTasks {

    record Weather(String server, String weather) { }

    public static void main(String[] args) {
        run();
    }

    public static  void run() {
        var weatherTasks = getAllWeatherTasks();

        List<CompletableFuture<Weather>> completableFutures = new ArrayList<>();
        for (Supplier<Weather> weatherTask : weatherTasks) {
            CompletableFuture<Weather> weatherCompletableFuture = CompletableFuture.supplyAsync(weatherTask);
            completableFutures.add(weatherCompletableFuture);
        }

        CompletableFuture<Object> future = CompletableFuture.anyOf(completableFutures.toArray(CompletableFuture[]::new));

        future.thenAccept(System.out::println).join();
    }

    public static List<Supplier<Weather>> getAllWeatherTasks() {

        Random random = new Random();
        Supplier<Weather> fetchQuoteA = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server A", "Sunny");
        };

        Supplier<Weather> fetchQuoteB = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server B", "Cloudy");
        };

        Supplier<Weather> fetchQuoteC = () -> {
            try {
                Thread.sleep(random.nextInt(80, 120));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Weather("Server C", "Cold");
        };

        return List.of(fetchQuoteA, fetchQuoteB, fetchQuoteC);
    }
}
