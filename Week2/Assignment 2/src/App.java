public class App {
    public static void main(String[] args) throws Exception {
        Account.printWelcomeMessage();

        SavingAccount svc = new SavingAccount(5);
        svc.deposit(200);
        svc.printAccountDetails();
        svc.savingInterest();
        svc.printAccountDetails();

        System.out.println();

        CurrentAccount crc = new CurrentAccount(300);
        crc.deposit(400);
        crc.printAccountDetails();
        crc.withdraw(450);
        crc.getLimit();
        crc.printAccountDetails();
    }
}
