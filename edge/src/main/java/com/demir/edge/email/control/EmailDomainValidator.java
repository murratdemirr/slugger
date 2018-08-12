package com.demir.edge.email.control;



import com.demir.edge.email.entity.EmailDomain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    public void initialize(EmailDomain constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return DomainHelper.isDomainValid(value);
    }
}
