package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.clients.OwnSecurityClient;
import com.greencity.api.models.*;
import com.greencity.api.models.DateUtils;
import com.greencity.api.models.EcoNews;
import com.greencity.api.testRunners.ApiTestRunner;
import com.greencity.utils.TestValueProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import jdk.jfr.Description;
import lombok.SneakyThrows;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;

public class EcoNewsClientTest extends ApiTestRunner {

    private static final String LANG_EN = "en";
    private static final String LANG_UA = "ua";
    private static final String EXPECTED_TITLE = "TestTitle";
    private static final String EXPECTED_CONTENT = "Content Content Content";
    private static final String EXPECTED_SOURCE = "http://test_domain.com/greenCity/news/create-news";
    private static final List<String> EXPECTED_TAGS_EN = Arrays.asList("News", "Events");
    private static final List<String> EXPECTED_TAGS_UA = Arrays.asList("Новини", "Події");
    private static final String TEST_IMAGE_PATH = "images/GreenCity5mb.png";

    private String filePath;
    private SignInResponse testUser;
    private EcoNewsClient ecoNewsClient;
    private EcoNews testEcoNews;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIUrl());
        this.filePath = testValueProvider.getFilePathByResources(TEST_IMAGE_PATH);
        String email = testValueProvider.getUserEmail();
        String password = testValueProvider.getUserPassword();
        testUser = new OwnSecurityClient(testValueProvider.getBaseAPIUrlUser())
                .signIn(email, password);
    }

    @BeforeMethod(groups = "requiresNews", dependsOnMethods = "setUp")
    public void createTestNews() {
        AddEcoNewsDtoRequest ecoNewsDtoRequest = AddEcoNewsDtoRequest.builder()
                .title(EXPECTED_TITLE)
                .text(EXPECTED_CONTENT)
                .tags(EXPECTED_TAGS_EN)
                .source(EXPECTED_SOURCE)
                .build();

        ecoNewsClient.setToken(testUser.getAccessToken());
        testEcoNews = ecoNewsClient.addEcoNews(ecoNewsDtoRequest, filePath);

        ecoNewsClient.setToken(null);
    }

    @Description("Verify that API successfully returns Eco News card by ID with status code 200")
    @Epic("Eco News API")
    @Feature("Get Eco News Card By Id")
    @Issue("152")
    @Owner("Nataliia Hrusha")
    @Test(dataProvider = "languageProvider", groups = "requiresNews")
    public void getEcoNewsById(String language, List<String> expectedTags) {
        EcoNews ecoNewsResponse = ecoNewsClient.getEcoNewsByIdRawResponse(testEcoNews.getId().intValue(), language)
                .then()
                .statusCode(200)
                .extract()
                .as(EcoNews.class);

        List<String> actualTags = language.equals(LANG_UA) ? ecoNewsResponse.getTagsUa() : ecoNewsResponse.getTags();
        verifyEcoNews(ecoNewsResponse, actualTags, expectedTags);
    }

    @DataProvider
    public Object[][] languageProvider() {
        return new Object[][]{
                {LANG_EN, EXPECTED_TAGS_EN},
                {LANG_UA, EXPECTED_TAGS_UA}
        };
    }

    @SneakyThrows
    private void verifyEcoNews(EcoNews ecoNews, List<String> actualTags, List<String> expectedTags) {
        String actualCreationDateRaw = ecoNews.getCreationDate().toString();
        String actualDateStr = DateUtils.convertRawDateToIso(actualCreationDateRaw);

        String expectedCreationDateRaw = testEcoNews.getCreationDate().toString();
        String expectedDateStr = DateUtils.convertRawDateToIso(expectedCreationDateRaw);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateStr, expectedDateStr,
                "Date mismatch. Actual: " + actualDateStr + ", Expected: " + expectedDateStr);
        softAssert.assertEquals(ecoNews.getId(), testEcoNews.getId(), "News ID mismatch");
        softAssert.assertEquals(ecoNews.getTitle(), testEcoNews.getTitle(), "Title mismatch");
        softAssert.assertEquals(ecoNews.getContent(), testEcoNews.getContent(), "Content mismatch");
        softAssert.assertEquals(actualTags, expectedTags,
                String.format("Tags mismatch, expected: '%s', actual: '%s'", expectedTags, actualTags)
        );
        softAssert.assertEquals(ecoNews.getAuthor().getId(), testEcoNews.getAuthor().getId(), "Author ID mismatch");
        softAssert.assertEquals(ecoNews.getAuthor().getName(), testEcoNews.getAuthor().getName(), "Author name mismatch");
        softAssert.assertEquals(ecoNews.getLikes(), testEcoNews.getLikes(), "Likes count mismatch");
        softAssert.assertEquals(ecoNews.getCountComments().intValue(), testEcoNews.getCountComments().intValue(), "Comments count mismatch");

        softAssert.assertAll();
    }


    @DataProvider
    public Object[][] validEcoNewsProvider() {
        if (testValueProvider == null) {
            testValueProvider = new TestValueProvider();
        }
        String imagePath = testValueProvider.getFilePathByResources(TEST_IMAGE_PATH);
        AddEcoNewsDtoRequest.AddEcoNewsDtoRequestBuilder ecoNewsDtoPreRequest = AddEcoNewsDtoRequest.builder()
                .title(EXPECTED_TITLE)
                .text(EXPECTED_CONTENT)
                .tags(EXPECTED_TAGS_EN);

        return new Object[][]{
                {ecoNewsDtoPreRequest.build(), ""},
                {ecoNewsDtoPreRequest.source(EXPECTED_SOURCE).build(), ""},
                {ecoNewsDtoPreRequest.source(EXPECTED_SOURCE).build(), imagePath},
        };
    }

    @Test(dataProvider = "validEcoNewsProvider")
    public void addEcoNewsSuccessfully(AddEcoNewsDtoRequest request, String imagePath) {
        ecoNewsClient.setToken(testUser.getAccessToken());

        EcoNews response = ecoNewsClient.addEcoNews(request, imagePath);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(response.getId(), "News ID should not be null");
        softAssert.assertEquals(response.getTitle(), EXPECTED_TITLE, "Title mismatch");
        softAssert.assertEquals(response.getContent(), EXPECTED_CONTENT, "Content mismatch");
        softAssert.assertEquals(response.getTagsEn(), EXPECTED_TAGS_EN,
                String.format("Tags mismatch, expected: '%s', actual: '%s'", EXPECTED_TAGS_EN, response.getTagsEn())
        );
        if (imagePath != null && !imagePath.isEmpty()) {
            softAssert.assertTrue(!response.getImagePath().isEmpty(), "Image path should be null when no file provided");
        } else {
            softAssert.assertNull(response.getImagePath(), "Image path should be null when no file provided");
        }
        softAssert.assertEquals(response.getLikes(), Integer.valueOf(0), "Initial likes should be 0");
        softAssert.assertEquals(response.getCountComments(), Integer.valueOf(0), "Initial comments should be 0");

        softAssert.assertAll();

        ecoNewsClient.deleteEcoNews(response.getId());
    }

    @Test(groups = "requiresNews")
    public void testUpdateEcoNews() {
        Long testEcoNewsId = testEcoNews.getId();
        String updatedTitle = EXPECTED_TITLE + " updated";
        String updatedContent = EXPECTED_CONTENT + "Updated content";
        List<String> updatedTags = new ArrayList<>(EXPECTED_TAGS_EN);
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
        softAssert.assertEquals(updatedNews.getId(), testEcoNews.getId(), "EcoNews id should not be updated");
        softAssert.assertEquals(updatedNews.getTitle(), updatedTitle, "EcoNews Title was not be updated");
        softAssert.assertEquals(updatedNews.getContent(), updatedContent, "EcoNews Content was not be updated");
        softAssert.assertEquals(updatedNews.getTagsEn(), updatedTags, "EcoNews Tags were not be updated");

        softAssert.assertAll();
    }

    @Test(groups = "requiresNews")
    public void testDeleteEcoNews() {
        Long testNewsId = testEcoNews.getId();
        ecoNewsClient.setToken(testUser.getAccessToken());

        Response deleteResponseFirst = ecoNewsClient.deleteEcoNews(testNewsId);
        Response getResponse = ecoNewsClient.getEcoNewsByIdRawResponse(testNewsId.intValue(), LANG_EN);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(deleteResponseFirst.getStatusCode(), 200, "Failed to delete EcoNews");
        softAssert.assertEquals(getResponse.getStatusCode(), 404,
                "The EcoNews is still present after is was deleted");

        Response deleteResponseSecond = ecoNewsClient.deleteEcoNews(testNewsId);
        softAssert.assertEquals(deleteResponseSecond.getStatusCode(), 404,
                "Second deleting of EcoNews must return 404 response.");

        testEcoNews = null;
    }

    @AfterMethod
    public void tearDownClass() {
        if (testEcoNews != null) {
            Long ecoNewsId = testEcoNews.getId();
            ecoNewsClient.setToken(testUser.getAccessToken());
            Response getResponse = ecoNewsClient.getEcoNewsByIdRawResponse(ecoNewsId.intValue(), LANG_EN);
            if (getResponse.getStatusCode() == 200) {
                ecoNewsClient.deleteEcoNews(ecoNewsId).then().statusCode(200).log();
            }
        }
        if (ecoNewsClient.getToken() != null) {
            ecoNewsClient.setToken(null);
        }
    }

}
