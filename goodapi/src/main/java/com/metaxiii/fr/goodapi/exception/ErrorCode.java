package com.metaxiii.fr.goodapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode implements ErrorCodeDetail {
    INVALID_REQUEST(400, "Request is invalid"),
    EMPLOYEE_NOT_FOUND(404, "Employee {0} not found"),
    STEP_MODIFIED(409, "The resource is already updated"),
    MISSING_PLUGINS(500, "Missing Plugin");

    @Getter
    private final int codeDetails;

    @Getter
    private final String message;
}
