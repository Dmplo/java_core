package org.example;

public class Worker extends BaseWorker {

    private final double baseRate;

    public Worker(String name, int age, double baseRate) {
        super(name, age);
        this.baseRate = baseRate;
    }

    @Override
    public double calcSalary() {
        return baseRate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "salary=" + calcSalary() +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
