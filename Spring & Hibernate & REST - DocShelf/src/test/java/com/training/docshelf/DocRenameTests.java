package com.training.docshelf;

import com.training.docshelf.factory.DocDocument;
import com.training.docshelf.model.Document;
import com.training.docshelf.service.DocumentService;
import com.training.docshelf.service.MissingDocumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocRenameTests {
    @Mock
    private DocumentService documentService;

    private List<Document> newDocList;


    @BeforeEach
    void setUp() {
        newDocList = new LinkedList<>();
        newDocList.add(new DocDocument("Mockito document", 1, "Mockito"));
    }

    @Test
    void testRenameExistingFileWithSuccess(){
        // given
        when(documentService.renameDocumentInList("Doc document", "Mockito document"))
                .thenReturn(newDocList);

        // when
        List<Document> docList = documentService
                .renameDocumentInList("Doc document", "Mockito document");

        // then
        verify(documentService, times(1)).renameDocumentInList("Doc document", "Mockito document");
        Assertions.assertEquals("Mockito document", docList.get(0).getName());
        verifyNoMoreInteractions(documentService);
    }

    @Test
    void testRenameExistingFileWithFail(){
        // given
        when(documentService.renameDocumentInList("Doc document", "Mockito document"))
                .thenThrow(new MissingDocumentException());

        // when
        Throwable exception = assertThrows(MissingDocumentException.class, () -> documentService
                .renameDocumentInList("Doc document", "Mockito document"));

        // then
        verify(documentService, times(1)).renameDocumentInList("Doc document", "Mockito document");
        Assertions.assertEquals("[WARNING]: File doesn't exist", exception.getMessage());
        verifyNoMoreInteractions(documentService);
    }
}
