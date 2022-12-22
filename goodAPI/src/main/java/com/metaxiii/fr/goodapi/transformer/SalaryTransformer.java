package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.SalaryDto;
import com.metaxiii.fr.goodapi.dto.input.SalaryInput;
import org.springframework.stereotype.Component;

@Component
public class SalaryTransformer implements DTOCreator<SalaryInput, SalaryDto> {

  @Override
  public SalaryInput toDomain(final SalaryDto salaryDto) {
    return SalaryInput.builder().amount(salaryDto.getAmount()).build();
  }
}
