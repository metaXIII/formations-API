package com.metaxiii.fr.goodapi.service.impl;

import com.metaxiii.fr.goodapi.entity.Salary;
import com.metaxiii.fr.goodapi.repository.SalaryRepository;
import com.metaxiii.fr.goodapi.service.SalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository repository;

    @Override
    public Salary save(final Salary salary) {
        return repository.save(salary);
    }

    @Override
    public Optional<Salary> findById(final UUID salaryId) {
        return repository.findById(salaryId);
    }

    @Override
    public Salary update(final Salary domain, final Salary salary) {
        salary.setAmount(domain.getAmount());
        salary.setUpdateAt(Instant.now());
        return repository.save(salary);
    }
}
