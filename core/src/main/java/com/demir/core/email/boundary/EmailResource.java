package com.demir.core.email.boundary;

import com.demir.core.AbstractRestHandler;
import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.Email;
import com.demir.core.email.entity.EmailDomain;
import com.demir.core.email.entity.EmailSummmary;
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
    EmailRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Email> all() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Email find(@PathVariable("id") Long id) {
        return repository.find(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @NotNull @EmailDomain String email, HttpServletRequest request, HttpServletResponse response) {
        Email entity = repository.save(email);
        response.setHeader("Location", request.getRequestURL().append("/").append(entity.getId()).toString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody @NotNull @EmailDomain String email) {
        repository.update(id, email);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        repository.delete(id);
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    EmailSummmary send() {
        return repository.summaryReport();
    }

    @RequestMapping(value = "/count/{email}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Long send(@PathVariable("email") String email) {
        return repository.countByEmail(email);
    }

}
