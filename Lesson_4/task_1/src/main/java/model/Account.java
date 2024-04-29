package model;

public class Account {
    private int startBalance;
    private int deposit;
    private int amountReceived;

    private String owner;


    public Account(int startBalance, String owner) {
        if (startBalance >= 0) {
            this.startBalance = startBalance;
        } else {
            throw new IllegalArgumentException(String.format("Не удалось завершить операцию по созданию счета %s. Ожидаемая сумма поступления >= 0, фактическая %d.", owner, startBalance));
        }
        this.owner = owner;
    }

    public int getStartBalance() {
        return startBalance;
    }

    public String getOwner() {
        return owner;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(int amountReceived) {
        this.amountReceived = amountReceived;
    }

    public void setDeposit(int deposit) {
        this.deposit += deposit;
    }

    public void getMoney (int money) {
        startBalance -= money;
    }
}
