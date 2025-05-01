package com.greencity.jdbc;

import com.greencity.utils.TestValueProvider;
import com.greencity.utils.jdbc.entity.CommentEntity;
import com.greencity.utils.jdbc.services.CommentService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CommentsJDBCTest {
    private static TestValueProvider testValueProvider;
    private static CommentService commentService;
    private static String userEmail;

    @BeforeMethod
    public void setUp() {
        testValueProvider = new TestValueProvider();
        commentService = new CommentService(
                testValueProvider.getJDBCGreenCityURL(),
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword()
        );
        userEmail = testValueProvider.getUserEmail();
    }

    @Description("Create new Comment by User")
    @Epic("Create Comments")
    @Issue("171")
    @Owner("Maria Markovych")
    @Test
    public void createAComment(){
        SoftAssert assertion = new SoftAssert();
           int rows = commentService.createCommentByUser(userEmail);
        List<CommentEntity> comments = commentService
                .getCommentByUser(userEmail);
        assertion.assertEquals(1,rows);
        assertion.assertFalse(comments.isEmpty());
        assertion.assertAll();
    }


    @Description("Delete all comments By User")
    @Epic("Create Comments")
    @Issue("171")
    @Owner("Maria Markovych")
    @Test
    public void deleteAllCommentsByUser() {
         commentService.deleteAllCommentsByUser(userEmail);
        List<CommentEntity> comments = commentService
                .getCommentByUser(userEmail);
        Assert.assertTrue(comments.isEmpty());
    }
}

