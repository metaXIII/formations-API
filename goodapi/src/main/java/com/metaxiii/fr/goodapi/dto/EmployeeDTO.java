package com.metaxiii.fr.goodapi.dto;

import com.metaxiii.fr.goodapi.enums.Power;

import javax.persistence.Transient;

public abstract class EmployeeDTO {
    @Transient
    public abstract Power getPower();
}
