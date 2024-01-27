package thread.consumer_producer.try_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageRepository {

    private String message;
    private boolean hasMessage = false;
    private final Lock tryLock = new ReentrantLock();

    public String read() {
        try {
            if (tryLock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (!hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = false;

                }finally {
                    tryLock.unlock();
                }
            }else {
                System.out.println("**read blocked");
                hasMessage = false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public void write(String message) {
        try {
            if (tryLock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = true;
                    this.message = message;
                }finally {
                    tryLock.unlock();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
