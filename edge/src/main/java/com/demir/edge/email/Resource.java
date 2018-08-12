package com.demir.edge.email;

import com.demir.edge.kafka.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    QueueSender sender;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void send() {
        sender.send(UUID.randomUUID().toString());
    }

}
