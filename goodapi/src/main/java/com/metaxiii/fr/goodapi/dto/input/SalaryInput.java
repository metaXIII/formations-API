package com.metaxiii.fr.goodapi.dto.input;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class SalaryInput {
    @NotNull
    private Long amount;
}
