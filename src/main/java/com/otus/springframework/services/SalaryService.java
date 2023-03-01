package com.otus.springframework.services;

import com.otus.springframework.enities.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    @Value("${myapp.premiumTariff:0.2}")
    private double premiumTariff;

    @Value("${myapp.baseTariff:60000}")
    private double salaryTariff;

    public double calculateSalary(Worker worker) {
        var grade = worker.getGrade();

        if (grade <= 5) {
            return salaryTariff;

        } else if (grade <= 10) {
            return Math.round(salaryTariff * 1.5);

        } else {
            return Math.round(salaryTariff * 2);
        }
    }

    public double calculatePremium(Worker worker) {
        var salary = worker.getCurrentSalary();
        return salary * premiumTariff;
    }
}
