package com.greencity.ui.component.cards;



import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.newscardpage.NewsCardPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;


public class NewsCardTableViewComponent extends BaseComponent {
    @Getter
    @FindBy(xpath = ".//img[@class='list-image-content']")
    private WebElement image;

    @Getter
    @FindBy(xpath = ".//div[@class='news-flags favourite-button']")
    private WebElement favouriteButton;

    @Getter
    @FindBy(xpath = ".//div[contains(@class,'ul-eco-buttons')]/span")
    private List<WebElement> filtersTag;

    @Getter
    @FindBy(xpath = ".//div[@class='title-list word-wrap']/h3")
    private WebElement titleLabel;

    @Getter
    @FindBy(xpath = ".//div[contains(@class, 'list-text')]//span")
    private WebElement contentLabel;

    @Getter
    @FindBy(xpath = ".//p[@class='user-data-text-date']//span")
    private WebElement dateOfCreationLabel;

    @Getter
    @FindBy(xpath = ".//p[@class='user-data-text-date user']//span")
    private WebElement usernameLabel;

    @Getter
    @FindBy(xpath = ".//img[@alt='comments']/following-sibling::span")
    private WebElement commentsLabel;

    @Getter
    @FindBy(xpath = ".//img[@alt='likes']//following-sibling::span")
    private WebElement likesLabel;

    public NewsCardTableViewComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    private void titleLabelClick() {
        waitUntilElementClickable(titleLabel);
        titleLabel.click();
    }

    public String getCommentsLabelText() {
        return commentsLabel.getText();
    }

    public String getLikesLabelText() {
        return likesLabel.getText();
    }

    public NewsCardPage openNewsCardPage() {
        titleLabelClick();
        return new NewsCardPage(driver);
    }
}

