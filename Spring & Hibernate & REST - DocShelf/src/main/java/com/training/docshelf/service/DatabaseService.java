package com.training.docshelf.service;

import com.training.docshelf.entity.DocumentEntity;
import com.training.docshelf.entity.RelationshipEntity;
import com.training.docshelf.factory.DocumentFactory;
import com.training.docshelf.mapper.DocumentMapper;
import com.training.docshelf.model.Document;
import com.training.docshelf.model.Relationship;
import com.training.docshelf.repository.DocumentRepository;
import com.training.docshelf.repository.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DatabaseService{
    private final RelationshipRepository relationRepo;

    private final DocumentRepository docRepo;

    private final DocumentFactory docFactory;

    public void addTo(Document document){
        DocumentEntity documentEntity = DocumentMapper.instance.docToEntity(document);
        docRepo.save(documentEntity);
        relationRepo.saveAll(documentEntity.getRelations());
    }

    public void updateRelations(Document document){
        DocumentEntity docEntity = docRepo.findById(document.getName()).orElseThrow(NullPointerException::new);
        int i = docEntity.getRelations().size();
        List<Relationship> relations = document.getDocumentRelationship();

        for(; i < relations.size(); i++){
            Relationship relation = relations.get(i);
            RelationshipEntity relationshipEntity =
                    new RelationshipEntity(relation.getName(), relation.getDescription(), docEntity);
            docEntity.getRelations().add(relationshipEntity);

            relationRepo.save(relationshipEntity);
        }
    }

    public List<Document> getData(){
        List<DocumentEntity> docEntities= docRepo.findAll();

        return docEntities.stream().map(doc -> DocumentMapper.instance.entityToDoc(doc, docFactory)).collect(Collectors.toList());
    }
}
