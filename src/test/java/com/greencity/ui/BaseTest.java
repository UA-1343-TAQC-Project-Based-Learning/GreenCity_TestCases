package com.greencity.ui;

import com.greencity.ui.page.econewspage.CreateEditNewsPage;
import com.greencity.ui.page.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class BaseTest extends BaseTestRunner {

    @Test
    public void firstTest() {
        WebElement image = homePage.getHeader().getLogo();
        Assert.assertTrue(image.isDisplayed(), "The element is not displayed.");
    }


    @Test
    public void testHomePageElements() {
//        Assert.assertEquals(homePage.getHeaderTitle(), "A new way to cultivate useful habits",
//                "Header title is incorrect!");

        Assert.assertFalse(homePage.getHeaderParagraphText().isEmpty(), "Header paragraph is missing!");

//        Assert.assertTrue(homePage.headerButton.isDisplayed(),
//                "Start habit button is not displayed!");

        Assert.assertTrue(homePage.isGuyImageDisplayed(), "Guy image is not displayed!");
    }

}
