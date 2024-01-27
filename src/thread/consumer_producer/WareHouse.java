package thread.consumer_producer;

import java.util.LinkedList;
import java.util.List;

public class WareHouse {
    private List<Order> orders;
    private final Object lockOrder = new Object();
    private final Object lockFulfill = new Object();

    public static final String [] PRODUCT_LIST =
            {"Running Shoes", "Sandals", "Boots", "Slippers", "High Tops"};

    public WareHouse() {
        this.orders = new LinkedList<>();
    }

    public synchronized void receiveOrder(Order order) {
        while (orders.size() >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        orders.add(order);
        System.out.printf("%s: Thread adding %s, current size of orders: %d%n", Thread.currentThread().getName(),
                order, orders.size());
        notifyAll();
    }

    public synchronized void fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order order = orders.remove(0);
        System.out.printf("%s: Thread Fulfilling the order %s%n", Thread.currentThread().getName(), order);
        notifyAll();
    }
}
