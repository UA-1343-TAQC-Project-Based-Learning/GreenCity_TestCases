package com.greencity.ui.component.homePage;

import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.econewspage.EcoNewsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;


public class EventsHomeComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//h2[@class='section-caption']")
    private WebElement eventsHeader;
    @Getter
    @FindBy(xpath = ".//a[@aria-label='link to eco-news page']")
    private WebElement eventsLink;
    @Getter
    @FindBy(xpath = ".//img[@alt='arrow']")
    private WebElement eventsArrowLink;

    public EventsHomeComponent(WebDriver driver, WebElement eventsHomeRoot) {
        super(driver, eventsHomeRoot);
    }

    public String getEventsHeaderText() {
        return eventsHeader.getText();
    }

    public String getEventsLinkText() {
        return eventsLink.getText();
    }

    @Step("Click on 'Read all news' link")
    public EcoNewsPage clickEventsLink() {
        scrollToElement(eventsLink);
        waitUntilElementClickable(eventsLink);
        eventsLink.click();
        return new EcoNewsPage(driver);
    }

    @Step("Click on 'Arrow' link")
    public EcoNewsPage clickEventsArrowLink() {
        scrollToElement(eventsArrowLink);
        waitUntilElementClickable(eventsArrowLink);
        eventsArrowLink.click();
        return new EcoNewsPage(driver);
    }
}