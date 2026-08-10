package com.greencity.ui;

import com.greencity.ui.testrunners.BaseTestRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest extends BaseTestRunner {

    @Test
    void logoShouldBeDisplayedOnHomePage() {
        WebElement logo = homePage.getHeader().getLogo();
        assertTrue(logo.isDisplayed(), "The element is not displayed.");
    }
}
