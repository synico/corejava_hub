package javac.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class StreamDemo {

    static class Cart {

        List<StreamDemo.Order> orders = new ArrayList<StreamDemo.Order>();

        Cart() {
            Random rd = new Random();
            Order order = null;
            for (int i = 0; i < 10000; i++) {
                int orderId = Math.abs(rd.nextInt());
                StreamDemo sd = new StreamDemo();
                order = sd.new Order(new Long(Integer.toString(orderId)));
                OrderItem orderItem = null;
                List<OrderItem> orderItems = new ArrayList<OrderItem>();
                for (int j = 1; j < 9; j++) {
                    orderItem = sd.new OrderItem();
                    orderItem.setOrderItemId(new Long(orderId + "0" + j));
                    orderItem.setCatentryId(new Long(j + "000" + orderId));
                    orderItem.setAmount(new Long(j + 2));
                    orderItems.add(orderItem);
                }
                order.setOrderItems(orderItems);
                orders.add(order);
            }
        }
    }

    private static class CartHolder {
        static Cart cart = new Cart();
    }

    public static Cart getCart() {
        return CartHolder.cart;
    }

    class Order {

        Order(Long orderId) {
            this.orderId = orderId;
        }

        private Long orderId;

        private Long amount;

        private List<OrderItem> orderItems;

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public List<OrderItem> getOrderItems() {
            return orderItems;
        }

        public void setOrderItems(List<OrderItem> orderItems) {
            this.orderItems = orderItems;
        }

    }

    class OrderItem {
        private Long orderItemId;

        private Long amount;

        private Long catentryId;

        public Long getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        public Long getCatentryId() {
            return catentryId;
        }

        public void setCatentryId(Long catentryId) {
            this.catentryId = catentryId;
        }

    }

    public static void main(String[] args) {
        Cart cart = StreamDemo.getCart();
        List<StreamDemo.Order> orders = cart.orders;
        for (StreamDemo.Order order : orders) {
            System.out.println("######################################################");
            System.out.println("OrderId: " + order.getOrderId());
            for (StreamDemo.OrderItem orderItem : order.getOrderItems()) {
                System.out.println("-----------------------------------------------");
                System.out.println("	OrderItemId: " + orderItem.getOrderItemId());
                System.out.println("	CatentryId: " + orderItem.getCatentryId());
                System.out.println("	Amount: " + orderItem.getAmount());
            }
            System.out.println("######################################################");
        }

        List<StreamDemo.OrderItem> orderitems = orders.parallelStream()
                .flatMap(order -> order.getOrderItems().parallelStream()).collect(Collectors.toList());
        Optional<Long> sum = orderitems.stream().map(item -> item.getAmount()).reduce(Long::sum);
        System.out.println(sum.toString());
    }

}
