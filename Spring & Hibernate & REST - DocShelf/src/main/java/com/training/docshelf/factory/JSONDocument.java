package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import com.training.docshelf.utility.DocumentType;

public class JSONDocument extends Document {
    public JSONDocument(String name, int size, String label) {
        super(name, size, DocumentType.json, label);
    }

    @Override
    public String toString() {
        return "{" +
                "\nname='" + name + '\'' +
                ",\n size=" + size +
                ",\n category='" + category + '\'' +
                ",\n label='" + label + '\'' +
                ",\n documentRelationship=" + documentRelationship +
                '}';
    }
}
