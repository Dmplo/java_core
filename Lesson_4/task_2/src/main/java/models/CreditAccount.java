package models;

import enums.Type;

public class CreditAccount extends Account {
Type type;
    public CreditAccount(String owner, int balance, Type type) {
        super(owner, balance);
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }

}
