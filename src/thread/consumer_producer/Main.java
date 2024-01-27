package thread.consumer_producer;

public class Main {
    public static void main(String[] args) {
        var messageRepository = new MessageRepository();
        var reader = new Thread(new MessageReader(messageRepository));
        var writer = new Thread(new MessageWriter(messageRepository));

        reader.start();
        writer.start();
    }
}
