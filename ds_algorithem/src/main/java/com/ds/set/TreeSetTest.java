package com.ds.set;

import java.util.TreeSet;

/**
 * @author nick
 */
public class TreeSetTest {

    public static void main(String[] String) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            Double random = Math.random() * 100;
            ts.add(random.intValue());
        }

        ts.stream().forEach(System.out::println);

        System.out.println("retrieve fist and last");
        System.out.println("first: " + ts.first());
        System.out.println("second: " + ts.higher(ts.first()));
        System.out.println("last but one: " + ts.lower(ts.last()));
        System.out.println("last: " + ts.last());

        ts.stream().forEach(System.out::println);

        System.out.println("poll first and last");

        System.out.println("first element: " + ts.pollFirst());
        ts.stream().forEach(System.out::println);
        System.out.println("last element: " + ts.pollLast());
        ts.stream().forEach(System.out::println);
        System.out.println("last element: " + ts.pollLast());
        ts.stream().forEach(System.out::println);
    }
}
