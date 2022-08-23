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

    //    @Override
//    public void createNewEmployee(final EmployeeDto EmployeeDto) {
//        final SalaryEntity salarySaved = salaryRepository.save(SalaryEntity.builder()
//                .amount(1000000L)
//                .build());
//        Employee employee = mapper.toEntity(EmployeeDto);
//        employee.setRole(Role.EMPLOYEE);
//        employee.setSalary(salarySaved);
//        repository.save(employee);
//    }
//
//    @Override
//    public EmployeeDto raiseEmployee(final EmployeeDto employeeDto, final Long concernedEmployee) {
//        final Employee asker = repository.findById(employeeDto.getId()).get();
//        if (asker.getRole().equals(Role.ADMIN)) {
//            final Employee raisedEmployee = repository.findById(concernedEmployee).get();
//            final SalaryEntity newSalary = SalaryEntity.builder()
//                    .id(concernedEmployee)
//                    .amount(employeeDto.getSalary()).build();
//            salaryRepository.save(newSalary);
//            raisedEmployee.setSalary(newSalary);
//            return mapper.toDto(raisedEmployee);
//        }
//        return mapper.toDto(repository.save(asker));
//    }
//
//    @Override
//    public EmployeeDto updateEmployee(final EmployeeDto employeeDto, final Long id) {
//        final Employee e1 = mapper.toEntity(employeeDto);
//        final Employee e2 = repository.findById(id).get();
//        Employee employee = mapper.mapped(e1, e2);
//        return mapper.toDto(repository.save(employee));
//    }
}
