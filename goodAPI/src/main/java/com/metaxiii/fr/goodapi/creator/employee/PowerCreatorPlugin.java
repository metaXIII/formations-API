package com.metaxiii.fr.goodapi.creator.employee;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.StrengthPatchDTO;
import com.metaxiii.fr.goodapi.enums.Power;
import com.metaxiii.fr.goodapi.transformer.EmployeePowerTransformer;
import com.metaxiii.fr.goodapi.transformer.EmployeeTransformerPlugin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PowerCreatorPlugin implements EmployeeTransformerPlugin {

  private final EmployeePowerTransformer transformer;

  @Override
  public EmployeeInput toDomain(final EmployeeDTO employeeDTO) {
    return transformer.toDomain((StrengthPatchDTO) employeeDTO);
  }

  @Override
  public boolean supports(final Power power) {
    return Power.STRENGTH == power;
  }
}
