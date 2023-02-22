package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import com.training.docshelf.utility.DocumentType;

public class DocDocument extends Document {
    public DocDocument(String name, int size, String subject) {
        super(name, size, DocumentType.doc, subject);
    }

    @Override
    public String toString() {
        return "{" +
                "\nname='" + name + '\'' +
                "\n, size=" + size +
                "\n, category=" + category +
                "\n, subject='" + label + '\'' +
                "\n, documentRelationship=" + documentRelationship +
                '}';
    }
}
