package com.greencity.ui.component.cards;

import com.greencity.ui.component.BaseComponent;

import com.greencity.ui.page.newscardpage.NewsCardPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NewsCardTableViewComponent extends BaseComponent {

    @FindBy(xpath = ".//img[@class='list-image-content']")
    private WebElement image;

    @FindBy(xpath = ".//div[@class='news-flags favourite-button']")
    private WebElement favouriteButton;

    @FindBy(xpath = ".//div[contains(@class,'ul-eco-buttons')]/span")
    private List<WebElement> filtersTag;

    @FindBy(xpath = ".//div[@class='title-list word-wrap']/h3")
    private WebElement titleLabel;

    @FindBy(xpath = ".//div[contains(@class, 'list-text')]//span")
    private WebElement contentLabel;

    @FindBy(xpath = ".//p[@class='user-data-text-date']//span")
    private WebElement dateOfCreationLabel;

    @FindBy(xpath = ".//p[@class='user-data-text-date user']//span")
    private WebElement usernameLabel;

    @FindBy(xpath = ".//img[@alt='comments']/following-sibling::span")
    private WebElement commentsLabel;

    @FindBy(xpath = ".//img[@alt='likes']//following-sibling::span")
    private WebElement likesLabel;

    public NewsCardTableViewComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        check();
    }

    private void check() {
        if (image == null || favouriteButton == null || filtersTag == null || titleLabel == null
                || contentLabel == null || dateOfCreationLabel == null || usernameLabel == null
                || commentsLabel == null || likesLabel == null) {
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

    public String getCommentsLabelText() {
        waitUntilElementVisible(commentsLabel);
        return commentsLabel.getText();
    }

    public String getLikesLabelText() {
        waitUntilElementVisible(likesLabel);
        return likesLabel.getText();
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

