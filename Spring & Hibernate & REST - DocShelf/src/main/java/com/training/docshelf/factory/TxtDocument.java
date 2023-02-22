package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import com.training.docshelf.utility.DocumentType;

public class TxtDocument extends Document {
    public TxtDocument(String name, int size, String label) {
        super(name, size, DocumentType.txt, label);
    }

    @Override
    public String toString() {
        return "{" +
                "\nname='" + name + '\'' +
                "\n, size=" + size +
                "\n, category=" + category +
                "\n, label='" + label + '\'' +
                "\n, documentRelationship=" + documentRelationship +
                '}';
    }
}
