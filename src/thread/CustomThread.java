package thread;

public class CustomThread extends Thread{

    @Override
    public void run() {
        var currentThread = Thread.currentThread();
        for (int i=0; i<=5; i++) {
            System.out.println(currentThread.getName()+" "+i);
            try {
                Thread.sleep(500);
            }catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
