package com.metaxiii.fr.goodapi.creator;

import com.metaxiii.fr.goodapi.dto.input.SalaryInput;
import com.metaxiii.fr.goodapi.entity.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SalaryCreator implements DomainCreator<Salary, SalaryInput> {

  @Override
  public Salary toDomain(final SalaryInput salaryInput) {
    return Salary.builder().amount(salaryInput.getAmount()).build();
  }
}
