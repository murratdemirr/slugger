package com.demir.core;

import com.demir.core.email.control.EmailDomainHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DomainValidationTest {

    @Test
    public void withWrongDomain() {
        assertEquals(false, EmailDomainHelper.isDomainValid("murat.demir@gmail.com"));
    }

    @Test
    public void withTrueDomain() {
        assertEquals(true, EmailDomainHelper.isDomainValid("murat.demir@comeon.com"));
    }

}
