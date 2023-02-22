package com.training.docshelf.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Relationship {
    private final String name;
    private final String description;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ",\n description='" + description + '\'' +
                "\n}";
    }
}
