package com.greencity.api;

import com.greencity.api.clients.EcoNewsClientAuth;
import com.greencity.api.clients.EcoNewsCommentClient;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCard;
import com.greencity.api.models.EcoNewsCard.ResponseEcoNewsCardPage;
import com.greencity.api.models.EcoNewsCommentCard.ResponseEcoNewsCardComment;
import com.greencity.api.testRunners.ApiTestRunner;
import com.greencity.utils.TestValueProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;

import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class EcoNewsCardCommentTest extends ApiTestRunner {
    private EcoNewsClientAuth ecoNewsClientAuth;
    private EcoNewsCommentClient ecoNewsCommentClient;
    private ResponseEcoNewsCard ecoNewsCard;
    private ResponseEcoNewsCardPage result;
    private int ecoNewsCardId;
    private int commentId;

    @BeforeMethod
    public void setUp() {
        testValueProvider = new TestValueProvider();
    }

    @Description("Get Eco News Card Id")
    @Epic("Create News")
    @Issue("153")
    @Owner("Maria Markovych")
    @Test
    public void getEcoNewsCardIdTest() {
        String title = "title";
        ecoNewsClientAuth = new EcoNewsClientAuth(testValueProvider.getBaseAPIUrl(), testValueProvider.getUserPassword(), testValueProvider.getUserEmail(), title);

        Response response = ecoNewsClientAuth.getEcoNewsId();
        response.then().statusCode(200);
        result = response.body().as(ResponseEcoNewsCardPage.class);
        ecoNewsCard = result.getPage().get(0);
        ecoNewsCardId = ecoNewsCard.getId();
    }

    @Description("Create Eco News Comment")
    @Epic("Eco News Comment")
    @Issue("153")
    @Owner("Maria Markovych")
    @Test
    public void createEcoNewsCommentTest() {
        getEcoNewsCardIdTest();
        ecoNewsCommentClient = new EcoNewsCommentClient(testValueProvider.getBaseAPIUrl(), ContentType.MULTIPART, testValueProvider.getUserPassword(), testValueProvider.getUserEmail(), ecoNewsCardId);

        String comment = "New comment";

        Response response = ecoNewsCommentClient.createComment(comment);
        response.then().statusCode(201);
        response.then().log().all();
        String text = response.body().as(ResponseEcoNewsCardComment.class).getText();
        commentId = response.body().as(ResponseEcoNewsCardComment.class).getId();

        Assert.assertEquals(text, comment);
    }

    @Description("Get Eco News Comment By Id")
    @Epic("Eco News Comment")
    @Issue("153")
    @Owner("Maria Markovych")
    @Test
    public void getEcoNewsCommentByIdTest() {
        createEcoNewsCommentTest();
        ecoNewsCommentClient = new EcoNewsCommentClient(testValueProvider.getBaseAPIUrl(), testValueProvider.getUserPassword(), testValueProvider.getUserEmail());

        Response response = ecoNewsCommentClient.getComment(commentId);
        response.then().log().all();
        response.then().statusCode(200)
                .body("text", equalTo("New comment"))
                .body("likes", equalTo(0))
                .body("dislikes", equalTo(0))
                .body("replies", equalTo(0))
                .body("author.name", equalTo("testuser"));
    }

    @Description("Delete Eco News Comment")
    @Epic("Eco News Comment")
    @Issue("153")
    @Owner("Maria Markovych")
    @Test
    public void deleteEcoNewsCommentByIdTest() {
        createEcoNewsCommentTest();
        ecoNewsCommentClient = new EcoNewsCommentClient(testValueProvider.getBaseAPIUrl(), testValueProvider.getUserPassword(), testValueProvider.getUserEmail());
        Response response = ecoNewsCommentClient.deleteComment(commentId);
        response.then().statusCode(200);

        response = ecoNewsCommentClient.getComment(commentId);
        response.then().statusCode(200)
                .body("status", equalTo("DELETED"));

    }
}
