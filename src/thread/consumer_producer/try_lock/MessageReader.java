package thread.consumer_producer.try_lock;

import java.util.Objects;
import java.util.Random;

public class MessageReader implements Runnable{

    private final MessageRepository incomingMessages;

    public MessageReader(MessageRepository incomingMessages) {
        this.incomingMessages = incomingMessages;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            latestMessage = incomingMessages.read();
            System.out.println(latestMessage);
        }while (!Objects.equals(latestMessage, "Finished"));
    }
}
