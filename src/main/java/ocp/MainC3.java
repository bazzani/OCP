package ocp;

import java.util.*;

public class MainC3 {

    public static void main(String[] args) {
        List<? super Object> objects = new ArrayList<>();
        objects.add("s");

        Set<Number> numbers = new HashSet<>();
        numbers.add(new Integer(86));
        numbers.add(75);
        numbers.add(null);
        numbers.add(309L);
        Iterator<Number> iter = numbers.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
//---------

        Map<String, Double> stringDoubleMap = new HashMap<>();
        stringDoubleMap.put("c", 2.2);
        System.out.println();
//---------

        class TreeComparator implements Comparator<String> {
            @Override
            public int compare(String s1, String s2) {
                return s2.toLowerCase().compareTo(s1.toLowerCase());
            }
        }

        TreeSet<String> tree = new TreeSet<>();
        tree.add("one");
        tree.add("One");
        tree.add("ONE");
        System.out.println("tree Ceil: " + tree.ceiling("On"));

        TreeSet<String> treeSorted = new TreeSet<>(new TreeComparator());
        treeSorted.add("one");
        treeSorted.add("One");
        treeSorted.add("ONE");
        System.out.println("treeSorted Ceil: " + treeSorted.ceiling("On"));
        System.out.println();
//---------

        new Helper().printException(new NullPointerException());

        Wildcard card = new Wildcard();
//---------

        ArrayDeque<String> ad = new ArrayDeque();
//---------

        TreeSet<Sorted> t1 = new TreeSet<>();
//---------

        Comparator<Integer> comp = ((o1, o2) -> o2 - o1);
        List<Integer> list = Arrays.asList(5, 4, 7, 1);
        Collections.sort(list, comp);
        System.out.println(Collections.binarySearch(list, 1));
//---------

        List<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(12);
        q.remove(1);
        System.out.println(q);

        Queue<Integer> q2 = new LinkedList<>();
        q2.add(10);
        q2.add(12);
        q2.remove(1);
        System.out.println(q2);
//---------

        Map m = new HashMap();
        m.put(123, "456");
        m.put("abc", "def");
        System.out.println(m.containsKey("123"));
//---------

        System.out.println(identity(MainC3.class));
//---------

        q2.removeIf(i -> i < 10);
        System.out.println("q2 = " + q2);
//---------

        Set<String> s = new HashSet<>();
        s.add("lion");
        s.add("tiger");
        s.add("bear");
        s.forEach(System.out::println);
        System.out.println();
//---------

        Map<A, ? super A> map = new TreeMap<>();
        A a = new A(1);
        B b = new B(2);
        C c = new C(3);

        map.put(a, a);
        map.put(b, b);
        map.put(c, null);
        map.merge(a, b, (a1, a2) -> a2);
        map.merge(b, a, (a1, a2) -> a2);
        map.merge(c, b, (a1, a2) -> a2);

        map.forEach((a1, a2) -> {
            System.out.println(a1);
            System.out.println(a2);
            System.out.println();
        });

        System.out.println("map = " + map);
    }

    static class A implements Comparable<A> {
        private final Number n;

        A(Number n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return String.format("%s[%s]", this.getClass().getTypeName(), n);
        }

        public int compareTo(A other) {
            return n.intValue() - other.n.intValue();
        }
    }

    static class B extends A {
        B(Number n) {
            super(n);
        }
    }

    static class C extends B {
        C(Number n) {
            super(n);
        }
    }


    static class Helper {
        <U extends Exception> void printException(U u) {
            System.out.println(u.getMessage());
        }

    }

    static class Wildcard {
        public void showSize(List<?> list) {
            System.out.println(list.size());
        }
    }

    static class Sorted implements Comparable<Sorted>, Comparator<Sorted> {
        @Override
        public int compareTo(Sorted o) {
            return 0;
        }

        @Override
        public int compare(Sorted o1, Sorted o2) {
            return 0;
        }
    }

    static <T> T identity(T t) {
        return t;
    }
}
