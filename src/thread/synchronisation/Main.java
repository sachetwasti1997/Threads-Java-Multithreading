package thread.synchronisation;

public class Main {
    public static void main(String[] args) {
        var companyBalance = new BankAccount("Sachet",10000);
        var thread1 = new Thread(() -> companyBalance.withDraw(2500));
        var thread2 = new Thread(() -> companyBalance.deposit(5000));
        var thread3 = new Thread(() -> companyBalance.setName("Sachet Wasti"));
        var thread4 = new Thread(() -> companyBalance.withDraw(5000));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.printf("%nTOTAL BALANCE:%.0f", companyBalance.getBalance());
    }
}
