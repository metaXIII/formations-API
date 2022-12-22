package com.metaxiii.fr.goodapi.dto.power;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeaknessPatchDTO extends EmployeeDTO {

  @NotNull
  @NotEmpty
  private String weakness;
}
