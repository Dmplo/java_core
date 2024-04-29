package models;

import enums.Type;

public class DebitAccount extends Account {
Type type;
    public DebitAccount(String owner, int balance, Type type) {
        super(owner, balance);
        this.type = type;
    }

    @Override
    public Type getType() {
        return type;
    }
}
