package com.talos.hospital.CustomUtils.Validations;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class EmployeeDateValidator implements ConstraintValidator<EmployeeDateConstraint, LocalDate> {

    private final LocalDate TODAY = LocalDate.now();
    private final LocalDate years_before_today_18 =  TODAY.minusYears(18);


    @Override
    public void initialize(EmployeeDateConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isBefore(years_before_today_18);
    }
}
