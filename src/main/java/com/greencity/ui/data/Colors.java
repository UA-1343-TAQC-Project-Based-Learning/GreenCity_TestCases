package com.greencity.ui.data;

public enum Colors {
    QUINTYNARY_LIGHT_GREY("rgb(156, 167, 176)"),
    RED("rgb(255, 0, 0)"),
    SECONDARY_GREY("rgba(135, 135, 135, 1)"),
    ERROR_RED("rgba(235, 24, 13, 1)"),
    PRIMARY_GREEN("rgba(19, 170, 87, 1)"),
    PRIMARY_WHITE("rgba(255, 255, 255, 1)"),
    PRIMARY_RED("rgb(255, 0, 0)");
    IMAGE_DROPZONE_WARNING_BACKGROUND("rgba(254, 241, 242, 1)");


    private  String color;

    private Colors(String color) {
        this.color=color;
    }

    public String getColor() {
        return color;
    }

}
