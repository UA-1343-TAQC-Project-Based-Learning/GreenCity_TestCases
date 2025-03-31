package com.greencity.ui.component.ecoNewsTag;

public enum TagButton {
    NEWS("НОВИНИ", "NEWS"),
    EVENTS("ПОДІЇ", "EVENTS"),
    EDUCATION("ОСВІТА", "EDUCATION"),
    INITIATIVES("ІНІЦІАТИВИ", "INITIATIVES"),
    ADS("РЕКЛАМА", "ADS");

    private final String ukrainianName;
    private final String englishName;

    TagButton(String ukrainianName, String englishName) {
        this.ukrainianName = ukrainianName;
        this.englishName = englishName;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public String getEnglishName() {
        return englishName;
    }
}
