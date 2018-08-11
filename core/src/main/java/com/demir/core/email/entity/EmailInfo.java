package com.demir.core.email.entity;

public class EmailInfo {

    private String email;
    private Long count;

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
