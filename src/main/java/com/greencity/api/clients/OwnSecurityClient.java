package com.greencity.api.clients;

import com.greencity.api.models.SignInRequest;
import com.greencity.api.models.SignInResponse;
import io.restassured.response.Response;

/**
 + * Client for interacting with the authentication endpoints.
 + */
public class OwnSecurityClient extends BaseClient {
    private final static String BASE_PATH = "/ownSecurity";

    public OwnSecurityClient(String baseUrl) {
        super(baseUrl);
    }

    /**
     * Performs sign-in operation and returns the raw HTTP response.
     *
     * @param email    User's email address
     * @param password User's password
     * @return Raw HTTP response from the sign-in endpoint
     */
    public Response signInRawResponse(String email, String password) {
        return preparedRequest()
                .body(new SignInRequest(email, password))
                .post(BASE_PATH + "/signIn");
    }

   /**
   * Performs sign-in operation and returns the parsed response object.
   *
   * @param email    User's email address
   * @param password User's password
   * @return Parsed sign-in response with user details and tokens
   */
    public SignInResponse signIn(String email, String password) {
        return signInRawResponse(email, password)
                .as(SignInResponse.class);
    }
}