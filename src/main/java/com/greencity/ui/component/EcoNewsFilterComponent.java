package com.greencity.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsFilterComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//span[contains(@class,'filter')]")
    private WebElement filterSpan;

    @Getter
    @FindBy(xpath = ".//button//span[contains(text(),'Новини') or contains(text(),'News')]")
    private WebElement newsTagButton;

    @Getter
    @FindBy(xpath = ".//button//span[contains(text(),'Події') or contains(text(),'Events')]")
    private WebElement eventsTagButton;

    @Getter
    @FindBy(xpath = ".//button//span[contains(text(),'Освіта') or contains(text(),'Education')]")
    private WebElement educationTagButton;

    @Getter
    @FindBy(xpath = "(.//button//span[contains(text(),'Ініціативи')or contains(text(),'Initiatives')]")
    private WebElement initiativesTagButton;

    @Getter
    @FindBy(xpath = ".//button//span[contains(text(),'Реклама') or contains(text(),'Ads')]")
    private WebElement adsTagButton;

    @FindBy(xpath = ".//app-remaining-count//h2")
    private WebElement newsCount;



    public EcoNewsFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getFilterSpanText() {
        return filterSpan.getText();
    }

    public String getNewsTagText() {
        return newsTagButton.getText();
    }

    public void clickNewsTag() {
        waitUntilElementClickable(newsTagButton);
        newsTagButton.click();
    }

    public String getEventsTagText() {
        return eventsTagButton.getText();
    }

    public void clickEventsTag() {
        waitUntilElementClickable(eventsTagButton);
        eventsTagButton.click();
    }

    public String getEducationTagText() {
        return educationTagButton.getText();
    }

    public void clickEducationTag() {
        waitUntilElementClickable(educationTagButton);
        educationTagButton.click();
    }

    public String getInitiativesTagText() {
        return initiativesTagButton.getText();
    }

    public void clickInitiativesTag() {
        waitUntilElementClickable(initiativesTagButton);
        initiativesTagButton.click();
    }

    public String getAdsTagText() {
        return adsTagButton.getText();
    }

    public void clickAdsTag() {
        waitUntilElementClickable(adsTagButton);
        adsTagButton.click();
    }

    public String getNewsCountText() {
        waitUntilElementVisible(newsCount);
        return newsCount.getText();
    }



}
