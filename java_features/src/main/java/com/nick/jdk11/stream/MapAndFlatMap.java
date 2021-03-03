package com.nick.jdk11.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapAndFlatMap {

    public static void main(String[] args) {
        List<String> strList = Arrays.asList("abcd", "bcdd", "defde", "fTr");
        List newStrList = strList.stream().map(String::toUpperCase).collect(Collectors.toList());

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);
    }
}
