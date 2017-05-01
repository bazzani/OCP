package ocp.C7;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.DoubleStream;

/**
 * Created by barry on 27/04/2017.
 */
public class MainC7 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ExecutorService service =
        ScheduledExecutorService service =
                Executors.newSingleThreadScheduledExecutor();

//        service.scheduleWithFixedDelay(() -> {
//            System.out.println("Open Zoo");
////            return null;
//        }, 0, 1, TimeUnit.SECONDS);

        Future<?> result = service.submit(() ->
                System.out.println("Wake Staff"));

        System.out.println(result.get());
        System.out.println();
        service.shutdown();
//---------------------------

//        System.out.println(Arrays.asList("duck", "chicken", "flamingo", "pelican")
//                .parallelStream().parallel()
//                .reduce(0,
//                        (c1, c2) -> c1.length() + c2.length(),
//                        (s1, s2) -> s1 + s2));
//        System.out.println();
//---------------------------

        Integer i1 = Arrays.asList(1, 2, 3, 4, 5).stream().findAny().get();
        synchronized (i1) {
            Integer i2 = Arrays.asList(6, 7, 8, 9, 10)
                    .parallelStream()
                    .sorted()
                    .findAny().get();

            System.out.println("i1 = " + i1);
            System.out.println("i2 = " + i2);
        }

        System.out.println();
//---------------------------

        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("", (c, s1) -> c + s1,
                        (s2, s3) -> s2 + s3));

        System.out.println();//---------------------------

        System.out.println(Arrays.asList("duck", "chicken", "flamingo", "pelican")
                .parallelStream().parallel()
//                .reduce((s, s2) -> s+s2));
//                .reduce("", (s, s2) -> s+s2));
                .reduce("",
                        (s1, s2) -> s1 + s2 + ":",
                        (c1, c2) -> c1 + c2));

        System.out.println();

//        System.out.println(Arrays.asList("duck", "chicken", "flamingo", "pelican")
//                .parallelStream().parallel()
//                .reduce(0,
//                        (int1, s1) -> int1 + s1.length(),
//                        (i2, i3) -> i2 + i3));


        System.out.println();
//---------------------------

        Callable<?> callable = () -> {
            System.out.println();
            return 10;
        };

        Callable<?> callable1 = () -> "The" + "Zoo";

        Runnable runnable = callable::notify;

//        Executors.newSingleThreadExecutor().submit(runnable);

        System.out.println();
//---------------------------

        ExecutorService executorService = Executors.newScheduledThreadPool(10);

        DoubleStream.of(3.14159, 2.71828)
                .forEach(c -> executorService.submit(
                        () -> System.out.println(10 * c)));

        executorService.execute(() -> System.out.println("Printed"));
        executorService.shutdown();
        System.out.println();
//---------------------------

        Object o1 = new Object();
        Object o2 = new Object();
        ExecutorService fixedThreadPoolService = Executors.newFixedThreadPool(2);

        Future<?> f1 = fixedThreadPoolService.submit(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    System.out.println("Tortoise");
                }
            }
        });

        Future<?> f2 = fixedThreadPoolService.submit(() -> {
            synchronized (o2) {
                synchronized (o1) {
                    System.out.println("Hare");
                }
            }
        });

        f1.get();
        f2.get();

        System.out.println();
        fixedThreadPoolService.shutdown();
//---------------------------
    }

    static void addAndPrintItems(BlockingDeque<Integer> deque) throws InterruptedException {
        deque.offer(103);
        deque.offerFirst(20, 1, TimeUnit.SECONDS);
        deque.takeFirst();
        deque.poll(1, TimeUnit.MINUTES);
    }
}
