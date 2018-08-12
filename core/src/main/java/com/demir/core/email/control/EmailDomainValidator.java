package com.demir.core.email.control;


import com.demir.core.email.entity.EmailDomain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    private static final String DOMAINS[] = {"comeon.com", "cherry.se"};

    public void initialize(EmailDomain constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        return EmailDomainHelper.isDomainValid(value);
    }
}
