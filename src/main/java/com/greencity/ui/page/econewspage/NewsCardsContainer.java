package com.greencity.ui.page.econewspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class NewsCardsContainer {

    protected WebDriver driver;
    protected WebElement rootElement;

    public NewsCardsContainer(WebDriver driver, WebElement rootElement) {
        this.driver = driver;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
        this.rootElement = rootElement;

    }
}
