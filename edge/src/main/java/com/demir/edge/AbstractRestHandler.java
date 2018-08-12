package com.demir.edge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

public class AbstractRestHandler implements ApplicationEventPublisherAware {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(NotFoundException ex, WebRequest request, HttpServletResponse response) {
        logger.info("NotFoundException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceBadRequestException(BadRequestException ex, WebRequest request, HttpServletResponse response) {
        logger.info("BadRequestException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Invalid Email Address");
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
