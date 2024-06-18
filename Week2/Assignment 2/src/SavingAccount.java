public class SavingAccount implements Account {
    private double balance;
    private double interestRate;

    public SavingAccount(double interest) {
        this.balance = 0;
        this.interestRate = interest;
    }

    public void savingInterest() {
        balance += balance*interestRate/100;
    }

    @Override
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    
}
