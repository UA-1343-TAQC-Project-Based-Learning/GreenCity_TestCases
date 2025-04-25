package com.greencity.api.clients;

import com.greencity.api.models.AddEcoNewsDtoRequest;
import com.greencity.api.models.EcoNews;
import com.greencity.api.models.UpdateEcoNewsRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.MULTIPART;

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

    public EcoNews addEcoNews(AddEcoNewsDtoRequest request, String imagePath) {
        return addEcoNewsRawResponse(request, imagePath).then()
                .statusCode(201)
                .extract()
                .as(EcoNews.class);
    }

    public Response addEcoNewsRawResponse(AddEcoNewsDtoRequest request, String imagePath) {
        RequestSpecification requestSpec = preparedRequest()
                .contentType(MULTIPART)
                .multiPart("addEcoNewsDtoRequest", request, JSON.toString());

        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            requestSpec.multiPart("image", imageFile);
        } else {
            requestSpec.multiPart("image", "", "");
        }

        return requestSpec.post(ECO_NEWS_END_POINT);
    }

    public EcoNews updateEcoNews(Long ecoNewsId, UpdateEcoNewsRequest request, String imagePath) {
        return updateEcoNewsRawResponse(ecoNewsId, request, imagePath)
                .then()
                .statusCode(200)
                .extract()
                .as(EcoNews.class);
    }

    public Response updateEcoNewsRawResponse(Long ecoNewsId, UpdateEcoNewsRequest request, String imagePath) {
        RequestSpecification requestSpec = preparedRequest()
                .contentType(MULTIPART)
                .pathParam("ecoNewsId", ecoNewsId)
                .multiPart("updateEcoNewsDto", request, JSON.toString());

        if (imagePath != null && !imagePath.isEmpty()) {
            requestSpec.multiPart("image", new File(imagePath));
        } else {
            requestSpec.multiPart("image", "", "");
        }

        return requestSpec.put(ECO_NEWS_END_POINT + "/{ecoNewsId}");
    }

    public Response deleteEcoNews(Long ecoNewsId) {
        return preparedRequest()
                .pathParam("ecoNewsId", ecoNewsId)
                .delete(ECO_NEWS_END_POINT + "/{ecoNewsId}");
    }
}
