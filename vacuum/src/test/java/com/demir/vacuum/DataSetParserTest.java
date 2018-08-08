package com.demir.vacuum;

import com.demir.vacuum.picker.control.DataSetMapper;
import com.demir.vacuum.picker.entity.DataSet;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Demir
 *
 * 8/8/18 3:03 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSetParserTest {


    @Inject
    DataSetMapper dataSetMapper;

    @Test
    public void parse() throws Exception{
        DataSet parse = dataSetMapper.parse(null);
        Assert.assertNotNull(parse);
    }

}
