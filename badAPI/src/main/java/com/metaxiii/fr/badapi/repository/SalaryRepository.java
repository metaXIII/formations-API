package com.metaxiii.fr.badapi.repository;

import com.metaxiii.fr.badapi.entity.SalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<SalaryEntity, Long> {
}
