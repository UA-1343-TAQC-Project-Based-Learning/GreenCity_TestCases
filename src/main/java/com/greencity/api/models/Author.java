package com.greencity.api.models;

import lombok.Getter;

public class Author {
    @Getter
    private Long id;
    @Getter
    private String name;

    public Author() {
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}

