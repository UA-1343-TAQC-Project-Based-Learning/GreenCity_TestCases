package com.greencity.ui.component.cards;


import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.greencity.ui.page.newscardpage.NewsCardPage;


import java.util.List;


public class NewsCardListViewComponent extends NewsCardComponent {
    @Getter
    @FindBy(xpath = ".//img[@class='eco-news_list-img']")
    private WebElement image;

    @Getter
    @FindBy(xpath = ".//div[@class='news-flags favourite-button']")
    private WebElement favouriteButton;

    @Getter
    @FindBy(xpath = ".//div[@class='filter-tag']//span")
    private List<WebElement> filtersTag;

    @Getter
    @FindBy(xpath = ".//div[@class='eco-news_list-content-title']/h3")
    private WebElement titleLabel;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'eco-news_list-content-text ')]//span")
    private WebElement contentLabel;

    @Getter
    @FindBy(xpath = ".//p[@class='eco-news_data-text-date']//span")
    private WebElement dateOfCreationLabel;

    @Getter
    @FindBy(xpath = ".//p[@class='eco-news_person']")
    private WebElement usernameLabel;

    @Getter
    @FindBy(xpath = ".//button[@class = 'secondary-global-button m-btn']")
    private WebElement moreButton;

    public NewsCardListViewComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getMoreButtonText() {
        return moreButton.getText();
    }

    public void moreButtonClick() {
        waitUntilElementClickable(moreButton);
        moreButton.click();
    }

    public NewsCardPage openNewsCardPage() {
        moreButtonClick();
        return new NewsCardPage(driver);
    }
}
