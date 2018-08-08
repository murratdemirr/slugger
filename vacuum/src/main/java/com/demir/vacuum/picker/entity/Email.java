package com.demir.vacuum.picker.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Demir
 *
 * 8/8/18 2:41 PM
 */

@XmlRootElement(name = "email")
@XmlAccessorType(XmlAccessType.FIELD)
public class Email {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
