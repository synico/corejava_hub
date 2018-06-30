package javac.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperation {

    private List<String> list = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul", "Peter");

    private Consumer<Object> print = System.out::print;

    private Consumer<Object> println = System.out::println;

    private void filter() {
        Predicate<String> removeLenNot4 = item -> item.length() == 4;
        list.stream()
                .filter(removeLenNot4)
                .forEach(println);
    }

    private void map() {
        Function<String, Integer> getWordLen = item -> item.length();
        list.stream()
                .peek(print)
                .map(getWordLen)
                .forEach(println);
    }

    private void flatMap() {
        Function<String, Stream<String>> flat = item -> Arrays.stream(item.split("a"));
        list.stream()
                .flatMap(flat)
                .forEach(println);
    }

    private void distinct() {
        list.stream()
                .map(item -> item.length())
//                .peek(item -> System.out.println("[" + item + "]"))
                .distinct()
                .forEach(println);
    }

    private void sorted() {
        String sortedLen = list.stream()
                                .map(item -> item.length() + "")
                                .distinct()
                                .sorted()
                                .collect(Collectors.joining(","));
        System.out.println(sortedLen);
    }

    private void limit() {
        String rst = list.stream()
                         .limit(2)
                         .collect(Collectors.joining(","));
        System.out.println(rst);
    }

    private void skip() {
        String rst = list.stream()
                         .skip(4)
                         .collect(Collectors.joining(","));
        System.out.println(rst);
    }

    public static void main(String args[]) {
        IntermediateOperation op = new IntermediateOperation();
//        op.filter();
//        System.out.println("----------------------");
//        op.map();
//        System.out.println("----------------------");
//        op.flatMap();
//        System.out.println("----------------------");
//        op.distinct();
//        op.sorted();
//        op.limit();
        op.skip();
    }

}
