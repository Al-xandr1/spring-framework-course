package com.otus.springframework;

import com.otus.springframework.enities.Worker;
import com.otus.springframework.repositories.WorkerRepository;
import com.otus.springframework.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class
)
public class Application {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private SalaryService salaryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Application has started");

            Worker worker = workerRepository.getById(1L);

            double salary = salaryService.calculateSalary(worker);
            worker.setCurrentSalary(salary);

            double premium = salaryService.calculatePremium(worker);
            System.out.println(String.format("Worker %s receive premium %s", worker.getId(), premium));

            System.out.println("Application has finished");
        };
    }
}
