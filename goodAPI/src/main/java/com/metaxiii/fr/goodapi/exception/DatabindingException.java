package com.metaxiii.fr.goodapi.exception;

import org.springframework.validation.BindingResult;

public class DatabindingException extends RuntimeException {

  public DatabindingException(final BindingResult details) {
    super(details.toString());
  }
}
