package com.greencity.api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClient{
    protected String resourceUrl = "eco-news?title=title&favorite=false&page=0&size=5";


    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    public Response findEcoNewsId() {
        return preparedRequest()
                .header("Accept","*/*")
                .get(resourceUrl);
    }

}
