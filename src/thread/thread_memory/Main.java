package thread.thread_memory;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        var stopWatch = new StopWatch(TimeUnit.SECONDS);
        Thread greenThread = new Thread(stopWatch::countDown, ThreadColor.ANSI_GREEN.color());
        Thread redThread = new Thread(() -> stopWatch.countDown(7), ThreadColor.ANSI_RED.color());
        Thread cyanThread = new Thread(() -> stopWatch.countDown(5), ThreadColor.ANSI_CYAN.color());
        greenThread.start();
        redThread.start();
        cyanThread.start();
    }
}

class StopWatch {
    private final TimeUnit timeUnit;
    private int i;
    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
    public void countDown() {
        countDown(5);
    }
    public void countDown(int unitTime) {
        var currentThread = Thread.currentThread().getName();
        var threadColor = ThreadColor.ANSI_RESET;

        try {
            threadColor = ThreadColor.valueOf(currentThread);
        }catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        String color = threadColor.color();
        for (i=unitTime; i>0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.printf("%s%s Thread : i = %d%n",color, currentThread, i);
        }
    }
}
