package com.demir.vacuum.picker.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Demir
 *
 * 8/8/18 2:41 PM
 */

@XmlRootElement(name = "url")
@XmlAccessorType(XmlAccessType.FIELD)
public class Resource {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
