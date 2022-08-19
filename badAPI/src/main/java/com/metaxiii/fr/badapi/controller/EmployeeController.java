package com.metaxiii.fr.badapi.controller;

import com.metaxiii.fr.badapi.dto.EmployeeDto;
import com.metaxiii.fr.badapi.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping("/get-all-employees")
    public List<EmployeeDto> allEmployees() {
        return service.findAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("insert-new-employee")
    public ResponseEntity insertNewEmployee(@RequestBody EmployeeDto EmployeeDto) {
        service.createNewEmployee(EmployeeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("update-power/{id}")
    public ResponseEntity insertNewEmployee(@RequestBody EmployeeDto EmployeeDto, @PathVariable Long id) {
        return ResponseEntity.ok(service.updateEmployee(EmployeeDto, id));
    }

    @PostMapping("raise-salary/{id}")
    public ResponseEntity raiseSalary(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
        return ResponseEntity.ok(service.raiseEmployee(employeeDto, id));
    }
}
