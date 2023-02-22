package com.training.docshelf.service;

import org.springframework.stereotype.Component;

@Component
public class MissingDocumentException extends RuntimeException{
    public MissingDocumentException(){
        super("[WARNING]: File doesn't exist");
    }
}
