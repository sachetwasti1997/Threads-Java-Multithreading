package thread.consumer_producer;

public class MainShoeOrder {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();
        Thread producerThread = new Thread(new OrderProducer(wareHouse));
        producerThread.start();
        for (int i=0; i<2; i++) {
            Thread consumerThread = new Thread(new OrderFulfiller(wareHouse, 5));
            consumerThread.start();
        }
    }
}
