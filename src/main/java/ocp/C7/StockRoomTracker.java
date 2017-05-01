package ocp.C7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * Created by barry on 29/04/2017.
 */
public class StockRoomTracker {
    public static void await(CyclicBarrier cb) {
        try {
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(10,
                () -> System.out.print("Stock Room Full!"));

        IntStream.iterate(1, i -> 1)
                .limit(9)
//                .parallel()
                .forEach(i -> await(cb));
    }
}
