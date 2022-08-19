package com.metaxiii.fr.badapi.service;

import com.metaxiii.fr.badapi.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAllEmployees();

    EmployeeDto findById(Long id);

    void createNewEmployee(EmployeeDto EmployeeDto);

    EmployeeDto raiseEmployee(EmployeeDto employeeDto, Long id);

    EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
}
