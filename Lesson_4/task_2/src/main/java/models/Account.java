package models;

import enums.Type;

public abstract class Account {
    private String owner;
    private int balance;

    public Account(String owner, int balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException();
        }
        this.owner = owner;
    }


    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public void transferIn(int sum) {
        balance += sum;
    }

    public void transferOut(int sum) {
        balance -= sum;
    }

    public abstract Type getType();
}
