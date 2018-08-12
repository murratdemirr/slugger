package com.demir.edge.dataset.control;


import com.demir.edge.dataset.entity.Dataset;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class DataSetRemote {

    public Dataset get(final String address) throws MalformedURLException {
        Dataset result = null;
        URL url = new URL(address);
        try (InputStream inputStream = url.openStream()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Dataset.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            result = (Dataset) jaxbUnmarshaller.unmarshal(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
