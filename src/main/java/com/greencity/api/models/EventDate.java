package com.greencity.api.models;

import lombok.Data;

@Data
public class EventDate {
    private String startDate;
    private String finishDate;
    private String onlineLink;
    private Integer id;
    private Object event;
    private Coordinates coordinates;
}
