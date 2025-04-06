package com.greencity.ui.component.homePage;


import com.greencity.ui.component.BaseComponent;
import com.greencity.ui.page.UbsPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import lombok.Getter;


public class StatItemHomeComponent extends BaseComponent {

    @Getter
    @FindBy(xpath = ".//h3")
    private WebElement header;

    @Getter
    @FindBy(xpath = ".//p")
    private WebElement paragraph;

    @Getter
    @FindBy(xpath = ".//span")
    private WebElement span;

    @Getter
    @FindBy(xpath = ".//button[@class='primary-global-button btn']")
    private WebElement button;

    @Getter
    @FindBy(xpath = ".//img[@alt='location-image']")
    private WebElement icon;

    @Getter
    @FindBy(xpath = ".//a[@class='tertiary-global-button btn-link']")
    private WebElement link;

    @Getter
    @FindBy(xpath = ".//img[@alt='stat-icon']")
    private WebElement statIcon;


    public StatItemHomeComponent(WebDriver driver, WebElement statsHomeRoot) {
        super(driver, statsHomeRoot);
    }


    public String getHeaderText() {
        return header.getText();
    }

    public String getParagraphText() {
        return paragraph.getText();
    }

    public String getSpanText() {
        return span.getText();
    }

    public String getButtonText() {
        return button.getText();
    }

    @Step("Click on 'Start forming a habit!' button")
    public UbsPage clickButton() {
        scrollToElement(button);
        waitUntilElementClickable(button);
        button.click();
        return new UbsPage(driver);
    }

    public boolean isIconDisplayed() {
        return icon.isDisplayed();
    }

    @Step("Click on 'Start forming a habit!' button")
    public String getLinkText() {
        return link.getText();
    }

    @Step("Click on link navigates to UbsPage")
    public UbsPage clickLink() {
        scrollToElement(link);
        waitUntilElementClickable(link);
        link.click();
        return new UbsPage(driver);
    }

    public boolean isStatIconDisplayed() {
        return statIcon.isDisplayed();
    }
}

