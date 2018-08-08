package com.demir.vacuum.picker.entity;

import com.demir.vacuum.picker.control.EmailDomainValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = EmailDomainValidator.class)
public @interface EmailDomain {

    String message() default "invalid domain address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
