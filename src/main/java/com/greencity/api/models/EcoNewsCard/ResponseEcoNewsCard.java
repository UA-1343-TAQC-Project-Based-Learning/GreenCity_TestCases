package com.greencity.api.models.EcoNewsCard;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ResponseEcoNewsCard {
    private int id;
    private String title;
    private String content;
    private Object shortInfo;
    private ResponseUser author;
    private Date creationDate;
    private String imagePath;
    private String source;
    private ArrayList<String> tagsUa;
    private ArrayList<String> tagsEn;
    private int likes;
    private int countComments;
    private int countOfEcoNews;
    private boolean favorite;
}
