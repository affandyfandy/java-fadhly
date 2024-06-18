public class CurrentAccount implements Account {
    private double balance;
    private double limit;

    public CurrentAccount(double limit) {
        this.limit = limit;
        this.balance = 0;
    }

    public double getLimit() {
        return limit;
    }

    @Override
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        if(amount > 0 && balance - amount >= -limit) {
            balance -= amount;
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

}
