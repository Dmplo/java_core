package service;

import enums.Type;
import exceptions.CustomIllegalArgumentException;
import exceptions.OwnerNotFoundException;
import models.Account;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private static final List<Account> accounts = new ArrayList<>();

    static String getFormatType(Type type) {
        String name = "";
        switch (type) {
            case DEBIT -> name = "DebitAccount";
            case CREDIT -> name = "CreditAccount";
        }
        return name;
    }

    public static Account create(int balance, String owner, String className, Type type) throws CustomIllegalArgumentException {
        try {
            Class<?> accountClass = Class.forName(String.format("models.%s", className));
            Constructor<?>[] constructors = accountClass.getConstructors();
            return (Account) constructors[0].newInstance(owner, balance, type);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 ClassNotFoundException e) {
            throw new CustomIllegalArgumentException(owner, type);
        }
    }

    public static void add(int balance, String owner, Type type) {
        try {
            String className = getFormatType(type);
            Account account = create(balance, owner, className, type);
            accounts.add(account);
            System.out.printf("Счет %s у пользователя %s успешно создан! Остаток средств на счете %d.\n", className, owner, balance);
        } catch (CustomIllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static Account getAccount(String owner, Type type) throws OwnerNotFoundException {
        Account account = accounts.stream().filter(f -> f.getOwner().equals(owner) && type == f.getType()).findFirst().orElse(null);
        if (account == null) {
            throw new OwnerNotFoundException(owner);
        }
        return account;
    }

    public static void transferMoney(String owner, int sum, Type from, Type to) throws IllegalArgumentException, OwnerNotFoundException {

        Account sender = getAccount(owner, from);
        Account receiver = getAccount(owner, to);
        String senderClass = sender.getClass().getSimpleName();
        String receiverClass = receiver.getClass().getSimpleName();
        if (sum < 0) {
            throw new IllegalArgumentException(String.format("Не удалось списать средства со счета %s у %s. Ожидаемая сумма > 0, фактическая %d.", senderClass, owner, sum));
        }
        if (sum > sender.getBalance()) {
            throw new IllegalArgumentException(String.format("Не удалось списать средства со счета %s у %s. Сумма к списанию %d, остаток на счете %d.", senderClass, owner, sum, sender.getBalance()));
        }
            sender.transferOut(sum);
            receiver.transferIn(sum);
            System.out.printf("Транзакция у %s прошла успешно! Сумма %d списана co счета %s. Остатки: %s - %d; %s - %d.\n", owner, sum, senderClass, senderClass, sender.getBalance(), receiverClass, receiver.getBalance());
    }


}
