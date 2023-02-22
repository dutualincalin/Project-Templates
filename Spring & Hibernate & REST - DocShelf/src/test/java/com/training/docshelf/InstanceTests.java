package com.training.docshelf;

import com.training.docshelf.factory.*;
import com.training.docshelf.strategy.ChooseStrategy;
import com.training.docshelf.strategy.DefaultStrategy;
import com.training.docshelf.strategy.JSONStrategy;
import com.training.docshelf.strategy.PdfStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InstanceTests {
    @Autowired
    ChooseStrategy strategy;

    @Autowired
    DocumentFactory docFactory;

    @Test
    public void checkStrategy() {
        // verify
        assertNotNull(strategy);
        assertNotNull(strategy.chooseStrategy("json"));
        Assertions.assertInstanceOf(JSONStrategy.class, strategy.chooseStrategy("json"));
        assertNotNull(strategy.chooseStrategy("Json"));
        Assertions.assertInstanceOf(JSONStrategy.class, strategy.chooseStrategy("Json"));
        assertNotNull(strategy.chooseStrategy("JSON"));
        Assertions.assertInstanceOf(JSONStrategy.class, strategy.chooseStrategy("JSON"));
        assertNotNull(strategy.chooseStrategy("pdf"));
        Assertions.assertInstanceOf(PdfStrategy.class, strategy.chooseStrategy("pdf"));
        assertNotNull(strategy.chooseStrategy("DoC"));
        Assertions.assertInstanceOf(DefaultStrategy.class, strategy.chooseStrategy("DoC"));
        assertNotNull(strategy.chooseStrategy("Txt"));
        Assertions.assertInstanceOf(DefaultStrategy.class, strategy.chooseStrategy("Txt"));
        assertNotNull(strategy.chooseStrategy("XLS"));
        Assertions.assertInstanceOf(DefaultStrategy.class, strategy.chooseStrategy("XLS"));
    }

    @Test
    public void checkFactory(){
        // verify
        Assertions.assertInstanceOf(JSONDocument.class,
                docFactory.createDocument("JSONDoc", 1, "json", "JSON doc"));
        Assertions.assertInstanceOf(PdfDocument.class,
                docFactory.createDocument("PDFDoc", 1, "pdf", "PDF doc"));
        Assertions.assertInstanceOf(DocDocument.class,
                docFactory.createDocument("DocDoc", 1, "doc", "DOC doc"));
        Assertions.assertInstanceOf(TxtDocument.class,
                docFactory.createDocument("TxtDoc", 1, "txt", "TXT doc"));
        Assertions.assertInstanceOf(XlsDocument.class,
                docFactory.createDocument("XlsDoc", 1, "xls", "XLS doc"));
    }
}
