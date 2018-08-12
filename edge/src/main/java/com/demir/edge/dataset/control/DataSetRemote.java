package com.demir.edge.dataset.control;


import com.demir.edge.dataset.entity.Dataset;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;


@Service
public class DataSetRemote {

    @Retryable(value = {Exception.class}, maxAttempts = 3,backoff = @Backoff(delay = 5000))
    public Dataset find(String url) throws Exception {
        return  get(url);
    }

    public Dataset get(final String address) throws Exception {
        Dataset result;
        URL url = new URL(address);
        try (InputStream inputStream = url.openStream()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Dataset.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = (Dataset) jaxbUnmarshaller.unmarshal(inputStream);
        }
        return result;
    }

}
