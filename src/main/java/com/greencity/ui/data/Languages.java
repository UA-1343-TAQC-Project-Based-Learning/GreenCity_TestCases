package com.greencity.ui.data;

public enum Languages {
    DEFAULT_LANGUAGE("Ua"),
    UKRAINIAN_LANGUAGE("Ua"),
    ENGLISH_LANGUAGE("En");
    private  String language;


    private Languages(String language) {
        this.language=language;
    }

    public String getLanguages() {
        return language;
    }
}
