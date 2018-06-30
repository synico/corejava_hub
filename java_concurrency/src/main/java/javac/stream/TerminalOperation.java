package javac.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class TerminalOperation {

    private List<String> list = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul", "Peter");

    private void reduce() {
        Optional<String> rst = list.stream()
//                            .peek(System.out::println)
                            .reduce((a, b) -> a + "|" + b);
        System.out.println(rst.get());
    }

    private void collect() {

    }

    private void count() {

    }

    private void match() {
        Predicate<String> lenEQ4 = item -> item.length() == 4;
        list.stream().allMatch(lenEQ4);
        list.stream().anyMatch(lenEQ4);
        list.stream().noneMatch(lenEQ4);
    }

    public static void main(String args[]) {
        TerminalOperation to = new TerminalOperation();
        to.reduce();
    }

}
