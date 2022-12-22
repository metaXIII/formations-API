package com.metaxiii.fr.goodapi.exception;

import java.text.MessageFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeException extends RuntimeException {

  private final transient ErrorCodeDetail details;

  public EmployeeException(final ErrorCodeDetail details, Object... params) {
    super(MessageFormat.format(details.getMessage(), details.getCodeDetails(), params));
    this.details = details;
  }
}
