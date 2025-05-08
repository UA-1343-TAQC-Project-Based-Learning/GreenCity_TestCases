package com.greencity.jdbc;

import com.greencity.api.testRunners.ApiTestRunner;
import com.greencity.utils.jdbc.dao.EventDAO;
import com.greencity.utils.jdbc.entity.EventEntity;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class EventsJDBCTest extends ApiTestRunner {

    private static final int EXPECTED_EVENTS_COUNT = 3;


    @Description("Verify all required fields exist and have valid values in events table through JDBC connection")
    @Feature("JDBC Events Validation")
    @Issue("169")
    @Owner("Nataliia Hrusha")
    @Test
    public void verifyAllRequiredEventFieldsExist() {
        EventDAO eventDAO = new EventDAO(
                testValueProvider.getJDBCGreenCityURL(),
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword());
        List<EventEntity> events = eventDAO.selectAll();
        SoftAssert softAssert = new SoftAssert();

        for (EventEntity event : events) {
            softAssert.assertTrue(event.getId() > 0, "Event ID should be positive");
            softAssert.assertNotNull(event.getTitle(), "Event title should not be null");
            softAssert.assertNotNull(event.getDescription(), "Event description should not be null");
            softAssert.assertNotNull(event.getOrganizerId(), "Event organizer ID should not be null");
            softAssert.assertNotNull(event.getTitleImage(), "Event title image should not be null");
            softAssert.assertNotNull(event.getCreationDate(), "Event creation date should not be null");
            softAssert.assertNotNull(event.getType(), "Event type should not be null");
        }

        int actualCount = eventDAO.selectAll().size();

        softAssert.assertTrue(actualCount > 0, "Number of events should be greater than 0");
        softAssert.assertNotEquals(actualCount, 0, "Number of events should not be 0");
        softAssert.assertEquals(actualCount, EXPECTED_EVENTS_COUNT,
                String.format("Number of events should be %d", EXPECTED_EVENTS_COUNT));

        softAssert.assertAll();
    }
}
