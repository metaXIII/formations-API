package com.metaxiii.fr.goodapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel extends RepresentationModel<EmployeeModel> {

  private UUID id;

  private String firstName;

  private String lastName;

  private String weakness;

  private String strength;

  private String role;

  private SalaryModel salary;

  @JsonProperty(value = "created_at")
  private Instant createdAt;

  @JsonProperty(value = "updated_at")
  private Instant updatedAt;
}
