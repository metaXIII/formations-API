package com.metaxiii.fr.goodapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

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
}
