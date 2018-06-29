package javac.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class LambdaDemo {

    private static void checkCollactor() {
        List<Order> orders = new ArrayList<Order>();
        Order order1 = new Order("order1", 1, 1.1);
        Order order2 = new Order("order2", 2, 2.2);
        Order order3 = new Order("order3", 3, 3.3);
        Order order4 = new Order("order4", 4, 4.4);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        Predicate<Order> orderIdIsOne = o -> o.getOrderId() == 1;
        Predicate<Order> orderIdIsNotTwo = o -> o.getOrderId() != 2;

        Function<Order, String> mapOrderId2Str = o -> o.getOrderId().toString();

        Consumer<Order> peekItemBeforeFilter = o -> System.out.println("before stream is processed: " + o.getOrderId());
        Consumer<String> peekItemAfterFiler = e -> System.out.println("after stream is filtered: " + e);
        Consumer<String> printElement = System.out::println;

        orders.stream()
                .filter(orderIdIsOne)
                .collect(Collectors.toList());

        orders.stream()
                .peek(peekItemBeforeFilter)
                .filter(orderIdIsNotTwo)
                .map(mapOrderId2Str)
                .peek(peekItemAfterFiler)
                .forEach(printElement);

        int value = 4;
        Runnable run = () -> System.out.println("runnable:" + value);

        System.out.println("End...." + printElement);
    }

    public static void main(String args[]) {
        LambdaDemo.checkCollactor();
    }

    static class Order {

        public Order(String name, Integer orderId, Double amount) {
            this.name = name;
            this.orderId = orderId;
            this.amount = amount;
        }

        private String name;

        private Integer orderId;

        private Double amount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

    }
}
