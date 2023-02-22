package com.training.docshelf.strategy;

import com.training.docshelf.model.Document;

public interface TransformStrategy {
    String transform(Document newDoc, Document referenceDoc);
}
