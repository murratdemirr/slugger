package com.demir.vacuum.picker.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Demir
 *
 * 8/8/18 2:39 PM
 */

@XmlRootElement(name = "dataset")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataSet {

    @XmlElement(name = "emails")
    private List<Email> emails;
    @XmlElement(name = "resources")
    private List<Resource> resources;

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
