package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import com.training.docshelf.utility.DocumentType;

public class XlsDocument extends Document {
    public XlsDocument(String name, int size, String subject) {
        super(name, size, DocumentType.xls, subject);
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
