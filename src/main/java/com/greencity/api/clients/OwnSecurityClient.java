package com.greencity.api.clients;

import com.greencity.api.models.SignInRequest;
import com.greencity.api.models.SignInResponse;
import io.restassured.response.Response;

public class OwnSecurityClient extends BaseClient {
    String basePath = "/ownSecurity";

    public OwnSecurityClient(String baseUrl) {
        super(baseUrl);
    }

    public Response signInRawResponse(String email, String password) {
        return preparedRequest()
                .body(new SignInRequest(email, password))
                .post(basePath + "/signIn");
    }

    public SignInResponse signIn(String email, String password) {
        return signInRawResponse(email, password)
                .as(SignInResponse.class);
    }
}