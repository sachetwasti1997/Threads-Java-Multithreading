package thread;

public class Main {
    public static void main(String[] args) {
        var currentThread = Thread.currentThread();
        printThreadState(currentThread);

        currentThread.setPriority(Thread.MAX_PRIORITY);
        currentThread.setName("MainGuy");

        printThreadState(currentThread);

        CustomThread customThread = new CustomThread();
        customThread.start();// run() will start the method concurrently like any other methods

        for (int i = 0; i<=5; i++) {
            System.out.println(currentThread.getName()+" "+i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void printThreadState(Thread thread) {
        System.out.println("--------------------------");
        System.out.println("Thread ID: "+thread.getId());
        System.out.println("Thread Name: "+thread.getName());
        System.out.println("Thread Priority: "+thread.getPriority());
        System.out.println("Thread State: "+thread.getState());
        System.out.println("Thread Group: "+thread.getThreadGroup());
        System.out.println("Is Thread Alive: "+thread.isAlive());
        System.out.println("---------------------------");
    }
}