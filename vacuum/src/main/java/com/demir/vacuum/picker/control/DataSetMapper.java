package com.demir.vacuum.picker.control;

import com.demir.vacuum.picker.entity.DataSet;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

/**
 * @author Demir
 *
 * 8/8/18 3:00 PM
 */

@Service
public class DataSetMapper {

    public DataSet parse(String content) throws Exception{
        DataSet entity = null;

        JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        //We had written this file in marshalling example
        entity = (DataSet) jaxbUnmarshaller.unmarshal( new File("/devhome/sample.dataset") );
        return entity;
    }

}
