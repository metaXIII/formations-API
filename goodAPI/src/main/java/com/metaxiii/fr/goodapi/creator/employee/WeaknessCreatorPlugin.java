package com.metaxiii.fr.goodapi.creator.employee;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.WeaknessPatchDTO;
import com.metaxiii.fr.goodapi.enums.Power;
import com.metaxiii.fr.goodapi.transformer.EmployeeTransformerPlugin;
import com.metaxiii.fr.goodapi.transformer.EmployeeWeaknessTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeaknessCreatorPlugin implements EmployeeTransformerPlugin {

  private final EmployeeWeaknessTransformer transformer;

  @Override
  public EmployeeInput toDomain(final EmployeeDTO employeeDTO) {
    return transformer.toDomain((WeaknessPatchDTO) employeeDTO);
  }

  @Override
  public boolean supports(final Power power) {
    return Power.WEAKNESS == power;
  }
}
