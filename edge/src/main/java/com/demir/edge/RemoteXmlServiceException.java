package com.demir.edge;

public class RemoteXmlServiceException extends RuntimeException {
    private String url;

    public RemoteXmlServiceException(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
