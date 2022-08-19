package com.metaxiii.fr.goodapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeDetail {
    EMPLOYEE_NOT_FOUND(404, "Employee {0} not found"),
    STEP_MODIFIED(409, "The resource is already updated"),
    MISSING_PLUGINS(500, "Missing Plugin");

    @Getter
    private final int codeDetails;

    @Getter
    private final String message;
}
