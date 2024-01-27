package thread;

public class InterruptingThread3 {
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
            System.out.println(tName+" will take 10 seconds to complete.");
            for (int i=1; i<=10; i++) {
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
            System.out.println("\n"+tName+" completed!");
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

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        installThread.start();



//        var now = System.currentTimeMillis();
//        while (thread.isAlive()) {
//            try {
//                Thread.sleep(1000);
//
//                if (System.currentTimeMillis() - now >= 3000) {
//                    thread.interrupt();
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }
}
