package com.greencity.ui.component.cards;

import com.greencity.ui.component.BaseComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public abstract class NewsCardComponent extends BaseComponent {

    protected WebElement image;

    protected WebElement favouriteButton;

    protected List<WebElement> filtersTag;

    protected WebElement titleLabel;

    protected WebElement contentLabel;

    protected WebElement dateOfCreationLabel;

    protected WebElement usernameLabel;

    private static final String CSS_PROPERTY_BACKGROUND = "background";

    private static final String CSS_PROPERTY_DISPLAY = "display";
    private static final String TAG_ATTRIBUTE_SRC = "src";

    private static final String BACKGROUND_URL = "/assets/img/places/bookmark-set.svg";


    public NewsCardComponent(WebDriver driver, WebElement rootElement) {
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

    public NewsCardComponent favouriteButtonClick() {
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
}
