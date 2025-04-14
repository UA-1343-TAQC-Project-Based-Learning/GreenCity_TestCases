package com.greencity.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    private int id;
    private String title;
    private Organizer organizer;
    private String creationDate;
    private String description;
    private ArrayList<EventDate> dates;
    private ArrayList<Tag> tags;
    private String titleImage;
    private ArrayList<String> additionalImages;
    private String type;
    private boolean isRelevant;
    private int likes;
    private int dislikes;
    private int countComments;
    private int eventRate;
    private int currentUserGrade;
    private boolean isSubscribed;
    private boolean isFavorite;
    private boolean isOrganizedByFriend;
}
