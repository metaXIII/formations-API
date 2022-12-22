package com.metaxiii.fr.badapi.service.impl;

import com.metaxiii.fr.badapi.dto.EmployeeDto;
import com.metaxiii.fr.badapi.entity.EmployeeEntity;
import com.metaxiii.fr.badapi.entity.SalaryEntity;
import com.metaxiii.fr.badapi.enums.Role;
import com.metaxiii.fr.badapi.mapper.EmployeeMapper;
import com.metaxiii.fr.badapi.repository.EmployeeRepository;
import com.metaxiii.fr.badapi.repository.SalaryRepository;
import com.metaxiii.fr.badapi.service.EmployeeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  private final SalaryRepository salaryRepository;

  private final EmployeeMapper mapper;

  @Override
  public List<EmployeeDto> findAllEmployees() {
    return mapper.toDto(repository.findAll());
  }

  @Override
  public EmployeeDto findById(final Long id) {
    return mapper.toDto(repository.findById(id).get());
  }

  @Override
  public void createNewEmployee(final EmployeeDto EmployeeDto) {
    final SalaryEntity salarySaved = salaryRepository.save(SalaryEntity.builder().amount(1000000L).build());
    EmployeeEntity employee = mapper.toEntity(EmployeeDto);
    employee.setRole(Role.EMPLOYEE);
    employee.setSalary(salarySaved);
    repository.save(employee);
  }

  @Override
  public EmployeeDto raiseEmployee(final EmployeeDto employeeDto, final Long concernedEmployee) {
    final EmployeeEntity asker = repository.findById(employeeDto.getId()).get();
    if (asker.getRole().equals(Role.ADMIN)) {
      final EmployeeEntity raisedEmployee = repository.findById(concernedEmployee).get();
      final SalaryEntity newSalary = SalaryEntity
        .builder()
        .id(concernedEmployee)
        .amount(employeeDto.getSalary())
        .build();
      salaryRepository.save(newSalary);
      raisedEmployee.setSalary(newSalary);
      return mapper.toDto(raisedEmployee);
    }
    return mapper.toDto(repository.save(asker));
  }

  @Override
  public EmployeeDto updateEmployee(final EmployeeDto employeeDto, final Long id) {
    final EmployeeEntity e1 = mapper.toEntity(employeeDto);
    final EmployeeEntity e2 = repository.findById(id).get();
    EmployeeEntity employee = mapper.mapped(e1, e2);
    return mapper.toDto(repository.save(employee));
  }
}
