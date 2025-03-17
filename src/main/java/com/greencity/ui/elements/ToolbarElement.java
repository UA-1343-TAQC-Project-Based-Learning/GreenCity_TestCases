package com.greencity.ui.elements;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public void clickSearchButton() {
        waitUntilElementClickable(searchButton);
        searchButton.click();
    }

    public void clickBookmarkButton() {
        waitUntilElementClickable(bookmarkButton);
        bookmarkButton.click();
    }

    public void clickCalendarButton() {
        waitUntilElementClickable(calendarButton);
        calendarButton.click();
    }


}
