package thread.consumer_producer;

public class OrderFulfiller implements Runnable{

    private final WareHouse wareHouse;
    private final int orderFullfillingQuantity;

    public OrderFulfiller(WareHouse wareHouse, int orderFullfillingQuantity) {
        this.wareHouse = wareHouse;
        this.orderFullfillingQuantity = orderFullfillingQuantity;
    }

    @Override
    public void run() {
        for (int i = 0; i<orderFullfillingQuantity; i++) {
            wareHouse.fulfillOrder();
        }
    }
}
