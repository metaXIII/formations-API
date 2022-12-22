package com.metaxiii.fr.goodapi.transformer;

import com.metaxiii.fr.goodapi.dto.input.EmployeeInput;

public abstract class AbstractEmployeeTransformer {

  public EmployeeInput.EmployeeInputBuilder toDomainInput() {
    return EmployeeInput.builder();
  }
}
