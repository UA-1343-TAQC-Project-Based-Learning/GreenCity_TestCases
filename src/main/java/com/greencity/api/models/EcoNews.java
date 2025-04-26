package com.greencity.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcoNews {
    private Long id;
    private String title;
    private String content;
    private String shortInfo;
    private String imagePath;
    private Author author;
    private Integer likes;
    private Integer dislikes;
    private Integer countComments;
    private Boolean hidden;
    private List<String> tagsUa;
    private List<String> tagsEn;
    private List<String> tags;
    private String source;
    private Integer countOfEcoNews;
    private Boolean favorite;
    private Date creationDate;

}