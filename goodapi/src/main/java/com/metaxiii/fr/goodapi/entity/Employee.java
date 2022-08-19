package com.metaxiii.fr.goodapi.entity;

import com.metaxiii.fr.goodapi.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Employee {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "weakness", nullable = false)
    private String weakness;

    @Column(name = "strength", nullable = false)
    private String strength;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.EMPLOYEE;

    @ManyToOne
    @JoinColumn(name = "salary_id", nullable = false)
    private Salary salary;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "update_at", nullable = false)
    private Instant updateAt;
}
