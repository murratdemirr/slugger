package com.demir.core;

import com.demir.edge.email.control.DomainHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DomainValidationTest {

    @Test
    public void withWrongDomain() {
        assertEquals(false, DomainHelper.isDomainValid("murat.demir@gmail.com"));
    }

    @Test
    public void withTrueDomain() {
        assertEquals(true, DomainHelper.isDomainValid("murat.demir@comeon.com"));
    }

}
