package com.greencity.api.clients;

import com.greencity.api.models.EcoNews;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClient {

    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    public EcoNews getEcoNewsById(int ecoNewsId, String lang) {
        return preparedRequest()
                .pathParam("id", ecoNewsId)
                .queryParam("lang", lang)
                .when()
                .get("/eco-news/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(EcoNews.class);
    }

    public Response getEcoNewsByIdRawResponse(int ecoNewsId, String lang) {
        return preparedRequest()
                .pathParam("id", ecoNewsId)
                .queryParam("lang", lang)
                .when()
                .get("/eco-news/{id}");
    }
}
