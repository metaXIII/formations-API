package com.metaxiii.fr.goodapi.creator;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.entity.Salary;
import com.metaxiii.fr.goodapi.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class EmployeeCreator implements DomainCreator<Employee, EmployeeInput> {
    @Override
    public Employee toDomain(final EmployeeInput employeeInput) {
        return Employee.builder()
                .firstName(employeeInput.getFirstName())
                .lastName(employeeInput.getLastName())
                .weakness(employeeInput.getWeakness())
                .strength(employeeInput.getStrength())
                .role(Role.EMPLOYEE)
                .salary(Salary.builder()
                        .amount(1000000L)
                        .build())
                .createdAt(Instant.now())
                .updateAt(Instant.now())
                .build();
    }
}
