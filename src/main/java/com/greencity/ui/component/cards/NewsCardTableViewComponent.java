package com.greencity.ui.component.cards;


import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.newscardpage.NewsCardPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.ArrayList;
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

    private static final String CSS_PROPERTY_BACKGROUND = "background";

    private static final String CSS_PROPERTY_DISPLAY = "display";

    private static final String TAG_ATTRIBUTE_SRC = "src";

    private static final String BACKGROUND_URL = "/assets/img/places/bookmark-set.svg";

    public NewsCardTableViewComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getTitleLabelText() {
        return titleLabel.getText();
    }

    public List<String> getFiltersTagText() {
        List<String> filtersTagNames = new ArrayList<>();
        for (WebElement current : filtersTag) {
            filtersTagNames.add(current.getText());
        }
        return filtersTagNames;
    }

    public String getContentLabelText() {
        return contentLabel.getText();
    }

    public String getDateOfCreationLabelText() {
        return dateOfCreationLabel.getText();
    }

    public String getUsernameLabelText() {
        return usernameLabel.getText();
    }

    public NewsCardTableViewComponent favouriteButtonClick() {
        waitUntilElementClickable(favouriteButton);
        favouriteButton.click();
        return this;
    }

    public String getImageSrc() {
        return image.getAttribute(TAG_ATTRIBUTE_SRC);
    }

    public boolean isFavouriteButtonSelected() {
        return favouriteButton.getCssValue(CSS_PROPERTY_BACKGROUND).contains(BACKGROUND_URL);
    }

    public boolean isImageDisplay() {
        return !image.getCssValue(CSS_PROPERTY_DISPLAY).equals("none");
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

