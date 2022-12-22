package com.metaxiii.fr.goodapi.service.impl;

import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.repository.EmployeeRepository;
import com.metaxiii.fr.goodapi.service.EmployeeService;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    employee.setUpdateAt(Instant.now());
    return repository.save(employee);
  }

  @Override
  public Employee updateWeakness(final Employee toDomain, final Employee employee) {
    final String weakness = employee.getWeakness() + ", " + toDomain.getWeakness();
    employee.setStrength(weakness);
    employee.setUpdateAt(Instant.now());
    return repository.save(employee);
  }
}
