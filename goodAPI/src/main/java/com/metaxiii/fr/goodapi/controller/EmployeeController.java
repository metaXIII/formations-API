package com.metaxiii.fr.goodapi.controller;

import com.metaxiii.fr.goodapi.assembler.EmployeeAssembler;
import com.metaxiii.fr.goodapi.config.EmployeeValidator;
import com.metaxiii.fr.goodapi.creator.EmployeeCreator;
import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.StrengthPatchDTO;
import com.metaxiii.fr.goodapi.dto.power.WeaknessPatchDTO;
import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.enums.Power;
import com.metaxiii.fr.goodapi.exception.DatabindingException;
import com.metaxiii.fr.goodapi.exception.EmployeeException;
import com.metaxiii.fr.goodapi.exception.ResourceModifiedException;
import com.metaxiii.fr.goodapi.model.EmployeeModel;
import com.metaxiii.fr.goodapi.service.EmployeeService;
import com.metaxiii.fr.goodapi.transformer.EmployeeTransformerPlugin;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.metaxiii.fr.goodapi.exception.ErrorCode.RESOURCE_NOT_FOUND;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.MISSING_PLUGINS;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.STEP_MODIFIED;
import static org.springframework.http.HttpHeaders.IF_UNMODIFIED_SINCE;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeAssembler assembler;
    private final EmployeeService service;
    private final EmployeeValidator validator;
    private final PluginRegistry<EmployeeTransformerPlugin, Power> transformerRegistry;
    private final EmployeeCreator creator;


    @GetMapping("/employees")
    @Transactional
    public ResponseEntity<CollectionModel<EmployeeModel>> allEmployees() {
        final List<Employee> employees = service.findAll();
        return new ResponseEntity<>(assembler.toCollectionModel(employees), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    @Transactional
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable UUID id) {
        final Employee employee = service.findById(id)
                .orElseThrow(() -> new EmployeeException(RESOURCE_NOT_FOUND, id));
        return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.OK);
    }

    @PostMapping(value = "new-employee", consumes = "application/json")
    @Transactional
    public ResponseEntity<EmployeeModel> newEmployee(@RequestBody @Valid EmployeeInput input) {
        final Employee employee = service.save(creator.toDomain(input));
        return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.CREATED);
    }


    @PatchMapping(value = "update-strength/{id}", consumes = "application/json-patch+json")
    @Transactional
    public ResponseEntity<EmployeeModel> updateStrength(@PathVariable UUID id,
                                                        @RequestHeader(name = IF_UNMODIFIED_SINCE) Instant
                                                                ifUnmodifiedSince,
                                                        @RequestBody @Valid StrengthPatchDTO patchDTO) {
        final EmployeeModel employeeModel = service.findById(id)
                .map(employee -> {
                    if (employee.getUpdateAt() != null && employee.getUpdateAt().isAfter(ifUnmodifiedSince)) {
                        throw new ResourceModifiedException(STEP_MODIFIED, employee.getUpdateAt());
                    }
                    this.validateDTO(patchDTO);
                    final EmployeeInput input = transformerRegistry.getPluginFor(Power.STRENGTH,
                                    () -> new EmployeeException(MISSING_PLUGINS))
                            .toDomain(patchDTO);
                    return service.updatePower(creator.toDomain(input), employee);
                })
                .map(assembler::toModel)
                .orElseThrow(() -> new EmployeeException(RESOURCE_NOT_FOUND, id));
        return new ResponseEntity<>(employeeModel, HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "update-weakness/{id}", consumes = "application/json-patch+json")
    @Transactional
    public ResponseEntity<EmployeeModel> updateWeakness(@PathVariable UUID id,
                                                        @RequestHeader(name = IF_UNMODIFIED_SINCE) Instant
                                                                ifUnmodifiedSince,
                                                        @RequestBody @Valid WeaknessPatchDTO patchDTO) {
        final EmployeeModel employeeModel = service.findById(id)
                .map(employee -> {
                    if (employee.getUpdateAt() != null && employee.getUpdateAt().isAfter(ifUnmodifiedSince)) {
                        throw new ResourceModifiedException(STEP_MODIFIED, employee.getUpdateAt());
                    }
                    this.validateDTO(patchDTO);
                    final EmployeeInput input = transformerRegistry.getPluginFor(Power.WEAKNESS,
                                    () -> new EmployeeException(MISSING_PLUGINS))
                            .toDomain(patchDTO);
                    return service.updatePower(creator.toDomain(input), employee);
                })
                .map(assembler::toModel)
                .orElseThrow(() -> new EmployeeException(RESOURCE_NOT_FOUND, id));
        return new ResponseEntity<>(employeeModel, HttpStatus.ACCEPTED);
    }


    private <T extends EmployeeDTO> void validateDTO(final T employeeDTO) {
        DataBinder dataBinder = new DataBinder(employeeDTO);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors())
            throw new DatabindingException(dataBinder.getBindingResult());
    }
}
