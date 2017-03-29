package ocp._07_concurrency;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParallelStreams {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1,2,3,4,5).stream();
        stream.forEach(System.out::println);

        System.out.println("--");

        Stream<Integer> parallelStream = Arrays.asList(1,2,3,4,5).parallelStream();
        parallelStream.forEach(System.out::println);
    }
}
