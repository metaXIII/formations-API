package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.StrengthPatchDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeePowerTransformer
  extends AbstractEmployeeTransformer
  implements DTOCreator<EmployeeInput, StrengthPatchDTO> {

  @Override
  public EmployeeInput toDomain(final StrengthPatchDTO employeeDTO) {
    return toDomainInput().strength(employeeDTO.getStrength()).build();
  }
}
