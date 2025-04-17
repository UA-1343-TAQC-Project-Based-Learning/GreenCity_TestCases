package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.clients.EcoNewsCommentClient;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCard;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCardPage;
import com.greencity.api.models.EcoNewsCommentCard.ResponseEcoNewsCardComment;
import com.greencity.api.testRunners.ApiTestRunner;
import com.greencity.utils.TestValueProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EcoNewsCardCommentTest extends ApiTestRunner{
    private EcoNewsClient ecoNewsClient;
    private EcoNewsCommentClient ecoNewsCommentClient;
    private ResponseEcoNewsCard ecoNewsCard;
    private ResponseEcoNewsCardPage result;
    public int ecoNewsCardId;

    @BeforeMethod
    public void setUp() {
        testValueProvider = new TestValueProvider();
    }

    @Test
    public void getEcoNewsCardIdTest(){
        String title = "title";
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIUrl(), testValueProvider.getUserPassword(), testValueProvider.getUserEmail(), title);

        Response response  =  ecoNewsClient.getEcoNewsId();
        response.then().statusCode(200);
        result = response.body().as(ResponseEcoNewsCardPage.class);
        ecoNewsCard = result.getPage().get(0);
        ecoNewsCardId = ecoNewsCard.getId();
    }

    @Test
    public void getEcoNewsCommentTest(){
        getEcoNewsCardIdTest();
        ecoNewsCommentClient = new EcoNewsCommentClient(testValueProvider.getBaseAPIUrl(), ContentType.MULTIPART, testValueProvider.getUserPassword(), testValueProvider.getUserEmail(), ecoNewsCardId);

        String comment = "New comment";

        Response response = ecoNewsCommentClient.createComment(comment);
        response.then().statusCode(201);
        response.then().log().all();
        String text = response.body().as(ResponseEcoNewsCardComment.class).getText();
        Assert.assertEquals(text,comment);
    }
}
