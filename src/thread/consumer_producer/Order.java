package thread.consumer_producer;

public record Order(
        Long shoeId,
        String shoeType,
        Long quantityOrdered
) {
}
