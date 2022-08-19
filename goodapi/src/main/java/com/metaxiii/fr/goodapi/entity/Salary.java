package com.metaxiii.fr.goodapi.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "salary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Salary {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "update_at", nullable = false)
    private Instant updateAt;
}
