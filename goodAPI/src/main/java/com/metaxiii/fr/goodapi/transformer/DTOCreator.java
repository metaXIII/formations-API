package com.metaxiii.fr.goodapi.transformer;

public interface DTOCreator<I, D> {
  public I toDomain(final D employeeDTO);
}
