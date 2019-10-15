package com.nick.jdk8.lambdas;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import lombok.Data;
import java.util.function.Predicate;

public class Lambda1 {

  public static void main(String[] args) {
    Predicate<Integer> predicate = x -> x > 100;
    Student student = new Student("9Dragon", 23, 175);
    System.out.println("Is 9Dragon higher than 100? : " + predicate.test(student.getHeight()));

    Consumer<String> consumer = System.out::println;
    consumer.accept("Yes, I am");

    Function<Student, String> function = Student::getName;
    String name = function.apply(student);
    System.out.println(name);

    Supplier<Integer> supplier = () -> Integer.valueOf(BigDecimal.TEN.toString());
    System.out.println(supplier.get());

    UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
    Boolean apply2 = unaryOperator.apply(true);
    System.out.println(apply2);

    BinaryOperator<Integer> operator = (x, y) -> x * y;
    Integer result = operator.apply(2, 3);
    System.out.println(result);
  }
}

@Data
class Student {

  private String name;

  private Integer age;

  private Integer height;

  public Student(String name, Integer age, Integer height) {
    this.name = name;
    this.age = age;
    this.height = height;
  }

}
