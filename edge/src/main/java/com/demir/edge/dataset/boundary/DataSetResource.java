package com.demir.edge.dataset.boundary;


import com.demir.edge.dataset.entity.DatasetType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("data-sets")
public class DataSetResource {


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void post(@RequestBody @NotNull DatasetType xml, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(xml);
    }

}
