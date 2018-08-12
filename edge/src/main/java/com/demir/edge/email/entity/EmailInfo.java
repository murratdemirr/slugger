package com.demir.edge.email.entity;

import java.io.Serializable;

public class EmailInfo implements Serializable {

    private String email;
    private Long count;

    public EmailInfo() {
    }

    public EmailInfo(String email, Long count) {
        this.email = email;
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
