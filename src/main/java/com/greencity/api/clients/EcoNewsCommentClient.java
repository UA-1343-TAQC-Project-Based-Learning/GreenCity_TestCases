package com.greencity.api.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EcoNewsCommentClient extends BaseClient {
    private int ecoNewsCardId;

    protected String resourceUrl ;

    public EcoNewsCommentClient(String baseUrl, int ecoNewsCardId) {
        super(baseUrl);
        this.ecoNewsCardId = ecoNewsCardId;
    }


    public Response createComment() {
        resourceUrl = "/eco-news/" + ecoNewsCardId + "/comments";

        return preparedRequest()
                .header("Accept","*/*")
                .multiPart("text", "new comment")
                .multiPart("parentCommentId", 0)
                .post(resourceUrl);
    }
}
