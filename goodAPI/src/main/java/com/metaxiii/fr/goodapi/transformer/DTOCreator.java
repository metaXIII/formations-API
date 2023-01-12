package com.metaxiii.fr.goodapi.transformer;

interface DTOCreator<I, D> {
  I toDomain(final D employeeDTO);
}
