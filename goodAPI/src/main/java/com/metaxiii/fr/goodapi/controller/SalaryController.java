package com.metaxiii.fr.goodapi.controller;

import com.metaxiii.fr.goodapi.assembler.SalaryAssembler;
import com.metaxiii.fr.goodapi.config.SalaryValidator;
import com.metaxiii.fr.goodapi.creator.SalaryCreator;
import com.metaxiii.fr.goodapi.dto.SalaryDto;
import com.metaxiii.fr.goodapi.dto.input.SalaryInput;
import com.metaxiii.fr.goodapi.enums.Role;
import com.metaxiii.fr.goodapi.exception.DatabindingException;
import com.metaxiii.fr.goodapi.exception.EmployeeException;
import com.metaxiii.fr.goodapi.exception.ResourceModifiedException;
import com.metaxiii.fr.goodapi.model.SalaryModel;
import com.metaxiii.fr.goodapi.service.EmployeeService;
import com.metaxiii.fr.goodapi.service.SalaryService;
import com.metaxiii.fr.goodapi.transformer.SalaryTransformer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

import static com.metaxiii.fr.goodapi.exception.ErrorCode.FORBIDDEN_REQUEST;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.INVALID_REQUEST;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.RESOURCE_NOT_FOUND;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.STEP_MODIFIED;
import static org.springframework.http.HttpHeaders.IF_UNMODIFIED_SINCE;

@RestController
@AllArgsConstructor
public class SalaryController {

    private final SalaryService service;
    private final SalaryCreator creator;
    private final EmployeeService employeeService;
    private final SalaryValidator validator;
    private final SalaryTransformer transformer;
    private final SalaryAssembler assembler;

    @PatchMapping("raise-salary/{employee-id}/{salary-id}")
    public ResponseEntity<SalaryModel> raiseSalary(@PathVariable(name = "employee-id") UUID employeeId,
                                                   @PathVariable(name = "salary-id") UUID salaryId,
                                                   @RequestHeader(name = IF_UNMODIFIED_SINCE) Instant ifUnmodifiedSince,
                                                   @RequestBody SalaryDto salaryDto) {
        final SalaryModel salaryModel = employeeService.findById(employeeId)
                .map(asker -> {
                    if (asker.getRole().equals(Role.EMPLOYEE)) {
                        throw new EmployeeException(FORBIDDEN_REQUEST);
                    }
                    if (asker.getUpdateAt() != null && asker.getUpdateAt().isAfter(ifUnmodifiedSince)) {
                        throw new ResourceModifiedException(STEP_MODIFIED, asker.getUpdateAt());
                    }
                    return service.findById(salaryId).map(salary -> {
                        this.validateDTO(salaryDto);
                        final SalaryInput salaryInput = transformer.toDomain(salaryDto);
                        return service.update(creator.toDomain(salaryInput), salary);
                    }).orElseThrow(() -> (new EmployeeException(RESOURCE_NOT_FOUND, salaryId)));
                })
                .map(assembler::toModel)
                .orElseThrow(() -> new EmployeeException(RESOURCE_NOT_FOUND, employeeId));
        return new ResponseEntity<>(salaryModel, HttpStatus.ACCEPTED);
    }

    private void validateDTO(final SalaryDto salaryDto) {
        DataBinder dataBinder = new DataBinder(salaryDto);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors())
            throw new DatabindingException(dataBinder.getBindingResult());
    }
}
