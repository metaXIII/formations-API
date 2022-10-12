package com.metaxiii.fr.goodapi.assembler;

import com.metaxiii.fr.goodapi.entity.Salary;
import com.metaxiii.fr.goodapi.model.SalaryModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SalaryAssembler implements RepresentationModelAssembler<Salary, SalaryModel> {
    @Override
    public SalaryModel toModel(final Salary salary) {
        return SalaryModel.builder()
                .id(salary.getId())
                .amount(salary.getAmount())
                .createdAt(salary.getCreatedAt())
                .updatedAt(salary.getUpdateAt())
                .build();
    }
}
