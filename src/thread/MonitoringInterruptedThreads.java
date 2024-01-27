package thread;

import java.util.Random;

public class MonitoringInterruptedThreads {
    public static void main(String[] args) {
        System.out.println("Main Thread running!");
        try {
            System.out.println("Main Thread stopping here for 1 second!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Thread thread = new Thread(() -> {
            var tName = Thread.currentThread().getName();
            var randomIntGenerator = new Random();
            int limit = randomIntGenerator.nextInt(1, 5);
            System.out.println(tName+" will take "+ limit+" seconds to complete.");
            int i;
            for (i=0; i<limit; i++) {
                try {
                    System.out.print(". ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" Interrupted");
                    Thread.currentThread().interrupt();
                    return;
                    // if the return is commented the thread will continue running,
                    // after it is interrupted in 2 seconds
                }
            }
            if(i == limit)System.out.println("\n"+tName+" completed!");
        });

        var installThread = new Thread(() -> {
            var tName = Thread.currentThread().getName();
            System.out.println(tName+" got started ");
            for (int i=1; i<=5; i++) {
                System.out.println(tName+" completed "+(i)+" step!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "InstallThread");

        var monitorThread = new Thread(() -> {
            System.out.println("\n"+Thread.currentThread().getName()+" started monitoring "+thread.getName()+
                    " status. "+thread.getName()+" will stop if download takes more than 3 seconds");
            var now = System.currentTimeMillis();
            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);

                    if (System.currentTimeMillis() - now > 3000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "MonitorThread");

        thread.start();
        monitorThread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        }else {
            System.out.println("The "+thread.getName()+" terminated, so "+installThread.getName()+" cannot be started!");
        }

    }
}
