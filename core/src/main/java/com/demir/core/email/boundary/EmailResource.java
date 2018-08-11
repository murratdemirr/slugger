package com.demir.core.email.boundary;

import com.demir.core.AbstractRestHandler;
import com.demir.core.email.control.EmailRepository;
import com.demir.core.email.entity.Email;
import com.demir.core.email.entity.EmailSummmary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("emails")
public class EmailResource extends AbstractRestHandler {

    @Autowired
    EmailRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Email> all() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Email find(@PathVariable("id") Long id) {
        return repository.find(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody String email, HttpServletRequest request, HttpServletResponse response) {
        Email entity = repository.save(email);
        response.setHeader("Location", request.getRequestURL().append("/").append(entity.getId()).toString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable("id") Long id, @RequestBody String email) {
        repository.update(id, email);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        repository.delete(id);
    }

    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public EmailSummmary send() {
        return repository.summmaryReport();
    }

}
