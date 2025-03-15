package com.greencity.ui.component.cards;

import com.greencity.ui.component.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NewsCardListViewComponent extends BaseComponent {

    @FindBy(xpath = ".//img[@class='eco-news_list-img']")
    private WebElement image;

    @FindBy(xpath = ".//div[@class='news-flags favourite-button']")
    private WebElement favouriteButton;

    @FindBy(xpath = ".//div[@class='filter-tag']//span")
    private List<WebElement> filtersTag;

    @FindBy(xpath = ".//div[@class='eco-news_list-content-title']/h3")
    private WebElement titleLabel;

    @FindBy(xpath = ".//div[contains(@class, 'eco-news_list-content-text ')]//span")
    private WebElement contentLabel;

    @FindBy(xpath = ".//p[@class='eco-news_data-text-date']//span")
    private WebElement dateOfCreationLabel;

    @FindBy(xpath = ".//p[@class='eco-news_person']")
    private WebElement usernameLabel;

    @FindBy(xpath = ".//button[@class = 'secondary-global-button m-btn']")
    private WebElement moreButton;

    public NewsCardListViewComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        check();
    }

    private void check() {
        if (image == null && favouriteButton == null && filtersTag == null && titleLabel == null
                && contentLabel == null && dateOfCreationLabel == null && usernameLabel == null
                && moreButton == null) {
            throw new IllegalStateException("Some elements were not initialized properly");
        }
    }

    public String getTitleLabelText() {
        waitUntilElementVisible(titleLabel);
        return titleLabel.getText();
    }

    public void titleLabelClick() {
        waitUntilElementClickable(titleLabel);
        titleLabel.click();
    }

    public List<String> getFiltersTagText() {
        waitUntilAllElementsVisible(filtersTag);
        List<String> filtersTagNames = new ArrayList<>();
        for (WebElement current : filtersTag) {
            filtersTagNames.add(current.getText());
        }
        return filtersTagNames;
    }

    public String getContentLabelText() {
        waitUntilElementVisible(contentLabel);
        return contentLabel.getText();
    }

    public String getDateOfCreationLabelText() {
        waitUntilElementVisible(dateOfCreationLabel);
        return dateOfCreationLabel.getText();
    }

    public String getUsernameLabelText() {
        waitUntilElementVisible(usernameLabel);
        return usernameLabel.getText();
    }

    public String getMoreButtonText() {
        waitUntilElementVisible(moreButton);
        return moreButton.getText();
    }

    public void moreButtonClick() {
        waitUntilElementClickable(moreButton);
        moreButton.click();
    }

    public void favouriteButtonClick() {
        waitUntilElementClickable(favouriteButton);
        favouriteButton.click();
    }

    public NewsCardPage openNewsCardPage() {
        titleLabelClick();
        return new NewsCardPage(driver);
    }
}
