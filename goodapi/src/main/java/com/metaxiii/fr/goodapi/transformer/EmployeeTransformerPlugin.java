package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;
import com.metaxiii.fr.goodapi.enums.Power;
import org.springframework.plugin.core.Plugin;

public interface EmployeeTransformerPlugin extends Plugin<Power> {
    EmployeeInput toDomain(EmployeeDTO employeeDTO);
}
