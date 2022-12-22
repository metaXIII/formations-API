package com.metaxiii.fr.badapi.dto;

import com.metaxiii.fr.badapi.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String weakness;

  private String strength;

  private Role role;

  private Long salary;
}
