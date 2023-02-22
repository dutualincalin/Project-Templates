package com.training.docshelf.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "relationship")
public class RelationshipEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private String docName;

    @Column
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name")
    private DocumentEntity docReference;

    public RelationshipEntity(String docName, String description, DocumentEntity docReference) {
        this.docName = docName;
        this.description = description;
        this.docReference = docReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationshipEntity)) return false;
        RelationshipEntity that = (RelationshipEntity) o;
        return id.equals(that.id) && docName.equals(that.docName) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docName, description);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "\n, docName='" + docName +
                "\n, description='" + description +
                "\n, docReference=" + docReference +
                "\n}";
    }
}
