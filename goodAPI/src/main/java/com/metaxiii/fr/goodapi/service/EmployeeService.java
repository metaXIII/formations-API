package com.metaxiii.fr.goodapi.service;

import com.metaxiii.fr.goodapi.entity.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(UUID id);

    Employee save(Employee employee);

    Employee updatePower(Employee toDomain, Employee employee);
}
