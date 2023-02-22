package com.training.docshelf.factory;

import com.training.docshelf.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentFactory {
    public Document createDocument(String name, int size, String category, String label){
        return switch(category){
            case "doc" -> new DocDocument(name, size, label);
            case "pdf" -> new PdfDocument(name, size, label);
            case "txt" -> new TxtDocument(name, size, label);
            case "xls" -> new XlsDocument(name, size, label);
            default -> new JSONDocument(name, size, label);
        };
    }
}
