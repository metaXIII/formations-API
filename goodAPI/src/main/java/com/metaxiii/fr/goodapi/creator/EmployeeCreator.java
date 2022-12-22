package com.metaxiii.fr.goodapi.creator;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.entity.Salary;
import com.metaxiii.fr.goodapi.enums.Role;
import com.metaxiii.fr.goodapi.service.SalaryService;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmployeeCreator implements DomainCreator<Employee, EmployeeInput> {

  private final SalaryService service;

  @Override
  public Employee toDomain(final EmployeeInput employeeInput) {
    return Employee
      .builder()
      .firstName(employeeInput.getFirstName())
      .lastName(employeeInput.getLastName())
      .weakness(employeeInput.getWeakness())
      .strength(employeeInput.getStrength())
      .role(Role.EMPLOYEE)
      .salary(service.save(Salary.builder().amount(1000000L).createdAt(Instant.now()).updateAt(Instant.now()).build()))
      .createdAt(Instant.now())
      .updateAt(Instant.now())
      .build();
  }
}
