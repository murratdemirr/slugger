package com.demir.core.email.control;


import com.demir.core.email.entity.EmailDomain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomain, String> {

    private static final String DOMAINS[] = {"comeon.com", "cherry.se"};

    public void initialize(EmailDomain constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        final int index = value.lastIndexOf("@");
        if (index > 1 && index < value.length()) {
            final String domain = value.substring((index + 1)).trim().toLowerCase();
            for (String path : DOMAINS) {
                if (domain.equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }
}
