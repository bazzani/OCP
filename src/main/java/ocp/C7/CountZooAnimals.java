package ocp.C7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Created by barry on 29/04/2017.
 */
public class CountZooAnimals {
    public static Integer performCount(int exhibitNumber) throws InterruptedException {
        Thread.sleep(new Random().nextInt(250));
        throw new RuntimeException();
    }

    public static void printResults(Future<?> f) {
        try {
            System.out.println(f.get());
        } catch (Exception e) {
            System.out.println("Exception!");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadScheduledExecutor();
        final List<Future<?>> results = new ArrayList<>();
        IntStream.range(0, 10)
                .forEach(i ->
                        results.add(
                                service.submit(() -> performCount(i))
                        )
                );
        service.execute(() -> {
            int i = 1;
            System.out.println("i = " + i);
        });
        service.submit(() -> 2);
        results.stream().forEach(f -> printResults(f));
        service.shutdown();
    }
}
