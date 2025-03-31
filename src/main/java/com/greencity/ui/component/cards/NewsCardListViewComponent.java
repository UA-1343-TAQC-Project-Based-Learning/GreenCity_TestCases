package com.greencity.ui.component.cards;


import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.econewspage.NewsCardPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class NewsCardListViewComponent extends BaseComponent {
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

    private static final String CSS_PROPERTY_BACKGROUND = "background";

    private static final String CSS_PROPERTY_DISPLAY = "display";
    private static final String TAG_ATTRIBUTE_SRC = "src";

    private static final String BACKGROUND_URL = "/assets/img/places/bookmark-set.svg";

    public NewsCardListViewComponent(WebDriver driver, WebElement rootElement) {
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

    public NewsCardListViewComponent favouriteButtonClick() {
        waitUntilElementClickable(favouriteButton);
        favouriteButton.click();
        return this;
    }

    public String getImageSrc(){
        return image.getAttribute(TAG_ATTRIBUTE_SRC);
    }

    public boolean isFavouriteButtonSelected(){
        return favouriteButton.getCssValue(CSS_PROPERTY_BACKGROUND).contains(BACKGROUND_URL);
    }

    public boolean isImageDisplay(){
        return !image.getCssValue(CSS_PROPERTY_DISPLAY).equals("none");
    }

    public String getMoreButtonText() {
        return moreButton.getText();
    }

    public NewsCardPage moreButtonClick() {
        waitUntilElementClickable(moreButton);
        moreButton.click();
        return new NewsCardPage(driver);
    }

    public NewsCardPage titleLabelClick() {
        waitUntilElementClickable(titleLabel);
        titleLabel.click();
        return new NewsCardPage(driver);
    }
}
