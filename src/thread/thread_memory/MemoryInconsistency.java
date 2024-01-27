package thread.thread_memory;

import java.util.concurrent.TimeUnit;

public class MemoryInconsistency {

    private volatile boolean flag;

    public void toggleFlag() {
        this.flag = !this.flag;
    }

    public boolean isReady() {
        return this.flag;
    }

    public static void main(String[] args) {
        MemoryInconsistency memoryInconsistency = new MemoryInconsistency();
        var writerThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            memoryInconsistency.toggleFlag();
            System.out.println("A.flag is set to "+memoryInconsistency.isReady());
        });
        var readerThread = new Thread(() -> {
            while (!memoryInconsistency.isReady()) {
                // Busy-wait until the flag becomes ready
            }
            System.out.println("B.flag is ready: "+memoryInconsistency.isReady());
        });

        writerThread.start();
        readerThread.start();
        //memory will be inconsistent as it is updated by the writerThread across readerThread
        //to prevent this we can use volatile keyword
        //volatile does not guarantee atomicity
    }

}
