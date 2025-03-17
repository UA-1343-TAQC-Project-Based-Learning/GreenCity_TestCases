package com.greencity.ui.component;

import com.greencity.ui.Base;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsFilterComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = "//span[contains(@class,'filter')]")
    private WebElement filterSpan;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]//span[contains(@class, 'text')])[1]")   // "//button//span[contains(text(),'Новини')]"
    private WebElement newsFilterButton;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]//span[contains(@class, 'text')])[2]")
    private WebElement eventsFilterButton;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]//span[contains(@class, 'text')])[3]")
    private WebElement educationFilterButton;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]//span[contains(@class, 'text')])[4]")
    private WebElement initiativesFilterButton;

    @Getter
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]//span[contains(@class, 'text')])[5]")
    private WebElement adsFilterButton;

    @FindBy(xpath = "//app-remaining-count//h2")
    private WebElement newsCountText;



    public EcoNewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getFilterSpanText() {
        waitUntilElementVisible(filterSpan);
        return filterSpan.getText();
    }

    public String getNewsFilterText() {
        waitUntilElementVisible(newsFilterButton);
        return newsFilterButton.getText();
    }

    public void clickNewsFilter() {
        waitUntilElementClickable(newsFilterButton);
        newsFilterButton.click();
    }

    public String getEventsFilterText() {
        waitUntilElementVisible(eventsFilterButton);
        return eventsFilterButton.getText();
    }

    public void clickEventsFilter() {
        waitUntilElementClickable(eventsFilterButton);
        eventsFilterButton.click();
    }

    public String getEducationFilterText() {
        waitUntilElementVisible(educationFilterButton);
        return educationFilterButton.getText();
    }

    public void clickEducationFilter() {
        waitUntilElementClickable(educationFilterButton);
        educationFilterButton.click();
    }

    public String getInitiativesFilterText() {
        waitUntilElementVisible(initiativesFilterButton);
        return initiativesFilterButton.getText();
    }

    public void clickInitiativesFilter() {
        waitUntilElementClickable(initiativesFilterButton);
        initiativesFilterButton.click();
    }

    public String getAdsFilterText() {
        waitUntilElementVisible(adsFilterButton);
        return adsFilterButton.getText();
    }

    public void clickAdsFilter() {
        waitUntilElementClickable(adsFilterButton);
        adsFilterButton.click();
    }

    public String getNewsCountText() {
        waitUntilElementVisible(newsCountText);
        return newsCountText.getText();
    }



}
