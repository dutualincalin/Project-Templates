package com.training.docshelf.strategy;

import com.training.docshelf.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DefaultStrategy implements TransformStrategy{
    @Override
    public String transform(Document newDoc, Document referenceDoc) {
        return "Total size: " + (newDoc.getSize() + referenceDoc.getSize());
    }
}
