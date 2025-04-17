package com.greencity.api.models.EcoNewsCommentCard;


import com.greencity.api.models.EcoNewsCard.ResponseUser;
import lombok.Data;

import java.util.ArrayList;


@Data
public class ResponseEcoNewsCardComment {
    private int id;
    private ResponseUser author;
    private String text;
    private String createdDate;
    public ArrayList<Object> additionalImages;

}
