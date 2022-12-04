package com.metaxiii.fr.goodapi.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


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
