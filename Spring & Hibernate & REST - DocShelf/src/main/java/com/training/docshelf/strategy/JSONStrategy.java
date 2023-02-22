package com.training.docshelf.strategy;

import com.training.docshelf.model.Document;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JSONStrategy implements TransformStrategy{
    @Override
    public String transform(Document newDoc, Document referenceDoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return "Register time: " + formatter.format(date);
    }
}
