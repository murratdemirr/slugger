package com.demir.core;

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

public class AbstractRestHandler implements ApplicationEventPublisherAware {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected ApplicationEventPublisher eventPublisher;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public
    @ResponseBody
    RestErrorInfo handleResourceNotFoundException(EntityNotFoundException ex, WebRequest request, HttpServletResponse response) {
        logger.info("EntityNotFoundException handler:" + ex.getMessage());
        return new RestErrorInfo(ex, "Sorry I couldn't find it.");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
