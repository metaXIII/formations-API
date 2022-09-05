package com.metaxiii.fr.goodapi.service.impl;

import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.repository.EmployeeRepository;
import com.metaxiii.fr.goodapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findById(final UUID id) {
        return repository.findById(id);
    }

    @Override
    public Employee save(final Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updatePower(final Employee toDomain, final Employee employee) {
        final String strength = employee.getStrength() + ", " + toDomain.getStrength();
        employee.setStrength(strength);
        return repository.save(employee);
    }
}
