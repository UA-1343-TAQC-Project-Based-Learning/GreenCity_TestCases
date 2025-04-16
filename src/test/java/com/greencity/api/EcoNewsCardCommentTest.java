package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.clients.EcoNewsCommentClient;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCard;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCardPage;
import com.greencity.api.models.EcoNewsCommentCard.ResponseEcoNewsCardComment;
import com.greencity.api.testRunners.ApiTestRunner;
import com.greencity.utils.TestValueProvider;
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
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void getEcoNewsCardTest(){
        Response response  =  ecoNewsClient.findEcoNewsId();
        response.then().statusCode(200);
        result = response.body().as(ResponseEcoNewsCardPage.class);
        ecoNewsCard = result.getPage().get(0);
        ecoNewsCardId = ecoNewsCard.getId();
    }

    @Test
    public void createCommentTest(){
        getEcoNewsCardTest();
        ecoNewsCommentClient = new EcoNewsCommentClient(testValueProvider.getBaseAPIUrl(),ecoNewsCardId);

        Response response = ecoNewsCommentClient.createComment();
        response.then().statusCode(200);
        response.then().log().all();
        String text = response.body().as(ResponseEcoNewsCardComment.class).getComment();
        Assert.assertEquals(text,"New comment");
    }

}
