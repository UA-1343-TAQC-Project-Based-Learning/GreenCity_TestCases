package com.greencity.api.testRunners;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.EcoNews;
import com.greencity.api.clients.OwnSecurityClient;
import com.greencity.api.models.*;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class EcoNewsClientTest extends ApiTestRunner {

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
    private static final String EXPECTED_SOURCE = "http://test_domain.com/greenCity/news/create-news";
    private static final List<String> EXPECTED_TAGS = Arrays.asList("News", "Events");

    private SignInResponse testUser;
    private EcoNewsClient ecoNewsClient;
    private EcoNews testEcoNews;


    @BeforeMethod
    public void setUp() {
        super.setUp();
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIUrl());

        String email = testValueProvider.getUserEmail();
        String password = testValueProvider.getUserPassword();
        testUser = new OwnSecurityClient(testValueProvider.getBaseAPIUrlUser()).signIn(email, password);
        //TEST_IMAGE_PATH = testValueProvider.getFilePath("images/GreenCity5mb.png");
    }

    @BeforeMethod(groups = "requiresNews", dependsOnMethods = "setUp")
    public void createTestNews() {

        AddEcoNewsDtoRequest ecoNewsDtoRequest = AddEcoNewsDtoRequest.builder()
                .title(EXPECTED_TITLE)
                .text(EXPECTED_CONTENT)
                .tags(EXPECTED_TAGS)
                .source(EXPECTED_SOURCE)
                .build();

        ecoNewsClient.setToken(testUser.getAccessToken());
        testEcoNews = ecoNewsClient.addEcoNews(ecoNewsDtoRequest, "");
        ecoNewsClient.setToken(null);
    }

    @Test(dataProvider = "languageProvider")
    public void getEcoNewsById(String language, List<String> expectedTags) {
        Response response = ecoNewsClient.getEcoNewsByIdRawResponse(KNOWN_EXISTING_NEWS_ID, language);
        System.out.println("Response cookies: " + response.getDetailedCookies());
        response.then().log().all();

        response.then().statusCode(200);
        EcoNews ecoNews = response.as(EcoNews.class);

        verifyEcoNews(ecoNews, language.equals(LANG_UK) ? ecoNews.getTagsUa() : ecoNews.getTagsEn(), expectedTags);
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


    @DataProvider
    public Object[][] validEcoNewsProvider() {
        AddEcoNewsDtoRequest.AddEcoNewsDtoRequestBuilder ecoNewsDtoRequest = AddEcoNewsDtoRequest.builder()
                .title(EXPECTED_TITLE)
                .text(EXPECTED_CONTENT)
                .tags(EXPECTED_TAGS);

        return new Object[][]{
                {ecoNewsDtoRequest.build(), ""},
                {ecoNewsDtoRequest.source(EXPECTED_SOURCE).build(), ""},
                //{ecoNewsDtoRequest.source(EXPECTED_SOURCE).build(), TEST_IMAGE_PATH},
        };
    }

    @Test(dataProvider = "validEcoNewsProvider")
    public void addEcoNewsSuccessfully(AddEcoNewsDtoRequest request, String imagePath) {
        ecoNewsClient.setToken(testUser.getAccessToken());

        Response response = ecoNewsClient.addEcoNewsRawResponse(request, imagePath);
        response.then().statusCode(201);
        EcoNews responseBody = response.as(EcoNews.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseBody.getId(), "News ID should not be null");
        softAssert.assertEquals(responseBody.getTitle(), EXPECTED_TITLE, "Title mismatch");
        softAssert.assertEquals(responseBody.getContent(), EXPECTED_CONTENT, "Content mismatch");
        softAssert.assertEquals(responseBody.getTagsEn(), EXPECTED_TAGS,
                String.format("Tags mismatch, expected: '%s', actual: '%s'", EXPECTED_TAGS, responseBody.getTagsEn())
        );
        softAssert.assertNull(responseBody.getImagePath(), "Image path should be null when no file provided");

        softAssert.assertEquals(responseBody.getLikes(), Integer.valueOf(0), "Initial likes should be 0");
        softAssert.assertEquals(responseBody.getCountComments(), Integer.valueOf(0), "Initial comments should be 0");

        softAssert.assertAll();

        Long addedEcoNewsId = response.as(EcoNews.class).getId();
        ecoNewsClient.deleteEcoNews(addedEcoNewsId);
    }

    @Test(groups = "requiresNews")
    public void testUpdateEcoNews() {
        Long testEcoNewsId = testEcoNews.getId();
        String updatedTitle = EXPECTED_TITLE + " updated";
        String updatedContent = EXPECTED_CONTENT + "Updated content";
        List<String> updatedTags = new java.util.ArrayList<>(List.copyOf(EXPECTED_TAGS));
        updatedTags.add("Ads");
        String updatedSource = EXPECTED_SOURCE + "_updated";

        ecoNewsClient.setToken(testUser.getAccessToken());
        UpdateEcoNewsRequest updateRequest = UpdateEcoNewsRequest.builder()
                .id(testEcoNewsId)
                .title(updatedTitle)
                .content(updatedContent)
                .text(updatedContent)
                .tags(updatedTags)
                .source(updatedSource)
                .build();

        EcoNews updatedNews = ecoNewsClient.updateEcoNews(testEcoNewsId, updateRequest, null);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(updatedNews.getId(), testEcoNews.getId());
        softAssert.assertEquals(updatedNews.getTitle(), updatedTitle);
        softAssert.assertEquals(updatedNews.getContent(), updatedContent);
        softAssert.assertEquals(updatedNews.getTagsEn(), updatedTags);

        softAssert.assertAll();
    }

    @Test(groups = "requiresNews")
    public void testDeleteEcoNews() {
        Long testNewsId = testEcoNews.getId();
        ecoNewsClient.setToken(testUser.getAccessToken());

        Response deleteResponse = ecoNewsClient.deleteEcoNews(testNewsId);
        Response getResponse = ecoNewsClient.getEcoNewsByIdRawResponse(testNewsId.intValue(), LANG_EN);

        deleteResponse.then().statusCode(200);
        getResponse.then().statusCode(404);
        ecoNewsClient.deleteEcoNews(testNewsId).then().statusCode(404);

        testEcoNews = null;
    }

    @AfterMethod
    public void tearDownClass() {
        if (testEcoNews != null) {
            Long ecoNewsId = testEcoNews.getId();
            Response getResponse = ecoNewsClient.getEcoNewsByIdRawResponse(ecoNewsId.intValue(), LANG_EN);
            if (getResponse.getStatusCode() == 200) {
                ecoNewsClient.deleteEcoNews(ecoNewsId);
            }
        }
        if (ecoNewsClient.getToken() != null) {
            ecoNewsClient.setToken(null);
        }
    }

}
