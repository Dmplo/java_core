package org.example;

public abstract class BaseWorker implements Comparable<BaseWorker> {
    protected String name;

    public int age;

    public BaseWorker(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(BaseWorker o) {
        return Double.compare(this.calcSalary(), o.calcSalary());
    }

    public abstract double calcSalary();
}
