package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HumanResourcesDepartment hr = new HumanResourcesDepartment();
        hr.addWorkers(new Freelancer("Johny", 20, 750));
        hr.addWorkers(new Freelancer("Will", 22, 500));
        hr.addWorkers(new Worker("Alex", 25, 50000));
        hr.addWorkers(new Worker("Peter", 24, 60000));
        hr.addWorkers(new Worker("Jack", 23, 80000));
        hr.addWorkers(new Freelancer("Mike", 21, 400));

        System.out.println();
        System.out.println("Список до сортировки по зарплате");
        System.out.println(hr.getWorkers());

        System.out.println();
        Collections.sort(hr.getWorkers());
        System.out.println();

        System.out.println("Список после сортировки по зарплате по возрастанию");
        System.out.println(hr.getWorkers());

        System.out.println();
        System.out.println("Перебор рабочих");
        for (BaseWorker worker : hr) {
            System.out.println(worker);
        }
    }
}