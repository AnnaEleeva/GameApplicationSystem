package com.gamecapmates.controller;

import com.gamecapmates.Exceptions.AvailabilityException;
import com.gamecapmates.Exceptions.BusinessException;
import com.gamecapmates.Exceptions.NoSuchGameException;
import com.gamecapmates.Exceptions.NoSuchUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerException.class);
    /**
     *          Method which handles {@link BusinessException} and
     *          {@link AvailabilityException} and
     *          {@link NoSuchGameException} and
     *          {@link NoSuchUserException}
     *
     * @return
     *          Error object with information about thrown exception
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> businessExceptionHandler() {
        LOGGER.error("Error occured");
        return new ResponseEntity<>("error occured",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AvailabilityException.class)
    public ResponseEntity<Object> AvailabilityExceptionHandler() {
        LOGGER.error("No such availability");
        return new ResponseEntity<>("No such availability",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<Object> UserExceptionHandler() {
        LOGGER.error("No such user");
        return new ResponseEntity<>("No such user", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchGameException.class)
    public ResponseEntity<Object> GameExceptionHandler() {
        LOGGER.error("No such game");
        return new ResponseEntity<>("No such game",HttpStatus.BAD_REQUEST);
    }
}
