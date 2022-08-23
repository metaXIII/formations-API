package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.dto.power.StrengthPatchDTO;
import com.metaxiii.fr.goodapi.dto.power.WeaknessPatchDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWeaknessTransformer extends AbstractEmployeeTransformer<EmployeeInput, WeaknessPatchDTO>
        implements DTOCreator<EmployeeInput, WeaknessPatchDTO> {

    @Override
    public EmployeeInput toDomain(final WeaknessPatchDTO employeeDTO) {
        return super.toDomainInput(employeeDTO)
                .weakness(employeeDTO.getWeakness())
                .build();
    }
}
