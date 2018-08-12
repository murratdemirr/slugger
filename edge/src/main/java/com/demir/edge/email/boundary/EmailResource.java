package com.demir.edge.email.boundary;

import com.demir.edge.AbstractRestHandler;
import com.demir.edge.email.control.EmailServiceClient;
import com.demir.edge.email.entity.Email;
import com.demir.edge.email.entity.EmailDomain;
import com.demir.edge.email.entity.EmailSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("emails")
public class EmailResource extends AbstractRestHandler {

    @Autowired
    EmailServiceClient client;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Email> all() {
        return client.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Email find(@PathVariable("id") Long id) {
        return client.find(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @NotNull @EmailDomain String email, HttpServletRequest request, HttpServletResponse response) {
        final Long id = client.save(email);
        response.setHeader("Location", request.getRequestURL().append("/").append(id).toString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("id") Long id, @RequestBody @NotNull @EmailDomain String email) {
        client.update(id, email);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        client.delete(id);
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    EmailSummary send() {
        return client.summaryReport();
    }

}
