package com.demir.edge.email.control;



import com.demir.edge.email.entity.EmailDomain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    private static final String DOMAINS[] = {"comeon.com", "cherry.se"};

    public void initialize(EmailDomain constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String domain = value.substring(value.lastIndexOf("@")).trim().toLowerCase();
        for (String path : DOMAINS) {
            if (domain.equals(path)) {
                return true;
            }
        }
        return false;
    }
}
