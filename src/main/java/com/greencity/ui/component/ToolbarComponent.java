package com.greencity.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToolbarComponent {
    private WebDriver driver;

    @Getter
    @FindBy(xpath = "//span[@class='search-img']")
    private WebElement searchButton;

    @Getter
    @FindBy(xpath = "//span[@class='bookmark-img']")
    private WebElement bookmarkButton;

    @Getter
    @FindBy(xpath = "//img[@alt='my-event']")
    private WebElement calendarButton;

    public ToolbarComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickBookmarkButton() {
        bookmarkButton.click();
    }

    public void clickCalendarButton() {
        calendarButton.click();
    }


}
