import exceptions.InsufficientFundsException;
import exceptions.OwnerNotFoundException;
import model.Account;
import service.BankAccountService;

public class Main {


    public static void main(String[] args) {
        BankAccountService accountService = new BankAccountService();

        accountService.add(50, "John");
        accountService.add(-1, "Will");
        accountService.add(100, "Jack");
        accountService.add(90, "Mike");

        withdrawMoney(accountService, "John", 40);
        withdrawMoney(accountService, "Will", 10);
        withdrawMoney(accountService, "Jack", 110);

        addDeposit(accountService, "Mike", 20);
        addDeposit(accountService, "Mike", 30);
        addDeposit(accountService, "Jack", -70);
    }


    static void withdrawMoney(BankAccountService accountService, String owner, int sum) {
        Account account = null;
        try {
            account = accountService.getAccount(owner);
        } catch (OwnerNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        try {
            accountService.getMoney(account, sum);
        } catch (InsufficientFundsException e) {
            System.err.println(e.getMessage());
        }
    }

    static void addDeposit(BankAccountService accountService, String owner, int sum) {
        Account account = null;
        try {
            account = accountService.getAccount(owner);
        } catch (OwnerNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }
        try {
            accountService.makeDeposit(account, sum);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

}
