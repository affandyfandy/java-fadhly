public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();

    default void printAccountDetails() {
        System.out.println("Detail Balance: " + getBalance());
    }

    static void printWelcomeMessage() {
        System.out.println("Welcome to the bank!");
    }
}
