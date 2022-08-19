package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.PowerPatchDTO;
import com.metaxiii.fr.goodapi.enums.Power;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PowerTransformerPlugin implements EmployeeTransformerPlugin {

    private final EmployeePowerTransformer transformer;

    @Override
    public EmployeeInput toDomain(final EmployeeDTO employeeDTO) {
        return transformer.toDomain((PowerPatchDTO) employeeDTO);
    }

    @Override
    public boolean supports(final Power power) {
        return Power.STRENGTH == power;
    }
}
