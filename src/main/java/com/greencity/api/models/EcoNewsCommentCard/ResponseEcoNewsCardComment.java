package com.greencity.api.models.EcoNewsCommentCard;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class ResponseEcoNewsCardComment {
    private String comment;
    private Date createDate;
    public ArrayList<Object> getAdditionalImages;

}
