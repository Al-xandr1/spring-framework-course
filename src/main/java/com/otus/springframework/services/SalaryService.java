package com.otus.springframework.services;

import com.otus.springframework.enities.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {

    private static Logger LOG = LoggerFactory.getLogger(SalaryService.class);

    @Value("${myapp.premiumTariff:0.2}")
    private double premiumTariff;

    @Value("${myapp.baseTariff:60000}")
    private double salaryTariff;

    public double calculateSalary(Worker worker) {
        LOG.info("Method com.otus.springframework.services.SalaryService.calculateSalary was called with parameters {}", worker);
        var grade = worker.getGrade();

        double sal;
        if (grade <= 5) {
            sal = salaryTariff;

        } else if (grade <= 10) {
            sal = salaryTariff * 1.5;

        } else {
            sal = salaryTariff * 2;
        }

        LOG.info("Method com.otus.springframework.services.SalaryService.calculateSalary has returned result {}", sal);
        return sal;
    }

    public double calculatePremium(Worker worker) {
        LOG.info("Method com.otus.springframework.services.SalaryService.calculatePremium was called with parameters {}", worker);
        var salary = worker.getCurrentSalary();
        double premium = salary * premiumTariff;
        LOG.info("Method com.otus.springframework.services.SalaryService.calculatePremium has returned result {}", premium);
        return premium;
    }
}
