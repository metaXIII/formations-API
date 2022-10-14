package com.metaxiii.fr.goodapi.dto.power;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrengthPatchDTO extends EmployeeDTO {
    @NotNull
    @NotEmpty
    private String strength;
}