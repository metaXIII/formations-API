package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;

public abstract class AbstractEmployeeTransformer<A extends EmployeeInput, B extends EmployeeDTO> {
    public EmployeeInput.EmployeeInputBuilder toDomainInput(final B b) {
        return EmployeeInput.builder();
    }
}
