package com.metaxiii.fr.goodapi.repository;

import com.metaxiii.fr.goodapi.entity.Salary;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {}
