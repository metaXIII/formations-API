package com.metaxiii.fr.badapi.mapper;

import com.metaxiii.fr.badapi.dto.EmployeeDto;
import com.metaxiii.fr.badapi.entity.EmployeeEntity;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
  @Named(value = "conversion")
  @Mapping(source = "salary.amount", target = "salary")
  EmployeeDto toDto(EmployeeEntity employee);

  @IterableMapping(qualifiedByName = "conversion")
  List<EmployeeDto> toDto(List<EmployeeEntity> employeeEntities);

  @Mapping(source = "salary", target = "salary.amount")
  EmployeeEntity toEntity(EmployeeDto EmployeeDto);

  @Mapping(source = "e2.id", target = "id")
  @Mapping(source = "e2.firstName", target = "firstName")
  @Mapping(source = "e2.lastName", target = "lastName")
  @Mapping(source = "e2.weakness", target = "weakness")
  @Mapping(source = "e1.strength", target = "strength")
  @Mapping(source = "e1.role", target = "role", defaultValue = "EMPLOYEE")
  @Mapping(source = "e2.salary", target = "salary")
  EmployeeEntity mapped(EmployeeEntity e1, EmployeeEntity e2);
}
