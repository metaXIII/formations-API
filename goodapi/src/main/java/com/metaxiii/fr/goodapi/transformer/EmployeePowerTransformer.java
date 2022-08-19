package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.PowerPatchDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeePowerTransformer extends AbstractEmployeeTransformer<EmployeeInput, PowerPatchDTO>
        implements DTOCreator<EmployeeInput, PowerPatchDTO> {

    @Override
    public EmployeeInput toDomain(final PowerPatchDTO employeeDTO) {
        return super.toDomainInput(employeeDTO)
                .strength(employeeDTO.getStrength())
                .build();
    }
}
