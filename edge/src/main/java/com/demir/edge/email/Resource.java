package com.demir.edge.email;

import com.demir.edge.kafka.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Demir
 *
 * 8/10/18 2:02 PM
 */

@CrossOrigin
@RestController
@RequestMapping("messages")
public class Resource {

    @Autowired
    Sender sender;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void send() {
        sender.send(UUID.randomUUID().toString());
    }

}