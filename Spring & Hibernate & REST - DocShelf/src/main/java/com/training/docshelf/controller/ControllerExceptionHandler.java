package com.training.docshelf.controller;

import com.training.docshelf.service.DuplicateDocumentException;
import com.training.docshelf.service.MissingDocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DuplicateDocumentException.class)
    public final ResponseEntity<Object> handleDuplicateDocumentException(Exception exception, WebRequest request){
        String error = "[SERVER]: Document already exists";
        log.warn(error, exception);
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MissingDocumentException.class)
    public final ResponseEntity<Object> handleMissingDocumentException(Exception exception, WebRequest request){
        String error = "[SERVER]: Document doesn't exist";
        log.warn(error, exception);
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
