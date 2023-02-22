package com.training.docshelf.repository;

import com.training.docshelf.entity.RelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipRepository extends JpaRepository<RelationshipEntity, Long> {
}
