package com.greencity.api.clients;

import com.greencity.api.models.EcoNews;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClient {
    private static final String ECO_NEWS_END_POINT = "/eco-news";

    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    public EcoNews getEcoNewsById(int ecoNewsId, String lang) {
        return preparedRequest()
                .pathParam("id", ecoNewsId)
                .queryParam("lang", lang)
                .when()
                .get(ECO_NEWS_END_POINT+"/{id}")
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
                .get(ECO_NEWS_END_POINT+"/{id}");
    }
}
