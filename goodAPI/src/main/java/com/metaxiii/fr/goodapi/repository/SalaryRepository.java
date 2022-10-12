package com.metaxiii.fr.goodapi.repository;

import com.metaxiii.fr.goodapi.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {
}
