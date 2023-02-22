package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import com.training.docshelf.utility.DocumentType;

public class PdfDocument extends Document {

    public PdfDocument(String name, int size, String signatures) {
        super(name, size, DocumentType.pdf, signatures);
    }

    @Override
    public String toString() {
        return "{" +
                "\nname='" + name + '\'' +
                "\n, size=" + size +
                "\n, category=" + category +
                "\n, signatures='" + label + '\'' +
                "\n, documentRelationship=" + documentRelationship +
                '}';
    }
}
