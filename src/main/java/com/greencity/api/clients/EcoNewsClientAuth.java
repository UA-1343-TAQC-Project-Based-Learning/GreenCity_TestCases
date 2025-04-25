package com.greencity.api.clients;

import io.restassured.response.Response;

public class EcoNewsClientAuth extends BaseClientAuthorized{
    private String title;


    public EcoNewsClientAuth(String baseUrl, String password, String email, String title) {
        super(baseUrl, password, email);
        this.title = title;
    }

    public Response getEcoNewsId() {
        String resourceUrl = "eco-news?title=";

        return preparedRequest()
                .header("Accept","*/*")
                .param(title)
                .get(resourceUrl);
    }

}
