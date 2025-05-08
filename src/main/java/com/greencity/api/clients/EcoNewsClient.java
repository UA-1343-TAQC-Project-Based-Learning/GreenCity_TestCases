package com.greencity.api.clients;

import com.greencity.api.models.AddEcoNewsDtoRequest;
import com.greencity.api.models.EcoNews;
import com.greencity.api.models.UpdateEcoNewsRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.http.ContentType.MULTIPART;

public class EcoNewsClient extends BaseClient {
    private static final String ECO_NEWS_END_POINT = "/eco-news";

    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    @Step("GET EcoNews by id POJO response")
    public EcoNews getEcoNewsById(int ecoNewsId, String lang) {
        return preparedRequest()
                .pathParam("id", ecoNewsId)
                .queryParam("lang", lang)
                .when()
                .get(ECO_NEWS_END_POINT + "/{id}")
                .then()
                .statusCode(200)
                .extract()
                .as(EcoNews.class);
    }

    @Step("GET EcoNews by id raw response")
    public Response getEcoNewsByIdRawResponse(int ecoNewsId, String lang) {
        return preparedRequest()
                .pathParam("id", ecoNewsId)
                .queryParam("lang", lang)
                .when()
                .get(ECO_NEWS_END_POINT + "/{id}");
    }

    @Step("POST EcoNews POJO response")
    public EcoNews addEcoNews(AddEcoNewsDtoRequest request, String imagePath) {
        Response response = addEcoNewsRawResponse(request, imagePath);
        response.then().log().all();
        return response.then()
                .statusCode(201)
                .extract()
                .as(EcoNews.class);
    }

    @Step("POST EcoNews raw response")
    public Response addEcoNewsRawResponse(AddEcoNewsDtoRequest request, String imagePath) {
        RequestSpecification requestSpec = preparedRequest()
                .contentType(MULTIPART)
                .multiPart("addEcoNewsDtoRequest", request, "application/json");

        attachImageIfExists(requestSpec, imagePath);

        return requestSpec.post(ECO_NEWS_END_POINT);
    }

    @Step("PUT EcoNews POJO response")
    public EcoNews updateEcoNews(Long ecoNewsId, UpdateEcoNewsRequest request, String imagePath) {
        return updateEcoNewsRawResponse(ecoNewsId, request, imagePath)
                .then()
                .statusCode(200)
                .extract()
                .as(EcoNews.class);
    }

    @Step("PUT EcoNews raw response")
    public Response updateEcoNewsRawResponse(Long ecoNewsId, UpdateEcoNewsRequest request, String imagePath) {
        RequestSpecification requestSpec = preparedRequest()
                .contentType(MULTIPART)
                .pathParam("ecoNewsId", ecoNewsId)
                .header("Accept", "application/json")
                .multiPart("updateEcoNewsDto", request, "application/json");

        attachImageIfExists(requestSpec, imagePath);

        return requestSpec
                .log().all()
                .put(ECO_NEWS_END_POINT + "/{ecoNewsId}");
    }

    @Step("DELETE EcoNews raw response")
    public Response deleteEcoNews(Long ecoNewsId) {
        return preparedRequest()
                .pathParam("ecoNewsId", ecoNewsId)
                .delete(ECO_NEWS_END_POINT + "/{ecoNewsId}");
    }

    @Step("Attach image for POST/PUT EcoNews request if exists")
    private void attachImageIfExists(RequestSpecification requestSpec, String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            requestSpec.multiPart("image", "", "");
            return;
        }

        try {
            File imageFile = new File(imagePath);
            String fileName = imageFile.getName().toLowerCase();

            if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg")
                    && !fileName.endsWith(".jpeg") && !fileName.endsWith(".gif")) {
                throw new IllegalArgumentException("Only PNG, JPEG or GIF images are allowed");
            }

            String mimeType = fileName.endsWith(".png") ? "image/png" :
                    fileName.endsWith(".gif") ? "image/gif" : "image/jpeg";
            requestSpec.multiPart("image", fileName, new FileInputStream(imageFile), mimeType);

        } catch (IOException e) {
            throw new RuntimeException("Failed to attach image: " + imagePath, e);
        }
    }
}
