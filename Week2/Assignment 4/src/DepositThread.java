public class DepositThread implements Runnable {
    private BankAccount account;

    public DepositThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.deposit(100); // Deposit $100, 5 times
            try {
                Thread.sleep(100); // Simulate some delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}