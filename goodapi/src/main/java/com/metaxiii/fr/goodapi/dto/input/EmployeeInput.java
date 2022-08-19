package com.metaxiii.fr.goodapi.dto.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class EmployeeInput {
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    @Setter
    private String weakness;
    @NotNull
    @NotEmpty
    @Setter
    private String strength;
}
