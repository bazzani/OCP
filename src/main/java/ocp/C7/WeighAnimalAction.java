package ocp.C7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import static java.lang.String.format;

/**
 * Created by barry on 29/04/2017.
 */
public class WeighAnimalAction extends RecursiveAction {

    private Double[] weights;
    private int start;
    private int end;

    public WeighAnimalAction(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    public void compute() {
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);

                System.out.println("Animal weighed: " + i);

                if (weights[i] > 95) {
                    throw new RuntimeException("weight above 95 found");
                }
            }
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println(format("start:%d, middle:%d, end:%d", start, middle, end));

            invokeAll(new WeighAnimalAction(weights, start, middle),
                    new WeighAnimalAction(weights, middle, end));
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        System.out.println("availableProcessors = " + java.lang.Runtime.getRuntime().availableProcessors());

        ForkJoinTask<?> task = new WeighAnimalAction(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        try {
            pool.invoke(task);

            System.out.println();
            System.out.println("Weights = ");
            Arrays.asList(weights)
                    .stream()
                    .forEach(d -> System.out.print(d.intValue() + " "));
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        System.out.println();
        System.out.println("---------------");
        System.out.println("isDone() = " + task.isDone());
        System.out.println("isCompletedAbnormally() = " + task.isCompletedAbnormally());
        System.out.println("isCompletedNormally() = " + task.isCompletedNormally());
    }
}
