package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.User.ResponseLogin;
import com.greencity.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest extends ApiTestRunner {
    private AuthClient authClient;

    @BeforeClass
    public void setUp() {
        authClient = new AuthClient(testValueProvider.getBaseUIUrl());
    }

    @Test
    public void loginTest() {
         String email = testValueProvider.getAdminEmail();
         String password = testValueProvider.getUserPassword();
         Response response = authClient.login(email, password);
         response.then().statusCode(200);
         ResponseLogin responseLogin = response.as(ResponseLogin.class);


         Assert.assertEquals(responseLogin.getUser().getEmail(), email));
    }
}
