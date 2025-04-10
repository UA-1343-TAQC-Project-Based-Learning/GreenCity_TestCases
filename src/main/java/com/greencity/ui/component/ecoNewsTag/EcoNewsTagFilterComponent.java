package com.greencity.ui.component.ecoNewsTag;

import com.greencity.ui.component.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.EnumMap;
import java.util.Map;

public class EcoNewsTagFilterComponent extends BaseComponent {

    private final Map<TagButton, WebElement> tagButtons = new EnumMap<>(TagButton.class);

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
        initTagButtonsMapper();

    }

    private void initTagButtonsMapper() {
        tagButtons.put(TagButton.NEWS, newsTagButton);
        tagButtons.put(TagButton.EVENTS, eventsTagButton);
        tagButtons.put(TagButton.EDUCATION, educationTagButton);
        tagButtons.put(TagButton.INITIATIVES, initiativesTagButton);
        tagButtons.put(TagButton.ADS, adsTagButton);
    }

    public String getTagButtonText(TagButton button) {
        return  tagButtons.get(button).getText();
    }

    @Step("Click the 'Tag' button")
    public EcoNewsTagFilterComponent clickTagButton(TagButton button) {
        WebElement buttonElement = tagButtons.get(button);
        buttonElement.click();
        return this;
    }

    public boolean isTagButtonSelected(TagButton button) {
        WebElement buttonElement = tagButtons.get(button);
        WebElement aElement = buttonElement.findElement(By.xpath("./.."));
        String aClass = aElement.getAttribute("class");
        return aClass.contains("global-tag-clicked");
    }

    public String getTagButtonColor(TagButton button) {
        WebElement buttonElement = tagButtons.get(button);
        WebElement aElement = buttonElement.findElement(By.xpath("./.."));
        return aElement.getCssValue("background-color");
    }

}


