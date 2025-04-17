package com.greencity.api.models;

import lombok.Data;
import lombok.Getter;
import java.util.Date;
import java.util.List;

@Data
public class EcoNews {
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String content;
    @Getter
    private String shortInfo;
    @Getter
    private String imagePath;
    @Getter
    private Author author;
    @Getter
    private Integer likes;
    @Getter
    private Integer dislikes;
    @Getter
    private Integer countComments;
    @Getter
    private Boolean hidden;
    @Getter
    private List<String> tags;
    @Getter
    private List<String> tagsUa;

    @Getter
    private Date creationDate;

    public EcoNews() {
    }
}
