package com.greencity.api.testRunners;

import com.greencity.api.clients.EventClient;
import com.greencity.utils.TestValueProvider;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class ApiTestRunner {
    protected static TestValueProvider testValueProvider;
    protected static EventClient eventClient;

    @BeforeSuite
    public void setUp() {
        testValueProvider = new TestValueProvider();
        RestAssured.registerParser("application/problem+json", Parser.JSON);
    }
    @BeforeClass
    public void set() {
        if (testValueProvider == null) {
            testValueProvider = new TestValueProvider();
        }
        String baseUri = testValueProvider.getBaseAPIUrl();
        eventClient = new EventClient(baseUri);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
