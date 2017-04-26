package ocp;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Created by barry on 24/04/2017.
 */
public class MainC4 {
    public static void main(String[] args) {
        Stream<String> stream1 =
                Stream.iterate("", (s) -> s + "1");
        System.out.println(stream1.limit(2).map(x -> x + "2"));

        Stream<String> stream2 =
                Stream.iterate("", (s) -> s + "1")
                        .limit(2).map(x -> x + "2");
        stream2.forEach(System.out::println);
        System.out.println();
//---------------------------

        Predicate<? super String> predicate = s -> s.startsWith("g");
        Stream<String> stream21 = Stream.generate(() -> "growl1! ");
        Stream<String> stream22 = Stream.generate(() -> "growl2! ");

        boolean b1 = stream21.anyMatch(predicate);
//        boolean b2 = stream22.allMatch(predicate);
//                .peek(System.out::println)
//        System.out.println(b1 + " " + b2);
//        System.out.println();
//---------------------------

        Predicate<? super String> predicate3 = s -> s.length() > 3;
        Stream<String> stream3 = Stream.iterate("-", s -> s + s);
        boolean b31 = stream3.noneMatch(predicate3);
//        boolean b32 = stream3.anyMatch(predicate3);
//        System.out.println(b31 + " " + b32);

        System.out.println();
//---------------------------

        Stream<Integer> intStream = Stream.generate(() -> new Random().nextInt());
        intStream
                .limit(5)
                .forEach(System.out::println);
        System.out.println();

        Stream<Integer> intStream2 = Stream.generate(() -> new Random().nextInt());
        IntStream intStream3 = intStream2.limit(5)
                .mapToInt(Integer::intValue);
        System.out.println(intStream3.sum());

        System.out.println();
//---------------------------

        Stream<String> s = Stream.generate(() -> "meow");
        boolean match = s.allMatch(String::isEmpty);
//        boolean match = s.anyMatch(String::isEmpty);
//        boolean match = s.noneMatch(String::isEmpty);
//        Optional<String> match = s.findFirst();

        System.out.println(match);
        System.out.println();
//---------------------------

        IntStream is = IntStream.empty();
        is.average();
//        is.findAny();
//        is.sum();
        System.out.println();
//---------------------------

        LongStream ls = LongStream.of(1, 2, 3);
        OptionalLong opt = ls.map(n -> n * 10).filter(n -> n < 15).findFirst();
        if (opt.isPresent()) System.out.println("is: " + opt.getAsLong());
        opt.ifPresent(System.out::println);
        System.out.println();

        Stream<String> ss = Stream.of("foo", "bar", "zaa");
        Optional<String> optS = ss.findFirst();
        optS.ifPresent(s1 -> System.out.println(s1.toUpperCase()));
//---------------------------

        System.out.println(
                Stream.iterate(1, x -> ++x)
                        .limit(5)
                        .map(x -> "" + x)
                        .collect(Collectors.joining())
        );

        System.out.println();
//---------------------------

        BinaryOperator<String> bo = (x, y) -> {
            return x.concat(y);
        };
        System.out.println(bo.apply("foo", "bar"));

        BiFunction<String, String, String> biF = (x, y) -> x.concat(" de ").concat(y);
        System.out.println(biF.apply("blah", "blah"));


        UnaryOperator<String> uo = z -> z.toUpperCase();
        System.out.println(uo.apply("blah"));

        Function<String, String> f = x -> x.toUpperCase().concat(" F blah");
        System.out.println(f.apply("blah"));

        System.out.println();
//---------------------------

        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Integer> l2 = Arrays.asList(4, 5, 6);
        List<Integer> l3 = Arrays.asList();

        Stream.of(l1, l2, l3)
                .flatMap(x -> x.stream())
                .map(x -> x + 1)
                .forEach(System.out::print);


        System.out.println();
//---------------------------

        Stream<Integer> s14 = Stream.of(1);
        IntStream s14I = s14.mapToInt(xs -> xs);
        DoubleStream s14D = s14I.mapToDouble(xd -> xd);
//        Stream<Integer> s14II = s14D.
//        s14II.forEach(System.out::print);

        System.out.println();
//---------------------------


        DoubleStream ds = DoubleStream.of(1.2, 2.4);
        ds.peek(System.out::println)
                .filter(x -> x > 2)
                .count();
        System.out.println();

        DoubleStream ds2 = DoubleStream.of(1.2, 2.4);
        ds2.filter(x -> x > 2)
                .peek(System.out::println)
                .count();

        System.out.println();
//---------------------------


        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
//        String result = ohMy.collect(Collectors.joining());
        Map<Boolean, List<String>> result =
                ohMy.collect(Collectors.partitioningBy(x -> x.startsWith("l")));

        System.out.println("result = " + result);

        System.out.println();
//---------------------------
    }
}
