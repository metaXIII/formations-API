package com.metaxiii.fr.goodapi.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class SalaryInput {
    @NotNull
    private Long amount;
}
