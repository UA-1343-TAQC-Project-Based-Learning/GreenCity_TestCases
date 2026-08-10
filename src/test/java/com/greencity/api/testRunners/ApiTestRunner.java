package com.greencity.api.testRunners;

import com.greencity.utils.TestValueProvider;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class ApiTestRunner {
    protected static TestValueProvider testValueProvider;

    @BeforeAll
    static void setUp() {
        testValueProvider = new TestValueProvider();
        RestAssured.registerParser("application/problem+json", Parser.JSON);
    }
}
