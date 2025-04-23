package com.greencity.api.testRunners;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.DateUtils;
import com.greencity.api.models.EcoNews;
import com.greencity.utils.TestValueProvider;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Collections;
import java.util.List;


public class EcoNewsClientTest extends ApiTestRunner {

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

    }

    @Test(dataProvider = "languageProvider")
    public void getEcoNewsById(String language, List<String> expectedTags) {
        Response response = ecoNewsClient.getEcoNewsByIdRawResponse(KNOWN_EXISTING_NEWS_ID, language);
        System.out.println("Response cookies: " + response.getDetailedCookies());
        response.then().log().all();

        response.then().statusCode(200);
        EcoNews ecoNews = response.as(EcoNews.class);

        verifyEcoNews(ecoNews, language.equals(LANG_UK) ? ecoNews.getTagsUa() : ecoNews.getTags(), expectedTags);
    }

    @DataProvider
    public Object[][] languageProvider() {
        return new Object[][] {
                {LANG_EN, Collections.singletonList("News")},
                {LANG_UK, Collections.singletonList("Новини")}
        };
    }

    @SneakyThrows
    private void verifyEcoNews(EcoNews ecoNews, List<String> actualTags, List<String> expectedTags) {
        String creationDateRaw = ecoNews.getCreationDate().toString();
        String actualDateStr = DateUtils.convertRawDateToIso(creationDateRaw);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateStr, EXPECTED_CREATION_DATE_PART,
                "Date mismatch. Actual: " + actualDateStr + ", Expected: " + EXPECTED_CREATION_DATE_PART);
        softAssert.assertEquals(ecoNews.getId().longValue(), EXPECTED_NEWS_ID, "News ID mismatch");
        softAssert.assertEquals(ecoNews.getTitle(), EXPECTED_TITLE, "Title mismatch");
        softAssert.assertEquals(ecoNews.getContent(), EXPECTED_CONTENT, "Content mismatch");
        softAssert.assertEquals(actualTags, expectedTags, "Tag mismatch");
        softAssert.assertEquals(ecoNews.getAuthor().getId().longValue(), EXPECTED_AUTHOR_ID, "Author ID mismatch");
        softAssert.assertEquals(ecoNews.getAuthor().getName(), EXPECTED_AUTHOR_NAME, "Author name mismatch");
        softAssert.assertEquals(ecoNews.getLikes().intValue(), EXPECTED_LIKES, "Likes count mismatch");
        softAssert.assertEquals(ecoNews.getDislikes().intValue(), EXPECTED_DISLIKES, "Dislikes count mismatch");
        softAssert.assertEquals(ecoNews.getCountComments().intValue(), EXPECTED_COMMENTS, "Comments count mismatch");
        softAssert.assertEquals(ecoNews.getHidden(), Boolean.valueOf(EXPECTED_HIDDEN), "News should not be hidden");

        softAssert.assertAll();
    }
}
