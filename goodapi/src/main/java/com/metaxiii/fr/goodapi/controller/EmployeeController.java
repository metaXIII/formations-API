package com.metaxiii.fr.goodapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.metaxiii.fr.goodapi.assembler.EmployeeAssembler;
import com.metaxiii.fr.goodapi.config.EmployeeValidator;
import com.metaxiii.fr.goodapi.creator.EmployeeCreator;
import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.PowerPatchDTO;
import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.enums.Power;
import com.metaxiii.fr.goodapi.exception.DatabindingException;
import com.metaxiii.fr.goodapi.exception.EmployeeException;
import com.metaxiii.fr.goodapi.exception.ResourceModifiedException;
import com.metaxiii.fr.goodapi.model.EmployeeModel;
import com.metaxiii.fr.goodapi.service.EmployeeService;
import com.metaxiii.fr.goodapi.transformer.EmployeeTransformerPlugin;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.metaxiii.fr.goodapi.exception.ErrorCode.EMPLOYEE_NOT_FOUND;
import static com.metaxiii.fr.goodapi.exception.ErrorCode.INVALID_REQUEST;
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
    private final ObjectMapper objectMapper;


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
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
        return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.OK);
    }

    @PostMapping(value = "new-employee", consumes = "application/json")
    @Transactional
    public ResponseEntity<EmployeeModel> newEmployee(@RequestBody @Valid EmployeeInput input) {
        final Employee employee = service.save(creator.toDomain(input));
        return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.CREATED);
    }


    @PatchMapping(value = "update-power/{id}", consumes = "application/json-patch+json")
    @Transactional
    public ResponseEntity<EmployeeModel> updatePower(@PathVariable UUID id,
                                                     @RequestHeader(name = IF_UNMODIFIED_SINCE) Instant
                                                             ifUnmodifiedSince,
                                                     @RequestBody JsonPatch jsonPatch) {
        final EmployeeModel employeeModel = service.findById(id)
                .map(employee -> {
                    if (employee.getUpdateAt() != null && employee.getUpdateAt().isAfter(ifUnmodifiedSince)) {
                        throw new ResourceModifiedException(STEP_MODIFIED, employee.getUpdateAt());
                    }
                    final EmployeeDTO employeeDTO;
                    try {
                        employeeDTO = objectMapper.treeToValue(jsonPatch.apply(objectMapper.convertValue(employee,
                                JsonNode.class)), PowerPatchDTO.class);
                    } catch (JsonPatchException | JsonProcessingException e) {
                        throw new EmployeeException(INVALID_REQUEST);
                    }
                    this.validateDTO(employeeDTO);
                    final EmployeeInput input = transformerRegistry.getPluginFor(Power.STRENGTH,
                                    () -> new EmployeeException(MISSING_PLUGINS))
                            .toDomain(employeeDTO);
                    return service.save(creator.toDomain(input));
                })
                .map(assembler::toModel)
                .orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND, id));
        return new ResponseEntity<>(employeeModel, HttpStatus.OK);
    }
//
//        if (employee.ifUnmodifiedSince.isAfter(originalEmployee.get().getUpdateAt())) {
//
//        }
//        final Employee saved = originalEmployee.map(employee -> {
//            this.validateDTO(employeeDTO);
//            employee.setFirstName(employee.getFirstName());
//            employee.setLastName(employee.getLastName());
//            return service.save(employee);
//        }).orElseThrow(() -> {
//            throw new EmployeeException(EMPLOYEE_NOT_FOUND, id);
//        });
//        return new ResponseEntity<>(assembler.toModel(saved), HttpStatus.ACCEPTED);
//    }
//
//    @PostMapping("update-power/{id}")
//    public ResponseEntity insertNewEmployee(@RequestBody EmployeeDto EmployeeDto, @PathVariable Long id) {
//        return ResponseEntity.ok(service.updateEmployee(EmployeeDto, id));
//    }
//
//    @PostMapping("raise-salary/{id}")
//    public ResponseEntity raiseSalary(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
//        return ResponseEntity.ok(service.raiseEmployee(employeeDto, id));
//    }


    private <T extends EmployeeDTO> void validateDTO(final T employeeDTO) {
        DataBinder dataBinder = new DataBinder(employeeDTO);
        dataBinder.addValidators(validator);
        dataBinder.validate();
        if (dataBinder.getBindingResult().hasErrors())
            throw new DatabindingException(dataBinder.getBindingResult());
    }
}
