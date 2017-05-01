package ocp.C7;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import static java.lang.String.format;

/**
 * Created by barry on 29/04/2017.
 */
public class WeighAnimalTask extends RecursiveTask<Double> {
    private Double[] weights;
    private int start;
    private int end;

    public WeighAnimalTask(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
//        System.out.println("this = " + this);
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighed: " + i);
                sum += weights[i];
            }

            return sum;
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println(format("start:%d, middle:%d, end:%d", start, middle, end));

            RecursiveTask<Double> otherTask = new WeighAnimalTask(weights, start, middle);
            otherTask.fork();
            return new WeighAnimalTask(weights, middle, end).compute() + otherTask.join();
//            Double otherResult = otherTask.fork().join();
//            return new WeighAnimalTask(weights, middle, end).compute() + otherResult;
//            return new WeighAnimalTask(weights, middle, end).compute() + otherTask.fork().join();
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[15];

        ForkJoinTask<Double> task = new WeighAnimalTask(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);
        System.out.println("sum = " + sum);
    }
}
