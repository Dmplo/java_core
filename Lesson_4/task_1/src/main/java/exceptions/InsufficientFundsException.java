package exceptions;

public class InsufficientFundsException extends Exception {
    private String message;
    private int balance;
    private int recieve;
    private String owner;


    public InsufficientFundsException(int balance, String owner, int recieve) {
        super();
        this.balance = balance;
        this.recieve = recieve;
        this.owner = owner;
    }

    @Override
    public String getMessage() {
        return String.format("Не достаточно средств на счете %s! Вы пытаетесь снять %d, остаток %d.", owner, recieve, balance);
    }

    public int getBalance() {
        return balance;
    }

}
