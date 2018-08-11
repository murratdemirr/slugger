package com.demir.core.email.boundary;

import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.EmailSummmary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("emails")
public class EmailResource {

    @Autowired
    EmailRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public EmailSummmary send() {
        return repository.summmaryReport();
    }

}
