package com.greencity.ui.page.econewspage;

import com.greencity.ui.page.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreviewNewsPage extends BasePage {

    @Getter
    @FindBy(xpath = "//div[@class='news-title word-wrap']")
    private WebElement newsTitle;

    @Getter
    @FindBy(xpath = "//div[@class='news-text']")
    private WebElement newsTextContent;

    @Getter
    @FindBy(xpath = "//div[@class='news-info-date']")
    private WebElement newsInfoDate;

    @Getter
    @FindBy(xpath = "//div[@class='news-info-author']")
    private WebElement newsAuthor;

    @Getter
    @FindBy(xpath = "//div[@class='button-text']")
    private WebElement backToEditingButton;

    public PreviewNewsPage(WebDriver driver) {
        super(driver);
    }



}
