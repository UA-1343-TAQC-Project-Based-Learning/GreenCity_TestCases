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
    @FindBy(xpath = ".//button//span[contains(text(),'Ініціативи') or contains(text(),'Initiatives')]")
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

    public EcoNewsFilterComponent clickNewsTag() {
        waitUntilElementClickable(newsTagButton);
        newsTagButton.click();
        return this;
    }

    public String getEventsTagText() {
        return eventsTagButton.getText();
    }

    public EcoNewsFilterComponent clickEventsTag() {
        waitUntilElementClickable(eventsTagButton);
        eventsTagButton.click();
        return this;
    }

    public String getEducationTagText() {
        return educationTagButton.getText();
    }

    public EcoNewsFilterComponent clickEducationTag() {
        waitUntilElementClickable(educationTagButton);
        educationTagButton.click();
        return this;
    }

    public String getInitiativesTagText() {
        return initiativesTagButton.getText();
    }

    public EcoNewsFilterComponent clickInitiativesTag() {
        waitUntilElementClickable(initiativesTagButton);
        initiativesTagButton.click();
        return this;
    }

    public String getAdsTagText() {
        return adsTagButton.getText();
    }

    public EcoNewsFilterComponent clickAdsTag() {
        waitUntilElementClickable(adsTagButton);
        adsTagButton.click();
        return this;
    }

    public String getNewsCountText() {
        waitUntilElementVisible(newsCount);
        return newsCount.getText();
    }



}
