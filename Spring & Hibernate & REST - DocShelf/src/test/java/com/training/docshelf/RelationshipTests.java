package com.training.docshelf;

import com.training.docshelf.factory.*;
import com.training.docshelf.model.Document;
import com.training.docshelf.strategy.ChooseStrategy;
import com.training.docshelf.strategy.TransformStrategy;
import com.training.docshelf.worker.RelationshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@SpringBootTest
public class RelationshipTests {
    @Autowired
    RelationshipService relationshipService;

    @Autowired
    ChooseStrategy strategyChooser;

    @Test
    void checkJsonJson(){
        // init
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH");
        Date date = new Date();

        List<Document> docList = new LinkedList<>();
        docList.add(new JSONDocument("JSON1", 100, "The registered JSON"));
        Document doc = new JSONDocument("JSON2", 200, "The unregistered JSON");

        TransformStrategy strategy = strategyChooser.chooseStrategy("Json");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        Assertions.assertEquals(1, docList.get(0).getDocumentRelationship().size());
        Assertions.assertTrue(docList.get(0).getDocumentRelationship().get(0)
                .getDescription().startsWith("Register time: " + formatter.format(date)));
    }

    @Test
    void checkJsonOthers(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new PdfDocument("pdf", 1, "1"));
        docList.add(new DocDocument("doc", 1, "The DOC"));
        docList.add(new TxtDocument("txt", 10, "The TXT"));
        docList.add(new XlsDocument("xls", 20, "The XLS"));
        Document doc = new JSONDocument("JSON", 100, "The registered JSON");

        TransformStrategy strategy = strategyChooser.chooseStrategy("json");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        docList.forEach(document -> Assertions.assertEquals(0, document.getDocumentRelationship().size()));
    }

    @Test
    void checkPdfPdf(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new PdfDocument("PDF1", 100, "1"));
        Document doc = new PdfDocument("PDF2", 200, "2");

        TransformStrategy strategy = strategyChooser.chooseStrategy("pdf");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        Assertions.assertEquals(1, docList.get(0).getDocumentRelationship().size());
        Assertions.assertTrue(docList.get(0).getDocumentRelationship().get(0)
                .getDescription().startsWith("3 signatures"));
    }

    @Test
    void checkPdfOthers(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new JSONDocument("JSON", 100, "The registered JSON"));
        docList.add(new DocDocument("doc", 1, "The DOC"));
        docList.add(new TxtDocument("txt", 10, "The TXT"));
        docList.add(new XlsDocument("xls", 20, "The XLS"));
        Document doc = new PdfDocument("pdf", 1, "1");

        TransformStrategy strategy = strategyChooser.chooseStrategy("pdf");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        docList.forEach(document -> Assertions.assertEquals(0, document.getDocumentRelationship().size()));
    }

    @Test
    void checkTxtTxt(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new TxtDocument("TXT1", 10, "The first Txt"));
        Document doc = new TxtDocument("TXT2", 200, "The second Txt");

        TransformStrategy strategy = strategyChooser.chooseStrategy("txt");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        Assertions.assertEquals(1, docList.get(0).getDocumentRelationship().size());
        Assertions.assertEquals("Total size: " + (doc.getSize() + docList.get(0).getSize()),
                docList.get(0).getDocumentRelationship().get(0).getDescription());
    }

    @Test
    void checkTxtOther(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new JSONDocument("JSON", 100, "The registered JSON"));
        docList.add(new DocDocument("doc", 1, "The DOC"));
        docList.add(new PdfDocument("pdf", 1, "1"));
        docList.add(new XlsDocument("xls", 20, "The XLS"));
        Document doc = new TxtDocument("txt", 10, "The TXT");

        TransformStrategy strategy = strategyChooser.chooseStrategy("txt");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        docList.forEach(document -> Assertions.assertEquals(0, document.getDocumentRelationship().size()));
    }

    @Test
    void checkDocDoc(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new DocDocument("DOC1", 100, "The first Doc"));
        Document doc = new DocDocument("DOC2", 20, "The second Doc");

        TransformStrategy strategy = strategyChooser.chooseStrategy("doc");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        Assertions.assertEquals(1, docList.get(0).getDocumentRelationship().size());
        Assertions.assertEquals("Total size: " + (doc.getSize() + docList.get(0).getSize()),
                docList.get(0).getDocumentRelationship().get(0).getDescription());
    }

    @Test
    void checkDocOthers(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new JSONDocument("JSON", 100, "The registered JSON"));
        docList.add(new TxtDocument("txt", 10, "The TXT"));
        docList.add(new PdfDocument("pdf", 1, "1"));
        docList.add(new XlsDocument("xls", 20, "The XLS"));
        Document doc = new DocDocument("doc", 1, "The DOC");

        TransformStrategy strategy = strategyChooser.chooseStrategy("doc");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        docList.forEach(document -> Assertions.assertEquals(0, document.getDocumentRelationship().size()));
    }

    @Test
    void checkXlsXls(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new PdfDocument("XLS1", 100, "The first xls"));
        Document doc = new PdfDocument("XLS2", 20, "The second xls");

        TransformStrategy strategy = strategyChooser.chooseStrategy("xls");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        Assertions.assertEquals(1, docList.get(0).getDocumentRelationship().size());
        Assertions.assertEquals("Total size: " + (doc.getSize() + docList.get(0).getSize()),
                docList.get(0).getDocumentRelationship().get(0).getDescription());
    }

    @Test
    void checkXlsOthers(){
        // init
        List<Document> docList = new LinkedList<>();
        docList.add(new JSONDocument("JSON", 100, "The registered JSON"));
        docList.add(new TxtDocument("txt", 10, "The TXT"));
        docList.add(new PdfDocument("pdf", 1, "1"));
        docList.add(new DocDocument("doc", 1, "The DOC"));
        Document doc = new XlsDocument("xls", 20, "The XLS");

        TransformStrategy strategy = strategyChooser.chooseStrategy("doc");

        // when
        relationshipService.setRelations(doc, docList, strategy);

        // verify
        docList.forEach(document -> Assertions.assertEquals(0, document.getDocumentRelationship().size()));
    }
}
