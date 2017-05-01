package ocp.C7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;

/**
 * Created by barry on 29/04/2017.
 */
public class PrintConstants {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newScheduledThreadPool(10);

        DoubleStream.of(3.14159, 2.71828)
                .forEach(c -> executorService.submit(
                        () -> System.out.println(10 * c)));

        executorService.execute(() -> System.out.println("Printed"));
    }
}
