package com.metaxiii.fr.goodapi.service;

import com.metaxiii.fr.goodapi.entity.Salary;

import java.util.Optional;
import java.util.UUID;

public interface SalaryService {
    Salary save(Salary salary);

    Optional<Salary> findById(UUID salaryId);

    Salary update(Salary toDomain, Salary salary);
}
