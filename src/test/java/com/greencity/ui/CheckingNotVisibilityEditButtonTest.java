package com.greencity.ui;

import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import com.greencity.ui.page.econewspage.NewsCardPage;
import com.greencity.ui.testrunners.TestRunnerForNotUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckingNotVisibilityEditButtonTest extends TestRunnerForNotUser {
    protected static WebDriverWait wait;
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void checkNotVisibilityEditButtonTest(){
        EcoNewsPage ecoNewsPage = homePage.gotoEcoNewsPage();
/*
       String title = ecoNewsPage.getFirstCardTitle();

        NewsCardPage newsCardPage = ecoNewsPage
                .goToNewsCardPage(title);
*/
       // WebElement editButton = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("Edit")));

       // softAssert.assertTrue(editButtonInvisible, "The Edit button should not be visible for a non-logged-in user.");
        softAssert.assertAll();

    }
}
