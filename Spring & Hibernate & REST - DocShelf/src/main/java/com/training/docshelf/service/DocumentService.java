package com.training.docshelf.service;

import com.training.docshelf.entity.DocumentEntity;
import com.training.docshelf.factory.DocumentFactory;
import com.training.docshelf.model.Document;
import com.training.docshelf.repository.DocumentRepository;
import com.training.docshelf.repository.RelationshipRepository;
import com.training.docshelf.strategy.ChooseStrategy;
import com.training.docshelf.strategy.TransformStrategy;
import com.training.docshelf.worker.RelationshipService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService{
    private List<Document> docList;

    private final DocumentRepository docRepository;

    private final RelationshipRepository relationshipRepository;

    private final DocumentFactory docFactory;

    private final ChooseStrategy strategyChooser;

    private final RelationshipService relationshipService;

    private final DatabaseService databaseService;

    private final DuplicateDocumentException duplicateException;

    private final MissingDocumentException missingException;

    public DocumentService(DocumentRepository docRepository, RelationshipRepository relationshipRepository,
                           DocumentFactory docFactory, ChooseStrategy strategyChooser,
                           RelationshipService relationshipService, DatabaseService databaseService,
                           DuplicateDocumentException duplicateException, MissingDocumentException missingException){
        this.docRepository = docRepository;
        this.relationshipRepository = relationshipRepository;
        this.docFactory = docFactory;
        this.strategyChooser = strategyChooser;
        this.relationshipService = relationshipService;
        this.databaseService = databaseService;
        this.duplicateException = duplicateException;
        this.missingException = missingException;
        docList = databaseService.getData();
    }

    public List<Document> renameDocumentInList(String oldName, String newName) throws MissingDocumentException{
        return null;
    }

    public Optional<Document> findByName(String name){
        return docList.stream()
                .filter(doc -> doc.getName().equals(name))
                .findFirst();
    }

    @Transactional
    public void saveDocument(String name, int size, String category, String label){
        if (findByName(name).isPresent()) {
            throw duplicateException;
        }

        Document doc = docFactory.createDocument(name, size, category, label);
        setRelations(doc);
        docList.add(doc);
        databaseUpdate(docList);
    }

    @Transactional
    public void deleteDocument(String name){
        if (findByName(name).isEmpty()) {
            throw missingException;
        }

        DocumentEntity docEntity = docRepository.getReferenceById(name);
        relationshipRepository.deleteAll(docEntity.getRelations());
        docRepository.delete(docEntity);

        docList = databaseService.getData();
    }

    void setRelations(Document doc){
        TransformStrategy strategy = strategyChooser.chooseStrategy(doc.getCategory().toString());

        if(docList.size() > 0) {
            relationshipService.setRelations(doc, docList, strategy);
        }
    }

    void databaseUpdate(List<Document> docList){
        for(Document document : docList){
            if(!docRepository.existsById(document.getName())){
                databaseService.addTo(document);
            }

            else{
                databaseService.updateRelations(document);
            }
        }
    }
}
