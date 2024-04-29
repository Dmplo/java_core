package service;

import exceptions.InsufficientFundsException;
import exceptions.OwnerNotFoundException;
import model.Account;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService {

    private final List<Account> accounts;

    public BankAccountService() {
        this.accounts = new ArrayList<>();
    }

    public void add(int balance, String owner) {
        try {
            accounts.add(new Account(balance, owner));
            System.out.printf("Аккаунт %s успешно создан! Остаток средств на счете %d.\n", owner, balance);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public Account getAccount(String owner) throws OwnerNotFoundException {
      Account account = accounts.stream().filter(f -> f.getOwner().equals(owner)).findFirst().orElse(null);
        if (account == null) {
            throw new OwnerNotFoundException(owner);
        }
        return account;
    }

    public void makeDeposit(Account account, int balance) throws IllegalArgumentException {
        if (balance > 0) {
            account.setDeposit(balance);
            System.out.printf("Сумма %d успешно зачислена на счет депозита %s. Остаток %d.\n", balance, account.getOwner(), account.getDeposit());
            return;
        }
        throw new IllegalArgumentException(String.format("Не удалось завершить операцию по начислению депозита на счет %s. Ожидаемая сумма поступления > 0, фактическая %d.", account.getOwner(), balance));
    }

    public void getMoney(Account account, int money) throws InsufficientFundsException {
        if (money < account.getStartBalance()) {
            account.getMoney(money);
            System.out.printf("Транзакция у %s прошла успешно! Сумма %d успешно снята, остаток средств на счете %d.\n", account.getOwner(), money, account.getStartBalance());
            return;
        }
        throw new InsufficientFundsException(account.getStartBalance(), account.getOwner(), money);
    }


}
