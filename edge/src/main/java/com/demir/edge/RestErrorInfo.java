package com.demir.edge;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class RestErrorInfo implements Serializable {

    private String detail;
    private String message;

    public RestErrorInfo() {
    }

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
