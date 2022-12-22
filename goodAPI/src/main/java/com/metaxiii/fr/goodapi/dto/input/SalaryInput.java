package com.metaxiii.fr.goodapi.dto.input;

import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SalaryInput {

  @Positive
  private Long amount;
}
