package com.training.docshelf;

import com.training.docshelf.factory.*;
import com.training.docshelf.model.Document;
import com.training.docshelf.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class ServiceTests {
    @Autowired
    private DocumentService service;

    @Test
    void checkDocumentInsert(){
        // given
        Document jsonDoc = new JSONDocument("jsonInsertTest", 10, "Json Document");

        // when
        service.saveDocument(jsonDoc.getName(), jsonDoc.getSize(), jsonDoc.getCategory().toString(), jsonDoc.getLabel());

        // verify
        Assertions.assertEquals(Optional.of(jsonDoc), service.findByName("jsonInsertTest"));
        Assertions.assertEquals(Optional.empty(), service.findByName("The other Json"));
    }

    @Test
    void checkMultipleDocumentInsert(){
        // given
        Document docDoc = new DocDocument("docInsertTest", 50, "CI/CD");
        Document xlsDoc = new XlsDocument("xlsInsertTest", 100, "Interns");
        Document txtDoc = new TxtDocument("txtInsertTest", 200, "Whatever");

        // when
        service.saveDocument(xlsDoc.getName(), xlsDoc.getSize(), xlsDoc.getCategory().toString(), xlsDoc.getLabel());
        service.saveDocument(docDoc.getName(), docDoc.getSize(), docDoc.getCategory().toString(), docDoc.getLabel());
        service.saveDocument(txtDoc.getName(), txtDoc.getSize(), txtDoc.getCategory().toString(), txtDoc.getLabel());

        // verify
        Assertions.assertEquals(Optional.of(xlsDoc), service.findByName("xlsInsertTest"));
        Assertions.assertEquals(Optional.of(docDoc), service.findByName("docInsertTest"));
        Assertions.assertEquals(Optional.of(txtDoc), service.findByName("txtInsertTest"));
        Assertions.assertEquals(Optional.empty(), service.findByName("The other Insert Test Document"));
    }

    @Test
    void checkDocumentRelationshipInsert(){
        // given
        Document pdfDoc = new PdfDocument("pdfRelationTest", 20, "3");
        Document pdfDocR = new PdfDocument("pdf reference RelationTest", 300, "5");

        // when
        service.saveDocument(pdfDoc.getName(), pdfDoc.getSize(), pdfDoc.getCategory().toString(), pdfDoc.getLabel());
        service.saveDocument(pdfDocR.getName(), pdfDocR.getSize(), pdfDocR.getCategory().toString(), pdfDocR.getLabel());

        // verify
        Assertions.assertEquals(Optional.of(pdfDocR), service.findByName("pdf reference RelationTest"));
        Assertions.assertEquals("8 signatures", service.findByName("pdfRelationTest").orElseThrow(NullPointerException::new)
                .getDocumentRelationship().get(0).getDescription());
        Assertions.assertEquals(Optional.empty(), service.findByName("The other Document Relation Test"));
    }
}
