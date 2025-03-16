package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.event.WindowAdapter;
import java.time.Duration;

public class BaseTest  extends BaseTestRunner {
    @Test
    public void firstTest() {
        WebElement image = homePage.getHeader().getLogo();
        Assert.assertTrue(image.isDisplayed(), "The element is not displayed.");
    }

}
