package com.training.docshelf.strategy;

import com.training.docshelf.utility.DocumentType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChooseStrategy {
    private final Map<DocumentType, TransformStrategy> strategyMap;

    public ChooseStrategy(PdfStrategy pdf, JSONStrategy json, DefaultStrategy defaultStrategy){
        strategyMap = new HashMap<>();

        for(DocumentType type: DocumentType.values()){
            switch(type){
                case json -> strategyMap.put(type, json);
                case pdf -> strategyMap.put(type, pdf);
                default -> strategyMap.put(type, defaultStrategy);
            }
        }
    }

    public TransformStrategy chooseStrategy(String category){
        DocumentType type = DocumentType.valueOf(category.toLowerCase());
        return strategyMap.get(type);
    }
}
