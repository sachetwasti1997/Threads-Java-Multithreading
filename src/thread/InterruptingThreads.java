package thread;

public class InterruptingThreads {
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
            System.out.println(tName+" will take 10 dots to complete.");
            for (int i=1; i<=10; i++) {
                try {
                    System.out.print(". ");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" Interrupted");
                    return;
                    // if the return is commented the thread will continue running,
                    // after it is interrupted in 2 seconds
                }
            }
            System.out.println("\n"+tName+" completed!");
        });

        System.out.println(thread.getName()+" starting!");
        thread.start();

        System.out.println("Main Thread will start running here!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        thread.interrupt();
    }
}
