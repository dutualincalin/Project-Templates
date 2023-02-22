package com.training.docshelf.mapper;

import com.training.docshelf.entity.DocumentEntity;
import com.training.docshelf.entity.RelationshipEntity;
import com.training.docshelf.factory.DocumentFactory;
import com.training.docshelf.model.Document;
import com.training.docshelf.model.Relationship;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentMapper {
    DocumentMapper instance = Mappers.getMapper(DocumentMapper.class);

    @Mapping(source = "entity.docName", target = "name")
    Relationship entityToRelation(RelationshipEntity entity);

    @Mapping(source = "relationship.name", target = "docName")
    RelationshipEntity relationToEntity(Relationship relationship);

    @Mapping(source = "doc.documentRelationship", target = "relations")
    DocumentEntity docToEntity(Document doc);

    default Document entityToDoc(DocumentEntity docEntity, DocumentFactory docFactory){
        Document doc = docFactory.createDocument(docEntity.getName(), docEntity.getSize(),
                String.valueOf(docEntity.getCategory()), docEntity.getLabel());

        doc.setDocumentRelationship(
                docEntity.getRelations().stream().map(this::entityToRelation).collect(Collectors.toList()));

        return doc;
    }
}
