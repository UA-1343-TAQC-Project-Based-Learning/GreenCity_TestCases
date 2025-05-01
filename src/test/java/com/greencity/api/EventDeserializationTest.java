package com.greencity.api;

import com.greencity.api.models.Root;
import com.greencity.api.testRunners.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class EventDeserializationTest extends ApiTestRunner {

    @DataProvider(name = "eventIdsProvider")
    public Object[][] provideEventIds() {
        return new Object[][]{
                {1}, {2}, {3}
        };
    }

    @Test(dataProvider = "eventIdsProvider")
    public void testDeserializeEventById(int eventId) {
        Root event = eventClient.getEventById(eventId);

        assertNotNull(event, "Event response is null");
        assertEquals(event.getId(), eventId, "Event ID mismatch");

        assertNotNull(event.getTitle(), "Title should not be null or empty");
        assertFalse(event.getTitle().trim().isEmpty(), "Title is empty");

        assertNotNull(event.getOrganizer(), "Organizer should not be null");
        assertNotNull(event.getOrganizer().getName(), "Organizer name is null");

        assertNotNull(event.getDates(), "Event dates list is null");
        assertFalse(event.getDates().isEmpty(), "Event dates list is empty");

        assertNotNull(event.getDates().get(0).getCoordinates(), "Coordinates are null");
        assertNotNull(event.getDates().get(0).getCoordinates().getCityUa(), "City UA is null");
        assertNotNull(event.isRelevant(), "Event isRelevant should not be null");
    }
    @Test
    public void testGetContentDeserializeEventById() {
        Root event = eventClient.getEventById(1);

        assertNotNull(event, "Event response is null");
        assertEquals(event.getId(), 1, "Event ID mismatch");
        assertEquals(event.getTitle(), "EventCreateV33", "Title mismatch");

        assertNotNull(event.getOrganizer(), "Organizer should not be null");
        assertEquals(event.getOrganizer().getName(), "TestUser", "Organizer name mismatch");

        assertNotNull(event.getDates(), "Event dates list is null");
        assertFalse(event.getDates().isEmpty(), "Event dates list is empty");

        assertEquals(event.getDates().get(0).getCoordinates().getCityUa(), "Київ", "City mismatch");

        assertFalse(event.isRelevant(), "Event should be relevant");
    }
    @Test
    public void testEventNotFound() {
        int invalidId = 5;
        Response response = eventClient.getRawEventResponseById(invalidId);
        assertEquals(response.statusCode(), 404);
        assertTrue(response.getBody().asString().contains("Event hasn't been found"));
    }
}
