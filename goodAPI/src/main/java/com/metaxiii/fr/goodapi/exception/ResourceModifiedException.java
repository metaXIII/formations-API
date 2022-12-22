package com.metaxiii.fr.goodapi.exception;

import java.time.Instant;

public class ResourceModifiedException extends RuntimeException {

  public ResourceModifiedException(final ErrorCode stepModified, final Instant updateAt) {
    super(String.format(stepModified.getMessage(), updateAt));
  }
}
