package com.training.docshelf.service;

import org.springframework.stereotype.Component;

@Component
public class DuplicateDocumentException  extends RuntimeException{
    public DuplicateDocumentException() {
        super("[WARNING]: File already exists");
    }
}
