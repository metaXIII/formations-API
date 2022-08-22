package com.metaxiii.fr.badapi.config;

import com.metaxiii.fr.badapi.entity.EmployeeEntity;
import com.metaxiii.fr.badapi.entity.SalaryEntity;
import com.metaxiii.fr.badapi.enums.Role;
import com.metaxiii.fr.badapi.repository.EmployeeRepository;
import com.metaxiii.fr.badapi.repository.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, SalaryRepository salaryRepository) {
        return args -> {
            final SalaryEntity batmanSalary = salaryRepository.save(SalaryEntity.builder()
                    .amount(0x7fffffffffffffffL)
                    .build());
            final SalaryEntity notBatmanSalary = salaryRepository.save(SalaryEntity.builder()
                    .amount(1000000L)
                    .build());
            employeeRepository.save(EmployeeEntity.builder()
                    .firstName("Bruce")
                    .lastName("Wayne")
                    .strength("Better detective in the world, too much money")
                    .weakness("Too much money")
                    .role(Role.ADMIN)
                    .salary(batmanSalary)
                    .build());
            employeeRepository.save(EmployeeEntity.builder()
                    .firstName("Clark")
                    .lastName("Kent")
                    .strength("Physical strenght, can destroy the planet if he want it")
                    .weakness("Green rocks, red rocks, blue rocks, let`s pretend he is scared of all rocks, and batman")
                    .role(Role.EMPLOYEE)
                    .salary(notBatmanSalary)
                    .build());
            employeeRepository.findAll().forEach(employeeEntity -> log.info("Preloaded " + employeeEntity));
            salaryRepository.findAll().forEach(orderEntity -> log.info("Preloaded " + orderEntity));
        };
    }
}
