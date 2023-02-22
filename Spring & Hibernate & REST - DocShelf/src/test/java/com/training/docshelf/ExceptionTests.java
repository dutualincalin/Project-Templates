package com.training.docshelf;

import com.training.docshelf.factory.JSONDocument;
import com.training.docshelf.model.Document;
import com.training.docshelf.service.DocumentService;
import com.training.docshelf.service.DuplicateDocumentException;
import com.training.docshelf.service.MissingDocumentException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ExceptionTests {
    @Autowired
    private DocumentService service;

    @Test
    void checkDocumentDeleteException() {
        // verify
        assertThatThrownBy(() -> service.deleteDocument("TestDeleteDoc"))
                .isInstanceOf(MissingDocumentException.class);
    }

    @Test
    void checkDocumentInsertException() {
        // given
        Document jsonDoc = new JSONDocument("jsonTestDoc", 10, "The first Json document test");
        Document jsonDoc2 = new JSONDocument("jsonTestDoc", 10, "The second Json document test");

        // when
        service.saveDocument(jsonDoc.getName(), jsonDoc.getSize(), jsonDoc.getCategory().toString(), jsonDoc.getLabel());

        // verify
        assertThatThrownBy(
                () -> service.saveDocument(
                        jsonDoc2.getName(), jsonDoc2.getSize(), jsonDoc2.getCategory().toString(), jsonDoc2.getLabel())
        )
                .isInstanceOf(DuplicateDocumentException.class);
    }
}