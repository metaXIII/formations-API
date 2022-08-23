package com.metaxiii.fr.goodapi.assembler;

import com.metaxiii.fr.goodapi.controller.EmployeeController;
import com.metaxiii.fr.goodapi.entity.Employee;
import com.metaxiii.fr.goodapi.model.EmployeeModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeAssembler implements RepresentationModelAssembler<Employee, EmployeeModel> {
    @Override
    public EmployeeModel toModel(final Employee entity) {
        return EmployeeModel.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .strength(entity.getStrength())
                .weakness(entity.getWeakness())
                .role(entity.getRole().name())
                .salary(entity.getSalary().getAmount() + "$")
                .build()
                .add(linkTo(methodOn(EmployeeController.class).getEmployee(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(EmployeeController.class).allEmployees()).withRel("List of employees"));
    }
}
