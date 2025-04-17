package com.greencity.api.testRunners;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.DateUtils;
import com.greencity.api.models.EcoNews;
import com.greencity.utils.TestValueProvider;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Collections;


import static com.greencity.api.testRunners.ApiTestRunner.testValueProvider;

public class EcoNewsClientTest {

    private EcoNewsClient ecoNewsClient;
    private static final String LANG_EN = "en";
    private static final String LANG_UK = "ua";
    private static final int KNOWN_EXISTING_NEWS_ID = 1;
    private static final long EXPECTED_NEWS_ID = 1L;
    private static final String EXPECTED_CREATION_DATE_PART = "2025-03-16";
    private static final String EXPECTED_TITLE = "TestTitle";
    private static final String EXPECTED_CONTENT = "Content Content Content";
    private static final long EXPECTED_AUTHOR_ID = 4L;
    private static final String EXPECTED_AUTHOR_NAME = "TestUser";
    private static final int EXPECTED_LIKES = 0;
    private static final int EXPECTED_DISLIKES = 0;
    private static final int EXPECTED_COMMENTS = 0;
    private static final boolean EXPECTED_HIDDEN = false;


    @BeforeMethod
    public void setUp() {
        testValueProvider = new TestValueProvider();
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIUrl());

        System.out.println("Using base URL: " + testValueProvider.getBaseAPIUrl());
        System.out.println("Testing with news ID: " + KNOWN_EXISTING_NEWS_ID);
    }

    @SneakyThrows
    @Test
    public void getEcoNewsByIdForEnLanguage() {
        Response response = ecoNewsClient.getEcoNewsByIdRawResponse(KNOWN_EXISTING_NEWS_ID, LANG_EN);
        System.out.println("Request URL: " + response.getDetailedCookies());
        response.then().log().all();

        response.then().statusCode(200);
        EcoNews ecoNews = response.as(EcoNews.class);

        String creationDateRaw = ecoNews.getCreationDate().toString();
        String actualDateStr = DateUtils.convertRawDateToIso(creationDateRaw);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateStr, EXPECTED_CREATION_DATE_PART,
                "Date mismatch. Actual: " + actualDateStr + ", Expected: " + EXPECTED_CREATION_DATE_PART);
        softAssert.assertEquals((long) ecoNews.getId(), EXPECTED_NEWS_ID, "News ID mismatch");
        softAssert.assertEquals(ecoNews.getTitle(), EXPECTED_TITLE, "Title mismatch");
        softAssert.assertEquals(ecoNews.getContent(), EXPECTED_CONTENT, "Content mismatch");
        softAssert.assertEquals(ecoNews.getTags(), Collections.singletonList("News"), "Tag mismatch");
        softAssert.assertEquals((long) ecoNews.getAuthor().getId(), EXPECTED_AUTHOR_ID, "Author ID mismatch");
        softAssert.assertEquals(ecoNews.getAuthor().getName(), EXPECTED_AUTHOR_NAME, "Author name mismatch");
        softAssert.assertEquals((int) ecoNews.getLikes(), EXPECTED_LIKES, "Likes count mismatch");
        softAssert.assertEquals((int) ecoNews.getDislikes(), EXPECTED_DISLIKES, "Dislikes count mismatch");
        softAssert.assertEquals((int) ecoNews.getCountComments(), EXPECTED_COMMENTS, "Comments count mismatch");
        softAssert.assertEquals(ecoNews.getHidden().booleanValue(), EXPECTED_HIDDEN, "News should not be hidden");

        softAssert.assertAll();
    }

    @SneakyThrows
    @Test
    public void getEcoNewsByIdForUaLanguage() {
        Response response = ecoNewsClient.getEcoNewsByIdRawResponse(KNOWN_EXISTING_NEWS_ID, LANG_UK);
        System.out.println("Request URL: " + response.getDetailedCookies());
        response.then().log().all();

        response.then().statusCode(200);
        EcoNews ecoNews = response.as(EcoNews.class);

        String creationDateRaw = ecoNews.getCreationDate().toString();
        String actualDateStr = DateUtils.convertRawDateToIso(creationDateRaw);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateStr, EXPECTED_CREATION_DATE_PART,
                "Date mismatch. Actual: " + actualDateStr + ", Expected: " + EXPECTED_CREATION_DATE_PART);
        softAssert.assertEquals((long) ecoNews.getId(), EXPECTED_NEWS_ID, "News ID mismatch");
        softAssert.assertEquals(ecoNews.getTitle(), EXPECTED_TITLE, "Title mismatch");
        softAssert.assertEquals(ecoNews.getContent(), EXPECTED_CONTENT, "Content mismatch");
        softAssert.assertEquals(ecoNews.getTagsUa(), Collections.singletonList("Новини"), "UA Tag mismatch");
        softAssert.assertEquals((long) ecoNews.getAuthor().getId(), EXPECTED_AUTHOR_ID, "Author ID mismatch");
        softAssert.assertEquals(ecoNews.getAuthor().getName(), EXPECTED_AUTHOR_NAME, "Author name mismatch");
        softAssert.assertEquals((int) ecoNews.getLikes(), EXPECTED_LIKES, "Likes count mismatch");
        softAssert.assertEquals((int) ecoNews.getDislikes(), EXPECTED_DISLIKES, "Dislikes count mismatch");
        softAssert.assertEquals((int) ecoNews.getCountComments(), EXPECTED_COMMENTS, "Comments count mismatch");
        softAssert.assertEquals(ecoNews.getHidden().booleanValue(), EXPECTED_HIDDEN, "News should not be hidden");

        softAssert.assertAll();
    }
}
