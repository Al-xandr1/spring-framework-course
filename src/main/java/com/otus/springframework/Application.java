package com.otus.springframework;

import com.otus.springframework.enities.Worker;
import com.otus.springframework.repositories.WorkerRepository;
import com.otus.springframework.services.SalaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class
)
@EnableAspectJAutoProxy
public class Application {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

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
            LOG.info("Application has started");

            Worker worker = workerRepository.getById(1L);

            double salary = salaryService.calculateSalary(worker);
            worker.setCurrentSalary(salary);

            double premium = salaryService.calculatePremium(worker);
            LOG.info("Worker {} receive premium {}", worker.getId(), premium);

            LOG.info("Application has finished");
        };
    }
}
