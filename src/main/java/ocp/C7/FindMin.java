package ocp.C7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.lang.String.format;

/**
 * Created by barry on 28/04/2017.
 */
public class FindMin extends MyTask {

    private Integer[] elements;
    private int a;
    private int b;

    public FindMin(Integer[] elements, int a, int b) {
        this.elements = elements;
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer compute() {
        if ((b - a) < 2) {
//            System.out.println("b-a:" + (b - a));
            return Math.min(elements[a], elements[b]);
        } else {
            int m = a + (b - a) / 2;
            System.out.println(format("a=%d, m=%d, b=%d", a, m, b));
            MyTask t1 = new FindMin(elements, a, m);
            t1.fork();
            return Math.min(new FindMin(elements, m, b).compute(), t1.join());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Integer[] elements = new Integer[]{8, -3, -2, -54};
        MyTask task = new FindMin(elements, 0, elements.length - 1);
        ForkJoinPool pool = new ForkJoinPool(1);
        Integer sum = pool.invoke(task);
        System.out.println("Min: " + sum);
    }
}

abstract class MyTask extends RecursiveTask<Integer> {
}

abstract class MyAction extends RecursiveAction {
}
