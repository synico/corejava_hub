package javac.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GenerateStream {

    private List<String> list = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul", "Peter");

    private void ofElement() {
        Stream.of("test", "hello")
                .map(item -> item.length())
                .forEach(System.out::println);
    }

    private void ofELementByArray() {
        Stream.of(list)
                .peek(item -> System.out.println(item + "xx"))
                .count();
    }

    public static void main(String args[]) {
        GenerateStream gs = new GenerateStream();
        gs.ofELementByArray();
    }

}
