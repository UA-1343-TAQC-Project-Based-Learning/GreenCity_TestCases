package com.greencity.api.clients;
import com.greencity.api.models.Root;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EventClient {
    private final String baseUri;

    public EventClient(String baseUri) {
        this.baseUri = baseUri;
    }
    public Root getEventById(int id) {
        return RestAssured.given()
                .baseUri(baseUri)
                .basePath("/events/{id}")
                .pathParam("id", id)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(Root.class);
    }

    public Response getRawEventResponseById(int id) {
        return RestAssured.given()
                .baseUri(baseUri)
                .basePath("/events/{id}")
                .pathParam("id", id)
                .accept(ContentType.JSON)
                .when()
                .get();
    }
}

