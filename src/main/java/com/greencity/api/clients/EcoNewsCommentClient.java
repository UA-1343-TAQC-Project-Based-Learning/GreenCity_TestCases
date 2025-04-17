package com.greencity.api.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EcoNewsCommentClient extends BaseClientAuthorized {
    private int ecoNewsCardId;

    protected String resourceUrl ;

    public EcoNewsCommentClient(String baseUrl, ContentType contentType, String password, String email, int ecoNewsCardId) {
        super(baseUrl,contentType, password, email);
        this.ecoNewsCardId = ecoNewsCardId;
    }


    public Response createComment(String comment) {
        resourceUrl = "/eco-news/" + ecoNewsCardId + "/comments";

        Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("text", comment);
        jsonBody.put("parentCommentId", 0);

        return preparedRequest()
                .header("Accept","*/*")
                .multiPart("request", jsonBody, "application/json")
                .post(resourceUrl);
    }
}
