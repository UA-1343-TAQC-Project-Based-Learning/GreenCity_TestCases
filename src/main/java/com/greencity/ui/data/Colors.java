package com.greencity.ui.data;

public enum Colors {
    DEFAULT_VALUE("rgb(255, 255, 255)", "rgb(255, 0, 0)"),
    CREATE_NEWS_TITLE_FIELD_BORDER_COLOR("rgb(156, 167, 176)", "rgb(255, 0, 0)"),
    CREATE_NEWS_TITLE_FIELD_COUNTER_TEXT_COLOR("rgba(135, 135, 135, 1)","rgba(235, 24, 13, 1)"),
    TAG_COLOR("rgba(255, 255, 255, 1)", "rgba(19, 170, 87, 1)");

    private final String normalColor;
    private final String warningColor;

    private Colors(String normalColor, String warningColor) {
        this.normalColor = normalColor;
        this.warningColor = warningColor;
    }

    public String normalColor() {
        return normalColor;
    }

    public String warningColor() {
        return warningColor;
    }


}

