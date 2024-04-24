package org.example;

public class Freelancer extends BaseWorker {

    private final double hourlyRate;

    public Freelancer(String name, int age, double hourlyRate) {
        super(name, age);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calcSalary() {
        double DAYS = 20.8;
        int HOURS = 8;
        return DAYS * HOURS * hourlyRate;
    }

    @Override
    public String toString() {
        return "Freelancer{" +
                "salary=" + calcSalary() +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
