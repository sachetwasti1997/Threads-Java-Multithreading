package thread.synchronisation;

public class BankAccount {
    private double balance;
    private String name;
    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println("Name Updated New name is: "+name);
        }
    }

    public void deposit(double amount) {
        synchronized (lockBalance) {
            try {
                System.out.println("\nDEPOSIT - TALKING WITH THE TELLER AT BANK");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            double originalBalance = balance;
            balance += amount;
            System.out.printf("%nSTARTING BALANCE:%.0f, DEPOSIT:%.0f, NEW BALANCE:%.0f, THREAD:%s", originalBalance, amount, balance, Thread.currentThread().getName());
            addPromo(amount);
        }

    }

    public void addPromo(double amount) {
        if (amount >= 5000) {
            synchronized (lockBalance) {
                System.out.println("Congratulations you are eligible for promotional benefits!");
                balance += 25;
            }
        }
    }

    public synchronized void withDraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        double originalBalance = balance;
        if (amount <= originalBalance) {
            balance -= amount;
            System.out.printf("%nSTARTING BALANCE:%.0f, WITHDRAWAL:%.0f, NEW BALANCE:%.0f, THREAD: %s", originalBalance, amount, balance, Thread.currentThread().getName());
        }else {
            System.out.printf("STARTING BALANCE:%.0f, WITHDRAWAL:%.0f, INSUFFICIENT FUNDS!", originalBalance, amount);
        }

    }
}
