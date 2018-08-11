package com.demir.core;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class RestErrorInfo implements Serializable {

    private final String detail;
    private final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public String getMessage() {
        return message;
    }
}
