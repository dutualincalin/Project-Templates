package com.training.docshelf.entity;

import com.sun.istack.NotNull;
import com.training.docshelf.utility.DocumentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Documents")
public class DocumentEntity {

    @Id
    String name;

    @Column
    @NotNull int size;

    @Column
    @NotNull DocumentType category;

    @Column
    @NotNull String label;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "docReference")
    List<RelationshipEntity> relations;

    public DocumentEntity(String name, int size, DocumentType category, String label) {
        this.name = name;
        this.size = size;
        this.category = category;
        this.label = label;
        this.relations = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentEntity)) return false;
        DocumentEntity that = (DocumentEntity) o;
        return size == that.size
                && name.equals(that.name)
                && category == that.category
                && label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, category, label);
    }
}
