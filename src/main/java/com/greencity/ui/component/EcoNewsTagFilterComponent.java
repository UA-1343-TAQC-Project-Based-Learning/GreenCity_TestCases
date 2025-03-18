package com.greencity.ui.component;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EcoNewsTagFilterComponent extends BaseComponent {


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
    @FindBy(xpath = ".//button//span[contains(text(),'Ініціативи')or contains(text(),'Initiatives')]")
    private WebElement initiativesTagButton;

    @Getter
    @FindBy(xpath = ".//button//span[contains(text(),'Реклама') or contains(text(),'Ads')]")
    private WebElement adsTagButton;

    public EcoNewsTagFilterComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }


    public String getNewsTagText() {
        return newsTagButton.getText();
    }

    public EcoNewsTagFilterComponent clickNewsTag() {
        waitUntilElementClickable(newsTagButton);
        newsTagButton.click();
        return this;
    }

    public String getEventsTagText() {
        return eventsTagButton.getText();
    }

    public EcoNewsTagFilterComponent clickEventsTag() {
        waitUntilElementClickable(eventsTagButton);
        eventsTagButton.click();
        return this;
    }

    public String getEducationTagText() {
        return educationTagButton.getText();
    }

    public EcoNewsTagFilterComponent clickEducationTag() {
        waitUntilElementClickable(educationTagButton);
        educationTagButton.click();
        return this;
    }

    public String getInitiativesTagText() {
        return initiativesTagButton.getText();
    }

    public EcoNewsTagFilterComponent clickInitiativesTag() {
        waitUntilElementClickable(initiativesTagButton);
        initiativesTagButton.click();
        return this;
    }

    public String getAdsTagText() {
        return adsTagButton.getText();
    }

    public EcoNewsTagFilterComponent clickAdsTag() {
        waitUntilElementClickable(adsTagButton);
        adsTagButton.click();
        return this;
    }


}
