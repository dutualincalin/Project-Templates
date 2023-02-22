package com.training.docshelf.controller;

import com.training.docshelf.model.Document;
import com.training.docshelf.service.DocumentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DocumentController {
    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @GetMapping("/docFinder")
    Optional<Document> findByName(String name){
        return service.findByName(name);
    }

    /**
     * on save, depending on category, apply a specific transformation for the relationship description field
     * e.g. description = "Document index " + the index under the collection
     */

    @PostMapping("/docAdder")
    void saveDocument(String name, int size, String category, String label){
        service.saveDocument(name,size, category, label);
    }

    @DeleteMapping("/docRemover")
    void deleteDocument(String name){
        service.deleteDocument(name);
    }
}
