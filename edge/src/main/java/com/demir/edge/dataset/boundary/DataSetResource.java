package com.demir.edge.dataset.boundary;


import com.demir.edge.dataset.control.DataSetManager;
import com.demir.edge.dataset.entity.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("data-sets")
public class DataSetResource {

    @Autowired
    DataSetManager manager;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void post(@RequestBody @NotNull Dataset xml) {
        manager.process(xml);
    }

}
