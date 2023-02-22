package com.training.docshelf.strategy;

import com.training.docshelf.model.Document;
import org.springframework.stereotype.Component;

@Component
public class PdfStrategy implements TransformStrategy{

    @Override
    public String transform(Document newDoc, Document referenceDoc) {
        return Integer.parseInt(newDoc.getLabel()) + Integer.parseInt(referenceDoc.getLabel()) + " signatures";
    }
}
