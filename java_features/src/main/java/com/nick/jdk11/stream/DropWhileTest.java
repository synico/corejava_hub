package com.nick.jdk11.stream;

import java.util.stream.Stream;

public class DropWhileTest {

    public static void main(String[] args) {
        long count = Stream.of(1, 2, 3, 4, 5).dropWhile(i -> i % 2 != 0).count();

    }

}
