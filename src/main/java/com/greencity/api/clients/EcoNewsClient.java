package com.greencity.api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClientAuthorized{
    private String title;


    public EcoNewsClient(String baseUrl, String password, String email, String title) {
        super(baseUrl, password, email);
        this.title = title;
    }

    public Response getEcoNewsId() {
        String resourceUrl = "eco-news?title=" + title;

        return preparedRequest()
                .header("Accept","*/*")
                .get(resourceUrl);
    }

}
