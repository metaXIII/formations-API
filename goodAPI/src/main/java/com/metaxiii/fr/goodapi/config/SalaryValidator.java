package com.metaxiii.fr.goodapi.config;

import com.metaxiii.fr.goodapi.dto.SalaryDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SalaryValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return SalaryDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "amount", "amount.empty");
        if (((SalaryDto) target).getAmount() < 0) {
            errors.rejectValue("amount", "amount cannot be negative");
        }
    }
}
