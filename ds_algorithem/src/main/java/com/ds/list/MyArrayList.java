package com.ds.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class MyArrayList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        Spliterator<String> sit = list.spliterator();
        // sit.forEachRemaining(t -> t.toUpperCase());
        sit.forEachRemaining(System.out::println);
    }

}
