package thread.consumer_producer;

import java.util.Random;

public class OrderProducer implements Runnable{

    private final WareHouse wareHouse;

    public OrderProducer(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i=0; i<=10; i++) {
            wareHouse.receiveOrder(new Order(
                    random.nextLong(),
                    WareHouse.PRODUCT_LIST[random.nextInt(0, 5)],
                    random.nextLong(1, 50)
            ));
        }
    }
}
