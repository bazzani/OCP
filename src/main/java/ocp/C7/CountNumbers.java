package ocp.C7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Created by barry on 29/04/2017.
 */
public class CountNumbers extends RecursiveAction {
    private int start;
    private int end;

    private static int count;

    public CountNumbers(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (start <= 0) count++;
        else {
            int middle = start + ((end - start) / 2);
            invokeAll(new CountNumbers(start, middle),
                      new CountNumbers(middle, end));
        }
    }

    public static void main(String[] args) {
        ForkJoinTask<?> task = new CountNumbers(0,4);
        ForkJoinPool pool = new ForkJoinPool();
        Object invoke = pool.invoke(task);

        System.out.println("count = " + count);
    }
}
