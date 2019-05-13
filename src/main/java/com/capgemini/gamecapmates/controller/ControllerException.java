package com.capgemini.gamecapmates.controller;

import com.capgemini.gamecapmates.Exceptions.AvailabilityException;
import com.capgemini.gamecapmates.Exceptions.BusinessException;
import com.capgemini.gamecapmates.Exceptions.NoSuchGameException;
import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerException {
    //zamiast try/catch w controllerze

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerException.class);

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler(Exception ex) {
        LOGGER.error("Error occured", ex);
        return new Error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AvailabilityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler1(Exception ex) {
        LOGGER.error("No such availability", ex);
        return new Error(ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(NoSuchUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler2(Exception ex) {
        LOGGER.error("No such user", ex);
        return new Error(ex.getMessage());
    }
    @ResponseBody
    @ExceptionHandler(NoSuchGameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error businessExceptionHandler3(Exception ex) {
        LOGGER.error("No such game", ex);
        return new Error(ex.getMessage());
    }
}
