package ocp.C7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by barry on 29/04/2017.
 */
public class SheepManager {
    private static AtomicInteger sheepCount1 = new AtomicInteger(0);
    private static int sheepCount2 = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = null;
        long start = System.currentTimeMillis();

        try {
            service = Executors.newSingleThreadScheduledExecutor();
//            service = Executors.newFixedThreadPool(32);
            for (int i = 0; i < 100000; i++) {
                service.execute(() -> {
                    sheepCount1.getAndIncrement();
                    sheepCount2++;
                });
            }

            Thread.sleep(100);
            System.out.println("sheepCount1 = " + sheepCount1);
            System.out.println("sheepCount2 = " + sheepCount2);
            System.out.println("ms: " + (System.currentTimeMillis() - start));
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
