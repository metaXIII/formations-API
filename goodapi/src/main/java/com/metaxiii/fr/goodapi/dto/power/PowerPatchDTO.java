package com.metaxiii.fr.goodapi.dto.power;

import com.metaxiii.fr.goodapi.dto.EmployeeDTO;
import com.metaxiii.fr.goodapi.enums.Power;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PowerPatchDTO extends EmployeeDTO {
    @NotNull
    @NotEmpty
    private String strength;

    @Override
    public Power getPower() {
        return Power.STRENGTH;
    }
}
