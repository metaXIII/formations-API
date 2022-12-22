package com.metaxiii.fr.goodapi.config;

import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.entity.Salary;
import com.metaxiii.fr.goodapi.enums.Role;
import com.metaxiii.fr.goodapi.repository.EmployeeRepository;
import com.metaxiii.fr.goodapi.repository.SalaryRepository;
import java.time.Instant;
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
      final Salary batmanSalary = salaryRepository.save(
        Salary.builder().amount(0x7fffffffffffffffL).createdAt(Instant.now()).updateAt(Instant.now()).build()
      );
      final Salary notBatmanSalary = salaryRepository.save(
        Salary.builder().amount(1000000L).createdAt(Instant.now()).updateAt(Instant.now()).build()
      );
      employeeRepository.save(
        Employee
          .builder()
          .firstName("Bruce")
          .lastName("Wayne")
          .strength("Better detective in the world, too much money")
          .weakness("Too much money")
          .role(Role.ADMIN)
          .salary(batmanSalary)
          .createdAt(Instant.now())
          .updateAt(Instant.now())
          .build()
      );
      employeeRepository.save(
        Employee
          .builder()
          .firstName("Clark")
          .lastName("Kent")
          .strength("Physical strenght, can destroy the planet if he want it")
          .weakness("Green rocks, red rocks, blue rocks, let`s pretend he is scared of all rocks, and batman")
          .role(Role.EMPLOYEE)
          .salary(notBatmanSalary)
          .createdAt(Instant.now())
          .updateAt(Instant.now())
          .build()
      );
      employeeRepository.findAll().forEach(employeeEntity -> log.info("Preloaded " + employeeEntity));
      salaryRepository.findAll().forEach(orderEntity -> log.info("Preloaded " + orderEntity));
    };
  }
}
