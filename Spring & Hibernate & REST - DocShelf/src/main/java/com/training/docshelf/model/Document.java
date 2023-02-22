package com.training.docshelf.model;

import com.training.docshelf.utility.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class Document {
    protected String name;
    protected int size;
    protected DocumentType category;
    protected String label;
    protected List<Relationship> documentRelationship;

    public Document (String name, int size, DocumentType category, String label){
        this.name = name;
        this.size = size;
        this.category = category;
        this.label = label;
        documentRelationship = new LinkedList<>();
    }

    public void addRelationship(Relationship document){
        this.documentRelationship.add(document);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return size == document.size
                && Objects.equals(name, document.name)
                && category == document.category
                && Objects.equals(label, document.label)
                && Objects.equals(documentRelationship, document.documentRelationship);
    }
}
