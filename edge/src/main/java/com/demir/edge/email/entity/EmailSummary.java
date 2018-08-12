package com.demir.edge.email.entity;

import java.io.Serializable;
import java.util.List;

public class EmailSummary implements Serializable {

    private List<EmailInfo> emailInfos;

    public EmailSummary() {
    }

    public EmailSummary(List<EmailInfo> emailInfos) {
        this.emailInfos = emailInfos;
    }

    public List<EmailInfo> getEmailInfos() {
        return emailInfos;
    }

    public void setEmailInfos(List<EmailInfo> emailInfos) {
        this.emailInfos = emailInfos;
    }
}
