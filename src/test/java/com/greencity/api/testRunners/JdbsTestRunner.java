package com.greencity.api.testRunners;

import com.greencity.utils.TestValueProvider;
import com.greencity.utils.jdbc.services.HabitsService;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class JdbsTestRunner {
    protected static TestValueProvider testValueProvider;
    protected static HabitsService habitsService;

    @BeforeSuite
    public void setUp() {
        testValueProvider = new TestValueProvider();
        RestAssured.registerParser("application/problem+json", Parser.JSON);
    }
    @BeforeClass
    public void setup() {
        habitsService = new HabitsService(
                testValueProvider.getJDBCGreenCityURL(),
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword()
        );
    }

}
