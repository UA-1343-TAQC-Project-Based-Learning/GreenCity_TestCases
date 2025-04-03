package com.greencity.ui.elements;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ToolbarElement extends BaseElement {

    @Getter
    @FindBy(xpath = ".//span[@class='search-img']")
    private WebElement searchButton;

    @Getter
    @FindBy(xpath = ".//span[@class='bookmark-img']")
    private WebElement bookmarkButton;

    @Getter
    @FindBy(xpath = ".//img[@alt='my-event']")
    private WebElement calendarButton;

    public ToolbarElement(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    @Step("Click search button")
    public void clickSearchButton() {
        waitUntilElementClickable(searchButton);
        searchButton.click();
    }

    @Step("Click bookmark button")
    public void clickBookmarkButton() {
        waitUntilElementClickable(bookmarkButton);
        bookmarkButton.click();
    }

    @Step("Click calendar button")
    public void clickCalendarButton() {
        waitUntilElementClickable(calendarButton);
        calendarButton.click();
    }


}
