package com.greencity.api.clients;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClientAuthorized {
    protected final String baseAPIUrl;
    protected final String password;
    protected final String email;
    protected final ContentType contentType;
    @Getter
    @Setter
    protected String token;


    public BaseClientAuthorized(String baseUrl, String password, String email) {
        this.baseAPIUrl = baseUrl;
        contentType = ContentType.JSON;
        this.email = email;
        this.password = password;
    }

    public BaseClientAuthorized(String baseUrl, ContentType contentType,String password, String email) {
        this.baseAPIUrl = baseUrl;
        this.contentType = contentType;
        this.email = email;
        this.password = password;
    }


    protected RequestSpecification preparedRequest() {
        getToken();

        RequestSpecification request = given()
//                .log()
//                .body()
                .baseUri(baseAPIUrl)
                .contentType(contentType);;


        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        return request;
    }

    public void getToken() {
        Map<String, Object> user =
                Map.of("email", email, "password", password);

        Response response = given().header("Content-Type", "application/json")
                .when().body(user)
                .baseUri(System.getenv("base.authorized.api.url"))
                .basePath("ownSecurity/signIn")
                .post();
        token = response.body().jsonPath().getString("accessToken");
    }
}
